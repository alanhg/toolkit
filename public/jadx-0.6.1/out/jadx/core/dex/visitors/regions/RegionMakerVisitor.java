package jadx.core.dex.visitors.regions;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.EdgeInsnAttr;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnContainer;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.Region;
import jadx.core.dex.regions.SwitchRegion;
import jadx.core.dex.regions.SynchronizedRegion;
import jadx.core.dex.regions.loops.LoopRegion;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.InstructionRemover;
import jadx.core.utils.RegionUtils;
import jadx.core.utils.exceptions.JadxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionMakerVisitor extends AbstractVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(RegionMakerVisitor.class);
    private static final PostRegionVisitor POST_REGION_VISITOR = new PostRegionVisitor();

    private static final class PostRegionVisitor extends AbstractRegionVisitor {
        private PostRegionVisitor() {
        }

        public void leaveRegion(MethodNode mth, IRegion region) {
            if (region instanceof LoopRegion) {
                ((LoopRegion) region).mergePreCondition();
            } else if (region instanceof SwitchRegion) {
                RegionMakerVisitor.processSwitch(mth, (SwitchRegion) region);
            } else if (region instanceof Region) {
                RegionMakerVisitor.insertEdgeInsn((Region) region);
            }
        }
    }

    public void visit(MethodNode mth) throws JadxException {
        if (!mth.isNoCode()) {
            RegionMaker rm = new RegionMaker(mth);
            mth.setRegion(rm.makeRegion(mth.getEnterBlock(), new RegionStack(mth)));
            if (!mth.isNoExceptionHandlers()) {
                IRegion expOutBlock = rm.processTryCatchBlocks(mth);
                if (expOutBlock != null) {
                    mth.getRegion().add(expOutBlock);
                }
            }
            postProcessRegions(mth);
        }
    }

    private static void postProcessRegions(MethodNode mth) {
        ProcessTryCatchRegions.process(mth);
        DepthRegionTraversal.traverse(mth, POST_REGION_VISITOR);
        CleanRegions.process(mth);
        if (mth.getAccessFlags().isSynchronized()) {
            removeSynchronized(mth);
        }
    }

    private static void insertEdgeInsn(Region region) {
        List<IContainer> subBlocks = region.getSubBlocks();
        if (!subBlocks.isEmpty()) {
            IContainer last = (IContainer) subBlocks.get(subBlocks.size() - 1);
            List<EdgeInsnAttr> edgeInsnAttrs = last.getAll(AType.EDGE_INSN);
            if (!edgeInsnAttrs.isEmpty()) {
                EdgeInsnAttr insnAttr = (EdgeInsnAttr) edgeInsnAttrs.get(0);
                if (insnAttr.getStart().equals(last)) {
                    region.add(new InsnContainer(Collections.singletonList(insnAttr.getInsn())));
                }
            }
        }
    }

    private static void processSwitch(MethodNode mth, SwitchRegion sw) {
        for (IContainer c : sw.getBranches()) {
            if (c instanceof Region) {
                Set<IBlock> blocks = new HashSet();
                RegionUtils.getAllRegionBlocks(c, blocks);
                if (blocks.isEmpty()) {
                    addBreakToContainer((Region) c);
                } else {
                    for (IBlock block : blocks) {
                        if (block instanceof BlockNode) {
                            BlockNode bn = (BlockNode) block;
                            for (BlockNode s : bn.getCleanSuccessors()) {
                                if (!blocks.contains(s) && !bn.contains(AFlag.SKIP) && !s.contains(AFlag.FALL_THROUGH)) {
                                    addBreak(mth, c, bn);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addBreak(MethodNode mth, IContainer c, BlockNode bn) {
        IContainer blockContainer = RegionUtils.getBlockContainer(c, bn);
        if (blockContainer instanceof Region) {
            addBreakToContainer((Region) blockContainer);
        } else if (c instanceof Region) {
            addBreakToContainer((Region) c);
        } else {
            LOG.warn("Can't insert break, container: {}, block: {}, mth: {}", new Object[]{blockContainer, bn, mth});
        }
    }

    private static void addBreakToContainer(Region c) {
        if (!RegionUtils.hasExitEdge(c)) {
            List<InsnNode> insns = new ArrayList(1);
            insns.add(new InsnNode(InsnType.BREAK, 0));
            c.add(new InsnContainer(insns));
        }
    }

    private static void removeSynchronized(MethodNode mth) {
        Region startRegion = mth.getRegion();
        List<IContainer> subBlocks = startRegion.getSubBlocks();
        if (!subBlocks.isEmpty() && (subBlocks.get(0) instanceof SynchronizedRegion)) {
            SynchronizedRegion synchRegion = (SynchronizedRegion) subBlocks.get(0);
            InsnNode synchInsn = synchRegion.getEnterInsn();
            if (synchInsn.getArg(0).isThis()) {
                startRegion.getSubBlocks().set(0, synchRegion.getRegion());
                InstructionRemover.remove(mth, synchInsn);
                for (InsnNode exit : synchRegion.getExitInsns()) {
                    InstructionRemover.remove(mth, exit);
                }
                CleanRegions.process(mth);
                return;
            }
            LOG.warn("In synchronized method {}, top region not synchronized by 'this' {}", mth, synchInsn);
        }
    }
}

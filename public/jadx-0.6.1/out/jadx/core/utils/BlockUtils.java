package jadx.core.utils;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.IgnoreEdgeAttr;
import jadx.core.dex.attributes.nodes.PhiListAttr;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.PhiInsn;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.InsnWrapArg;
import jadx.core.dex.instructions.mods.TernaryInsn;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.conditions.IfCondition;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class BlockUtils {
    private BlockUtils() {
    }

    public static BlockNode getBlockByOffset(int offset, Iterable<BlockNode> casesBlocks) {
        for (BlockNode block : casesBlocks) {
            if (block.getStartOffset() == offset) {
                return block;
            }
        }
        throw new JadxRuntimeException("Can't find block by offset: " + InsnUtils.formatOffset(offset) + " in list " + casesBlocks);
    }

    public static BlockNode selectOther(BlockNode node, List<BlockNode> blocks) {
        List<BlockNode> list = blocks;
        if (list.size() > 2) {
            list = cleanBlockList(list);
        }
        if (list.size() != 2) {
            throw new JadxRuntimeException("Incorrect nodes count for selectOther: " + node + " in " + list);
        }
        BlockNode first = (BlockNode) list.get(0);
        return first != node ? first : (BlockNode) list.get(1);
    }

    public static BlockNode selectOtherSafe(BlockNode node, List<BlockNode> blocks) {
        int size = blocks.size();
        BlockNode first;
        if (size == 1) {
            first = (BlockNode) blocks.get(0);
            return first != node ? first : null;
        } else if (size != 2) {
            return null;
        } else {
            first = (BlockNode) blocks.get(0);
            if (first == node) {
                return (BlockNode) blocks.get(1);
            }
            return first;
        }
    }

    public static boolean isBlockMustBeCleared(BlockNode b) {
        if (b.contains(AType.EXC_HANDLER) || b.contains(AFlag.SKIP)) {
            return true;
        }
        if (b.contains(AFlag.SYNTHETIC)) {
            List<BlockNode> s = b.getSuccessors();
            if (s.size() == 1 && ((BlockNode) s.get(0)).contains(AType.EXC_HANDLER)) {
                return true;
            }
        }
        return false;
    }

    private static List<BlockNode> cleanBlockList(List<BlockNode> list) {
        List<BlockNode> ret = new ArrayList(list.size());
        for (BlockNode block : list) {
            if (!isBlockMustBeCleared(block)) {
                ret.add(block);
            }
        }
        return ret;
    }

    public static void cleanBitSet(MethodNode mth, BitSet bs) {
        int i = bs.nextSetBit(0);
        while (i >= 0) {
            if (isBlockMustBeCleared((BlockNode) mth.getBasicBlocks().get(i))) {
                bs.clear(i);
            }
            i = bs.nextSetBit(i + 1);
        }
    }

    public static List<BlockNode> filterPredecessors(BlockNode block) {
        List<BlockNode> predecessors = block.getPredecessors();
        List<BlockNode> list = new ArrayList(predecessors.size());
        for (BlockNode pred : predecessors) {
            IgnoreEdgeAttr edgeAttr = (IgnoreEdgeAttr) pred.get(AType.IGNORE_EDGE);
            if (edgeAttr == null) {
                list.add(pred);
            } else if (!edgeAttr.contains(block)) {
                list.add(pred);
            }
        }
        return list;
    }

    public static boolean isBackEdge(BlockNode from, BlockNode to) {
        if (to == null || from.getCleanSuccessors().contains(to)) {
            return false;
        }
        return from.getSuccessors().contains(to);
    }

    public static boolean blockContains(BlockNode block, InsnNode insn) {
        for (InsnNode bi : block.getInstructions()) {
            if (bi == insn) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkLastInsnType(IBlock block, InsnType expectedType) {
        InsnNode insn = getLastInsn(block);
        return insn != null && insn.getType() == expectedType;
    }

    @Nullable
    public static InsnNode getLastInsn(IBlock block) {
        List<InsnNode> insns = block.getInstructions();
        if (insns.isEmpty()) {
            return null;
        }
        return (InsnNode) insns.get(insns.size() - 1);
    }

    public static BlockNode getBlockByInsn(MethodNode mth, InsnNode insn) {
        if (insn instanceof PhiInsn) {
            return searchBlockWithPhi(mth, (PhiInsn) insn);
        }
        if (insn.contains(AFlag.WRAPPED)) {
            return getBlockByWrappedInsn(mth, insn);
        }
        for (BlockNode bn : mth.getBasicBlocks()) {
            if (blockContains(bn, insn)) {
                return bn;
            }
        }
        return null;
    }

    public static BlockNode searchBlockWithPhi(MethodNode mth, PhiInsn insn) {
        for (BlockNode block : mth.getBasicBlocks()) {
            PhiListAttr phiListAttr = (PhiListAttr) block.get(AType.PHI_LIST);
            if (phiListAttr != null) {
                for (PhiInsn phiInsn : phiListAttr.getList()) {
                    if (phiInsn == insn) {
                        return block;
                    }
                }
                continue;
            }
        }
        return null;
    }

    private static BlockNode getBlockByWrappedInsn(MethodNode mth, InsnNode insn) {
        for (BlockNode bn : mth.getBasicBlocks()) {
            for (InsnNode bi : bn.getInstructions()) {
                if (bi == insn) {
                    return bn;
                }
                if (foundWrappedInsn(bi, insn) != null) {
                    return bn;
                }
            }
        }
        return null;
    }

    public static InsnNode searchInsnParent(MethodNode mth, InsnNode insn) {
        InsnArg insnArg = searchWrappedInsnParent(mth, insn);
        if (insnArg == null) {
            return null;
        }
        return insnArg.getParentInsn();
    }

    public static InsnArg searchWrappedInsnParent(MethodNode mth, InsnNode insn) {
        if (!insn.contains(AFlag.WRAPPED)) {
            return null;
        }
        for (BlockNode bn : mth.getBasicBlocks()) {
            for (InsnNode bi : bn.getInstructions()) {
                InsnArg res = foundWrappedInsn(bi, insn);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    private static InsnArg foundWrappedInsn(InsnNode container, InsnNode insn) {
        for (InsnArg arg : container.getArguments()) {
            if (arg.isInsnWrap()) {
                InsnNode wrapInsn = ((InsnWrapArg) arg).getWrapInsn();
                if (wrapInsn == insn) {
                    return arg;
                }
                InsnArg res = foundWrappedInsn(wrapInsn, insn);
                if (res != null) {
                    return res;
                }
            }
        }
        if (container instanceof TernaryInsn) {
            return foundWrappedInsnInCondition(((TernaryInsn) container).getCondition(), insn);
        }
        return null;
    }

    private static InsnArg foundWrappedInsnInCondition(IfCondition cond, InsnNode insn) {
        if (cond.isCompare()) {
            return foundWrappedInsn(cond.getCompare().getInsn(), insn);
        }
        for (IfCondition nestedCond : cond.getArgs()) {
            InsnArg res = foundWrappedInsnInCondition(nestedCond, insn);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    public static BitSet blocksToBitSet(MethodNode mth, List<BlockNode> blocks) {
        BitSet bs = new BitSet(mth.getBasicBlocks().size());
        for (BlockNode block : blocks) {
            bs.set(block.getId());
        }
        return bs;
    }

    public static List<BlockNode> bitSetToBlocks(MethodNode mth, BitSet bs) {
        int size = bs.cardinality();
        if (size == 0) {
            return Collections.emptyList();
        }
        List<BlockNode> blocks = new ArrayList(size);
        int i = bs.nextSetBit(0);
        while (i >= 0) {
            blocks.add((BlockNode) mth.getBasicBlocks().get(i));
            i = bs.nextSetBit(i + 1);
        }
        return blocks;
    }

    public static BlockNode getNextBlock(BlockNode block) {
        List<BlockNode> s = block.getCleanSuccessors();
        return s.isEmpty() ? null : (BlockNode) s.get(0);
    }

    public static BlockNode getNextBlockToPath(BlockNode block, BlockNode pathEnd) {
        List<BlockNode> successors = block.getCleanSuccessors();
        if (successors.contains(pathEnd)) {
            return pathEnd;
        }
        Set<BlockNode> path = getAllPathsBlocks(block, pathEnd);
        for (BlockNode s : successors) {
            if (path.contains(s)) {
                return s;
            }
        }
        return null;
    }

    public static Set<BlockNode> getAllPathsBlocks(BlockNode start, BlockNode end) {
        Set<BlockNode> set = new HashSet();
        set.add(start);
        if (start != end) {
            addPredecessors(set, end, start);
        }
        return set;
    }

    private static void addPredecessors(Set<BlockNode> set, BlockNode from, BlockNode until) {
        set.add(from);
        for (BlockNode pred : from.getPredecessors()) {
            if (!(pred == until || set.contains(pred))) {
                addPredecessors(set, pred, until);
            }
        }
    }

    private static boolean traverseSuccessorsUntil(BlockNode from, BlockNode until, BitSet visited, boolean clean) {
        for (BlockNode s : clean ? from.getCleanSuccessors() : from.getSuccessors()) {
            if (s == until) {
                return true;
            }
            int id = s.getId();
            if (!visited.get(id)) {
                visited.set(id);
                if (until.isDominator(s)) {
                    return true;
                }
                if (traverseSuccessorsUntil(s, until, visited, clean)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPathExists(BlockNode start, BlockNode end) {
        if (start == end || end.isDominator(start) || start.getCleanSuccessors().contains(end)) {
            return true;
        }
        if (start.getPredecessors().contains(end)) {
            return false;
        }
        return traverseSuccessorsUntil(start, end, new BitSet(), true);
    }

    public static boolean isAnyPathExists(BlockNode start, BlockNode end) {
        if (start == end || end.isDominator(start) || start.getSuccessors().contains(end)) {
            return true;
        }
        if (start.getPredecessors().contains(end)) {
            return false;
        }
        return traverseSuccessorsUntil(start, end, new BitSet(), false);
    }

    public static BlockNode getTopBlock(Collection<BlockNode> blocks) {
        if (blocks.size() == 1) {
            return (BlockNode) blocks.iterator().next();
        }
        for (BlockNode from : blocks) {
            boolean top = true;
            for (BlockNode to : blocks) {
                if (from != to && !isAnyPathExists(from, to)) {
                    top = false;
                    continue;
                    break;
                }
            }
            if (top) {
                return from;
            }
        }
        return null;
    }

    public static boolean isOnlyOnePathExists(BlockNode start, BlockNode end) {
        if (start == end) {
            return true;
        }
        if (!end.isDominator(start)) {
            return false;
        }
        while (start.getCleanSuccessors().size() == 1) {
            start = (BlockNode) start.getCleanSuccessors().get(0);
            if (start == end) {
                return true;
            }
        }
        return false;
    }

    public static BlockNode traverseWhileDominates(BlockNode dom, BlockNode start) {
        for (BlockNode node : start.getCleanSuccessors()) {
            if (!node.isDominator(dom)) {
                return node;
            }
            BlockNode out = traverseWhileDominates(dom, node);
            if (out != null) {
                return out;
            }
        }
        return null;
    }

    public static BlockNode getPathCross(MethodNode mth, BlockNode b1, BlockNode b2) {
        if (b1 == null || b2 == null) {
            return null;
        }
        BitSet b = new BitSet();
        b.or(b1.getDomFrontier());
        b.and(b2.getDomFrontier());
        b.clear(b1.getId());
        b.clear(b2.getId());
        if (b.cardinality() == 1) {
            BlockNode end = (BlockNode) mth.getBasicBlocks().get(b.nextSetBit(0));
            if (isPathExists(b1, end) && isPathExists(b2, end)) {
                return end;
            }
        }
        if (isPathExists(b1, b2)) {
            return b2;
        }
        if (isPathExists(b2, b1)) {
            return b1;
        }
        return null;
    }

    public static List<BlockNode> collectBlocksDominatedBy(BlockNode dominator, BlockNode start) {
        List<BlockNode> result = new ArrayList();
        collectWhileDominates(dominator, start, result);
        return result;
    }

    private static void collectWhileDominates(BlockNode dominator, BlockNode child, List<BlockNode> result) {
        for (BlockNode node : child.getCleanSuccessors()) {
            if (node.isDominator(dominator)) {
                result.add(node);
                collectWhileDominates(dominator, node, result);
            }
        }
    }

    public static List<BlockNode> buildSimplePath(BlockNode block) {
        List<BlockNode> list = new LinkedList();
        while (block != null && block.getCleanSuccessors().size() < 2 && block.getPredecessors().size() == 1) {
            list.add(block);
            block = getNextBlock(block);
        }
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    public static void skipPredSyntheticPaths(BlockNode block) {
        for (BlockNode pred : block.getPredecessors()) {
            if (pred.contains(AFlag.SYNTHETIC) && !pred.contains(AType.SPLITTER_BLOCK) && pred.getInstructions().isEmpty()) {
                pred.add(AFlag.SKIP);
                skipPredSyntheticPaths(pred);
            }
        }
    }

    public static boolean isEmptySimplePath(BlockNode start, BlockNode end) {
        if (start == end && start.getInstructions().isEmpty()) {
            return true;
        }
        if (!start.getInstructions().isEmpty() || start.getCleanSuccessors().size() != 1) {
            return false;
        }
        BlockNode block = getNextBlock(start);
        while (block != null && block != end && block.getCleanSuccessors().size() < 2 && block.getPredecessors().size() == 1 && block.getInstructions().isEmpty()) {
            block = getNextBlock(block);
        }
        if (block != end) {
            return false;
        }
        return true;
    }

    public static BlockNode skipSyntheticSuccessor(BlockNode block) {
        if (block.isSynthetic() && block.getSuccessors().size() == 1) {
            return (BlockNode) block.getSuccessors().get(0);
        }
        return block;
    }

    public static BlockNode skipSyntheticPredecessor(BlockNode block) {
        if (block.isSynthetic() && block.getPredecessors().size() == 1) {
            return (BlockNode) block.getPredecessors().get(0);
        }
        return block;
    }

    public static boolean isAllBlocksEmpty(List<BlockNode> blocks) {
        for (BlockNode block : blocks) {
            if (!block.getInstructions().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

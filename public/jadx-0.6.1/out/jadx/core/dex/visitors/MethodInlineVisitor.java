package jadx.core.dex.visitors;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.nodes.MethodInlineAttr;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.JadxException;
import java.util.List;

public class MethodInlineVisitor extends AbstractVisitor {
    public void visit(MethodNode mth) throws JadxException {
        AccessInfo accessFlags = mth.getAccessFlags();
        if (accessFlags.isSynthetic() && accessFlags.isStatic() && mth.getBasicBlocks().size() == 2) {
            BlockNode returnBlock = (BlockNode) mth.getBasicBlocks().get(1);
            if (returnBlock.contains(AFlag.RETURN) || returnBlock.getInstructions().isEmpty()) {
                inlineMth(mth, (BlockNode) mth.getBasicBlocks().get(0), returnBlock);
            }
        }
    }

    private static void inlineMth(MethodNode mth, BlockNode firstBlock, BlockNode returnBlock) {
        List<InsnNode> insnList = firstBlock.getInstructions();
        if (insnList.isEmpty()) {
            addInlineAttr(mth, InsnNode.wrapArg(((InsnNode) ((BlockNode) mth.getBasicBlocks().get(1)).getInstructions().get(0)).getArg(0)));
        } else if (insnList.size() == 1) {
            addInlineAttr(mth, (InsnNode) insnList.get(0));
        } else if (insnList.size() == 2 && returnBlock.getInstructions().size() == 1 && !mth.getReturnType().equals(ArgType.VOID)) {
            InsnNode get = (InsnNode) insnList.get(0);
            InsnNode put = (InsnNode) insnList.get(1);
            InsnArg retArg = ((InsnNode) returnBlock.getInstructions().get(0)).getArg(0);
            if (get.getType() == InsnType.IGET && put.getType() == InsnType.IPUT && retArg.isRegister() && get.getResult().equalRegisterAndType((RegisterArg) retArg)) {
                RegisterArg retReg = (RegisterArg) retArg;
                retReg.getSVar().removeUse(retReg);
                CodeShrinker.shrinkMethod(mth);
                insnList = firstBlock.getInstructions();
                if (insnList.size() == 1) {
                    addInlineAttr(mth, (InsnNode) insnList.get(0));
                }
            }
        }
    }

    private static void addInlineAttr(MethodNode mth, InsnNode insn) {
        mth.addAttr(new MethodInlineAttr(insn));
        mth.add(AFlag.DONT_GENERATE);
    }
}
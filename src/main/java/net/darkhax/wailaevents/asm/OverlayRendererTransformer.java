package net.darkhax.wailaevents.asm;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import net.darkhax.wailaevents.util.Constants;
import net.minecraft.launchwrapper.IClassTransformer;

public class OverlayRendererTransformer implements IClassTransformer {
    
    @Override
    public byte[] transform (String name, String transformedName, byte[] classBytes) {
        
        if (transformedName.equals("mcp.mobius.waila.overlay.OverlayRenderer"))
            return transformOverlayRenderer(classBytes);
            
        return classBytes;
    }
    
    /**
     * Transforms the OverlayRenderer class within Waila. This will add two new hooks, one
     * before the rendering of the HUD, and one after the rendering of the HUD. These hooks are
     * then made available to the addon system.
     * 
     * @param bytes: The raw bytes for the OverlayRenderer class.
     * @return byte[]: The modified version of the OverlayRenderer as bytes.
     */
    private byte[] transformOverlayRenderer (byte[] bytes) {
        
        Constants.LOG.info("OverlayRenderer found. Preparing to inject new events, this will only hurt a little bit.");
        ClassNode classNode = ASMHelper.createClassFromByteArray(bytes);
        MethodNode methodNode = ASMHelper.getMethodFromClass(classNode, "renderOverlay", "()V");
        
        AbstractInsnNode startNode = null;
        AbstractInsnNode endNode = null;
        
        for (AbstractInsnNode insn : methodNode.instructions.toArray()) {
            
            if (startNode == null)
                startNode = insn;
                
            if (insn.getOpcode() == Opcodes.RETURN)
                endNode = insn.getPrevious();
        }
        
        if (startNode != null)
            methodNode.instructions.insert(startNode, getPreInstructions());
            
        if (endNode != null)
            methodNode.instructions.insertBefore(endNode, getPostInstructions());
            
        Constants.LOG.info("Transformation complete, new events have been injected.");
        return ASMHelper.createByteArrayFromClass(classNode, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }
    
    /**
     * Creates a list of instructions for the WailaRenderEvent.Pre event. This event is added
     * at the beginning of the renderOverlay method. Before the event is fired, a check is done
     * to make sure that it's safe to render a tooltip. This event can be canceled, if that
     * happens the method will exit before the HUD can be rendered.
     * 
     * @return InsnList: A new list of instruction nodes that will be injected into the
     *         renderOverlay method.
     */
    private static InsnList getPreInstructions () {
        
        Constants.LOG.info("Injecting WailaRenderEvent.Pre");
        LabelNode start = new LabelNode();
        LabelNode end = new LabelNode();
        InsnList instructions = new InsnList();
        
        instructions.add(start);
        instructions.add(new MethodInsnNode(INVOKESTATIC, "net/darkhax/wailaevents/util/Utilities", "isSafeForTooltip", "()Z", false));
        instructions.add(new JumpInsnNode(IFEQ, end));
        instructions.add(new FieldInsnNode(GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lcpw/mods/fml/common/eventhandler/EventBus;"));
        instructions.add(new TypeInsnNode(NEW, "net/darkhax/wailaevents/event/WailaRenderEvent$Pre"));
        instructions.add(new InsnNode(DUP));
        instructions.add(new MethodInsnNode(INVOKESPECIAL, "net/darkhax/wailaevents/event/WailaRenderEvent$Pre", "<init>", "()V", false));
        instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "cpw/mods/fml/common/eventhandler/EventBus", "post", "(Lcpw/mods/fml/common/eventhandler/Event;)Z", false));
        instructions.add(new JumpInsnNode(IFEQ, end));
        instructions.add(new InsnNode(RETURN));
        instructions.add(end);
        
        return instructions;
    }
    
    /**
     * Creates a list of instructions for the WailaRenderEvent.Post event. This event is added
     * before the last return instruction.
     * 
     * @return InsnList: A new list of instruction nodes that will be injected into the
     *         rederOverlay method.
     */
    private static InsnList getPostInstructions () {
        
        Constants.LOG.info("Injecting WailaRenderEvent.Post");
        
        LabelNode start = new LabelNode();
        InsnList i = new InsnList();
        
        i.add(start);
        i.add(new FieldInsnNode(GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lcpw/mods/fml/common/eventhandler/EventBus;"));
        i.add(new TypeInsnNode(NEW, "net/darkhax/wailaevents/event/WailaRenderEvent$Post"));
        i.add(new InsnNode(DUP));
        i.add(new MethodInsnNode(INVOKESPECIAL, "net/darkhax/wailaevents/event/WailaRenderEvent$Post", "<init>", "()V", false));
        i.add(new MethodInsnNode(INVOKEVIRTUAL, "cpw/mods/fml/common/eventhandler/EventBus", "post", "(Lcpw/mods/fml/common/eventhandler/Event;)Z", false));
        i.add(new InsnNode(POP));
        
        return i;
    }
}
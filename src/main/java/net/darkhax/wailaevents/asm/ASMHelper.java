package net.darkhax.wailaevents.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public final class ASMHelper {
    
    /**
     * Converts a ClassNode into a byte array which can then be returned by your transformer.
     * 
     * @param classNode: An instance of the ClassNode you wish to convert into a byte array.
     * @param flags: The flags to use when converting the ClassNode. These are generally
     *            COMPUTE_FRAMES and COMPUTE_MAXS.
     * @return byte[]: A byte array representation of the ClassNode.
     */
    public static byte[] createByteArrayFromClass (ClassNode classNode, int flags) {
    
        ClassWriter classWriter = new ClassWriter(flags);
        classNode.accept(classWriter);
        return classWriter.toByteArray();
    }
    
    /**
     * Converts a byte array into a ClassNode which can then easily be worked with and
     * manipulated.
     * 
     * @param classBytes: The byte array representation of the class.
     * @return ClassNode: A ClassNode representation of the class, built from the byte array.
     */
    public static ClassNode createClassFromByteArray (byte[] classBytes) {
    
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(classBytes);
        classReader.accept(classNode, ClassReader.EXPAND_FRAMES);
        return classNode;
    }
    
    /**
     * Checks if a ClassNode has an instance of the target method. This does not take
     * descriptors into account.
     * 
     * @param classNode: The instance of ClassNode to look through.
     * @param methodName: The name of the method you are looking for.
     * @return boolean: True if the method is found, false if it is not.
     */
    public static boolean hasClassMethodName (ClassNode classNode, String methodName) {
    
        for (MethodNode method : classNode.methods)
            if (methodName.equals(method.name))
                return true;
        
        return false;
    }
    
    /**
     * Retrieves a MethodNode from a ClassNode, if one can not be found, an exception will be
     * thrown, and the game will stop.
     * 
     * @param classNode: An instance of the ClassNode to go looking through.
     * @param methodName: The name of the desired method.
     * @param descriptor: The descriptor for the method, used to find a specific version of the
     *            desired method.
     * @return MethodNode: A MethodNode which represents the desired method. If this method can
     *         not be found, a MethodNotFoundException will be thrown and the game will stop.
     */
    public static MethodNode getMethodFromClass (ClassNode classNode, String methodName, String descriptor) {
    
        for (MethodNode mnode : classNode.methods)
            if (methodName.equals(mnode.name) && descriptor.equals(mnode.desc))
                return mnode;
        
        throw new MethodNotFoundException(methodName, descriptor);
    }
    
    public static class MethodNotFoundException extends RuntimeException {
        
        /**
         * An exception which is thrown when a MethodNode is being looked for, but couldn't be
         * found.
         * 
         * @param methodName: The name of the method being looked for.
         * @param methodDesc: The descriptor for the method being looked for.
         */
        public MethodNotFoundException(String methodName, String methodDesc) {
        
            super("Attempt to find a method has failed. Method: " + methodName + " Descriptor: " + methodDesc);
        }
    }
}
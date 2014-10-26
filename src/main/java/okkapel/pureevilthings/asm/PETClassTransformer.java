package okkapel.pureevilthings.asm;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;



import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;


public class PETClassTransformer implements IClassTransformer {
	
	private static final String ENTITY_RENDERER_CLASS_OBFUSCATED = "blt";
	private static final String SETUP_CAMERA_TRANSF_OBFUSCATED = "a";
	
	private static final String ENTITY_RENDERER_CLASS = "net.minecraft.client.renderer.EntityRenderer";
	private static final String SETUP_CAMERA_TRANSF = "setupCameraTransform";
	
	@Override
	public byte[] transform(String cls, String arg1, byte[] bc) {
		if(cls.equals(ENTITY_RENDERER_CLASS)) {
			System.out.println("PET Patching stuff in EntityRenderer.setupCameraTransform...");
			return doTransformEntityRenderer(cls, bc, false);
		}
		
		if(cls.equals(ENTITY_RENDERER_CLASS_OBFUSCATED)) {
			System.out.println("PET Patching stuff in obfuscated EntityRenderer.setupCameraTransform...");
			return doTransformEntityRenderer(cls, bc, true);
		}
		
		return bc;
	}
	
	private byte[] doTransformEntityRenderer(String name, byte[] bytes, boolean obf) {
		String methodName = null;
		
		if(obf) {
			methodName = SETUP_CAMERA_TRANSF_OBFUSCATED;
		} else {
			methodName = SETUP_CAMERA_TRANSF;
		}
		
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(bytes);
		classReader.accept(classNode, 0);
		
		Iterator<MethodNode> methods = classNode.methods.iterator();
		while(methods.hasNext()) {
			MethodNode mn = methods.next();
			
			if(mn.name.equals(methodName) && mn.desc.equals("(FI)V")) {
				AbstractInsnNode currNode = null;
				AbstractInsnNode insertSpeedBeforeNode = null; // LDC 5.0
				AbstractInsnNode insertAmtMultBeforeNode = null; // FSTORE 6
				
				Iterator<AbstractInsnNode> iter = mn.instructions.iterator();
				boolean confPotionFound = false;
				boolean findInsertNode = false;
				
				int amtMultInjMatch = 0;
				boolean findInsertBeforeNode = false;
				
				while(iter.hasNext()) {
					currNode = iter.next();
					
					if(currNode.getOpcode() == Opcodes.GETSTATIC && ((FieldInsnNode)currNode).name.equals("confusion")) {
						confPotionFound = true;
					}
					
					if(findInsertBeforeNode) {
						if(amtMultInjMatch == 0 && currNode.getOpcode() == Opcodes.FLOAD) {
							amtMultInjMatch++;
						}
						else if(amtMultInjMatch == 1 && currNode.getOpcode() == Opcodes.LDC) {
							amtMultInjMatch++;
						}
						else if(amtMultInjMatch == 2 && currNode.getOpcode() == Opcodes.FMUL) {
							amtMultInjMatch++;
						}
						else if(amtMultInjMatch == 3 && currNode.getOpcode() == Opcodes.FSUB) {
							amtMultInjMatch++;
						}
						else if(amtMultInjMatch == 4 && currNode.getOpcode() == Opcodes.FSTORE) {
							insertAmtMultBeforeNode = currNode;
							break;
						}
						else {
							amtMultInjMatch=0;
						}
					}
					
					if(findInsertNode && currNode.getOpcode() == Opcodes.LDC) {
						insertSpeedBeforeNode = currNode;
						findInsertNode = false;
						findInsertBeforeNode = true;
					}
					
//					if(findInsertNode && currNode.getOpcode() == Opcodes.ISTORE && ((VarInsnNode)currNode).var == 5) {
//						insertSpeedAfterNode = currNode;
//						findInsertNode = false;
//						findInsertBeforeNode = true;
//					}
					
					if(confPotionFound && currNode.getOpcode() == Opcodes.INVOKEVIRTUAL && currNode.getType() == AbstractInsnNode.METHOD_INSN) {
						findInsertNode = true;
					}
					
					
				}
				
				if(insertSpeedBeforeNode != null) {
					InsnList toInject = new InsnList();
					toInject.add(new VarInsnNode(Opcodes.ILOAD, 5));
					toInject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "okkapel/pureevilthings/potioneffect/PotionPET", "getScreenWarpSpeed", "(B)B", false));
					toInject.add(new VarInsnNode(Opcodes.ISTORE, 5));
					mn.instructions.insertBefore(insertSpeedBeforeNode, toInject);
				}
				
				if(insertAmtMultBeforeNode != null) {
					InsnList toInject = new InsnList();
					toInject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "okkapel/pureevilthings/potioneffect/PotionPET", "getScreenWarpMultiplier", "()F", false));
					toInject.add(new InsnNode(Opcodes.FMUL));
					mn.instructions.insertBefore(insertAmtMultBeforeNode, toInject);
				}
				
				break;
			}
		}
		
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		classNode.accept(writer);
		return writer.toByteArray();
	}
	
}

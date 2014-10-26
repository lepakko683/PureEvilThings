package okkapel.pureevilthings.asm;

import java.util.Map;

import okkapel.pureevilthings.ref.Reference;
import okkapel.pureevilthings.PureEvilThings;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion(value = Reference.MC_VERSION)
@SideOnly(Side.CLIENT)
public class PETFMLLoadingPlugin implements IFMLLoadingPlugin {

	/*
	 * FMLCorePlugin: ... in jar MANIFEST file
	 * 
	 * */
	
	@Override
	public String[] getASMTransformerClass() {
		System.out.println("Get transformer class");
		return new String[]{"okkapel.pureevilthings.asm.PETClassTransformer"};
	}

	@Override
	public String getModContainerClass() {
		System.out.println("Get mod container class");
		return "okkapel.pureevilthings.asm.PETEdits";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}

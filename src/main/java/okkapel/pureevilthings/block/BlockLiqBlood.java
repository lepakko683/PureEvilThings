package okkapel.pureevilthings.block;

import okkapel.pureevilthings.PureEvilThings;
import okkapel.pureevilthings.ref.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockLiqBlood extends BlockLiquid {

	private IIcon still, flowing;
	private final String bName;
	
	protected BlockLiqBlood(Material mat, String bName) {
		super(mat);
		this.bName = bName;
		setCreativeTab(PureEvilThings.creativeTab);
//		setBlockTextureName(Reference.MODID + ":" + bName);
		setBlockName(bName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {
		still = ir.registerIcon(Reference.MODID + ":" + bName+"_still");
		flowing = ir.registerIcon(Reference.MODID + ":" + bName + "_flowing");
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side != 0 && side != 1 ? still : flowing;
    }

}

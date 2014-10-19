package okkapel.pureevilthings.block;

import okkapel.pureevilthings.PureEvilThings;
import okkapel.pureevilthings.ref.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockET extends Block {

	protected BlockET(Material mat, String bName) {
		super(mat);
		setBlockTextureName(Reference.MODID + ":" + bName);
		setBlockName(bName);
		setCreativeTab(PureEvilThings.creativeTab);
	}

}

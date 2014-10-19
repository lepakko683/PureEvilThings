package okkapel.pureevilthings.block;

import okkapel.pureevilthings.item.ModItems;
import okkapel.pureevilthings.ref.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value=Reference.MODID)
public class ModBlocks {
	public static Block oreOnyx = new BlockOreET("oreOnyx", ModItems.itemOnyx, 1, 2, 1, 0, 5).setHardness(3.0f).setResistance(5.0f);
	public static Block liqBlood = new BlockLiqBlood(Material.water, "liqBlood");
	
	public static void initBlocks() {
		GameRegistry.registerBlock(oreOnyx, "oreOnyx");
		GameRegistry.registerBlock(liqBlood, "liqBlood");
	}
}

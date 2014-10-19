package okkapel.pureevilthings.item;

import okkapel.pureevilthings.ref.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value=Reference.MODID)
public class ModItems {
	public static ItemET itemOnyx = new ItemOnyx("onyx");
	
	public static void initItems() {
		GameRegistry.registerItem(itemOnyx, "onyx");
	}
}

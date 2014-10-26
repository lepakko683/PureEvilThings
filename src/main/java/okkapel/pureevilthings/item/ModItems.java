package okkapel.pureevilthings.item;

import net.minecraft.item.Item;
import okkapel.pureevilthings.ref.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value=Reference.MODID)
public class ModItems {
	public static Item itemOnyx = new ItemOnyx("onyx");
	public static Item itemBloodBottle = new ItemBloodBottle("bloodBottle");
	public static Item itemBloodOnyxPick = new ItemBloodOnyxPick("toolOnyxPickaxe");
	
	public static void initItems() {
		GameRegistry.registerItem(itemOnyx, "onyx");
		GameRegistry.registerItem(itemBloodBottle, "bloodBottle");
		GameRegistry.registerItem(itemBloodOnyxPick, "toolOnyxPickaxe");
	}
}

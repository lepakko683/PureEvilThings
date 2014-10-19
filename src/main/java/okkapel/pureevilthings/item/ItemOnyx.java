package okkapel.pureevilthings.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemOnyx extends ItemET {

	public ItemOnyx(String unLocName) {
		super(unLocName);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addInformation(ItemStack stack, EntityPlayer pl, List lst, boolean b) {
		super.addInformation(stack, pl, lst, b);
		switch(stack.getItemDamage()) {
		case 0:
			lst.add(StatCollector.translateToLocal("item.onyx.desc"));
			break;
		case 1:
			lst.add(StatCollector.translateToLocal("item.bloodStainedOnyx.desc"));
			break;
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch(stack.getItemDamage()) {
		case 0:
			return "item.onyx";
		case 1:
			return "item.bloodStainedOnyx";
		case 2:
			return "item.bloodOnyx";
		}
		return super.getUnlocalizedName(stack);
	}

}

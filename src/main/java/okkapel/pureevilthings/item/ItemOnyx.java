package okkapel.pureevilthings.item;

import java.util.List;

import okkapel.pureevilthings.entity.EntityItemOnyx;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

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
		case 2:
			lst.add(StatCollector.translateToLocal("item.bloodOnyx.desc"));
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
	
	@Override
	public Entity createEntity(World world, Entity loc, ItemStack stack) {
		if(stack.getItem() == this) {
			EntityItemOnyx drop = new EntityItemOnyx(world, loc.posX, loc.posY, loc.posZ, stack);
			drop.motionX = loc.motionX;
			drop.motionY = loc.motionY;
			drop.motionZ = loc.motionZ;
			drop.delayBeforeCanPickup = 40;
			return drop;
		} else {
			System.out.println("This shouldn't have happened!");
		}
		return super.createEntity(world, loc, stack);
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}

}

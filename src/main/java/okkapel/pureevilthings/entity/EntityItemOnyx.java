package okkapel.pureevilthings.entity;

import java.util.Random;

import okkapel.pureevilthings.block.ModBlocks;
import okkapel.pureevilthings.item.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityItemOnyx extends EntityItem {

	public EntityItemOnyx(World world, double x, double y, double z, ItemStack stack) {
		super(world, x, y, z, stack);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource src, float dmg) {
		if(src.isFireDamage()) {
			ItemStack item = getEntityItem();
			if(item != null && item.getItem() == ModItems.itemOnyx && item.getItemDamage() == 1) {
				setEntityItemStack(new ItemStack(item.getItem(), item.stackSize, 2));
			}
			this.extinguish();
			return false;
		}
		return super.attackEntityFrom(src, dmg);
    }
	
	public Random getRand() {
		return this.rand;
	}
	
	@Override
	public void onUpdate() {
		if(worldObj.getBlock((int)posX, (int)posY, (int)posZ) == ModBlocks.liqBlood) {
			ItemStack item = this.getEntityItem();
			if(item.getItem() == ModItems.itemOnyx && item.getItemDamage() == 0) {
				setEntityItemStack(new ItemStack(item.getItem(), item.stackSize, 1));
			}
		}
		super.onUpdate();
	}
	
	
}

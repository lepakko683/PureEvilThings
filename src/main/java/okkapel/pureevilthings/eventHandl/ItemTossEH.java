package okkapel.pureevilthings.eventHandl;

import okkapel.pureevilthings.entity.EntityItemOnyx;
import okkapel.pureevilthings.item.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class ItemTossEH {
	@SubscribeEvent
	public void onItemToss(ItemTossEvent e) {
		if(!e.player.worldObj.isRemote && !(e.entityItem instanceof EntityItemOnyx)) {
			ItemStack dropped = e.entityItem.getEntityItem();
			if(dropped != null && dropped.getItem() == ModItems.itemOnyx) {
				EntityItemOnyx drop = new EntityItemOnyx(e.entityItem.worldObj, e.player.posX, e.player.posY - 0.30000001192092896D + (double)e.player.getEyeHeight(), e.player.posZ, dropped.copy());
				
				float f = 0.3F;
				drop.motionX = (double)(-MathHelper.sin(e.player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(e.player.rotationPitch / 180.0F * (float)Math.PI) * f);
				drop.motionZ = (double)(MathHelper.cos(e.player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(e.player.rotationPitch / 180.0F * (float)Math.PI) * f);
				drop.motionY = (double)(-MathHelper.sin(e.player.rotationPitch / 180.0F * (float)Math.PI) * f + 0.1F);
				
				f = 0.02F;
				
				float f1 = drop.getRand().nextFloat() * (float)Math.PI * 2.0F;
				f *= drop.getRand().nextFloat();
				drop.motionX += Math.cos((double)f1) * (double)f;
				drop.motionY += (double)((drop.getRand().nextFloat() - drop.getRand().nextFloat()) * 0.1F);
				drop.motionZ += Math.sin((double)f1) * (double)f;
				
				drop.delayBeforeCanPickup = 20;
				e.player.worldObj.spawnEntityInWorld(drop);
				e.entityItem.age = e.entityItem.lifespan+1;
			}
		}
		
	}
}

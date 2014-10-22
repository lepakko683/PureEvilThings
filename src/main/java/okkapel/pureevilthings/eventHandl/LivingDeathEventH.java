package okkapel.pureevilthings.eventHandl;

import okkapel.pureevilthings.item.ItemBloodBottle;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LivingDeathEventH {

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent e) {
		if(!e.entityLiving.worldObj.isRemote) {
			if(e.source instanceof EntityDamageSource) {
				EntityDamageSource eds = (EntityDamageSource) e.source;
				if(eds.getSourceOfDamage() instanceof EntityPlayer) {
					EntityPlayer plr = (EntityPlayer) eds.getSourceOfDamage();
					int emptyBottleIdx = -1;
					int bloodBottleIdx = -1;
//						for(int i=0;i<plr.inventory.mainInventory.length;i++) {
//							if(plr.inventory.mainInventory[i] != null && plr.inventory.mainInventory[i].getItem() == Items.glass_bottle) {
//								plr.inventory.decrStackSize(i, )
//							}
//						}
					int bloodData = ItemBloodBottle.getBloodTypeAndAmount(e.entityLiving);
					System.out.println("FillBottle: type=" + ItemBloodBottle.bloodTypes[bloodData >> 8] + ", amount=" + (bloodData & 0xFF) + "mB");
				}
			}
				
		}
//		e.source.
	}
}

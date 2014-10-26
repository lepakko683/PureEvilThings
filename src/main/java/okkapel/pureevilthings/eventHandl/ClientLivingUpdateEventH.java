package okkapel.pureevilthings.eventHandl;

import okkapel.pureevilthings.potioneffect.PotionPET;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ClientLivingUpdateEventH {
	
	public static float screenWarpMultip = 1.05f;
	
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent e) {
		
		if(e.entityLiving.worldObj.isRemote) {
			if(e.entityLiving instanceof EntityPlayerSP) {
				EntityPlayerSP splr = (EntityPlayerSP) e.entityLiving;
				
				if(splr.isPotionActive(PotionPET.insanity) && splr.getActivePotionEffect(PotionPET.insanity).getDuration() > 60 && !splr.inPortal && !splr.isPotionActive(Potion.confusion)) {
	                splr.timeInPortal = 0.001f;
	                if(screenWarpMultip < 1.05f) {
	                	screenWarpMultip += 0.0003f;
	                }
	                if(screenWarpMultip > 1.05f) {
	                	screenWarpMultip = 1.05f;
	                }
				} else {
					// Note: Entity.inPortal has to be 'access transformed' to be public
					if(splr.inPortal || splr.isPotionActive(Potion.confusion)) {
						return;
					}
					if(screenWarpMultip > 1.0f) {
	                	screenWarpMultip -= 0.0008334f;
	                	splr.timeInPortal = 0.001f;
	                }
	                if(screenWarpMultip < 1.0f) {
	                	screenWarpMultip = 1.0f;
	                	splr.timeInPortal = 0;
	                }
					
				}
				
			}
		}
	}
}

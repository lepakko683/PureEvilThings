package okkapel.pureevilthings.eventHandl;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import okkapel.pureevilthings.potioneffect.PotionPET;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class RenderGameOverlayEventH {
	
	@SubscribeEvent
	public void onRenderGameOverlayEvent(RenderGameOverlayEvent e) {
		if(e.type == RenderGameOverlayEvent.ElementType.PORTAL) {
			if(PotionPET.hidePortalOverlay()) {
				e.setCanceled(true);
			}
		}
	}
}

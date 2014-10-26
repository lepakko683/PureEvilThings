package okkapel.pureevilthings.eventHandl;


import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import okkapel.pureevilthings.potioneffect.PotionPET;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;

public class RenderFogEventH {
	
//	private FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
	
	@SubscribeEvent
	public void onRenderFogEvent(RenderFogEvent e) {
		if(e.entity instanceof EntityPlayer) {
			if(e.entity.isPotionActive(PotionPET.insanity)) { // TODO: smoothen
				float f1 = 5.0F;
				int j = e.entity.getActivePotionEffect(PotionPET.insanity).getDuration();
				float amt = 5.0F;
				
                if (j < 20)
                {
                    f1 = amt + (e.farPlaneDistance - amt) * (1.0F - (float)j / 20.0F);
                }
//                GL11.glFog(GL11.GL_FOG_COLOR, setFogColor(.2f, .1f, .1f, 1f));
//                GL11.glFogf(GL11.GL_FOG_DENSITY, 250f);
                GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
                
//                if (e.fogMode < 0)
//                {
//                    GL11.glFogf(GL11.GL_FOG_START, 0.0F);
//                    GL11.glFogf(GL11.GL_FOG_END, f1 * 3F);
//                }
//                else
//                {
//                    GL11.glFogf(GL11.GL_FOG_START, f1 * 0.25F);
//                    GL11.glFogf(GL11.GL_FOG_END, f1);
//                }
                GL11.glFogf(GL11.GL_FOG_START, 0.0F);
                GL11.glFogf(GL11.GL_FOG_END, 30f);
			}
		}
	}
	
//	private FloatBuffer setFogColor(float r, float g, float b, float a) {
//		fogColor.clear();
//		fogColor.put(r); fogColor.put(g); fogColor.put(b); fogColor.put(a);
//		fogColor.flip();
//		return fogColor;
//	}
}

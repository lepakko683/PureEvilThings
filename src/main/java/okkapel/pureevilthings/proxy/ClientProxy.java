package okkapel.pureevilthings.proxy;

import net.minecraftforge.common.MinecraftForge;
import okkapel.pureevilthings.client.render.RenderBrainWorm;
import okkapel.pureevilthings.entity.EntityBrainWorm;
import okkapel.pureevilthings.eventHandl.ClientLivingUpdateEventH;
import okkapel.pureevilthings.eventHandl.RenderGameOverlayEventH;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBrainWorm.class, new RenderBrainWorm());
	}
	
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		
		MinecraftForge.EVENT_BUS.register(new ClientLivingUpdateEventH());
		
		MinecraftForge.EVENT_BUS.register(new RenderGameOverlayEventH());
	}
}

package okkapel.pureevilthings.proxy;

import okkapel.pureevilthings.client.render.RenderBrainWorm;
import okkapel.pureevilthings.entity.EntityBrainWorm;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBrainWorm.class, new RenderBrainWorm());
	}
}

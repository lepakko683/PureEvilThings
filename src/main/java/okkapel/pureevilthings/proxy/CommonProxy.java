package okkapel.pureevilthings.proxy;

import okkapel.pureevilthings.eventHandl.LivingDeathEventH;
import net.minecraftforge.common.MinecraftForge;


public abstract class CommonProxy implements IProxy {
	@Override
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new LivingDeathEventH());
	}
}

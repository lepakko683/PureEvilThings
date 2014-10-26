package okkapel.pureevilthings;

import okkapel.pureevilthings.block.ModBlocks;
import okkapel.pureevilthings.entity.EntityBrainWorm;
import okkapel.pureevilthings.item.ModItems;
import okkapel.pureevilthings.proxy.IProxy;
import okkapel.pureevilthings.ref.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=Reference.MODID, version=Reference.VERSION, name=Reference.MODNAME)
public class PureEvilThings {
	
	@SidedProxy(clientSide = Reference.PROXY_CLASS_CLIENT, serverSide = Reference.PROXY_CLASS_SERVER)
	public static IProxy proxy;
	
	@Mod.Instance(Reference.MODID)
	public static PureEvilThings INSTANCE;
	
	public static CreativeTabs creativeTab = new CreativeTabs(Reference.MODID) {
		@Override
		public Item getTabIconItem() {
			return ModItems.itemOnyx;
		}
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		EntityRegistry.registerModEntity(EntityBrainWorm.class, "BrainWorm", 0, INSTANCE, 64, 1, true);
		
		ModItems.initItems();
		
		ModBlocks.initBlocks();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.registerRenderer();
		
		proxy.registerEventHandlers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {}
}

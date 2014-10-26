package okkapel.pureevilthings.potioneffect;

import java.util.List;

import okkapel.pureevilthings.eventHandl.ClientLivingUpdateEventH;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class PotionPET extends Potion {

	public static final PotionPET insanity = createPotion(true, 0x000000, new ItemStack[0]).setPotionName("potion.insanity");

	private final List<ItemStack> curItems;
	
	@SuppressWarnings("unchecked")
	protected PotionPET(int id, boolean badEffect, int color, ItemStack[] curItems) {
		super(id, badEffect, color);
		
		this.curItems = Arrays.asList(curItems);
	}
	
	private static PotionPET createPotion(boolean badEffect, int color, ItemStack[] curItems) {
		int id = getFirstFreeID();
		if(id != -1) {
			return new PotionPET(id, badEffect, color, curItems);
		}
		return null;
	}
	
	public PotionPET setPotionName(String name) {
		super.setPotionName(name);
		return this;
	}
	
	@Override
	public void performEffect(EntityLivingBase ent, int amplifier) {
		if(id == insanity.id && ent instanceof EntityPlayer) {
		}
	}
	
	public boolean isReady(int dur, int amp) {
		if(id == insanity.id) {
			return dur % (20 >> amp) == 0; 
		}
		return false;
	}
	
	public void applyEffect(EntityLivingBase ent, int dur, int amp) {
		PotionEffect eff = new PotionEffect(id, dur, amp);
		eff.setCurativeItems(curItems);
		ent.addPotionEffect(eff);
	}
	
	private static int getFirstFreeID() {
		for(int i=1;i<Potion.potionTypes.length;i++) {
			if(Potion.potionTypes[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	/** No need to check if player has insanity effect twice on same tick */
	private static boolean isPlayerInsane = false;
	public static boolean isInsane() {
		isPlayerInsane = FMLClientHandler.instance().getClient().thePlayer.isPotionActive(insanity);
		return isPlayerInsane;
	}

	public static byte getScreenWarpSpeed(byte old) {
		return isInsane() ? 1 : old;
	}
	
	public static float getScreenWarpMultiplier() {
		return isPlayerInsane ? ClientLivingUpdateEventH.screenWarpMultip : 1f;
	}
	
	public static boolean hidePortalOverlay() {
		return isPlayerInsane;
	}
	
}

package okkapel.pureevilthings.potioneffect;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class PotionEffectET extends PotionEffect {

	private boolean isCurable = true;
	
	public PotionEffectET(int id, int dur, int amp, boolean ambient) {
		super(id, dur, amp, ambient);
	}
	
	public PotionEffectET(int id, int dur, int amp, boolean ambient, boolean curable) {
		super(id, dur, amp, ambient);
		this.isCurable = curable;
	}
	
	public void setCurable(boolean curable) {
		this.isCurable = curable;
	}
	
	@Override
	public boolean isCurativeItem(ItemStack stack) {
		if(!isCurable) {
			return false;
		}
		return super.isCurativeItem(stack);
	}

}

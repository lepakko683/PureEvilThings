package okkapel.pureevilthings.potioneffect;

import java.util.Random;

public class PEInsanity extends PotionEffectET {
	private static final Random insRand = new Random();
	private int insanityFlags;
	
	
	public PEInsanity(int id, int dur, int amp) {
		super(id, dur, amp, true, false);
		
		insanityFlags = 0;
		
	}
	
	

}

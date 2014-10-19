package okkapel.pureevilthings.item;

import java.util.List;

import okkapel.pureevilthings.PureEvilThings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemET extends Item {
	
	private String descString = null;
	
	public ItemET(String unLocName) {
		setCreativeTab(PureEvilThings.creativeTab);
		setUnlocalizedName(unLocName);
		setTextureName("pureevilthings:" + unLocName);
	}
	
	public ItemET(String unLocName, String desc) {
		setCreativeTab(PureEvilThings.creativeTab);
		setUnlocalizedName(unLocName);
		setTextureName("pureevilthings:" + unLocName);
		descString = desc;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addInformation(ItemStack stack, EntityPlayer pl, List lst, boolean b) {
		super.addInformation(stack, pl, lst, b);
		if(descString != null) {
			lst.add(descString);
		}
	}
}

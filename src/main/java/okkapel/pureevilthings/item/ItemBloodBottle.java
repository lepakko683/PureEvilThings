package okkapel.pureevilthings.item;

import java.util.List;

import okkapel.pureevilthings.ref.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBloodBottle extends ItemET {
	
	private IIcon[] icons;
	public static final String[] bloodTypes = new String[] {"regular", "undead", "ender", "infernal"};
	private static final double divdr = 250d / 4d;
	public static final int MAX_AMOUNT = 250;

	public ItemBloodBottle(String unLocName) {
		super(unLocName);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addInformation(ItemStack stack, EntityPlayer pl, List lst, boolean b) {
		super.addInformation(stack, pl, lst, b);
		switch(stack.getItemDamage() >> 8) {
		case 0:
			lst.add(StatCollector.translateToLocal("item.bloodBottle_regular.desc"));
			break;
		case 1:
			lst.add(StatCollector.translateToLocal("item.bloodBottle_undead.desc"));
			break;
		case 2:
			lst.add(StatCollector.translateToLocal("item.bloodBottle_ender.desc"));
			break;
		case 3:
			lst.add(StatCollector.translateToLocal("item.bloodBottle_infernal.desc"));
			break;
		}
		int fill = stack.getItemDamage() & 0xFF;
		lst.add((fill == 0 ? 250 : 250-fill) + "mB / " + 250 + "mB");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch(stack.getItemDamage() >> 8) {
		case 0:
			return "item.bloodBottle_regular";
		case 1:
			return "item.bloodBottle_undead";
		case 2:
			return "item.bloodBottle_ender";
		case 3:
			return "item.bloodBottle_infernal";
		}
		return super.getUnlocalizedName(stack);
	}
	
	@Override
	public IIcon getIconFromDamage(int dmg) {
		if((dmg >> 8) > 3) {
			return super.getIconFromDamage(dmg);
		}
		return icons[4*(dmg >> 8)+(int)(Math.floor((dmg & 0xFF)/divdr))];
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[16];
		for(int i=0;i<icons.length;i++) {
			icons[i] = reg.registerIcon(Reference.MODID + ":bloodBottle_" + bloodTypes[(int)(i/4)] + "_" + i%4);
		}
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return (stack.getItemDamage() & 0xFF) != 0;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		double ret = (double)(stack.getItemDamage() & 0xFF) / 249d;
		return ret;
	}
	
	// Yays for hardcoding ^.^
	public static int getBloodTypeAndAmount(EntityLivingBase e) {
		int ret = 0;
		
		if(e instanceof EntityAnimal || e instanceof EntitySpider) {
			if(e instanceof EntityChicken) {
				ret |= 25;
			} else {
				ret |= 50;
			}
		} else if(e instanceof EntityZombie || e instanceof EntityPigZombie) {
			ret |= (1 << 8);
			ret |= 25;
		} else if(e instanceof EntityEnderman) {
			ret |= (2 << 8);
			ret |= 10;
		} else if(e instanceof EntityBlaze || e instanceof EntityGhast || e instanceof EntityMagmaCube) {
			ret |= (3 << 8);
			ret |= 25;
		}
		return ret;
	}
	
	public static int getBottleBloodType(int dmg) {
		return dmg >> 8;
	}
	
	

}

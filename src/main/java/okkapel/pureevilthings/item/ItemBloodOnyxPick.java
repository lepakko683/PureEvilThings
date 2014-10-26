package okkapel.pureevilthings.item;


import java.util.ArrayList;

import okkapel.pureevilthings.potioneffect.PotionPET;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBloodOnyxPick extends ItemPickaxe {

	protected ItemBloodOnyxPick(String itemName) {
		super(ToolMaterial.EMERALD);
		ItemET.initItem(this, itemName);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer plr, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			Block blc = world.getBlock(x, y, z);
			ArrayList<ItemStack> drops;
			EntityItem itemEnt;
			int bx, by, bz;
			for(int xx=-1;xx<2;xx++) {
				for(int yy=-1;yy<2;yy++) {
					for(int zz=-1;zz<2;zz++) {
						if(Math.abs(xx) + Math.abs(yy) + Math.abs(zz) < 3 && !world.isAirBlock(x+xx, y+yy, z+zz)) {
							bx=x+xx; by=y+yy; bz=z+zz;
							drops = plr.capabilities.isCreativeMode ? null : blc.getDrops(world, bx, by, bz, world.getBlockMetadata(bx, by, bz), 0);
							if(blc == Blocks.bedrock || blc.hasTileEntity(world.getBlockMetadata(bx, by, bz))) {
								return false;
							}
							blc.onBlockPreDestroy(world, bx, by, bz, world.getBlockMetadata(bx, by, bz));
							blc.onBlockDestroyedByPlayer(world, bx, by, bz, world.getBlockMetadata(bx, by, bz));
							world.setBlockToAir(bx, by, bz);
							if(drops != null) {
								for(ItemStack s : drops) {
									itemEnt = new EntityItem(world, bx+.5d, by+.5d, bz+.5f, s);
									itemEnt.setVelocity(-0.05f+world.rand.nextFloat()*.1f, -0.05f+world.rand.nextFloat()*.1f, -0.05f+world.rand.nextFloat()*.1f);
									world.spawnEntityInWorld(itemEnt);
								}
							}
						}
					}	
				}
			}
			if(!plr.capabilities.isCreativeMode) {
				plr.setHealth(plr.getHealth()-2f);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity ent, int invSlot, boolean inHand) {
		if(!world.isRemote && ent instanceof EntityPlayer && inHand) {
			PotionPET.insanity.applyEffect(((EntityPlayer)ent), 200, 0);
		}
//		super.onUpdate(stack, world, ent, i, bool);
	}
	

}

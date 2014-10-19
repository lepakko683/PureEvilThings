package okkapel.pureevilthings.block;


import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

public class BlockOreET extends BlockET {
	private static final Random rand = new Random();
	private final Item droppedItem;
	private final int dropQuant, dropQuantRand, extPerFort, minExp, randExp;

	protected BlockOreET(String bName, Item droppedItem, int quantity, int quantRandom, int extraPerFortuneLevel, int minExp, int maxExp) {
		super(Material.rock, bName);
		this.droppedItem = droppedItem;
		this.dropQuant = quantity;
		this.dropQuantRand = quantRandom+1;
		this.extPerFort = extraPerFortuneLevel;
		this.minExp = minExp;
		this.randExp = (maxExp-minExp) < 1 ? 1 : (maxExp-minExp);
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return droppedItem;
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random rand) {
		return dropQuant + rand.nextInt(dropQuantRand < 1 ? 1 : dropQuantRand) + fortune*extPerFort;
	}
	
	@Override
	public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
		return minExp+rand.nextInt(randExp);
	}
}

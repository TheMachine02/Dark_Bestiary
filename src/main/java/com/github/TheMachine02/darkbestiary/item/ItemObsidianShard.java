package com.github.TheMachine02.darkbestiary.item;

import com.github.TheMachine02.darkbestiary.DarkBestiary;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemObsidianShard extends Item {

	public ItemObsidianShard () {
		this.setCreativeTab(DarkBestiary.TAB);
	this.setUnlocalizedName("darkbestiary.obsidian_shard");
		this.setRegistryName(DarkBestiary.MODID,"darkbestiary.obsidian_shard");
	}

}


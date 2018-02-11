package com.github.TheMachine02.darkbestiary.misc;

import com.github.TheMachine02.darkbestiary.core.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

	public CreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.obsidian_shard);
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}
}

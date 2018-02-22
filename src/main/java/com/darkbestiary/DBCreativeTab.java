package com.darkbestiary;

import com.darkbestiary.init.DBItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DBCreativeTab extends CreativeTabs {

	public DBCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(DBItem.obsidian_shard);
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}
}

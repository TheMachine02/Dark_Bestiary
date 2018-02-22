package com.darkbestiary.item;

import com.darkbestiary.DarkBestiary;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGeneric extends Item {
	public ItemGeneric(String gameName, String name) {
		this.setUnlocalizedName(name);
		this.setCreativeTab(DarkBestiary.TAB);
		this.setRegistryName(DarkBestiary.MODID, gameName);
	}

}

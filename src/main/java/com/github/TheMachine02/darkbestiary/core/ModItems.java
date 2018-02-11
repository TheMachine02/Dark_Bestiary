package com.github.TheMachine02.darkbestiary.core;

import com.github.TheMachine02.darkbestiary.DarkBestiary;
import com.github.TheMachine02.darkbestiary.item.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	@GameRegistry.ObjectHolder(DarkBestiary.MODID + ":obsidian_shard")
	public static Item obsidian_shard = new ItemGeneric("obsidian_shard", "darkbestiary.obsidian_shard");
	@GameRegistry.ObjectHolder(DarkBestiary.MODID + ":phoenix_ash")
	public static Item phoenix_ash= new ItemPhoenixAsh();


}
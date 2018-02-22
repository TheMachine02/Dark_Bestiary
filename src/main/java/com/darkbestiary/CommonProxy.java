package com.darkbestiary;

import com.darkbestiary.init.DBBlock;
import com.darkbestiary.init.DBItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class CommonProxy {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		try {
			for (Field f : DBBlock.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Block) {
					event.getRegistry().register((Block) obj);
				} else if (obj instanceof Block[]) {
					for (Block block : (Block[]) obj) {
						event.getRegistry().register(block);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		try {
			for (Field f : DBBlock.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Block) {
					ItemBlock itemBlock;
					itemBlock = new ItemBlock((Block) obj);
					itemBlock.setRegistryName(((Block) obj).getRegistryName());
					event.getRegistry().register(itemBlock);
				} else if (obj instanceof Block[]) {
					for (Block block : (Block[]) obj) {
						ItemBlock itemBlock = new ItemBlock(block);
						itemBlock.setRegistryName(block.getRegistryName());
						event.getRegistry().register(itemBlock);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		try {
			for (Field f : DBItem.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Item) {
					event.getRegistry().register((Item) obj);
				} else if (obj instanceof Item[]) {
					for (Item item : (Item[]) obj) {
						event.getRegistry().register(item);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void preRender() {

	}

	public void render() {
	}

	public void postRender() {
	}

	public void spawnParticle(String name, World world, double x, double y, double z, double motX, double motY, double motZ) {
	}


}

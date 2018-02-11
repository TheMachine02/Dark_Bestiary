package com.github.TheMachine02.darkbestiary.core;

import com.github.TheMachine02.darkbestiary.DarkBestiary;
import com.github.TheMachine02.darkbestiary.entity.*;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;

public class ModEntities {

	public static void registerSpawnable(Class entityClass, String name, int id, int mainColor, int subColor) {
		EntityRegistry.registerModEntity(new ResourceLocation(DarkBestiary.MODID, name), entityClass, name, id, DarkBestiary.INSTANCE, 64, 3, true, mainColor, subColor);
	}

	public static void init() {
	//	registerSpawnable(EntityWyvern.class, "wyvern", 1, 0, 0);

	/*	if (DarkBestiary.CONFIG.spawnWyvern) {
					for (Biome biome : Biome.REGISTRY) {
						if (biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
							List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.CREATURE);
							spawnList.add(new Biome.SpawnListEntry(EntityWyvern.class, 5, 1, 1));
						}
			}
		}*/
	}
}

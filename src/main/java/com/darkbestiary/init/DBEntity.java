package com.darkbestiary.init;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.*;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;

public class DBEntity {

	public static void registerSpawnable(Class entityClass, String name, int id, int mainColor, int subColor) {
		EntityRegistry.registerModEntity(new ResourceLocation(DarkBestiary.MODID, name), entityClass, name, id, DarkBestiary.INSTANCE, 64, 3, true, mainColor, subColor);
	}

	public static void init() {

		registerSpawnable(EntityPhoenix.class, "Phoenix", 1, 0X340000, 0XA52929);
		registerSpawnable(EntityWyvern.class, "Wyvern", 2, 0X000000, 0XA02020);
		registerSpawnable(EntityWillOWisp.class, "WillOWisp", 3, 0X000000, 0XA02020);

       // EntityRegistry.addSpawn(EntityLion.class, 6, 1, 5, EnumCreatureType.creature, BiomeGenBase.savanna); //change the values to vary the spawn rarity, biome, etc.              

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

package com.github.TheMachine02.darkbestiary;

import net.ilexiconn.llibrary.server.config.ConfigEntry;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class DarkBestiaryConfig {

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateObsidianOre = true;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "wyvern", comment = "True if wyvern are allowed to spawn.")
	public boolean spawnWyvern = true;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "wyvern", comment = "Health of Wyvern.")
	public int wyvernHealth = 400;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateWyvernCave = true;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation", comment = "1 out of number of chance to generate per chunk in forest")
	public int generateWyvernCaveChance = 64;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "thunderbird", comment = "True if thunderbird are allowed to spawn.")
	public boolean spawnThunderBird = true;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation")
	public boolean generateThunderBirdNest = true;

	@SuppressWarnings("deprecation")
	@ConfigEntry(category = "generation", comment = "1 out of number of chance to generate per chunk in extreme hill")
	public int generateThunderBirdNestChance = 64;


}

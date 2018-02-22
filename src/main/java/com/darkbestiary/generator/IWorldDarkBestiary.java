package com.darkbestiary.generator;

import com.darkbestiary.generator.*;
import com.darkbestiary.DarkBestiary;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IWorldDarkBestiary implements IWorldGenerator
{

	private static final WorldGenWyvernCave WYVERN_CAVE = new WorldGenWyvernCave();

	public static BlockPos getHeight(World world, BlockPos pos) {
		for (int y = 0; y < 256; y++) {
			BlockPos pos1 = pos.up(y);
			if (world.getBlockState(pos1.up()).getBlock() == Blocks.AIR && world.getBlockState(pos1.down()).getBlock() != Blocks.AIR) {
				return pos1;
			}
		}
		return pos;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int blockX = chunkX * 16 + 8;
		int blockZ = chunkZ * 16 + 8;
		// generate differently based on dimension
		switch(world.provider.getDimension())
		{
		case -1: generateNether(world, random, blockX, blockZ);
		break;
		case 0: generateOverworld(world, random, blockX, blockZ);
		break;
		case 1: generateEnd(world, random, blockX, blockZ);
		break;
		}

	}

	private void generateNether(World world, Random rand, int blockX, int blockZ) 
	{
		// leaving blank for now		
	}

	private void generateOverworld(World world, Random rand, int blockX, int blockZ) 
	{

		BlockPos height = getHeight(world, new BlockPos(blockX, 0, blockZ));
		if(BiomeDictionary.hasType(world.getBiome(height), Type.FOREST) )
		{
			if(rand.nextInt(100)==0)
			{
				WYVERN_CAVE.generate(world, rand,height);
			}
		}
	}

	private void generateEnd(World world, Random rand, int blockX, int blockZ) 
	{
		// leaving blank for now
	}

}
package com.darkbestiary.generator;

import com.darkbestiary.DarkBestiary;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class WorldGenWyvernCave extends WorldGenerator {

	@Override
	public boolean generate(World world0, Random rand, BlockPos position) {

		generateCave(world0, rand, position);
		/*EntityWyvern wyvern = new EntityWyvern(world0);
		wyvern.setGender(wyvern.getRNG().nextBoolean());
		wyvern.setHealth(300);
		wyvern.setPositionAndRotation(position.getX() + 0.5, world0.getHeight(position).getY() + 1.5, position.getZ() + 0.5, rand.nextFloat() * 360, 0);
		wyvern.cave = position;
		world0.spawnEntity(wyvern);*/
		return true;
	}

	public void generateCave(World world, Random rand, BlockPos position) {

		gaussianHill(world, position);
		return;
	}
	
	public void entranceSphere(World world, BlockPos position)
	{
		//generate a sphere of 7 diameter, grass ground, +wall

		for (BlockPos blockpos : BlockPos.getAllInBox(position.add(-3, -3, -3), position.add(3, 3, 3))) {
		if (blockpos.distanceSq(position)<=6.0D) {
					world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 3);
		}
		}


	}


	public void gaussianHill(World world, BlockPos pos) {

		int centerx = pos.getX();
		int centerz = pos.getZ();
		int centery = pos.getY();

		for( int i= -24 ; i<24; i++) {
			for ( int j = -24 ; j<24 ; j++) {

			BlockPos block = new BlockPos(i+centerx, centery+(int)gaussian2d((float)i,(float)j, 24.0F, 24.0F),centerz+j);
			world.setBlockState(block, Blocks.STONE.getDefaultState(), 3);
			}	
		}


	}

	public float gaussian2d(float x, float y, float sigma, float amplitude){
		float sigmaSq = sigma * sigma;
		return amplitude * (float)Math.exp(- ( (x*x)/sigmaSq + (y*y)/sigmaSq ));
	}

}


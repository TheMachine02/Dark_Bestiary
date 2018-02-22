package com.darkbestiary.entity;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.ai.EntityAIDanceOnTheWater;

import net.minecraft.world.World;
import net.minecraft.world.EnumSkyBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

/* TODO HERE

	- ai to wander on water
	- change the current model which is crap and just a mockup
	- better implementation
*/


public class EntityWillOWisp extends EntityCreature
{
	
	protected final EntityAIBase aiDanceOnTheWater = new EntityAIDanceOnTheWater(this, 1.0F);


	public EntityWillOWisp(World world) {
		super(world);
		this.setSize(1.0F, 1.0F);
	}

    @Override
    protected void initEntityAI()
    {
		//this.tasks.addTask(0, aiDanceOnTheWater);
    }

    @Override
    public boolean isAIDisabled()
    {
        return false;
    }

	public boolean isWaterBlock(BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		return block == Blocks.WATER;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, @Nonnull IBlockState state, @Nonnull BlockPos pos) {
	}

	@Override
	public void fall(float dist, float damageMultiplier) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender() {
		return 15728880;
	}

	public float getGlowBrightness() {
		return (float) Math.sin(this.ticksExisted / 7.0) + 1F;
}

}
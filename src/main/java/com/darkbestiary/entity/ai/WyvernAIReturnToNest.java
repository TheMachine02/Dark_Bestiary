package com.darkbestiary.entity.ai;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.EntityWyvern;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.entity.ai.RandomPositionGenerator;

import java.util.Random;


public class WyvernAIReturnToNest extends EntityAIBase
{
	private final EntityWyvern entity;
	private final float speed;
	private int sleepTicks;

    public WyvernAIReturnToNest(EntityWyvern wyvern)
    {
        this.entity= wyvern;
	   this.speed = 0.7F;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
	   return this.entity.canMove() && (!this.entity.isNight()) ;
    }
	@Override
	public boolean shouldContinueExecuting() {

	return !this.entity.getNavigator().noPath() && this.entity.canMove();
	}

	@Override
	public void startExecuting() {

	this.sleepTicks=0;	this.entity.getNavigator().tryMoveToXYZ(this.entity.getHomePosition().getX() + 0.5D, this.entity.getHomePosition().getY(), this.entity.getHomePosition().getZ() + 0.5D, this.speed);
	this.entity.getLookHelper().setLookPosition(this.entity.getHomePosition().getX() + 0.5D, this.entity.getHomePosition().getY(), this.entity.getHomePosition().getZ() + 0.5D, 10.0F, (float)this.entity.getVerticalFaceSpeed());
	}

	@Override
	public void updateTask() {
		if(sleepTicks++>1000){
			this.entity.setSleeping(true);
		}

	}
	

	@Override
	public void resetTask()
	{
		this.sleepTicks=0;
		this.entity.getNavigator().clearPath();
	}

}


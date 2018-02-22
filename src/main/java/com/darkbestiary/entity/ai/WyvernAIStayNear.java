package com.darkbestiary.entity.ai;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.EntityWyvern;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.RandomPositionGenerator;
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

import java.util.Random;


public class WyvernAIStayNear extends EntityAIBase {
	private final EntityWyvern entity;
	private final float speed;
	private Vec3d vec3;

	public WyvernAIStayNear(EntityWyvern wyvern) {
		this.entity = wyvern;
		this.speed = 0.7F;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		return !this.entity.isWithinHomeDistanceCurrentPosition() && this.entity.isFemale() && this.entity.canMove();
	}

	@Override
	public boolean shouldContinueExecuting() {
		return !this.entity.getNavigator().noPath() && this.entity.canMove();
	}

	@Override
	public void startExecuting() {
		if (this.entity.getDistanceSq(this.entity.getHomePosition()) > 256.0D) {
			vec3 = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, 14, 3, new Vec3d(this.entity.getHomePosition().getX() + 0.5D, this.entity.getHomePosition().getY(), this.entity.getHomePosition().getZ() + 0.5D));

			if (vec3 != null) {
				this.entity.getNavigator().tryMoveToXYZ(vec3.x, vec3.y, vec3.z, speed);
	this.entity.getLookHelper().setLookPosition((double)this.vec3.x, (double)(this.vec3.y + this.entity.getEyeHeight()), (double)this.vec3.z, 10.0F, (float)this.entity.getVerticalFaceSpeed());
			}
		}
	}


}

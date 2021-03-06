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

public class WyvernAIWander extends EntityAIBase
{
    private final EntityWyvern entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private final float speed;

    public WyvernAIWander(EntityWyvern wyvern)
    {
	    this.entity=wyvern;
	    this.speed = 0.7F;
    }

	@Override
    public boolean shouldExecute()
    {

	  if(!this.entity.isFemale() || !this.entity.canMove()){
		return false;
	   }

       if (this.entity.getRNG().nextInt(20) != 0)
       {
          return false;
       }


        Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

        if (vec3d == null)
        {
            return false;
        }
        else
        {
            this.xPosition = vec3d.x;
            this.yPosition = vec3d.y;
            this.zPosition = vec3d.z;
            return true;
        }
    }
	@Override
    public boolean shouldContinueExecuting()
    {
        return !this.entity.getNavigator().noPath() && this.entity.canMove();
    }

	@Override
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
	this.entity.getLookHelper().setLookPosition((double)this.xPosition, (double)(this.yPosition + this.entity.getEyeHeight()), (double)this.zPosition, 10.0F, (float)this.entity.getVerticalFaceSpeed());
    }

}
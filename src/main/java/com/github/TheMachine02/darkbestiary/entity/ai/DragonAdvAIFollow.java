package com.github.TheMachine02.darkbestiary.entity.ai;

import com.github.TheMachine02.darkbestiary.DarkBestiary;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
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



public class DragonADVAIFollow extends EntityAIBase
{
	private EntityDragonBase dragon;
	private EntityPlayer player;
	World world;
    private float speed;
    private int tick;
    private double max;
    private double min;

    public DragonADVAIFollow(EntityDragonBase dragon0, float speed0, double min0, double max0)
    {
        this.dragon = dragon0;
        this.speed = speed0;
        this.min = min0;
        this.max = max0;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityPlayer player0 = (EntityPlayer)this.dragon.getOwner();

        if (player0 == null)
        {
            return false;
        }
        else if (this.dragon.isSitting() || this.dragon.isSleeping()  || !this.dragon.canMove() )
        {
            return false;
        }
	   else if(this.dragon.isRidingPlayer(player0))
	   {
		  return false;
	   }
        else if (this.dragon.getDistanceSq(player0) < (this.min*this.min))
        {
            return false;
        }
        else
        {
            this.player = player0;
            return true;
        }
    }

    public boolean continueExecuting()
    {
		DarkBestiary.logger.info(!this.dragon.getNavigator().noPath() && this.dragon.getDistanceSq(this.player)>(this.max*this.max) && !this.dragon.isSitting());
        return !this.dragon.getNavigator().noPath() && this.dragon.getDistanceSq(this.player)>(this.max*this.max) && !this.dragon.isSitting();
    }

    public void startExecuting()
    {
        this.tick= 0;
    }

    public void resetTask()
    {
        this.player = null;
    }

    public void updateTask()
    {
        this.dragon.getLookHelper().setLookPositionWithEntity(this.player, 30.0F, (float)this.dragon.getVerticalFaceSpeed());

        if (!this.dragon.isSitting())
        {
		 --this.tick;
            if (this.tick<=0)
            {
                this.tick = 16;
	this.dragon.setFlying(false);
	this.dragon.getNavigator().tryMoveToEntityLiving(this.player, this.speed);
                }
            }
        }
}
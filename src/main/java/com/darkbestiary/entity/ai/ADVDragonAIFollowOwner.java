package com.darkbestiary.entity.ai;

import com.darkbestiary.DarkBestiary;
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

public class ADVDragonAIFollowOwner extends EntityAIBase
{
	private EntityDragonBase dragon;
	private EntityPlayer player;
	private float speed;
     private int tick;
     private double max;
     private double min;

    public ADVDragonAIFollowOwner(EntityDragonBase dragon0, float speed0, double min0, double max0)
    {
        this.dragon = dragon0;
        this.speed = speed0;
        this.min = min0;
        this.max = max0;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityPlayer player0 = (EntityPlayer)this.dragon.getOwner();

        if (player0 == null)
        {
            return false;
        }
        else if (!this.dragon.canMove())
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
    @Override
    public boolean shouldContinueExecuting()
    {
        return !this.dragon.getNavigator().noPath() && this.dragon.getDistanceSq(this.player)>(this.max*this.max) && this.dragon.canMove();
    }

    @Override
    public void startExecuting()
    {
        this.tick=0;
    }

    @Override
    public void resetTask()
    {
	this.player = null;
	this.dragon.getNavigator().clearPath();   	
    }

    @Override
    public void updateTask()
    {
        
this.dragon.getLookHelper().setLookPositionWithEntity(this.player, 20.0F, (float)this.dragon.getVerticalFaceSpeed());

        if (!this.dragon.isSitting())
        {
		 --this.tick;
            if (this.tick<=0)
            {
                this.tick = 16;
	this.dragon.getNavigator().tryMoveToXYZ(this.player.posX,this.player.posY,this.player.posZ, this.speed);
             }

	DarkBestiary.logger.info(this.dragon.getDistanceSq(this.player));


		  if(this.dragon.getDistanceSq(this.player)>4096.0D){
		this.dragon.airTarget=findAirBlockAbove(this.player);
		this.dragon.setLocationAndAngles(this.player.posX, this.dragon.airTarget.getY(),this.player.posZ, 20.0F, (float)this.dragon.getVerticalFaceSpeed());
			this.dragon.getNavigator().clearPath();
			this.dragon.setFlying(true);
	        }
        }
	}

	private BlockPos findAirBlockAbove(EntityPlayer player)
	{
		BlockPos position = new BlockPos(player.posX,player.posY+16,player.posZ);
		int i;
		for(i=position.getY();i<256;i++){
			if(this.dragon.world.isAirBlock(position)){
				break;
			}
			position.add(0,1,0);
		}
		return position;
	}
}

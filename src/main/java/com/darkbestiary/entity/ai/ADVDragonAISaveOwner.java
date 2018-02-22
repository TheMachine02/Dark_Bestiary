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

public class ADVDragonAISaveOwner extends EntityAIBase
{
	private EntityDragonBase dragon;
	private EntityPlayer owner;
	World world;
	private float speed;
	private boolean isComplete;

    public ADVDragonAISaveOwner(EntityDragonBase dragon0, float speed0)
    {
        this.dragon = dragon0;
        this.speed = speed0;
        this.setMutexBits(7);
	   this.isComplete=true;
    }

    @Override
    public boolean shouldExecute()
    {
        	EntityPlayer owner0 = (EntityPlayer)this.dragon.getOwner();
		if(owner0==null){
			return false;
		}
		else if(!this.dragon.canMove()){
			return false;
		}
		else{
			if(owner0.fallDistance>2 && (owner0.posY-targetLand(owner0)>9)){
				this.owner=owner0;
				isComplete=false;
				return true;
			}
		}
		return false;
    }

    private int targetLand(EntityPlayer player){
	BlockPos position = new BlockPos((int)player.posX, (int)player.posY, (int)player.posZ);
	for(int i=(int)player.posY;i>0;i--){
		if(this.dragon.world.isAirBlock(position)){
			break;
		}
		position.add(0,-1,0);
	}
		return position.getY();
	}	


    @Override
    public boolean shouldContinueExecuting()
    {
		return this.dragon.canMove() && !this.owner.isDead && !this.dragon.getNavigator().noPath() && !isComplete;
    }

    @Override
    public void resetTask()
    {
		this.owner = null;
		this.dragon.getNavigator().clearPath();
		this.dragon.setFlying(false);
		this.dragon.airTarget=null;
    }

    @Override
    public void updateTask()
    {
	if(!this.dragon.isFlying()){
		this.dragon.setFlying(true);
           this.dragon.motionY += (this.speed*0.5D);
		this.dragon.onGround=false;
	}
	this.dragon.airTarget= new BlockPos(this.owner.posX,this.owner.posY-9, this.owner.posZ);
	DarkBestiary.logger.info("Save:");
	DarkBestiary.logger.info(this.dragon.airTarget);
//
	this.dragon.getLookHelper().setLookPositionWithEntity(this.owner, 20.0F, (float)this.dragon.getVerticalFaceSpeed());
//	this.dragon.getNavigator().tryMoveToXYZ(this.owner.posX,this.owner.posY-8, this.owner.posZ,this.speed);
	
	if(this.dragon.getEntityBoundingBox().expand(4.0D,4.0D,4.0D).intersects(this.owner.getEntityBoundingBox()))
	{
	   this.isComplete=true;
		this.dragon.roar();
		if(!this.owner.isDead){
			this.owner.startRiding(this.dragon, true);
		}
	}
    }
}



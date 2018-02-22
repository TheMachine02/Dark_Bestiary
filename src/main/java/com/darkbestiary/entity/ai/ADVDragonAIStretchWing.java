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

import java.util.Random;


public class ADVDragonAIStretchWing extends EntityAIBase
{
	private EntityDragonBase dragon;
	private boolean  isRunning;
	private BlockPos waypoint;
	private double   radius;
	private double   angle;

    public ADVDragonAIStretchWing(EntityDragonBase dragon0)
    {
        this.dragon = dragon0;
        this.setMutexBits(3);
        this.radius=((double)(this.dragon.getDragonStage())-2.0D)*16.0D;
	   this.angle=0.0D;
    }

    @Override
    public boolean shouldExecute()
    {
		if(!this.dragon.canMove()){
			return false;
		}
		if(this.dragon.getDragonStage()<3)
		{
			return false;
		}
		if (!this.isRunning){
			if (this.dragon.getRNG().nextInt(50)!=0) {
				return false;
			}
			this.isRunning=true;
	     		this.angle=0.0D;
	           this.dragon.motionY += 1.0D;
			this.dragon.airTarget=findWaypoint();
			this.dragon.roar();
		}
		return this.isRunning;
    }
	@Override
	public boolean shouldContinueExecuting() {
		this.dragon.airTarget=findWaypoint();
		if(!this.dragon.canMove() || this.angle>360.0D){
			this.isRunning=false;
		}else	 if (this.dragon.isTargetBlocked(new Vec3d(this.dragon.airTarget))) {
			this.dragon.airTarget = null;
			this.isRunning=false;	
		}
		return this.isRunning;
	}


	@Override
	public void resetTask()
	{
		this.isRunning=false;
		this.dragon.getNavigator().clearPath();
		this.dragon.setFlying(false);
		this.dragon.airTarget=null;
	}

    @Override
    public void updateTask()
    {

	if(getDistanceToEntity(this.dragon,this.waypoint)<4.0D){
	//new waypoint;
		this.angle+=12.0D;
		this.dragon.airTarget=findWaypoint();
	}

this.dragon.getLookHelper().setLookPosition(this.waypoint.getX() + 0.5D, this.waypoint.getY() + this.dragon.getEyeHeight(), this.waypoint.getZ() + 0.5D, this.dragon.getHorizontalFaceSpeed(), this.dragon.getVerticalFaceSpeed());

	}


	private BlockPos findWaypoint(){
		EntityPlayer owner=(EntityPlayer)this.dragon.getOwner();
		if(owner!=null && this.dragon.isTamed()){
			
			this.waypoint=new BlockPos(owner.posX, owner.posY, owner.posZ).add(this.radius*Math.cos(this.angle),16,this.radius*Math.sin(this.angle));
		}
		else {
		if(this.dragon.homeArea==null){
			this.dragon.homeArea = new BlockPos(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
		}
	this.waypoint=this.dragon.homeArea.add(this.radius*Math.cos(this.angle),16,this.radius*Math.sin(this.angle));
		}
		return this.waypoint;
	}

private static double getDistanceToEntity(Entity entity, BlockPos pos) {
	double deltaX = entity.posX - pos.getX();
	double deltaY = entity.posY - pos.getY();
	double deltaZ = entity.posZ - pos.getZ();
		
	return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
}

}


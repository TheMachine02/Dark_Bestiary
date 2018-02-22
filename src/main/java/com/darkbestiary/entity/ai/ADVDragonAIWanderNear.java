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


public class ADVDragonAIWanderNear extends EntityAIBase
{
	private EntityDragonBase dragon;
	private boolean isRunning;
	private double newX;
	private double newY;
	private double newZ;

    public ADVDragonAIWanderNear(EntityDragonBase dragon0)
    {
        this.dragon = dragon0;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
		if(!this.dragon.canMove()){
			return false;
		}
		if (this.dragon.isFlying()||this.dragon.isHovering())			{
			return false;
		}
		if (!this.isRunning){
			if (this.dragon.getRNG().nextInt(20)!=0) {
				return false;
			}
		}
		this.isRunning=true;
		return this.isRunning;
    }
	@Override
	public boolean shouldContinueExecuting() {
		if(this.dragon.getNavigator().noPath() || !this.dragon.canMove()){
			this.isRunning=false;
		}
		return this.isRunning;
	}

	@Override
	public void startExecuting() {
		Random rand = new Random();
		EntityPlayer owner=(EntityPlayer)this.dragon.getOwner();
		if(owner!=null && this.dragon.isTamed()){
			this.newX=(double)(owner.posX + rand.nextInt(64)-32);
			this.newY=(double)(owner.posY + rand.nextInt(32)-16);
			this.newZ=(double)(owner.posZ + rand.nextInt(64)-32);

		}
		else {
		if(this.dragon.homeArea==null){
			this.dragon.homeArea = new BlockPos(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
		}

		this.newX=(double)(this.dragon.homeArea.getX() + rand.nextInt(64)-32);
		this.newY=(double)(this.dragon.homeArea.getY() + rand.nextInt(32)-16);
		this.newZ=(double)(this.dragon.homeArea.getZ() + rand.nextInt(64)-32);
		}

		this.dragon.getNavigator().tryMoveToXYZ(this.newX, this.newY, this.newZ,1.0F);
this.dragon.getLookHelper().setLookPosition(this.newX + 0.5D, this.newY + this.dragon.getEyeHeight(), this.newZ + 0.5D, this.dragon.getHorizontalFaceSpeed(), this.dragon.getVerticalFaceSpeed());

	}

	@Override
	public void resetTask()
	{
		this.isRunning=false;
		this.dragon.getNavigator().clearPath();
	}

}

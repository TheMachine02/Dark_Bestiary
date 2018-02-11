package com.github.Theachine02.darkbestiary.entity.ai;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class DragonAdvAICatch extends EntityAIBase {

	private EntityPlayer player;
	private const int fallTriggerConst=6;
	private const int distantTriggerConst=16;

    public DragonAdvAICatch(EntityDragonBase dragon)      
	{
        super(dragon);
    }

    @Override
    public boolean shouldExecute() {        

	if(!this.dragon.isTamed() || !this.dragon.canMove() || !this.dragon.isSleeping())
	{
		return false;
	}

	this.player=(EntityPlayer)this.dragon.getOwner();

	if(this.dragon.isSitting() || this.dragon.isRidingPlayer(this.player))
	{
		return false;
	}        
    
      double distanceRange = getFollowRange()+distantTriggerConst;
      if (this.dragon.getDistanceToEntity(this.player) > distanceRange) {
		return false;
        }
                
        return this.player.fallDistance > fallTriggerConst;
    }

    @Override
    public boolean continueExecuting() {
        return shouldExecute() && !this.dragon.getNavigator().noPath();
    }
    
    @Override
    public void updateTask() {
        if (!dragon.isFlying()) {
            dragon.setFlying(true);
        }

           if (this.dragon.getDistanceToEntity(this.player) < this.dragon.getRenderSize()) {
            this.player.startRiding(this.dragon);
        } else {
	this.dragon.getLookHelper().setLookPositionWithEntity(this.player, 30.0F, 30.0F);
	this.dragon.getNavigator().tryMoveToEntityLiving(this.player, 1);
        }
}

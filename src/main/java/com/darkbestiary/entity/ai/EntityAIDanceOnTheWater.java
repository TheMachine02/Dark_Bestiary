package com.darkbestiary.entity.ai;

import com.darkbestiary.DarkBestiary;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.EntityCreature;

public class EntityAIDanceOnTheWater extends EntityAIBase {

	private EntityCreature entity;
	private float speed;

	public EntityAIDanceOnTheWater(EntityCreature thing, float speedThing) {
		this.entity = thing;
		this.speed = speedThing;
		this.setMutexBits(7);	// disable everything else
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
	
	@Override
	public void updateTask() {

	


	}

}

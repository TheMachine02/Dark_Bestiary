package com.darkbestiary.entity.ai;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.EntityPhoenix;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFly extends EntityAIBase {

	private EntityPhoenix entity;

	public EntityAIFly(EntityPhoenix phoenix) {
		this.entity = phoenix;
		this.setMutexBits(5);
	}

	@Override
	public boolean shouldExecute() {
		return entity.isFlying();
	}
}
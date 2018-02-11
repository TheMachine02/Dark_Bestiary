package com.github.TheMachine02.darkbestiary.entity;

import com.github.TheMachine02.darkbestiary.DarkBestiary;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.*;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;

public class EntityWyvern extends EntityTameable{

	public EntityWyvern(World worldIn){
		super(worldIn);
	}

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal instanceof EntityWyvern && otherAnimal != this && otherAnimal.getClass() == this.getClass()) {
        }
        return false;
    }

}

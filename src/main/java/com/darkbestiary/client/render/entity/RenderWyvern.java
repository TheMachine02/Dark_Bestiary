package com.darkbestiary.client.render.entity;

import com.darkbestiary.client.model.ModelWyvern;
import com.darkbestiary.entity.EntityWyvern;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWyvern extends RenderLiving<EntityWyvern> {

	public RenderWyvern(RenderManager renderManager) {
		super(renderManager, new ModelWyvern(), 0.8F);
	}

	@Override
	protected void preRenderCallback(EntityWyvern entity, float par2) {
			super.preRenderCallback(entity, par2);
		}

	@Override
	protected ResourceLocation getEntityTexture(EntityWyvern entity){
	return new ResourceLocation(entity.getTexture()+".png");
	}

}

package com.darkbestiary.client.render.entity;

import com.darkbestiary.client.model.ModelPhoenix;
import com.darkbestiary.entity.EntityPhoenix;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPhoenix extends RenderLiving {

	public RenderPhoenix(RenderManager renderManager) {
		super(renderManager, new ModelPhoenix(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
			super.preRenderCallback(par1EntityLivingBase, par2);
		}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity){
				return new ResourceLocation("darkbestiary:textures/models/phoenix.png");
	}

}


package com.darkbestiary.client.render.entity;

import com.darkbestiary.client.model.ModelWillOWisp;
import com.darkbestiary.entity.EntityWillOWisp;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWillOWisp extends RenderLiving<EntityWillOWisp> {

	public RenderWillOWisp(RenderManager renderManager) {
		super(renderManager, new ModelWillOWisp(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityWillOWisp entity, float par2) {
			super.preRenderCallback(entity, par2);
		}

	@Override
	protected ResourceLocation getEntityTexture(EntityWillOWisp entity){
	return new ResourceLocation("darkbestiary:textures/models/willowisp.png");
	}

}

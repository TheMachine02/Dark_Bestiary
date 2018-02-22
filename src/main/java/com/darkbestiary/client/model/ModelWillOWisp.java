package com.darkbestiary.client.model;

import com.darkbestiary.entity.EntityWillOWisp;

import com.darkbestiary.DarkBestiary;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * WillOWisp - Machichi
 * Created using Tabula 7.0.0
 */
public class ModelWillOWisp extends ModelBase {
    public ModelRenderer light0;
    public ModelRenderer light1;
    public ModelRenderer light2;

    public ModelWillOWisp() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.light0 = new ModelRenderer(this, 0, 0);
        this.light0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.light0.addBox(-6.0F, -4.0F, 6.0F, 12, 12, 1, 0.0F);
        this.light2 = new ModelRenderer(this, 0, 15);
        this.light2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.light2.addBox(-6.0F, -6.0F, -5.0F, 12, 16, 1, 0.0F);
        this.setRotateAngle(light2, 0.0F, -1.0471975511965976F, 0.0F);
        this.light1 = new ModelRenderer(this, 29, -2);
        this.light1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.light1.addBox(-6.0F, -8.0F, -7.0F, 12, 16, 1, 0.0F);
        this.setRotateAngle(light1, 0.0F, 1.0471975511965976F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 

		EntityWillOWisp willy = (EntityWillOWisp)entity;

	     this.setRotateAngle(light0, 0.0F, 0.0F, 0.0F);
          this.setRotateAngle(light1, 0.0F, 0.0F, 0.0F);
          this.setRotateAngle(light2, 0.0F, 0.0F, 0.0F);

		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.disableLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, willy.getGlowBrightness());
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, 1);

        this.light0.render(f5);
        this.light2.render(f5);
        this.light1.render(f5);

		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableAlpha();
		GlStateManager.enableLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

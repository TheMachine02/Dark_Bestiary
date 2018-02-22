package com.darkbestiary.client.model;

import com.darkbestiary.entity.EntityPhoenix;

import com.darkbestiary.DarkBestiary;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Phoenix - Machichi
 * Created using Tabula 7.0.0
 */
public class ModelPhoenix extends ModelBase {

    public ModelRenderer body0;
    public ModelRenderer body3;
    public ModelRenderer head0;
    public ModelRenderer body1;
    public ModelRenderer tailextra;
    public ModelRenderer tailmain0;
    public ModelRenderer tailside00;
    public ModelRenderer tailside10;
    public ModelRenderer wingleft0;
    public ModelRenderer wingright0;
    public ModelRenderer bodyfeatherleft0;
    public ModelRenderer bodyfeatherright0;
    public ModelRenderer head1;
    public ModelRenderer headfeather0;
    public ModelRenderer head2;
    public ModelRenderer headfeather1;
    public ModelRenderer tailmain1;
    public ModelRenderer tailmain2;
    public ModelRenderer tailside01;
    public ModelRenderer tailside02;
    public ModelRenderer tailside11;
    public ModelRenderer tailside12;
    public ModelRenderer wingleft1;
    public ModelRenderer wingleft2;
    public ModelRenderer wingleft3;
    public ModelRenderer wingright1;
    public ModelRenderer wingright2;
    public ModelRenderer wingright3;
    public ModelRenderer bodyfeatherleft1;
    public ModelRenderer bodyfeatherleft2;
    public ModelRenderer bodyfeatherright1;
    public ModelRenderer bodyfeatherright2;

    public ModelPhoenix() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.tailside02 = new ModelRenderer(this, 0, 0);
        this.tailside02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailside02.addBox(-3.0F, 0.0F, -18.0F, 6, 2, 10, 0.0F);
        this.body0 = new ModelRenderer(this, 0, 0);
        this.body0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body0.addBox(-5.0F, -4.5F, -7.0F, 10, 9, 14, 0.0F);
        this.head0 = new ModelRenderer(this, 54, 0);
        this.head0.setRotationPoint(0.0F, -2.0F, 17.0F);
        this.head0.addBox(-3.0F, -1.0F, 0.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(head0, -0.17453292519943295F, 0.0F, 0.0F);
        this.tailextra = new ModelRenderer(this, 0, 0);
        this.tailextra.setRotationPoint(0.0F, -4.0F, -7.0F);
        this.tailextra.addBox(-5.0F, 0.0F, -12.0F, 10, 2, 12, 0.0F);
        this.setRotateAngle(tailextra, -0.12217304763960307F, 0.0F, 0.0F);
        this.tailside01 = new ModelRenderer(this, 0, 0);
        this.tailside01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailside01.addBox(-2.0F, 0.0F, -8.0F, 4, 2, 4, 0.0F);
        this.head2 = new ModelRenderer(this, 54, 0);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.addBox(-1.0F, 1.0F, 8.0F, 2, 4, 1, 0.0F);
        this.wingright1 = new ModelRenderer(this, 0, 0);
        this.wingright1.mirror = true;
        this.wingright1.setRotationPoint(6.0F, 0.0F, 0.0F);
        this.wingright1.addBox(0.0F, -1.0F, -7.0F, 10, 2, 10, 0.0F);
        this.wingright2 = new ModelRenderer(this, 0, 0);
        this.wingright2.mirror = true;
        this.wingright2.setRotationPoint(10.0F, 0.0F, -4.0F);
        this.wingright2.addBox(0.0F, -1.0F, -9.0F, 20, 2, 14, 0.0F);
        this.headfeather1 = new ModelRenderer(this, 62, 14);
        this.headfeather1.mirror = true;
        this.headfeather1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headfeather1.addBox(-3.0F, -2.5F, -10.0F, 2, 2, 10, 0.0F);
        this.tailmain0 = new ModelRenderer(this, 0, 0);
        this.tailmain0.setRotationPoint(0.0F, -2.0F, -11.0F);
        this.tailmain0.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tailmain0, -0.08726646259971647F, 0.0F, 0.0F);
        this.tailside10 = new ModelRenderer(this, 0, 0);
        this.tailside10.mirror = true;
        this.tailside10.setRotationPoint(0.0F, -2.0F, -11.0F);
        this.tailside10.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tailside10, 0.08726646259971647F, 0.4363323129985824F, -0.4363323129985824F);
        this.bodyfeatherleft0 = new ModelRenderer(this, 0, 0);
        this.bodyfeatherleft0.setRotationPoint(-4.0F, -3.0F, 13.0F);
        this.bodyfeatherleft0.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 8, 0.0F);
        this.setRotateAngle(bodyfeatherleft0, 0.0F, 0.0F, 1.7453292519943295F);
        this.wingleft3 = new ModelRenderer(this, 0, 0);
        this.wingleft3.setRotationPoint(-22.0F, 0.0F, -1.0F);
        this.wingleft3.addBox(0.0F, -1.0F, -15.0F, 2, 2, 18, 0.0F);
        this.tailside11 = new ModelRenderer(this, 0, 0);
        this.tailside11.mirror = true;
        this.tailside11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailside11.addBox(-2.0F, 0.0F, -8.0F, 4, 2, 4, 0.0F);
        this.bodyfeatherleft1 = new ModelRenderer(this, 0, 0);
        this.bodyfeatherleft1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyfeatherleft1.addBox(-1.0F, -1.0F, -10.0F, 2, 4, 6, 0.0F);
        this.body3 = new ModelRenderer(this, 0, 0);
        this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body3.addBox(-3.0F, -2.0F, -11.0F, 6, 4, 4, 0.0F);
        this.wingright0 = new ModelRenderer(this, 0, 0);
        this.wingright0.mirror = true;
        this.wingright0.setRotationPoint(3.0F, -4.5F, 4.0F);
        this.wingright0.addBox(0.0F, -1.0F, -3.0F, 6, 2, 6, 0.0F);
        this.tailmain2 = new ModelRenderer(this, 0, 0);
        this.tailmain2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailmain2.addBox(-3.0F, 0.0F, -22.0F, 6, 2, 14, 0.0F);
        this.tailside12 = new ModelRenderer(this, 0, 0);
        this.tailside12.mirror = true;
        this.tailside12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailside12.addBox(-3.0F, 0.0F, -18.0F, 6, 2, 10, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head1.addBox(-2.0F, 0.0F, 6.0F, 4, 4, 2, 0.0F);
        this.tailmain1 = new ModelRenderer(this, 0, 0);
        this.tailmain1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailmain1.addBox(-2.0F, 0.0F, -8.0F, 4, 2, 4, 0.0F);
        this.bodyfeatherleft2 = new ModelRenderer(this, 0, 0);
        this.bodyfeatherleft2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyfeatherleft2.addBox(-1.0F, -1.0F, -22.0F, 2, 6, 12, 0.0F);
        this.bodyfeatherright0 = new ModelRenderer(this, 0, 0);
        this.bodyfeatherright0.setRotationPoint(4.0F, -3.0F, 13.0F);
        this.bodyfeatherright0.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 8, 0.0F);
        this.setRotateAngle(bodyfeatherright0, 0.0F, 0.0F, -1.7453292519943295F);
        this.wingleft1 = new ModelRenderer(this, 0, 0);
        this.wingleft1.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.wingleft1.addBox(-10.0F, -1.0F, -7.0F, 10, 2, 10, 0.0F);
        this.wingleft2 = new ModelRenderer(this, 0, 0);
        this.wingleft2.setRotationPoint(-10.0F, 0.0F, -4.0F);
        this.wingleft2.addBox(-20.0F, -1.0F, -9.0F, 20, 2, 14, 0.0F);
        this.bodyfeatherright2 = new ModelRenderer(this, 0, 0);
        this.bodyfeatherright2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyfeatherright2.addBox(-1.0F, -1.0F, -22.0F, 2, 6, 12, 0.0F);
        this.headfeather0 = new ModelRenderer(this, 48, 12);
        this.headfeather0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headfeather0.addBox(1.0F, -2.5F, -10.0F, 2, 2, 10, 0.0F);
        this.body1 = new ModelRenderer(this, 78, 0);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.addBox(-4.0F, -3.5F, 7.0F, 8, 7, 10, 0.0F);
        this.wingleft0 = new ModelRenderer(this, 0, 0);
        this.wingleft0.setRotationPoint(-2.5F, -4.5F, 4.0F);
        this.wingleft0.addBox(-6.0F, -1.0F, -3.0F, 6, 2, 6, 0.0F);
        this.tailside00 = new ModelRenderer(this, 0, 0);
        this.tailside00.setRotationPoint(0.0F, -2.0F, -11.0F);
        this.tailside00.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tailside00, 0.08726646259971647F, -0.4363323129985824F, 0.4363323129985824F);
        this.wingright3 = new ModelRenderer(this, 0, 0);
        this.wingright3.mirror = true;
        this.wingright3.setRotationPoint(20.0F, 0.0F, -1.0F);
        this.wingright3.addBox(0.0F, -1.0F, -15.0F, 2, 2, 18, 0.0F);
        this.bodyfeatherright1 = new ModelRenderer(this, 0, 0);
        this.bodyfeatherright1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyfeatherright1.addBox(-1.0F, -1.0F, -10.0F, 2, 4, 6, 0.0F);
        this.tailside00.addChild(this.tailside02);
        this.body0.addChild(this.head0);
        this.body0.addChild(this.tailextra);
        this.tailside00.addChild(this.tailside01);
        this.head0.addChild(this.head2);
        this.wingright0.addChild(this.wingright1);
        this.wingright1.addChild(this.wingright2);
        this.head0.addChild(this.headfeather1);
        this.body0.addChild(this.tailmain0);
        this.body0.addChild(this.tailside10);
        this.body0.addChild(this.bodyfeatherleft0);
        this.wingleft2.addChild(this.wingleft3);
        this.tailside10.addChild(this.tailside11);
        this.bodyfeatherleft0.addChild(this.bodyfeatherleft1);
        this.body0.addChild(this.body3);
        this.body0.addChild(this.wingright0);
        this.tailmain0.addChild(this.tailmain2);
        this.tailside10.addChild(this.tailside12);
        this.head0.addChild(this.head1);
        this.tailmain0.addChild(this.tailmain1);
        this.bodyfeatherleft0.addChild(this.bodyfeatherleft2);
        this.body0.addChild(this.bodyfeatherright0);
        this.wingleft0.addChild(this.wingleft1);
        this.wingleft1.addChild(this.wingleft2);
        this.bodyfeatherright0.addChild(this.bodyfeatherright2);
        this.head0.addChild(this.headfeather0);
        this.body0.addChild(this.body1);
        this.body0.addChild(this.wingleft0);
        this.body0.addChild(this.tailside00);
        this.wingright2.addChild(this.wingright3);
        this.bodyfeatherright0.addChild(this.bodyfeatherright1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 

	// animate \o/

	EntityPhoenix phoenix = (EntityPhoenix)entity;

		setRotateAngle(this.body0, 0.523599F*(phoenix.horizontalFactor+phoenix.verticalFactor), 0.0F, 0.0F);
	setRotateAngle(this.head0, -0.6F*phoenix.horizontalFactor+0.523599F*phoenix.verticalFactor, 0.0F, 0.0F);

	   float factor = (float)Math.cos(phoenix.ticksExisted*3.1415F/15.0F);
	// between -1 and 1

	   setRotateAngle(this.wingright0, 0.0F, -0.349066F, -smoothInterpolate(phoenix.ticksExisted,0));
	   setRotateAngle(this.wingright2, 0.0F, 0.0F, -smoothInterpolate(phoenix.ticksExisted,1));

	   setRotateAngle(this.wingleft0, 0.0F, 0.349066F, smoothInterpolate(phoenix.ticksExisted,0));
	   setRotateAngle(this.wingleft2, 0.0F, 0.0F , smoothInterpolate(phoenix.ticksExisted, 1));


        GlStateManager.pushMatrix();
        GlStateManager.translate(this.body0.offsetX, this.body0.offsetY, this.body0.offsetZ);
        GlStateManager.translate(this.body0.rotationPointX * f5, this.body0.rotationPointY * f5, this.body0.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 1.0D, -1.0D);
        GlStateManager.translate(-this.body0.offsetX, -this.body0.offsetY + cyclicFactor(phoenix.ticksExisted)*0.25F, -this.body0.offsetZ);
        GlStateManager.translate(-this.body0.rotationPointX * f5, -this.body0.rotationPointY * f5, -this.body0.rotationPointZ * f5);
        this.body0.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }	


    protected float[][] wingAngleCycle = new float[][]
    {
        {1.0472F, -0.174533F},
        {0.0F, -0.174533F},
        {-0.4F, -0.872665F},
        {0.349066F, -1.0472F}, 
	   {0.785441F, -0.5F}
    };

	public float smoothInterpolate(int tick, int offset){
		int adress0 = (tick % 20) / 4;
		int adress1 = ((tick+4)%20) / 4;

		return wingAngleCycle[adress0][offset] * (1.0F-(tick%4)/4.0F) + wingAngleCycle[adress1][offset] * (tick%4)/4.0F;
	}

	public float cyclicFactor(int tick) {
		return (float)Math.cos(tick*6.28318530718F/20.0F);	// 0 => 1.0F, 10-> -1.0F, 20->1.0F
	}


}

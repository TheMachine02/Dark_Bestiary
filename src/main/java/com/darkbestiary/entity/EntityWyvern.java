package com.darkbestiary.entity;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.ai.*;
import net.ilexiconn.llibrary.LLibrary;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;


public class EntityWyvern extends EntityTameable{

    private static final DataParameter<Boolean> GENDER = EntityDataManager.<Boolean>createKey(EntityWyvern.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityWyvern.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityWyvern.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SPEEDING = EntityDataManager.<Boolean>createKey(EntityWyvern.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> THROWING = EntityDataManager.<Boolean>createKey(EntityWyvern.class, DataSerializers.BOOLEAN);

	private Boolean FEMALE = true;
	private Boolean MALE = false;
	private EntityLiving prey;

	private int huntingTicks;
	private int sleepingTicks;

	public EntityWyvern(World world){
		super(world);
		this.ignoreFrustumCheck = true;
		this.setSize(1.0F, 1.0F);
	}

	@Override
	protected void initEntityAI() {
	//	this.tasks.addTask(1, new EntityAISwimming(this));
	//	this.tasks.addTask(2, new EntityAISit(this));
		this.tasks.addTask(3, new WyvernAIReturnToNest(this));
		this.tasks.addTask(4, new WyvernAIStayNear(this));
		this.tasks.addTask(5, new WyvernAIWander(this));
		//this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.5D, false));

	}

     @Override
     public boolean isAIDisabled() {
		return false;
     }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(GENDER, false);
        this.dataManager.register(VARIANT, 0);
        this.dataManager.register(SLEEPING, false);
        this.dataManager.register(SPEEDING, false);
        this.dataManager.register(THROWING, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("gender", this.isFemale());
        compound.setInteger("variant", this.getVariant());
        compound.setBoolean("sleeping", this.isSleeping());
        compound.setBoolean("speeding", this.isSpeeding());
        compound.setBoolean("throwing", this.isThrowing());
        compound.setBoolean("sitting", this.isSitting());
        if (this.getCustomNameTag() != null && !this.getCustomNameTag().isEmpty()) {
            compound.setString("CustomName", this.getCustomNameTag());
        }
    }


    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setGender(compound.getBoolean("gender"));
        this.setVariant(compound.getInteger("variant"));
        this.setSleeping(compound.getBoolean("sleeping"));
        this.setSitting(compound.getBoolean("sitting"));
        this.setSpeeding(compound.getBoolean("speeding"));
	   this.setThrowing(compound.getBoolean("throwing"));
        if (!compound.getString("CustomName").isEmpty()) {
           
this.setCustomNameTag(compound.getString("CustomName"));
        }

    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);

    }

    public int getVariant() {
        return this.dataManager.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.dataManager.set(VARIANT, variant);
    }

	public boolean getGender() {
		return this.dataManager.get(GENDER);
	}

    public void setGender(boolean female) {
        this.dataManager.set(GENDER, female);
    }


    public boolean isFemale() {
        return this.dataManager.get(GENDER);
    }

    public boolean isProtectingNest() {
	// search for near player
		List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(96.0D, 96.0D, 96.0D));
		return this.isFemale() && list!=null;
	}


    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.dataManager.set(SLEEPING, sleeping);
    }

   public boolean isHunting() {
        return !this.isFemale() && this.isNight();
    }


   public boolean isSpeeding() {
        return this.dataManager.get(SPEEDING);
    }

    public void setSpeeding(boolean speeding) {
        this.dataManager.set(SPEEDING, speeding);
    }

   public boolean isThrowing() {
         return this.dataManager.get(THROWING);
    }

    public void setThrowing(boolean throwing) {
        this.dataManager.set(THROWING, throwing);
    }

    @Override
    public boolean isSitting() {
	
    /*    if (this.world.isRemote) {
            this.isSitting=(this.dataManager.get(TAMED) & 1) != 0;
        }
        return this.isSitting;*/
		return false;
    }

    @Override
    public void setSitting(boolean sitting) {
        super.setSitting(sitting);
    }

    public boolean canMove() {
        return !this.isSitting() && !this.isSleeping() && !this.isThrowing();
    }

    protected void despawnEntity() {
         super.despawnEntity();
    }

    public boolean isNight() {
        return !this.world.isDaytime();
    }

	public EntityLiving getPrey() {
		return  this.prey;
	}
	
	public void setPrey(EntityLiving preyIn) {
		this.prey = preyIn;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();	
    }

	@Override
	public int getVerticalFaceSpeed() {
		return 10;
	}

	@Override
	public int getHorizontalFaceSpeed() {
		return 10;	
	}

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        //this.setGender(new Random().nextBoolean());
        this.setGender(FEMALE);
	   this.setVariant(new Random().nextInt(3));
        this.setSleeping(false);
	   this.setSpeeding(false);
	   this.setThrowing(false);
        this.heal(20);
		this.huntingTicks=0;
		this.sleepingTicks=0;

		this.setHomePosAndDistance(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 64);

        return livingdata;
    }


    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal instanceof EntityWyvern && otherAnimal != this && otherAnimal.getClass() == this.getClass()) {
            EntityWyvern wyvern = (EntityWyvern) otherAnimal;
            if (this.isFemale() ^ wyvern.isFemale()) {
                return true;
            }
        }
        return false;
    }

    public boolean canBeSteered() {
        return true;
    }


	public String getTexture() {
		if (this.isSleeping()) {
		return "darkbestiary:textures/models/wyvern/"+this.getVariantName(this.getVariant());    // +"_sleep"
		} else {
		return "darkbestiary:textures/models/wyvern/"+this.getVariantName(this.getVariant());
		}
	}

	public String getVariantName(int variant) {
		switch (variant) {
			case 0:
				return "red";
			case 1:
				return "booled";
			case 2:
				return "green";
			default:
				return "";
		}
	}

}

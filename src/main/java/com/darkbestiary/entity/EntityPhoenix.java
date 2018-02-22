package com.darkbestiary.entity;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.ai.EntityAIFly;

import net.minecraft.world.World;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.init.Items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

public class EntityPhoenix extends EntityTameable
{
        protected final EntityAIBase aiFly = new EntityAIFly(this);
        protected final EntityAIBase aiAttackMelee = new EntityAIAttackMelee(this, 1.0D, true);
        protected final EntityAIBase aiFollowOwner = new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F);
        protected final EntityAIBase aiWanderAvoidWater = new EntityAIWanderAvoidWater(this, 1.0D);
        protected final EntityAIBase aiWatchClosest = new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F);
        protected final EntityAIBase aiLookIdle = new EntityAILookIdle(this);
	   private static final DataParameter<Byte> PHOENIX_FLAGS = EntityDataManager.createKey(EntityPhoenix.class, DataSerializers.BYTE);

	private BlockPos targetPosition;
	private int flightTime;
    public float horizontalFactor;	// todo : make them as getter
    public float verticalFactor;

////// INIT /////////////

    public EntityPhoenix(World world)
    {
        super(world);
        this.setSize(1.4F, 2.0F);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire=true;
	   this.setFlying(false);
        this.verticalFactor = 0.0F;
	   this.horizontalFactor = 0.0F;
    }

    @Override
    protected void initEntityAI()
    {
		this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.tasks.addTask(0, new EntityAIFly(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, true));
    }

    @Override
    public boolean isAIDisabled()
    {
        return false;
    }

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(PHOENIX_FLAGS, (byte) 0);
	}

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.BLOCK_FIRE_AMBIENT;
    }

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.isFlying()) {
			this.motionY *= 0.6D;
		}

	// rotation of head & body depends of flight status progression.

	   if(Math.abs(this.motionX)<0.3F && Math.abs(this.motionZ)<0.3F && Math.abs(this.motionY)<0.3F) {
		this.horizontalFactor= Math.min(1.0F, this.horizontalFactor+0.1F);
	   } else {
		this.horizontalFactor= Math.max(0.0F, this.horizontalFactor-0.1F);
	   }

	   if ( this.motionY<-0.4F){
		//going down, align head & body
		// ie get horizontal factor to 0 and get a new vertical factor rock ! -> toward -1.0 else toward 1.0
		this.verticalFactor= Math.max(-1.0F, this.verticalFactor-0.2F);
		}else if(this.motionY>0.4F) {
		this.verticalFactor= Math.min(1.0F, this.verticalFactor+0.2F);
		}else {
		//toward 0.0F
		
		if ( this.verticalFactor<0.0F) {
		this.verticalFactor= Math.min(0.0F, this.verticalFactor+0.2F);
		} else {
		this.verticalFactor= Math.max(0.0F, this.verticalFactor-0.2F);
		}

		}

	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		if (!this.isFlying()) {
			this.flightTime = 0;

			if (this.rand.nextInt(50) == 0 && !isLandableBlock(new BlockPos(posX, posY - 1, posZ))) {
				this.setFlying(true);
				this.motionY = 0.4F;
			}			
		} else {
			this.flightTime++;

			if (this.targetPosition != null && (!this.world.isAirBlock(this.targetPosition) || this.targetPosition.getY() < 1)) {
				this.targetPosition = null;
			}

			if ( this.getAttackTarget() !=null) {
				this.targetPosition = new BlockPos((int)this.getAttackTarget().posX, (int)this.getAttackTarget().posY, (int)this.getAttackTarget().posZ);
			}

			if (this.targetPosition == null || this.rand.nextInt(30) == 0 || this.targetPosition.distanceSq((double) ((int) this.posX), (double) ((int) this.posY), (double) ((int) this.posZ)) < 4.0D) {
				int yTarget = this.flightTime < 400 ? 1 : 2;
				this.targetPosition = new BlockPos((int) this.posX + this.rand.nextInt(12) - this.rand.nextInt(12), (int) this.posY + this.rand.nextInt(4) - yTarget, (int) this.posZ + this.rand.nextInt(12) - this.rand.nextInt(12));
			}

			double d0 = (double) this.targetPosition.getX() + 0.5D - this.posX;
			double d1 = (double) this.targetPosition.getY() + 0.1D - this.posY;
			double d2 = (double) this.targetPosition.getZ() + 0.5D - this.posZ;
			this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.1D;
			this.motionY += (Math.signum(d1) * 0.7D - this.motionY) * 0.1D;
			this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.1D;
			float f = (float) (MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
			float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += f1;

			if (this.rand.nextInt(50) == 0 && isLandableBlock(new BlockPos(posX, posY - 1, posZ))) 
			{
				this.setFlying(false);
				this.motionY = 0.0F;
			}
		}
	}

	public boolean isLandableBlock(BlockPos pos) {
	/*	IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block == Blocks.AIR) {
			return false;
		} else {
			return state.isSideSolid(world, pos, EnumFacing.UP);
		}*/

		return this.world.getBlockState(pos).isSideSolid(world, pos, EnumFacing.UP);

	}

	public boolean isFlying() {
		return (dataManager.get(PHOENIX_FLAGS) & 1) != 0;
	}

	public void setFlying(boolean flag) {
		byte b0 = dataManager.get(PHOENIX_FLAGS);

		if (flag) {
			dataManager.set(PHOENIX_FLAGS, (byte) (b0 | 1));
		} else {
			dataManager.set(PHOENIX_FLAGS, (byte) (b0 & -2));
		}
	}


	@Override
	protected void updateFallState(double y, boolean onGroundIn, @Nonnull IBlockState state, @Nonnull BlockPos pos) {
	}

	@Override
	public void fall(float dist, float damageMultiplier) {
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}


/////////// COMBAT ///////////////////

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        boolean success = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 7);

        if (success)
        {
            entity.motionY += 0.2;
            //entity.motionX	// knockback effect
            //entity.motionZ
            entity.setFire(30);
        }
        return success;
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner)
    {
        if (target instanceof EntityCreeper || target instanceof EntityGhast)
        {
            return false;
        }
        return target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target) ? false : !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();

    }

/////////// TAMING ///////////////////

    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        return null;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        return otherAnimal instanceof EntityPhoenix && otherAnimal != this && otherAnimal.getClass() == this.getClass();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);

        if(this.isTamed())
        {
            //healing
            return true;
        }
        else if ( stack!=null && stack.getItem() == Items.FIRE_CHARGE)
        {
            //taming

            if (!player.capabilities.isCreativeMode)
            {
                stack.shrink(1);
            }

            if (!this.world.isRemote)
            {
                if (this.rand.nextInt(8) == 0)
                {
                    this.setTamed(true);
                    this.getNavigator().clearPath();
                    this.setAttackTarget((EntityLiving)null);
                    this.setHealth(40);
                    this.setOwnerId(player.getUniqueID());
                    this.setInLove(player);
                }
            }

            return true;
        }
        return false;

    }


}


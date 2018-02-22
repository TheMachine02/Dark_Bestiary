package com.darkbestiary.event;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.entity.*;
import com.darkbestiary.entity.ai.*;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.ai.*;
import com.github.alexthe666.iceandfire.core.ModItems;

import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.block.BlockChest;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EventLiving {

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) 	{
		if(event.getEntity() != null)
		{
			if(event.getEntity() instanceof EntityFireDragon)
			{
				EntityFireDragon dragon = (EntityFireDragon)event.getEntity();
			dragon.tasks.taskEntries.clear();

		dragon.tasks.addTask(1, new ADVDragonAISaveOwner(dragon,2.0F));
		dragon.tasks.addTask(2, new EntityAISwimming(dragon));
		dragon.tasks.addTask(3, new EntityAISit(dragon));
		dragon.tasks.addTask(4, new ADVDragonAIFollowOwner(dragon,2.0F,16.0D,32.0D));
		dragon.tasks.addTask(5, new EntityAIAttackMelee(dragon, 1.5D, false));
		dragon.tasks.addTask(6, new DragonAIMate(dragon, 1.0D));
		dragon.tasks.addTask(7, new EntityAITempt(dragon, 1.0D, ModItems.fire_stew, false));
		dragon.tasks.addTask(8, new ADVDragonAIStretchWing(dragon));
		dragon.tasks.addTask(9, new ADVDragonAIWanderNear(dragon));
		dragon.tasks.addTask(10, new DragonAIWatchClosest(dragon, EntityLivingBase.class, 6.0F));
		dragon.tasks.addTask(11, new DragonAILookIdle(dragon));
		dragon.tasks.addTask(12, new DragonAIBreakBlocks(dragon));

			}
		}
	}

}


package com.darkbestiary;

import com.darkbestiary.init.DBBlock;
import com.darkbestiary.init.DBItem;
import com.darkbestiary.entity.*;
import com.darkbestiary.client.render.*;
import com.darkbestiary.client.render.entity.*;
import com.darkbestiary.event.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(DBItem.obsidian_shard, 0, new ModelResourceLocation("darkbestiary:obsidian_shard", "inventory"));
	ModelLoader.setCustomModelResourceLocation(DBItem.phoenix_ash, 0, new ModelResourceLocation("darkbestiary:phoenix_ash", "inventory"));

	}


	@SideOnly(Side.CLIENT)
	@Override
	@SuppressWarnings("deprecation")
	public void render() {

	RenderManager manager = Minecraft.getMinecraft().getRenderManager();
		
	RenderingRegistry.registerEntityRenderingHandler(EntityPhoenix.class, new RenderPhoenix(manager));
	RenderingRegistry.registerEntityRenderingHandler(EntityWyvern.class, new RenderWyvern(manager));
	RenderingRegistry.registerEntityRenderingHandler(EntityWillOWisp.class, new RenderWillOWisp(manager));


	}


	public void postRender() {
	}

	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
	private void renderEntities() {
	}

}

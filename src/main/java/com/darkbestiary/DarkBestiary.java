package com.darkbestiary;

import com.darkbestiary.init.DBEntity;
import com.darkbestiary.event.EventLiving;
import com.darkbestiary.DBCreativeTab;
import com.darkbestiary.generator.*;

import net.ilexiconn.llibrary.server.config.Config;
import net.ilexiconn.llibrary.server.network.NetworkWrapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = DarkBestiary.MODID, dependencies = "required-after:llibrary@[" + DarkBestiary.LLIBRARY_VERSION + ",)", version = DarkBestiary.VERSION, name = DarkBestiary.NAME)
public class DarkBestiary {

	public static final String MODID = "darkbestiary";
	public static final String VERSION = "1.0";
	public static final String LLIBRARY_VERSION = "1.7.7";
	public static final String ICEFIRE_VERSION = "1.3.1";
	public static final String DARKBESTIARY_VERSION = "1.0.0";
	public static final String NAME = "Dark Bestiary";
	public static final Logger logger = LogManager.getLogger(NAME);
	@Instance(value = MODID)
	public static DarkBestiary INSTANCE;

	public static SimpleNetworkWrapper NETWORK_WRAPPER;
	@SidedProxy(clientSide = "com.darkbestiary.ClientProxy", serverSide = "com.darkbestiary.CommonProxy")

	public static CommonProxy PROXY;
	public static CreativeTabs TAB;
	public static DarkBestiaryConfig CONFIG;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventLiving());
		DBEntity.init();
		TAB = new DBCreativeTab(MODID);
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		PROXY.render();
		//GameRegistry.registerWorldGenerator(new IWorldDarkBestiary(), 0);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		PROXY.postRender();
	}
}

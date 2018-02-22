package com.darkbestiary;

import com.darkbestiary.DarkBestiary;
import net.ilexiconn.llibrary.server.config.ConfigEntry;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;

@Config(modid = DarkBestiary.MODID)
public class DarkBestiaryConfig {

    public static class Generation
    {
        @Config.Comment("Enable thunder bird nest")
        public final boolean generateThunderBirdNest = true;
        @Config.Comment("Enable wyvern nest")
	   public final boolean generateWyvernNest = true;
	}
	
	public static class Entities {
         @Config.Comment("ThunderBird heath")
         public final int thunderBirdHealth = 40;
	    @Config.Comment("Wyvern heath")
         public final int wyvernHealth = 300;
         @Config.Comment("Phoenix heath")
         public final int phoenixHealth = 60;
     }



    @Mod.EventBusSubscriber(modid = DarkBestiary.MODID)
    private static class Handler
    {
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(DarkBestiary.MODID))
            {
                ConfigManager.sync(DarkBestiary.MODID, Config.Type.INSTANCE);
            }
        }
}
}

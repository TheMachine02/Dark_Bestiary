package com.darkbestiary.init;

import com.darkbestiary.DarkBestiary;
import com.darkbestiary.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DBBlock {

	@GameRegistry.ObjectHolder(DarkBestiary.MODID + ":soul_light")
	public static Block soul_light = new BlockSoulLight();


}

package com.github.TheMachine02.darkbestiary.item;

import com.github.TheMachine02.darkbestiary.DarkBestiary;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import javax.annotation.Nullable;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPhoenixAsh extends Item {

	public ItemPhoenixAsh () {
		this.setMaxStackSize(1);
		this.setCreativeTab(DarkBestiary.TAB);
	this.setUnlocalizedName("darkbestiary.phoenix_ash");
	this.setRegistryName(DarkBestiary.MODID,"darkbestiary.phoenix_ash");
	}


	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		itemStack.setTagCompound(new NBTTagCompound());
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int f, boolean f1) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("Age", 1);
		}
	}


	@Override
	public void addInformation(ItemStack stack0, @Nullable World world0, List list0, ITooltipFlag flag0) {	
	if(stack0.getTagCompound()!=null)
	{
	list0.add("Age "+stack0.getTagCompound().getInteger("Age"));	
	}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.UP) {
			return EnumActionResult.PASS;
		} else {
			ItemStack itemstack = player.getHeldItem(hand);

			if (!player.capabilities.isCreativeMode) {
				itemstack.shrink(1);
				if (itemstack.getCount() <= 0) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				}
			}
			return EnumActionResult.SUCCESS;

		}
}


}

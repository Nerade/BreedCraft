package de.blockschmiede.breedcraft.items;

import de.blockschmiede.breedcraft.BreedCraft;
import de.blockschmiede.breedcraft.CommonProxy;
import de.blockschmiede.breedcraft.entities.BreedCow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BrandingIronHot extends Item {

	public BrandingIronHot(int par1) {
		super(par1);
		this.setIconIndex(6);
		this.setMaxStackSize(1);
		this.setCreativeTab(BreedCraft.breedTab);
		this.setItemName("brandingIronHot");
		this.setMaxDamage(10);
		this.setTextureFile(CommonProxy.ITEMS_PNG);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
			Entity entity) {
		if (!player.worldObj.isRemote) {
			if (entity instanceof EntityCow && !(entity instanceof BreedCow)) {
				BreedCow genCow = new BreedCow(player.worldObj);
				genCow.setPositionAndRotation(entity.lastTickPosX,
						entity.lastTickPosY, entity.lastTickPosZ,
						entity.rotationPitch, entity.rotationYaw);
				player.worldObj.spawnEntityInWorld(genCow);
				//player.worldObj.setEntityDead(entity);
				entity.setDead();
				if (!player.capabilities.isCreativeMode) {
					--stack.stackSize;
					player.inventory.addItemStackToInventory(new ItemStack(
							BreedCraft.brandingIron));
				}
				return false;
			}
		}
		return true;

	}

}

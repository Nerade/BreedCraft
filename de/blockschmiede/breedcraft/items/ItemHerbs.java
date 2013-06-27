package de.blockschmiede.breedcraft.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemHerbs extends ItemBlock {
	
	private final static String[] subNames = {
		"thyme", "valerian", "aloe",
		"fennel", "stJohnsWort", "chamomile", "yarrow"
	};

	public ItemHerbs(int id) {
		super(id);
		setHasSubtypes(true);
		setItemName("herbs");
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	public int getIconFromDamage(int damage){
		return 11+damage;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return getItemName() + "." + subNames[itemstack.getItemDamage()];
	}

}
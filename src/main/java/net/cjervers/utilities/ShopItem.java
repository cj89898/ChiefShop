package net.cjervers.utilities;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;

import net.cjervers.actions.ItemAction;

public class ShopItem {
	
	private ItemType itemType;
	private double cost;
	private int amount;
	private int slot;
	private String damageValue;
	private ItemAction action;
	
	public ShopItem(String itemType, double cost, int amount, int slot, String damageValue, ItemAction action) {
		super();
		if (Sponge.getRegistry().getType(ItemType.class, itemType).isPresent())
			this.itemType = Sponge.getRegistry().getType(ItemType.class, itemType).get();
		else
			this.itemType = Sponge.getRegistry().getType(ItemType.class, "minecraft:diamond_sword").get();
		this.cost = cost;
		this.amount = amount;
		this.slot = slot;
		this.damageValue = damageValue;
		this.action = action;
	}
	
	public ItemType getItemType() {
		return itemType;
	}
	
	public double getCost() {
		return cost;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getDamageValue() {
		return damageValue;
	}
	
	public ItemAction getAction(){
		return action;
	}

	public int getSlot() {
		return slot;
	}
}

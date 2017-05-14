package net.cjervers.utilities;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;

public class ShopItem {
	
	private ItemType itemType;
	private double cost;
	private int amount;
	private String damageValue;
	
	public ShopItem(String itemType, double cost, int amount, String damageValue) {
		super();
		this.itemType = Sponge.getRegistry().getType(ItemType.class, itemType).get();
		this.cost = cost;
		this.amount = amount;
		this.damageValue = damageValue;
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
	
}

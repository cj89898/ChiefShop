package net.cjervers.utilities;

public class ShopItem {
	
	private String itemType;
	private double cost;
	private int amount;
	private String damageValue;
	
	public ShopItem(String itemType, double cost, int amount, String damageValue) {
		super();
		this.itemType = itemType;
		this.cost = cost;
		this.amount = amount;
		this.damageValue = damageValue;
	}
	
	public String getItemType() {
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

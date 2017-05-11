package net.cjervers.utilities;

import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

import net.cjervers.ChiefShop;
import net.cjervers.utilities.Utils;

public class Shop {
	
	private String name;
	private Inventory inv;
	private double cost;
	
	public Shop(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public void refresh() {
		inv = Utils.getShopBuilder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(name)))
				.build(ChiefShop.getPlugin());
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}
	
	public Inventory getInv() {
		return inv;
	}
}

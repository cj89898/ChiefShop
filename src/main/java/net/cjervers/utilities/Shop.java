package net.cjervers.utilities;

import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

import net.cjervers.ChiefShop;
import net.cjervers.utilities.Utils;



public class Shop {
	
	private String name;
	private Inventory inv;
	
	public Shop(String name) {
		this.name = name;
	}
	
	public void refresh() {
		inv = Utils.getShopBuilder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(name)))
				.build(ChiefShop.getPlugin());
	}

	
	public String getName() {
		return name;
	}

	
	public Inventory getInv() {
		return inv;
	}
}

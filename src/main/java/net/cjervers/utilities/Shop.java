package net.cjervers.utilities;

import java.util.List;

import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

import net.cjervers.ChiefShop;

public class Shop {
	
	private String name;
	private Inventory inv;
	private List<ShopItem> shopItems;
	
	public Shop(String name, List<ShopItem> shopItems) {
		this.name = name;
		this.shopItems = shopItems;
	}
	
	public void refresh() {
		inv = Utils.getShopBuilder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(name)))
				.build(ChiefShop.getPlugin());
		for (ShopItem item : shopItems) {
			inv.offer(ItemStack.builder().itemType(item.getItemType()).build());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public List<ShopItem> getShopItems() {
		return shopItems;
	}
	
	public Inventory getInv() {
		return inv;
	}
}

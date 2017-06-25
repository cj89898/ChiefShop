package net.cjervers.utilities;

import java.util.List;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.mutable.item.EnchantmentData;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

import net.cjervers.ChiefShop;

public class Shop {
	
	private String name;
	private Inventory inv;
	private List<ShopItem> shopItems;
	private boolean permRequired;
	
	public Shop(String name, List<ShopItem> shopItems, boolean needsPerm) {
		this.name = name;
		this.shopItems = shopItems;
		this.permRequired = needsPerm;
		refresh();
	}
	
	public void refresh() {
		ShopInventoryCarrier carrier = new ShopInventoryCarrier();
		inv = Utils.getShopBuilder().property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(name)))
				.withCarrier(carrier).build(ChiefShop.getPlugin());
		carrier.setInventory(inv);
		for (ShopItem item : shopItems) {
			int quantity;
			if (item.getAmount() > item.getItemType().getMaxStackQuantity()) {
				quantity = item.getItemType().getMaxStackQuantity();
			} else {
				quantity = item.getAmount();
			}
			ItemStack stack = ItemStack.builder().itemType(item.getItemType()).quantity(quantity).build();
			stack.offer(Keys.ITEM_LORE, item.getLore());
			stack.offer(Keys.DISPLAY_NAME, item.getName());
			ChiefShop.getPlugin().getLogger().warn(item.getEnchantmentData()+"");
			if (item.getEnchantmentData() != null)
				stack.offer(item.getEnchantmentData());
			inv.offer(stack);
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
	
	public boolean permRequired() {
		return permRequired;
	}
}

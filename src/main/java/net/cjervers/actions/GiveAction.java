package net.cjervers.actions;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.Hotbar;
import org.spongepowered.api.item.inventory.transaction.InventoryTransactionResult;
import org.spongepowered.api.item.inventory.type.GridInventory;

import net.cjervers.utilities.ShopItem;

public class GiveAction extends ItemAction {
	
	private ItemStack item;
	
	public GiveAction(ShopItem shopItem) {
		this.item = ItemStack.builder().itemType(shopItem.getItemType()).quantity(shopItem.getAmount()).build();
	}
	
	public GiveAction(ItemStack item) {
		this.item = item;
	}
	
	public InventoryTransactionResult giveItem(Player p) {
		Inventory pInv = p.getInventory().query(GridInventory.class, Hotbar.class);
		return pInv.offer(item);
	}
	
}

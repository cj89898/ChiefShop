package net.cjervers.actions;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;

import net.cjervers.utilities.ShopItem;

public class GiveAction extends ItemAction {
	
	private ItemStack item;
	
	public GiveAction(ShopItem shopItem) {
		this.item = ItemStack.builder().itemType(shopItem.getItemType()).quantity(shopItem.getAmount()).build();
	}
	
	public GiveAction(ItemStack item) {
		this.item = item;
	}
	
	public void giveItem(Player p) {
		p.getInventory().offer(item);
	}
	
}

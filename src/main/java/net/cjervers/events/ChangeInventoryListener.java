package net.cjervers.events;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

import net.cjervers.actions.GiveAction;
import net.cjervers.utilities.ShopInventoryCarrier;
import net.cjervers.utilities.ShopItem;
import net.cjervers.utilities.Utils;

public class ChangeInventoryListener {
	
	@Listener
	public void onChangeInventoryEvent(ChangeInventoryEvent event,
			@Getter("getTargetInventory") CarriedInventory<?> result) {
		CarriedInventory<?> inv = (CarriedInventory<?>) event.getTargetInventory();
		if (inv.getCarrier().isPresent() && inv.getCarrier().get() instanceof ShopInventoryCarrier) {
			event.setCancelled(true);
			String name = inv.getName().get();
			for (SlotTransaction cur : event.getTransactions()) {
				for (ShopItem item : Utils.getShops().get(name).getShopItems()) {
					if (item.getSlot() == cur.getSlot().getProperty(SlotIndex.class, "slotindex").get().getValue()) {
						if (event.getCause().first(Player.class).isPresent())
							new GiveAction(item).giveItem(event.getCause().first(Player.class).get());
					}
				}
			}
		}
		
	}
}

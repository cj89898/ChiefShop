package net.cjervers.events;

import java.math.BigDecimal;
import java.util.Optional;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import net.cjervers.ChiefShop;
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
			Player player = event.getCause().first(Player.class).get();
			Optional<UniqueAccount> uOpt = ChiefShop.getPlugin().getEconomy().getOrCreateAccount(player.getUniqueId());
			UniqueAccount acc;
			double balance;
			if (uOpt.isPresent()) {
				acc = uOpt.get();
				balance = acc.getBalance(ChiefShop.getPlugin().getEconomy().getDefaultCurrency()).doubleValue();
			} else {
				acc = null;
				balance = 0;
			}
			for (SlotTransaction cur : event.getTransactions()) {
				for (ShopItem item : Utils.getShops().get(name).getShopItems()) {
					if (item.getSlot() == cur.getSlot().getProperty(SlotIndex.class, "slotindex").get().getValue()) {
						if (event.getCause().first(Player.class).isPresent())
							if (balance >= item.getCost()) {
								acc.withdraw(ChiefShop.getPlugin().getEconomy().getDefaultCurrency(),
										BigDecimal.valueOf(item.getCost()), Cause.source(this).build());
								new GiveAction(item).giveItem(player);
							} else {
								player.sendMessage(Text.builder("Not enough money!").color(TextColors.RED).build());
							}
					}
				}
			}
		}
		
	}
}

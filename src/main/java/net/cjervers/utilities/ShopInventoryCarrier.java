package net.cjervers.utilities;

import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

public class ShopInventoryCarrier implements Carrier {
	
	private Inventory inventory;
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public CarriedInventory<? extends Carrier> getInventory() {
		return ((CarriedInventory<? extends Carrier>) this.inventory);
	}
	
}

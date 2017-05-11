package net.cjervers.utilities;

import java.util.Map;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.property.InventoryDimension;

import net.cjervers.ChiefShop;
import net.cjervers.utilities.Shop;
import ninja.leaping.configurate.ConfigurationNode;

public class Utils {
	
	private Map<String, Shop> shops;
	private static final ChiefShop plugin = ChiefShop.getPlugin();
	private static ConfigurationNode conf = plugin.getConfig();
	
	public static Inventory.Builder getShopBuilder() {
		return Inventory.builder().of(InventoryArchetypes.CHEST).property(InventoryDimension.PROPERTY_NAM,
				new InventoryDimension(9, 4));
	}
	
	public static void createShops() {
		plugin.getLogger().warn(conf.getNode("").toString());
	}
	
	public static void openShop(Player player, Shop shop) {
		
	}
	
}

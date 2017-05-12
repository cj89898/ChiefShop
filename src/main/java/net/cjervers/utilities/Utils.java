package net.cjervers.utilities;

import java.util.Map;
import java.util.Map.Entry;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.property.InventoryDimension;

import com.google.common.reflect.TypeToken;

import net.cjervers.ChiefShop;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class Utils {
	
	private Map<String, Shop> shops;
	private static final ChiefShop plugin = ChiefShop.getPlugin();
	private static ConfigurationNode conf = plugin.getConfig();
	
	public static Inventory.Builder getShopBuilder() {
		return Inventory.builder().of(InventoryArchetypes.CHEST).property(InventoryDimension.PROPERTY_NAME,
				new InventoryDimension(9, 4));
	}
	
	public static void createShops() {
		try {
			for (Entry<Object, ? extends ConfigurationNode> entry : conf.getNode("shops").getChildrenMap().entrySet()) {
				//for (Entry<Object, ? extends ConfigurationNode> entry2 : entry.getValue().getNode("items")
						//.getChildrenMap().entrySet()) {
					plugin.getLogger().info(entry.getValue().getNode("items").getChildrenMap().toString());
					Shop shop = entry.getValue().getValue(TypeToken.of(Shop.class));
					plugin.getLogger().info(shop.getName() + " " + shop.getShopItems());
				//}
			}
		} catch (ObjectMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openShop(Player player, Shop shop) {
		
	}
	
}

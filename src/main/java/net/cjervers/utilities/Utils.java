package net.cjervers.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.property.InventoryDimension;

import com.google.common.reflect.TypeToken;

import net.cjervers.ChiefShop;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class Utils {
	
	private static Map<String, Shop> shops = new HashMap<String, Shop>();
	private static final ChiefShop plugin = ChiefShop.getPlugin();
	private static ConfigurationNode conf = plugin.getConfig();
	
	public static Inventory.Builder getShopBuilder() {
		return Inventory.builder().of(InventoryArchetypes.CHEST).property(InventoryDimension.PROPERTY_NAME,
				new InventoryDimension(9, 4));
	}
	
	public static void createShops() {
		try {
			for (Entry<Object, ? extends ConfigurationNode> entry : conf.getNode("shops").getChildrenMap().entrySet()) {
				Shop shop = entry.getValue().getValue(TypeToken.of(Shop.class));
				shops.put(shop.getName(), shop);
			}
		} catch (ObjectMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openShop(Player player, String shopName) {
		if(shops.containsKey(shopName)){
			Shop shop = shops.get(shopName);
			player.openInventory(shop.getInv(), Cause.of(NamedCause.owner(plugin), NamedCause.source(player)));
		}
	}

	public static Map<String, Shop> getShops() {
		return shops;
	}
	
}

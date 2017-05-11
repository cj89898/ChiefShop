package net.cjervers.utilities;

import java.util.Map;

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
		return Inventory.builder().of(InventoryArchetypes.CHEST).property(InventoryDimension.PROPERTY_NAM,
				new InventoryDimension(9, 4));
	}
	
	public static void createShops() {
		try {
			for (ConfigurationNode shopNode : conf.getNode("shops").getChildrenList()) {
				Shop shop = shopNode.getValue(TypeToken.of(Shop.class));
				plugin.getLogger().info(shop.getName());
			}
		} catch (ObjectMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openShop(Player player, Shop shop) {
		
	}
	
}

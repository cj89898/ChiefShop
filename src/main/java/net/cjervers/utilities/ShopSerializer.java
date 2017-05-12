package net.cjervers.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.reflect.TypeToken;

import net.cjervers.ChiefShop;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

public class ShopSerializer implements TypeSerializer<Shop> {
	
	@Override
	public Shop deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
		String name = value.getKey().toString();
		List<ShopItem> shopItems = new ArrayList<>();
		for (Entry<Object, ? extends ConfigurationNode> item : value.getNode("items").getChildrenMap().entrySet()) {
			shopItems.add(item.getValue().getNode("items").getValue(TypeToken.of(ShopItem.class)));
			ChiefShop.getPlugin().getLogger().info(item.getValue().toString());
		}
		return new Shop(name, shopItems);
	}
	
	@Override
	public void serialize(TypeToken<?> type, Shop shop, ConfigurationNode value) throws ObjectMappingException {
	}
	
}

package net.cjervers.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

public class ShopSerializer implements TypeSerializer<Shop> {
	
	@Override
	public Shop deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
		String name = value.getKey().toString();
		List<ShopItem> shopItems = new ArrayList<>();
		for (Entry<Object, ? extends ConfigurationNode> item : value.getNode("stacks").getChildrenMap().entrySet()) {
			shopItems.add(item.getValue().getValue(TypeToken.of(ShopItem.class)));
		}
		return new Shop(name, shopItems);
	}
	
	@Override
	public void serialize(TypeToken<?> type, Shop shop, ConfigurationNode value) throws ObjectMappingException {
	}
	
}

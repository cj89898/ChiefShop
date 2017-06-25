package net.cjervers.utilities;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.item.ItemTypes;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

public class ShopSerializer implements TypeSerializer<Shop> {
	
	@Override
	public Shop deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
		String name = value.getKey().toString();
		boolean needsPerm = value.getNode("permission-required").isVirtual() ? false
				: value.getNode("permission-required").getBoolean();
		List<ShopItem> shopItems = new ArrayList<>();
		for (ShopItem item : value.getNode("items").getList(TypeToken.of(ShopItem.class))) {
			if (!item.getItemType().equals(ItemTypes.AIR))
				shopItems.add(item);
		}
		return new Shop(name, shopItems, needsPerm);
	}
	
	@Override
	public void serialize(TypeToken<?> type, Shop shop, ConfigurationNode value) throws ObjectMappingException {
	}
	
}

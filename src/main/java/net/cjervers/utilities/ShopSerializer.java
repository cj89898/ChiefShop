package net.cjervers.utilities;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;

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
		for (ShopItem item : value.getNode("items").getList(TypeToken.of(ShopItem.class))) {
			if (!item.getItemType().equals(Sponge.getRegistry().getType(ItemType.class, "minecraft:air")))
				shopItems.add(item);
		}
		return new Shop(name, shopItems);
	}
	
	@Override
	public void serialize(TypeToken<?> type, Shop shop, ConfigurationNode value) throws ObjectMappingException {
	}
	
}

package net.cjervers.utilities;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

public class ShopSerializer implements TypeSerializer<Shop>{

	@Override
	public Shop deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
		String name = value.getString();
		double cost = value.getNode("cost").getDouble();
		return new Shop(name, cost);
	}

	@Override
	public void serialize(TypeToken<?> type, Shop shop, ConfigurationNode value) throws ObjectMappingException {
	}
	
}

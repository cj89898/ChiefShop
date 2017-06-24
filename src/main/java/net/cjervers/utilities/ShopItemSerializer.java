package net.cjervers.utilities;

import com.google.common.reflect.TypeToken;

import net.cjervers.actions.ItemAction;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

public class ShopItemSerializer implements TypeSerializer<ShopItem> {
	
	@Override
	public ShopItem deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
		String itemType = value.getNode("ItemType").getString();
		double cost = value.getNode("Cost").getDouble();
		int amount = value.getNode("Amount").getInt();
		int slot = value.getNode("Slot").getInt();
		String damageValue = value.getNode("Data").getNode("Damage").getString();
		ItemAction action = value.getNode("Data").getNode("Action").getValue(TypeToken.of(ItemAction.class));
		return new ShopItem(itemType, cost, amount, slot, damageValue, action);
	}
	
	@Override
	public void serialize(TypeToken<?> type, ShopItem item, ConfigurationNode value) throws ObjectMappingException {
		value.getNode("ItemType").setValue(item.getItemType());
		value.getNode("Cost").setValue(item.getCost());
		value.getNode("Amount").setValue(item.getAmount());
		value.getNode("Damage").setValue(item.getDamageValue());
	}
	
}

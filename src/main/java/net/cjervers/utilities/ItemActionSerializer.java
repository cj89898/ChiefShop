package net.cjervers.utilities;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;

import com.google.common.reflect.TypeToken;

import net.cjervers.actions.GiveAction;
import net.cjervers.actions.ItemAction;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;

public class ItemActionSerializer implements TypeSerializer<ItemAction> {
	
	@Override
	public ItemAction deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
		if (value.getNode("Action").getString().equals("give")) {
			ItemType itemType = Sponge.getRegistry()
					.getType(ItemType.class, value.getParent().getNode("ItemType").getString()).get();
			int amount = value.getParent().getNode("Amount").getInt();
			ItemStack item = ItemStack.builder().itemType(itemType).quantity(amount).build();
			return new GiveAction(item);
		}
		return null;
	}
	
	@Override
	public void serialize(TypeToken<?> type, ItemAction action, ConfigurationNode value) throws ObjectMappingException {
		// TODO Auto-generated method stub
		
	}
	
}

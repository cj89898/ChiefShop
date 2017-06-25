package net.cjervers.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.Enchantment;

import com.google.common.reflect.TypeToken;

import net.cjervers.ChiefShop;
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
		
		Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
		
		ChiefShop.getPlugin().getLogger().warn(value.getNode("Data").getNode("Enchantments").getChildrenList().toString());
		for (ConfigurationNode enchantmentInfo : value.getNode("Data").getNode("Enchantments").getChildrenList()) {
			
			ChiefShop.getPlugin().getLogger().warn(enchantmentInfo.getKey().toString()+" level: "+enchantmentInfo.getInt());
			if (Sponge.getRegistry().getType(Enchantment.class, enchantmentInfo.getKey().toString()).isPresent()) {
				Enchantment enchantment = Sponge.getRegistry()
						.getType(Enchantment.class, enchantmentInfo.getKey().toString()).get();
				enchantments.put(enchantment, enchantmentInfo.getInt());
			}
		}
		return new ShopItem(itemType, cost, amount, slot, damageValue, action, enchantments);
	}
	
	@Override
	public void serialize(TypeToken<?> type, ShopItem item, ConfigurationNode value) throws ObjectMappingException {
		value.getNode("ItemType").setValue(item.getItemType());
		value.getNode("Cost").setValue(item.getCost());
		value.getNode("Amount").setValue(item.getAmount());
		value.getNode("Damage").setValue(item.getDamageValue());
	}
	
}

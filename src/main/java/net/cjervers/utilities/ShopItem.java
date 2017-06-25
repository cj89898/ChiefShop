package net.cjervers.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.manipulator.mutable.item.EnchantmentData;
import org.spongepowered.api.data.meta.ItemEnchantment;
import org.spongepowered.api.item.Enchantment;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import net.cjervers.ChiefShop;
import net.cjervers.actions.ItemAction;

public class ShopItem {
	
	private ItemType itemType;
	private double cost;
	private int amount;
	private int slot;
	private String damageValue;
	private ItemAction action;
	private List<Text> lore = new ArrayList<Text>();
	private Text name;
	private EnchantmentData enchantmentData;
	
	public ShopItem(String itemType, double cost, int amount, int slot, String damageValue, ItemAction action,
			Map<Enchantment, Integer> enchantments) {
		super();
		if (Sponge.getRegistry().getType(ItemType.class, itemType).isPresent())
			this.itemType = Sponge.getRegistry().getType(ItemType.class, itemType).get();
		else {
			this.itemType = Sponge.getRegistry().getType(ItemType.class, "minecraft:air").get();
			ChiefShop.getPlugin().getLogger().warn(itemType + " is not a valid type!");
		}
		this.cost = cost;
		this.amount = amount;
		this.slot = slot;
		this.damageValue = damageValue;
		this.action = action;
		ItemStack stack = ItemStack.of(this.itemType, 1);
		if (amount != 1)
			name = Text.builder(amount + " ").color(TextColors.GOLD).append(Text.builder(stack.getTranslation())
					.color(TextColors.AQUA).append(Text.of("s")).color(TextColors.AQUA).build()).build();
		else
			name = Text.builder(amount + " ").color(TextColors.GOLD)
					.append(Text.builder(stack.getTranslation()).color(TextColors.AQUA).build()).build();
		lore.add(Text.builder("Costs: ").color(TextColors.GOLD)
				.append(Text.builder(cost + "").color(TextColors.GREEN).build()).build());
		
		for (Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
			enchantmentData.set(enchantmentData.enchantments()
					.add(new ItemEnchantment(enchantment.getKey(), enchantment.getValue())));
		}
		
	}
	
	public ItemType getItemType() {
		return itemType;
	}
	
	public double getCost() {
		return cost;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getDamageValue() {
		return damageValue;
	}
	
	public ItemAction getAction() {
		return action;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public Text getName() {
		return name;
	}
	
	public List<Text> getLore() {
		return lore;
	}
	
	public EnchantmentData getEnchantmentData() {
		return enchantmentData;
	}
}

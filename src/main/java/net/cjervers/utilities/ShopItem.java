package net.cjervers.utilities;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

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
	
	public ShopItem(String itemType, double cost, int amount, int slot, String damageValue, ItemAction action) {
		super();
		if (Sponge.getRegistry().getType(ItemType.class, itemType).isPresent())
			this.itemType = Sponge.getRegistry().getType(ItemType.class, itemType).get();
		else
			this.itemType = Sponge.getRegistry().getType(ItemType.class, "minecraft:air").get();
		this.cost = cost;
		this.amount = amount;
		this.slot = slot;
		this.damageValue = damageValue;
		this.action = action;
		if (amount != 1)
			name = Text.builder(amount + " ").color(TextColors.GOLD)
					.append(Text.builder(itemType.toString() + "s").color(TextColors.AQUA).build()).build();
		else
			name = Text.builder(amount + " ").color(TextColors.GOLD)
					.append(Text.builder(itemType.toString()).color(TextColors.AQUA).build()).build();
		lore.add(Text.builder("Costs: ").color(TextColors.GOLD)
				.append(Text.builder(cost + "").color(TextColors.GREEN).build()).build());
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
}

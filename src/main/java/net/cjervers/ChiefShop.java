package net.cjervers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.text.Text;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;

import net.cjervers.commands.ShopCommand;
import net.cjervers.events.ChangeInventoryListener;
import net.cjervers.utilities.Shop;
import net.cjervers.utilities.ShopItem;
import net.cjervers.utilities.ShopItemSerializer;
import net.cjervers.utilities.ShopSerializer;
import net.cjervers.utilities.Utils;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;

@Plugin(id = "chiefshop", name = "Chief Shop", version = "1.0.0")
public class ChiefShop {
	
	private static ChiefShop plugin;
	private static EconomyService econ;
	
	@Inject
	private Logger logger;
	
	public static ChiefShop getPlugin() {
		return plugin;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public EconomyService getEconomy() {
		return econ;
	}
	
	@Listener
	public void init(GameInitializationEvent event) {
		plugin = this;
		
		Optional<EconomyService> serviceOpt = Sponge.getServiceManager().provide(EconomyService.class);
		if (!serviceOpt.isPresent()) {
			getLogger().warn("No economy service found! ChiefShop will not work correctly!");
			
		} else {
			econ = serviceOpt.get();
		}
		
		createConfig();
		TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(Shop.class), new ShopSerializer());
		TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(ShopItem.class), new ShopItemSerializer());
		Utils.createShops();
		
		Sponge.getEventManager().registerListeners(this, new ChangeInventoryListener());
		
		CommandSpec shop = CommandSpec.builder().description(Text.of("Opens the shop")).executor(new ShopCommand())
				.build();
		
		Sponge.getCommandManager().register(this, shop, "chiefshop", "shop");
	}
	
	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;
	private ConfigurationLoader<ConfigurationNode> confLoader;
	private ConfigurationNode conf;
	
	public void createConfig() {
		Path configFile = configDir.resolve("shops.json");
		if (!Files.exists(configFile)) {
			try {
				Files.createDirectories(configDir);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Sponge.getAssetManager().getAsset(this, "shops.json").get().copyToFile(configFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.confLoader = GsonConfigurationLoader.builder().setPath(configFile).build();
		conf = confLoader.createEmptyNode(ConfigurationOptions.defaults());
		loadConfig();
		saveConfig();
	}
	
	public ConfigurationNode getConfig() {
		return conf;
	}
	
	public void saveConfig() {
		try {
			confLoader.save(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		try {
			conf = confLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

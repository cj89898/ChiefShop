package main.java.net.cjervers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "chiefshop", name = "Chief Shop", version = "1.0.0")
public class ChiefShop {
	
	private static ChiefShop plugin;
	
	@Inject
	private static Logger logger;
	
	public static ChiefShop getPlugin() {
		return plugin;
	}
	
	public static Logger getLogger() {
		return logger;
	}
	
	@Listener
	public void init(GameInitializationEvent event) {
		plugin = this;
		createConfig();
	}
	
	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;
	private ConfigurationLoader<CommentedConfigurationNode> confLoader;
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
		this.confLoader = HoconConfigurationLoader.builder().setPath(configFile).build();
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

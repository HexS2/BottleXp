package fr.ctrl.bottle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		getCommand("bottlexp").setExecutor(new BottleCommands());
		Bukkit.getPluginManager().registerEvents(new BottleListener(), this);
	}
}

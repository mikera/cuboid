package net.mikera.cuboid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("Cuboid plugin enabled");
	}

	@Override
	public void onDisable() {
		getLogger().info("Cuboid plugin disabled");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cuboid")) { // If the player typed /basic then do the following, note: If you only registered this executor for one command, you don't need this
			sender.sendMessage("Hello from Cuboid.");
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}

}

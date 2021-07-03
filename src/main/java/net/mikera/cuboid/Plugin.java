package net.mikera.cuboid;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import convex.api.Convex;
import convex.core.Result;
import convex.core.util.Utils;

public class Plugin extends JavaPlugin {
	public Convex convex;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(), this);
		Items.init();
		getLogger().info("Cuboid plugin enabled");
		
		Bukkit.getScheduler().runTaskAsynchronously(this, ()->{
			try {
				InetSocketAddress addr=Utils.toInetSocketAddress("convex.world:43579");
				convex=Convex.connect(addr);
			} catch (IOException | TimeoutException e) {
				//
			}
		});
	}

	@Override
	public void onDisable() {
		getLogger().info("Cuboid plugin disabled");
		
		if (convex!=null) {
			convex.close();
			convex=null;
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cuboid")) {
			
			Bukkit.getScheduler().runTaskAsynchronously(this, ()->{
				if (convex==null) sender.sendMessage("No Convex Connection.");
				try {
					Result r=convex.query(null).get(1000, TimeUnit.MILLISECONDS);
					sender.sendMessage("Query result: "+r);
				} catch (InterruptedException | ExecutionException | TimeoutException | IOException e) {
					sender.sendMessage("Query failed: "+e.getMessage());
				}
			});
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}

}

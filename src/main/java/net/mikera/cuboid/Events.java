package net.mikera.cuboid;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;

public class Events implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) {
			return;
		}
		
		if (e.getClickedInventory().getHolder() instanceof SelectionScreen) {
			e.setCancelled(true);
			Player player = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE) {
				player.sendMessage(ChatColor.GREEN + "You have selected Yes");
				player.closeInventory();
			} else if (e.getSlot() == 4) {
				player.sendMessage(ChatColor.AQUA + "Please make a selection...");
			} else if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
				player.sendMessage(ChatColor.RED + "You have selected No");
				player.closeInventory();
			}
		}
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		Action act=event.getAction();
		if ((act == Action.RIGHT_CLICK_AIR)||(act==Action.RIGHT_CLICK_BLOCK)) {
			if (event.getItem() != null) {
				if (event.getItem().getItemMeta().equals(Items.convexWand.getItemMeta())) {
					Player player = event.getPlayer();
					player.openInventory(new SelectionScreen().getInventory());
					event.setCancelled(true);
				}
			}
		}
	}
}

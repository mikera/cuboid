package net.mikera.cuboid;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	public static ItemStack convexWand;

	public static void init() {
		createWand();
	}

	private static void createWand() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§bConvex Wand");
		List<String> lore = new ArrayList<>();
		lore.add("§7Empowers the user to access");
		lore.add("§7a secret magical space");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		convexWand = item;
		
        //Shapeless Recipe
        ShapelessRecipe sir = new ShapelessRecipe(NamespacedKey.minecraft("convex_wand_shapeless"), item);
        sir.addIngredient(2, Material.STICK);
        Bukkit.getServer().addRecipe(sir);
	}
}

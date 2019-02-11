package com.valkutils.modules;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.valkutils.ValkUtils;
import com.vinkaitems.VinkaItems;

public class ItemModule {
	public static List<Inventory> translatedHandRecipes = new ArrayList<Inventory>();
	public static List<ItemStack> handRecipeItems = new ArrayList<ItemStack>();
	public List<Inventory> handNavigation = new ArrayList<Inventory>();
	
	public void handRecipeHomeInv() {
		Inventory inv = null;
		final int breakEach = 45;
		for (int i = 0; i < handRecipeItems.size(); i++) {
			if (i % breakEach == 0) {
				inv = Bukkit.createInventory(null, 9 * 6, "Hand Recipe Navigation");
				inv.setItem(45, ItemModule.item("Previous", "Flip a page.", Material.BLACK_STAINED_GLASS_PANE));
				inv.setItem(49, ItemModule.item("Back", "Go back a page.", Material.BLACK_STAINED_GLASS_PANE));
				inv.setItem(53, ItemModule.item("Next", "Flip a page.", Material.BLACK_STAINED_GLASS_PANE));
				handNavigation.add(inv);
			}
			inv.setItem(i % breakEach, handRecipeItems.get(i));
		}
	}
	
	public static void translateHandRecipe(ItemStack result, ItemStack[] required, String shape, String ids) {
		Inventory recipe = Bukkit.createInventory(null, 36, "Hand Recipe Guide");
		String[] the_ids = ids.split(",");
		for (int i = 0; i < the_ids.length; i++) {
			for (int n = 0; n < shape.length(); n++) {
				char c = shape.charAt(n);
				if (c == the_ids[i].charAt(0)) {
					if (n > 1) {
						recipe.setItem(n + 7, required[i]);
					} else {
						recipe.setItem(n, required[i]);
					}
				}
			}
		}
		recipe.setItem(2, ItemModule.item("--->", "--->", Material.BLACK_STAINED_GLASS_PANE));
		recipe.setItem(3, result);
		recipe.setItem(18 + 9, ItemModule.item("Previous", "Flip a page.", Material.BLACK_STAINED_GLASS_PANE));
		recipe.setItem(18 + 9 + 4, ItemModule.item("Back", "Go back a page.", Material.BLACK_STAINED_GLASS_PANE));
		recipe.setItem(26 + 9, ItemModule.item("Next", "Flip a page.", Material.BLACK_STAINED_GLASS_PANE));
		translatedHandRecipes.add(recipe);
	}
	
	public static void craftedRecipe(String key, ItemStack item, String rows, ItemStack[] ingredients, String ids) {
		NamespacedKey namespacedKey = new NamespacedKey(ValkUtils.plugin, key);
		ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
		String row1 = rows.substring(0, 3);
		String row2 = rows.substring(3, 6);
		String row3 = rows.substring(6, 9);
		recipe.shape(row1, row2, row3);
		
		String[] the_ids = ids.split(",");
		
		for (int i = 0; i < ingredients.length; i++) {
			recipe.setIngredient(the_ids[i].charAt(0), ingredients[i].getType());
		}
		ValkUtils.plugin.getServer().addRecipe(recipe);
		ValkUtils.plugin.recipes.add(new NamespacedKey(ValkUtils.plugin, key));
	}
	
	public static void handRecipe(String key, ItemStack item, String rows, ItemStack[] ingredients, String ids) {
		NamespacedKey namespacedKey = new NamespacedKey(ValkUtils.plugin, key);
		ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
		String row1 = rows.substring(0, 2);
		String row2 = rows.substring(2, 4);
		recipe.shape(row1, row2);
		
		String[] the_ids = ids.split(",");
		
		for (int i = 0; i < ingredients.length; i++) {
			recipe.setIngredient(the_ids[i].charAt(0), ingredients[i].getType());
		}
		ValkUtils.plugin.getServer().addRecipe(recipe);
		ValkUtils.plugin.recipes.add(new NamespacedKey(ValkUtils.plugin, key));
		translateHandRecipe(item, ingredients, rows, ids);
		handRecipeItems.add(item);
	}
	
	/*
	 * Time is in seconds.
	 */
	public static void furnaceRecipe(String key, ItemStack result, Material required, int time) {
		NamespacedKey nameSpacedKey = new NamespacedKey(ValkUtils.plugin, key);
		FurnaceRecipe recipe = new FurnaceRecipe(nameSpacedKey, result, required, 0f, time * 20);
		ValkUtils.plugin.getServer().addRecipe(recipe);
		ValkUtils.plugin.recipes.add(new NamespacedKey(ValkUtils.plugin, key));
	}
	
	public static void shapelessRecipe(String key, ItemStack item, ItemStack[] ingredients) {
		NamespacedKey namespacedKey = new NamespacedKey(ValkUtils.plugin, key);
		ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, item);
		for (ItemStack ingredient : ingredients) {
			recipe.addIngredient(ingredient.getType());
		}
		ValkUtils.plugin.getServer().addRecipe(recipe);
	}
	
	public static ItemStack item(String name, String lore, Material material) {
		ItemStack item = new ItemStack(material);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + TextModule.color(name));
		im.addItemFlags(ItemFlag.values());
		List<String> list = new ArrayList<String>();
		for (String element : lore.split("\n")) {
			list.add(ChatColor.GRAY + TextModule.color(element));
		}
		im.setLore(list);
		item.setItemMeta(im);
		VinkaItems.items.add(item);
		return item;
	}
	
	public static ItemStack tool(String name, String lore, Material material) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + TextModule.color(name));
		im.addItemFlags(ItemFlag.values());
		List<String> list = new ArrayList<String>();
		for (String element : lore.split("\n")) {
			list.add(ChatColor.GRAY + TextModule.color(element));
		}
		im.setLore(list);
		item.setItemMeta(im);
		VinkaItems.items.add(item);
		return item;
	}
}

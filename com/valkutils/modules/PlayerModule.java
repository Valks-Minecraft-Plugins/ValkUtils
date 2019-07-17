package com.valkutils.modules;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerModule {
	
	public static void addPotionEffect(Player p, PotionEffectType type, int duration, int level) {
		if (!p.hasPotionEffect(type)) {
			p.addPotionEffect(new PotionEffect(type, duration, level));
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void fillPlayerHealth(Player p) {
		p.setHealth(p.getMaxHealth());
	}
	
	public static void fillPlayerFood(Player p) {
		p.setFoodLevel(20);
	}

	public static boolean inSurvival(Player p) {
		return p.getGameMode() == GameMode.SURVIVAL;
	}
	
	public static void removeAllPotionEffects(Player p) {
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
	}

	public static boolean isAxe(Material tool) {
		switch (tool) {
		case WOODEN_AXE:
		case STONE_AXE:
		case IRON_AXE:
		case GOLDEN_AXE:
		case DIAMOND_AXE:
			return true;
		default:
			return false;
		}
	}

	public static boolean isPickaxe(Material tool) {
		switch (tool) {
		case WOODEN_PICKAXE:
		case STONE_PICKAXE:
		case IRON_PICKAXE:
		case GOLDEN_PICKAXE:
		case DIAMOND_PICKAXE:
			return true;
		default:
			return false;
		}
	}

	public static boolean isHoe(Material tool) {
		switch (tool) {
		case WOODEN_HOE:
		case STONE_HOE:
		case IRON_HOE:
		case GOLDEN_HOE:
		case DIAMOND_HOE:
			return true;
		default:
			return false;
		}
	}

	public static boolean isShovel(Material tool) {
		switch (tool) {
		case WOODEN_SHOVEL:
		case STONE_SHOVEL:
		case IRON_SHOVEL:
		case GOLDEN_SHOVEL:
		case DIAMOND_SHOVEL:
			return true;
		default:
			return false;
		}
	}

	public static boolean isSword(Material tool) {
		switch (tool) {
		case WOODEN_SWORD:
		case STONE_SWORD:
		case IRON_SWORD:
		case GOLDEN_SWORD:
		case DIAMOND_SWORD:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isTool(Material tool) {
		return isPickaxe(tool) || isAxe(tool) || isHoe(tool) || isShovel(tool);
	}
	
	public static boolean isWeapon(Material tool) {
		return isSword(tool) || tool == Material.BOW;
	}
}

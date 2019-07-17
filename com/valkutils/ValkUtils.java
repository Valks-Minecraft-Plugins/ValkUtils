package com.valkutils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class ValkUtils extends JavaPlugin {
	public List<NamespacedKey> recipes = new ArrayList<NamespacedKey>();
	
	public static ValkUtils plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
	}
}

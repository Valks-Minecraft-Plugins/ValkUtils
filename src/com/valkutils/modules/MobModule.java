package com.valkutils.modules;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class MobModule {
	public static int countMobsInChunk(Entity[] entities, EntityType type) {
		int count = 0;
		for (Entity entity : entities) {
			if (entity.getType() == type) {
				count++;
			}
		}
		return count;
	}
	
	public static int countMobsInWorld(World w, EntityType type) {
		int count = 0;
		for (LivingEntity entity : w.getLivingEntities()) {
			if (entity.getType() == type) {
				count++;
			}
		}
		return count;
	}
}

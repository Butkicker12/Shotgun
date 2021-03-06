package me.butkicker12.Shotgun;

import org.bukkit.Effect;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EntityListener implements Listener {

	private Shotgun plugin;

	public EntityListener(Shotgun instance) {
		plugin = instance;
	}

	@EventHandler
	public void ProjectileHit(ProjectileHitEvent event) {

		final Entity entity = event.getEntity();

		/*
		 * Grenade (Egg)
		 */
		if (entity instanceof Egg) {
			if (plugin.getCustomConfig().getBoolean("weapon.enabled.grenade",
					true)) {
				entity.getWorld().createExplosion(entity.getLocation(), 5F,
						true);
				for (int i = 0; i < 10; i++) {
					entity.getWorld().playEffect(entity.getLocation(),
							Effect.MOBSPAWNER_FLAMES, 100);
				}
			}
		}

		/*
		 * Smoke grenade (Snowball)
		 */
		if (entity instanceof Snowball) {

			Snowball snowball = (Snowball) event.getEntity();
			if (snowball.getShooter() instanceof Player) {
				Player player = (Player) snowball.getShooter();

				if (player.hasPermission("shotgun.smoke")) {

					//
					if (plugin.getCustomConfig().getBoolean(
							"weapon.enabled.smoke", true)) {

						entity.getWorld().createExplosion(entity.getLocation(),
								-12F);

						for (int i = 0; i < 3000; i++) {
							entity.getWorld().playEffect(entity.getLocation(),
									Effect.SMOKE, 1);
						}
					}
				}
			}
		}
	}
}

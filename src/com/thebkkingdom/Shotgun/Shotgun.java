package com.thebkkingdom.Shotgun;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Shotgun extends JavaPlugin {

	public void onEnable() {

		this.registerEvents();
		System.out.println("[Shotgun enabled]");

	}

	public void onDisable() {

	}

	private void registerEvents() {

		getServer().getPluginManager().registerEvents(new PlayerListener(),
				this);

		getServer().getPluginManager().registerEvents(new EntityListener(),
				this);
	}

	public boolean onCommand(CommandSender sender, Command command,
			String cmdLabel, String[] args) {

		Player player = (Player) sender;
		Block target = player.getTargetBlock(null, 200);
		Location targetLocation = target.getLocation();

		if (command.getName().equalsIgnoreCase("airstrike")) {
			if (!(args.length == 0)) {
				sender.sendMessage(ChatColor.BLUE
						+ "[Shotgun] Did you mean /Airstrike");
			}
			if (player.hasPermission("shotgun.airstrike")) {
				target.getWorld().strikeLightning(targetLocation);
				target.getWorld().createExplosion(targetLocation, 5);
				sender.sendMessage(ChatColor.BLUE
						+ "[Shotgun] Airstrike called at your crosshairs");
			} else {
				sender.sendMessage(ChatColor.RED
						+ "You do not have permission!");
			}
			return true;
		}
		return false;
	}
}
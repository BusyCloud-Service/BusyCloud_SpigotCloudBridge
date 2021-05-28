package de.plugdev.spigot.general.listener;

import org.bukkit.Bukkit;

import de.terrarier.netlistening.api.event.ConnectionTimeoutEvent;
import de.terrarier.netlistening.api.event.ConnectionTimeoutListener;

public class TimeoutListener implements ConnectionTimeoutListener {
	
	@Override
	public void trigger(ConnectionTimeoutEvent event) {
		Bukkit.getConsoleSender().sendMessage("§cConnection to Cloud timed out. Stopping Cloud.");
		Bukkit.shutdown();
	}
	
}

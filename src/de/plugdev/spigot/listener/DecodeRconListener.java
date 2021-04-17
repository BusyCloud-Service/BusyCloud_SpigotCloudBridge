package de.plugdev.spigot.listener;

import org.bukkit.Bukkit;

import de.plugdev.spigot.CloudBridge;
import de.terrarier.netlistening.api.event.DecodeEvent;
import de.terrarier.netlistening.api.event.DecodeListener;

public class DecodeRconListener implements DecodeListener {

	String nextMessage;
	
	@Override
	public void trigger(DecodeEvent event) {
		final String key = event.getData().read();
		if (key.equalsIgnoreCase("rcon")) {
			while ((nextMessage = event.getData().read()) != null) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(CloudBridge.getCloud(), new Runnable() {
					
					@Override
					public void run() {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), nextMessage);
					}
				});
			}
		}
	}

}

package de.plugdev.spigot.listener;

import org.bukkit.Bukkit;

import de.terrarier.netlistening.api.event.DecodeEvent;
import de.terrarier.netlistening.api.event.DecodeListener;

public class DecodeRconListener implements DecodeListener {
	
	@Override
	public void trigger(DecodeEvent event) {
		final String key = event.getData().read();
		if(key.equalsIgnoreCase("rcon")) {
			String nextMessage;
			while((nextMessage = event.getData().read()) != null) {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), nextMessage);
			}
		}
	}
	
}

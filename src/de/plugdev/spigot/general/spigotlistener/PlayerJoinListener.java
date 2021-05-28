package de.plugdev.spigot.general.spigotlistener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import de.plugdev.spigot.CloudBridge;
import de.terrarier.netlistening.api.DataContainer;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onEvent(PlayerLoginEvent event) {
		if(!event.getAddress().getHostAddress().equals("127.0.0.1")) {
			event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§4You have to join through the proxy!");
			
			DataContainer container = new DataContainer();
			container.add("Spigot");
			container.add("adminmessage");
			container.add(event.getPlayer().getName() + " with UUID " + event.getPlayer().getUniqueId() + " and " + event.getAddress().getHostAddress() + " tried to connect without the proxy.");
			
			CloudBridge.getCloud().getNetworkClient().sendData(container);
		}
	}
	
}

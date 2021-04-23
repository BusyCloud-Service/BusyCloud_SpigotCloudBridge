package de.plugdev.spigot;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.plugdev.spigot.listener.DecodePingListener;
import de.plugdev.spigot.listener.DecodeRconListener;
import de.plugdev.spigot.spigotlistener.PlayerJoinListener;
import de.terrarier.netlistening.Client;
import de.terrarier.netlistening.api.DataContainer;
import de.terrarier.netlistening.api.event.ConnectionTimeoutEvent;
import de.terrarier.netlistening.api.event.ConnectionTimeoutListener;

public class CloudBridge extends JavaPlugin {
	
	private Client client;
	private String cloudKey;
	private static CloudBridge cloud;
	
	public static void main(String[] args) {
	}
	
	@Override
	public void onEnable() {
		
		cloud = this;
		
		client = new Client.Builder("localhost", 1130).timeout(15000).build();
		
		File con = null;
		for(File file : new File(".").listFiles()) {
			if(file.getName().startsWith("KEY_") && file.isFile()) {
				cloudKey = file.getName();
				con = file;
			}
		}
		con.delete();
		
		
		DataContainer dataContainer = new DataContainer();
		dataContainer.add("Spigot");
		dataContainer.add("linkserver");
		dataContainer.add(cloudKey);
		client.sendData(dataContainer);

		client.registerListener(new DecodeRconListener());
		client.registerListener(new DecodePingListener());
		client.registerListener(new ConnectionTimeoutListener() {
			
			@Override
			public void trigger(ConnectionTimeoutEvent event) {
				Bukkit.getConsoleSender().sendMessage("§cConnection to Cloud timed out. Stopping Cloud.");
				Bukkit.shutdown();
			}
		});
		
		
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
	}
	
	@Override
	public void onDisable() {
		DataContainer dataContainer = new DataContainer();
		dataContainer.add("Spigot");
		dataContainer.add("shutdown");
		dataContainer.add(cloudKey);
		client.sendData(dataContainer);
		
		client.stop();
		client = null;
	}
	
	public static CloudBridge getCloud() {
		return cloud;
	}
	
	public Client getClient() {
		return client;
	}
	
	public String getCloudKey() {
		return cloudKey;
	}
	
}

package de.plugdev.spigot;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import de.plugdev.spigot.general.General;
import de.terrarier.netlistening.Client;
import de.terrarier.netlistening.api.DataContainer;

public class CloudBridge extends JavaPlugin {
	
	private Client client;
	private String cloudKey;
	private static CloudBridge cloud;
	
	private General general;
	
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
		
		general = new General();
		
		general.enable(cloudKey);
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
	
	public Client getNetworkClient() {
		return client;
	}
	
	public General getGeneral() {
		return general;
	}
	
	public String getCloudKey() {
		return cloudKey;
	}
	
}

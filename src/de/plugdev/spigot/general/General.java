package de.plugdev.spigot.general;

import de.plugdev.spigot.CloudBridge;
import de.plugdev.spigot.general.listener.DecodePingListener;
import de.plugdev.spigot.general.listener.DecodeRconListener;
import de.plugdev.spigot.general.listener.TimeoutListener;
import de.terrarier.netlistening.api.DataContainer;

public class General {
	
	public void enable(String cloudKey) {
		DataContainer dataContainer = new DataContainer();
		dataContainer.add("Spigot");
		dataContainer.add("linkserver");
		dataContainer.add(cloudKey);
		CloudBridge.getCloud().getNetworkClient().sendData(dataContainer);
		CloudBridge.getCloud().getNetworkClient().registerListener(new DecodeRconListener());
		CloudBridge.getCloud().getNetworkClient().registerListener(new DecodePingListener());
		CloudBridge.getCloud().getNetworkClient().registerListener(new TimeoutListener());
	}
	
	public void disable() {
		
	}
	
}

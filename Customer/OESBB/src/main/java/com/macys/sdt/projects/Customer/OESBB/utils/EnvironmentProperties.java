package com.macys.sdt.projects.Customer.OESBB.utils;

public class EnvironmentProperties {
	
	public static String getProdEnvironment(){
		String serverType = null;
		serverType = System.getProperty("SERVERTYPE");
		if(serverType == null){
			serverType = System.getenv("SERVERTYPE");
		}
		return serverType;
	}

}

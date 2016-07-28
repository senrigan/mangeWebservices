package com.gdc.mangedengine.util;

import java.util.HashMap;

public class ServiceConsultor {
	private static HashMap<String ,Service> servicesData=new HashMap<String,Service>();
	
	
	public static HashMap<String,Service> getServiceData(){
		return servicesData;
	}
	
	public static void setNewValue(String url,Service serv){
		servicesData.put(url, serv);
	}
	
	public static void deleteValue(String url){
		servicesData.remove(url);
	}
}

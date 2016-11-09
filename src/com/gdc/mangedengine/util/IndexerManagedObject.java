package com.gdc.mangedengine.util;

import java.util.HashMap;

public class IndexerManagedObject {
	private  HashMap<Long, String>  servicesDetectedResourceID;
	private  HashMap<Long, String>  servicesNotMatched;
	
	public HashMap<Long, String> getServicesDetectedResourceID() {
		return servicesDetectedResourceID;
	}
	public void setServicesDetectedResourceID(HashMap<Long, String> servicesDetectedResourceID) {
		this.servicesDetectedResourceID = servicesDetectedResourceID;
	}
	public HashMap<Long, String> getServicesNotMatched() {
		return servicesNotMatched;
	}
	public void setServicesNotMatched(HashMap<Long, String> servicesNotMatched) {
		this.servicesNotMatched = servicesNotMatched;
	}
	
	
}

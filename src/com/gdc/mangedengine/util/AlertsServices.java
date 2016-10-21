package com.gdc.mangedengine.util;

import java.util.ArrayList;

public class AlertsServices {
	private ArrayList<AlertObject> alerts;
	private Service service;
	private AlertType type;
	private boolean newServices;
	
	public void cleanAlerts(){
		if(alerts!=null && !alerts.isEmpty()){
			alerts.clear();			
		}
	}
	
	public void setServices(Service service){
		this.service=service;
	}
	
	public Service getService(){
		return service;
	}
	
	public void setAlerts(ArrayList<AlertObject> alerts){
		this.alerts=alerts;
	}
	
	public ArrayList<AlertObject> getAlerts(){
		return alerts;
	}

	public AlertType getType() {
		return type;
	}

	public void setType(AlertType type) {
		this.type = type;
	}
	
	
	public boolean isNewServices(){
		return newServices;
	}
	
	
	public void setNewServcices(boolean isNew){
		this.newServices=isNew;
	}
	
	
	public enum AlertType{
		SV,
		DA;
		public String  toString(){
			switch(this){
				case SV	: return "SV";
				case DA: return "DA";
			    default: throw new IllegalArgumentException();

			}
		}
	}
	
	
}

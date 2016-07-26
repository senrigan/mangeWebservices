package com.gdc.mangedengine.util;

import java.util.Vector;


public class AlertService {
	
	private alertType type;
	private Vector<AlertObject> alerts;
	
	
	public Vector<AlertObject> getAlerts(){
		return this.alerts;
	}
	
	public  void setAlers(Vector<AlertObject> alerts){
		this.alerts=alerts;
	}
	public void setAlertType(alertType type){
		this.type=type;
	}
	
	public alertType getAlertType(){
		return type;
	}
	
	public enum alertType{
		DA,
		SV
	}
	
	
	
}

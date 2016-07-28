package com.gdc.mangedengine.util.workers;

import java.util.ArrayList;

import com.gdc.mangedengine.util.AlertObject;
import com.gdc.mangedengine.util.GCPAppPoller;

public class ServicesReporter {
	private Long idResource;
	private Long lastId;
	private ArrayList<AlertObject> alert;
	
	public  ServicesReporter(long idResource) {
		this.idResource=idResource;
	}	
	
	public ServicesReporter(long idResoruce,long lastId){
		this.idResource=idResoruce;
		this.lastId=lastId;
	}
	
	
	public ServicesReporter(ArrayList<AlertObject> alerts){
		this.alert=alerts;
	}
	
	public void run() {
		if(alert!=null && !alert.isEmpty()){
			sendToWebServices(alert);
		}else{
			
			ArrayList<AlertObject> allAlertForidResources=null;
			if(lastId!=null){
				allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idResource, lastId);
			}else{
				allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idResource);
				
			}
			if(allAlertForidResources!=null && !allAlertForidResources.isEmpty()){
				sendToWebServices(allAlertForidResources);
			}
		}
	}
	
	private void sendToWebServices(ArrayList<AlertObject> alerts){
		for (AlertObject alertObject : alerts) {
			
		}
	}
}

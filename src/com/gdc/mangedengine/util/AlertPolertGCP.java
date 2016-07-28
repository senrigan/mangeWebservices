package com.gdc.mangedengine.util;

import java.io.IOException;
import java.util.ArrayList;

public class AlertPolertGCP {
	public AlertPolertGCP(){
		
	}
	
	private void firtsExecution(){
		GCPAppPoller poller=new GCPAppPoller();
		ArrayList<Service> allServices = poller.getAllServices();
		poller.getIDForServices(url)
	}
	
	
	
	private void getAlertForServices(ArrayList<Service> services){
		for (Service service : services) {
			String simpleUrl;
			if(service.isEnableDA() && service.isEnableSV()){
				if(service.geturlDA().equals(service.geturlSV())){
					simpleUrl=service.geturlDA();
					try {
						long idForServices = GCPAppPoller.getIDForServices(service.geturlDA());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else{
				try {
					long idForServices=service.isEnableDA()?GCPAppPoller.getIDForServices(service.geturlDA()):-1;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					long idForServicesSV=service.isEnableSV()?GCPAppPoller.getIDForServices(service.geturlSV()):-1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private void getAlerts(long idForService){
		ArrayList<AlertObject> allAlertForidResources=new ArrayList<AlertObject>();
		if(idForService!=-1){
			allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idForService);			
		}
	}

}

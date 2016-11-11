package com.gdc.mangedengine.util.workers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import com.gdc.mangedengine.util.AlertPolertGCP;
import com.gdc.mangedengine.util.GCPAppPoller;
import com.gdc.mangedengine.util.Service;
/**
 * this class is for scanning for new application in samyg database and add to scan fo alerts
 * @author senrigan
 *
 */
public class ServiceScaner extends TimerTask {

	@Override
	public void run() {
		System.out.println("SCanning for newServices");
		GCPAppPoller basePoller=new GCPAppPoller();
		ArrayList<Service> allServices = basePoller.getAllServices();
		HashMap<String, Service> allServicesMap = AlertPolertGCP. getAllServicesMap();
		for (Service service : allServices) {
			try{
				if(service.isEnableDA()){
					if(service.geturlDA()!=null){
						if(!allServicesMap.containsKey(service.geturlDA())){
							System.out.println("putting new Services DA"+ service.geturlDA());
							allServicesMap.put(service.geturlDA(), service);
						}
					}
				}
				if(service.isEnableSV()){
					if(service.geturlSV()!=null){
						if(!allServicesMap.containsKey(service.geturlSV())){
							System.out.println("putting new Services SV"+ service.geturlSV());
							allServicesMap.put(service.geturlSV(), service);
						}
					}
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
}
	
	



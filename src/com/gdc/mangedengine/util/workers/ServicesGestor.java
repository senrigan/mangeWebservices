package com.gdc.mangedengine.util.workers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TimerTask;


import com.gdc.mangedengine.util.AlertPolertGCP;
import com.gdc.mangedengine.util.AlertsServices;
import com.gdc.mangedengine.util.AlertsServices.AlertType;
import com.gdc.mangedengine.util.GCPAppPoller;
import com.gdc.mangedengine.util.Service;
/**
 * this class is for scanning for new application in samyg database and add to scan fo alerts
 * @author senrigan
 *
 */
public class ServicesGestor extends TimerTask {

	@Override
	public void run() {
		System.out.println("SCanning for newServices");
		HashMap<String, AlertsServices> alertByServices = AlertPolertGCP.getAlertByServices();
		Set<String> keySet = alertByServices.keySet();
		GCPAppPoller basePoller=new GCPAppPoller();
		ArrayList<Service> allServices = basePoller.getAllServices();
		
		for (Service service : allServices) {
			try{
				AlertsServices alertsServices=new AlertsServices();				
				alertsServices.setServices(service);
				alertsServices.setNewServcices(true);
				if(service.isEnableDA()){
					if(service.geturlDA()!=null && !alertByServices.containsKey(service.geturlDA())){
						System.out.println("found new servids DA"+service.getName()+" "+service.geturlDA());
						alertsServices.setType(AlertType.DA);
						long idForServices = GCPAppPoller.getIDForServices(service.geturlDA());
						service.setIdService(idForServices);
						alertsServices.setServices(service);

						AlertPolertGCP.addAlert(alertsServices, service.geturlDA());
					}
				}
				if(service.isEnableSV()){
					if(service.geturlSV()!=null && !alertByServices.containsKey(service.geturlSV())){
						alertsServices.setType(AlertType.SV);
						System.out.println("found new servids SV"+service.getName()+" "+service.geturlSV());

						long idForServices = GCPAppPoller.getIDForServices(service.geturlSV());
						service.setIdResource(idForServices);
						alertsServices.setServices(service);
						AlertPolertGCP.addAlert(alertsServices, service.geturlSV());


					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	

}

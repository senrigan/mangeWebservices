package com.gdc.mangedengine.util.workers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gdc.mangedengine.util.AlertObject;
import com.gdc.mangedengine.util.AlertPolertGCP;
import com.gdc.mangedengine.util.AlertService;
import com.gdc.mangedengine.util.AlertsServices;
import com.gdc.mangedengine.util.GCPAppPoller;
import com.gdc.mangedengine.util.Service;

public class ServicesScanerTask extends TimerTask{
	private HashMap<String,AlertsServices> alertServices;
	private  HashMap<String  , HashSet<AlertObject>> alertsByServicesObjectMangeEng;

	 public ServicesScanerTask(HashMap<String,AlertsServices> alertsServices, HashMap<String  , HashSet<AlertObject>> alertsByServices) {
		 this.alertServices=alertsServices;
		 this.alertsByServicesObjectMangeEng=alertsByServices;
	 }
	 
	 
		@Override
		public void run() {
			GCPAppPoller.reportAllAlertManageEngines();
//			 = GCPAppPoller.reportAllAlertManageEngines();
//			System.out.println("Starting Scanning for alerts "+Calendar.getInstance().getTime());
//			Set<String> keySet = alertServices.keySet();
//			ExecutorService executor = Executors.newFixedThreadPool(10);
//	
//			for (String key : keySet) {
//				AlertsServices alertsServices = alertServices.get(key);
//				Service service = alertsServices.getService();
//				if(!alertsServices.isNewServices()){
//					long idResource = service.getIdResource();
//					HashSet<AlertObject> alerts = allNewAlertManageEngine.get(""+idResource);
////					long lastIdConsult = service.getLastIdConsult();
////					System.out.println("** id resources "+idResource+" lastid"+lastIdConsult);
////					ArrayList<AlertObject> allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idResource, lastIdConsult);
//					
////					if(!alerts.isEmpty()){
////						AlertsServices alertNewServices=new AlertsServices();
////						alertNewServices.setAlerts(new ArrayList<AlertObject>(alerts));
////						alertNewServices.setServices(service);
////						
////						System.out.println("**+ id resources "+idResource+" lastid"+lastIdConsult);
////						
//////						lastIdConsult=allAlertForidResources.get(allAlertForidResources.size()-1).getIdAlert();
//////						alertsServices.getService().setLastIdConsult(lastIdConsult);
////						alertsByServicesObjectMangeEng.put(""+idResource, new HashSet<AlertObject>(allAlertForidResources));
//////						alertsServices.setAlerts(allAlertForidResources);
////						
////						executor.execute(new ServicesReporter(alertsServices));
//////						AlertPolertGCP.addAlert(alertsServices, key);
////						
////					}
//					ArrayList<AlertsServices> alertForServices = AlertPolertGCP.getAlertForServices(service, allNewAlertManageEngine);
//					if(!alertForServices.isEmpty()){
//						for (AlertsServices alertsServices2 : alertForServices) {
//							executor.execute(new ServicesReporter(alertsServices2));
//							
//						}
//					}
//				}else{
//					ArrayList<AlertsServices> alertForServices = AlertPolertGCP.getAlertForServices(service, allNewAlertManageEngine);
//					if(!alertForServices.isEmpty()){
//						for (AlertsServices alertsServices2 : alertForServices) {
//							executor.execute(new ServicesReporter(alertsServices2));
//							
//						}
//					}
////					HashMap<String, HashSet<AlertObject>> newAlerts = AlertPolertGCP.getNewAlerts();
////					executor.execute(new ServicesReporter());
////					System.out.println("is new Services Detected");
////					long idResource = alertsServices.getService().getIdResource();
////					ArrayList<AlertObject> alerts = AlertPolertGCP.getAlerts(idResource);
////					alertsServices.setAlerts(alerts);
////					if(!alerts.isEmpty())
////						alertsServices.getService().setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
////					alertsServices.setNewServcices(false);
////					executor.execute(new ServicesReporter(alertsServices));
////					AlertPolertGCP.addAlert(alertsServices, key);
//				}
//				
//			}
//			executor.shutdownNow();
//			while(!executor.isTerminated()){
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println("Finished all threads");
		}	 
		
		
		
//		public String getIdResource(ArrayList<AlertObject> alerts){
//			for (AlertObject alertObject : alerts) {
//				String idSource = alertObject.getIdSource();
//				if(idSource!=null)
//					return idSource;
//			}
//			return null;
//			
//		}
		
		
//	@Override
//	public void run() {
//		System.out.println("Starting Scanning for alerts "+Calendar.getInstance().getTime());
//		Set<String> keySet = alertServices.keySet();
//		ExecutorService executor = Executors.newFixedThreadPool(10);
//
//		for (String key : keySet) {
//			AlertsServices alertsServices = alertServices.get(key);
//			if(!alertsServices.isNewServices()){
//				Service service = alertsServices.getService();
//				long idResource = service.getIdResource();
//				long lastIdConsult = service.getLastIdConsult();
//				System.out.println("** id resources "+idResource+" lastid"+lastIdConsult);
//				ArrayList<AlertObject> allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idResource, lastIdConsult);
//				
//				if(!allAlertForidResources.isEmpty()){
//					System.out.println("**+ id resources "+idResource+" lastid"+lastIdConsult);
//					
//					lastIdConsult=allAlertForidResources.get(allAlertForidResources.size()-1).getIdAlert();
//					alertsServices.getService().setLastIdConsult(lastIdConsult);
//					alertsServices.setAlerts(allAlertForidResources);
//					
//					executor.execute(new ServicesReporter(alertsServices));
//					AlertPolertGCP.addAlert(alertsServices, key);
//					
//				}
//				
//			}else{
//				System.out.println("is new Services Detected");
//				long idResource = alertsServices.getService().getIdResource();
//				ArrayList<AlertObject> alerts = AlertPolertGCP.getAlerts(idResource);
//				alertsServices.setAlerts(alerts);
//				if(!alerts.isEmpty())
//					alertsServices.getService().setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
//				alertsServices.setNewServcices(false);
//				executor.execute(new ServicesReporter(alertsServices));
//				AlertPolertGCP.addAlert(alertsServices, key);
//			}
//			
//		}
//		executor.shutdownNow();
//		while(!executor.isTerminated()){
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("Finished all threads");
//	}
	
	
	
	
	
	

}

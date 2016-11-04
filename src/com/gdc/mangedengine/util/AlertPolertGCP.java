package com.gdc.mangedengine.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.gdc.mangedengine.util.AlertsServices.AlertType;
import com.gdc.mangedengine.util.workers.ServicesGestor;
import com.gdc.mangedengine.util.workers.ServicesReporter;
import com.gdc.mangedengine.util.workers.ServicesScanerTask;


public class AlertPolertGCP {
	
	final static Logger logger = Logger.getLogger(AlertPolertGCP.class);
	private static HashMap<String,AlertsServices> alerByService=new HashMap<String,AlertsServices>();
	private static HashMap<String  , HashSet<AlertObject>> alertsByServicesObjectMangeEng=new HashMap<String  , HashSet<AlertObject>>();
	private static HashMap<String, Service> allServicesMap ;
	private static HashMap<Long, String> listObjectServices ;
	public AlertPolertGCP(){
		
	}
	
	public void firtsExecution(){
//		GCPAppPoller poller=new GCPAppPoller();
//		ArrayList<Service> allServices = poller.getAllServices();
//		try {
//			HashMap<String, AlertsServices> alertForServices = getAlertForServices(allServices);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.out.println("first execution");
		GCPAppPoller poller=new GCPAppPoller();
		allServicesMap = poller.getAllServicesMap();
		listObjectServices = poller.getListObjectServices(allServicesMap);
//		ArrayList<Service> allServices = poller.getAllServices();
		GCPAppPoller.reportAllAlertManageEngines();
//		getAlertForServices(allServices);
//		sendReport();
	}
	
public static HashMap<String, Service> getAllServicesMap(){
	return allServicesMap;
}

public static HashMap<Long, String> getListObjectService(){
	return listObjectServices;
}
	
private HashMap<String,AlertsServices> getAlertForServices(ArrayList<Service> services){
	for (Service service : services) {
		long idForServices=-1;
		if(service.isEnableDA() && service.isEnableSV() && service.geturlDA()!=null && service.geturlSV()!=null){
			 System.out.println("services id"+service.getIdService());

			if(service.geturlDA().equals(service.geturlSV())){
				try {
					 idForServices= GCPAppPoller.getIDForServices(service.geturlDA());
					 AlertsServices alertsServices=new AlertsServices();
					 HashSet<AlertObject> alerts = alertsByServicesObjectMangeEng.get(idForServices);
					 if(!alerts.isEmpty()){
						 service.setIdResource(idForServices);
						 alertsServices.setAlerts(new ArrayList<AlertObject>(alerts));
						 alertsServices.setType(AlertType.SV);
						 addAlert(alertsServices, service.geturlSV());
//						 service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
						 alertsServices.setServices(service);							 
					 }
//					 report(service, alerts, "SV", idForServices);
				} catch (IOException e) {
					
//					e.printStackTrace();
					logger.info("cannot found the services in manageengine for application"+service.getName());

				}
			}
		}else{
			 System.out.println("services id"+service.getIdService());
	
			try {
				idForServices=service.isEnableDA()?GCPAppPoller.getIDForServices(service.geturlDA()):-1;
				if(idForServices!=-1){
					AlertsServices alertsServices=new AlertsServices();
					System.out.println("id for services"+idForServices);
					HashSet<AlertObject> alerts = alertsByServicesObjectMangeEng.get(idForServices);
					if(alerts!=null && !alerts.isEmpty()){
						service.setIdResource(idForServices);
						//					service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
						alertsServices.setServices(service);
						alertsServices.setAlerts(new ArrayList<AlertObject>(alerts));
						alertsServices.setType(AlertType.DA);
						addAlert(alertsServices, service.geturlSV());							
					}
					
				}
			} catch (IOException e) {
	//			e.printStackTrace();
				logger.info("cannot found the services in manageengine for application"+service.getName());
	
			}
			
			try {
					idForServices=service.isEnableSV()?GCPAppPoller.getIDForServices(service.geturlSV()):-1;
					if(idForServices!=-1){
						AlertsServices alertsServices=new AlertsServices();
						System.out.println("id for services"+idForServices);
						HashSet<AlertObject> alerts = alertsByServicesObjectMangeEng.get(idForServices);
						if(alerts!=null &&!alerts.isEmpty()){
							service.setIdResource(idForServices);
							//					service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
							alertsServices.setServices(service);
							alertsServices.setAlerts(new ArrayList<AlertObject>(alerts));
							alertsServices.setType(AlertType.SV);
							addAlert(alertsServices, service.geturlSV());							
						}
						
					}
	//				report(service, alerts, "SV", idForServices);
			} catch (IOException e) {
	//			e.printStackTrace();
				logger.info("cannot found the services in manageengine for application"+service.getName());
	
			}
		}
		
	}
	return alerByService;
	
}



public static ArrayList<AlertsServices> getAlertForServices(Service service,HashMap<String  , HashSet<AlertObject>> alertsByServices){
		long idForServices=-1;
		ArrayList<AlertsServices> alertsServicesArray=new ArrayList<AlertsServices>();
		if(service.isEnableDA() && service.isEnableSV() && service.geturlDA()!=null && service.geturlSV()!=null){
			 System.out.println("services id"+service.getIdService());

			if(service.geturlDA().equals(service.geturlSV())){
				try {
					 idForServices= GCPAppPoller.getIDForServices(service.geturlDA());
					 AlertsServices alertsServices=new AlertsServices();
					 HashSet<AlertObject> alerts = alertsByServices.get(idForServices);
					 if(!alerts.isEmpty()){
						 service.setIdResource(idForServices);
						 alertsServices.setAlerts(new ArrayList<AlertObject>(alerts));
						 alertsServices.setType(AlertType.SV);
						 alertsServices.setServices(service);
						 alertsServicesArray.add(alertsServices);
					 }
				} catch (IOException e) {
					logger.info("cannot found the services in manageengine for application"+service.getName());

				}
			}
		}else{
			 System.out.println("services id"+service.getIdService());
	
			try {
					idForServices=service.isEnableDA()?GCPAppPoller.getIDForServices(service.geturlDA()):-1;
					AlertsServices alertsServices=new AlertsServices();
					 HashSet<AlertObject> alerts = alertsByServices.get(idForServices);
					if(!alerts.isEmpty()){
						service.setIdResource(idForServices);
	//					service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
						alertsServices.setServices(service);
						alertsServices.setAlerts(new ArrayList<AlertObject>(alerts));
						alertsServices.setType(AlertType.DA);
						alertsServicesArray.add(alertsServices);
					}
			} catch (IOException e) {
	//			e.printStackTrace();
				logger.info("cannot found the services in manageengine for application"+service.getName());
	
			}
			
			try {
					idForServices=service.isEnableSV()?GCPAppPoller.getIDForServices(service.geturlSV()):-1;
					AlertsServices alertsServices=new AlertsServices();
					 HashSet<AlertObject> alerts = alertsByServices.get(idForServices);
					if(!alerts.isEmpty()){
						service.setIdResource(idForServices);
	//					service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
						alertsServices.setServices(service);
						alertsServices.setAlerts(new ArrayList<AlertObject>(alerts));
						alertsServices.setType(AlertType.SV);
						alertsServicesArray.add(alertsServices);
					}
	//				report(service, alerts, "SV", idForServices);
			} catch (IOException e) {
	//			e.printStackTrace();
				logger.info("cannot found the services in manageengine for application"+service.getName());
	
			}
		}
		
	return alertsServicesArray;
	
}


public static  HashMap<String, HashSet<AlertObject>> getNewAlerts(){
//	HashMap<String, HashSet<AlertObject>> allAlertsAllManageEngine = GCPAppPoller.reportAllAlertManageEngines();
	HashMap<String, HashSet<AlertObject>> newAlertsAux=new HashMap<String,HashSet<AlertObject>>();
//	Set<String> keySet = allAlertsAllManageEngine.keySet();
//	for (String key : keySet) {
//		if(alertsByServicesObjectMangeEng.containsKey(key)){
//			HashSet<AlertObject> oldAlerts = alertsByServicesObjectMangeEng.get(key);
//			HashSet<AlertObject> newAlerts = allAlertsAllManageEngine.get(key);
//			newAlerts.removeAll(oldAlerts);
//			newAlertsAux.put(key, newAlerts);
//		}else{
//			newAlertsAux.put(key, allAlertsAllManageEngine.get(key));
//		}
//	}
	return newAlertsAux;
}
	
	private HashMap<String, AlertsServices> getAlertForServices2(ArrayList<Service> services) throws Exception{
		
		for (Service service : services) {
			
			long idForServices=-1;
			ArrayList<AlertObject> alerts=null;
			if(service.isEnableDA() && service.isEnableSV() && service.geturlDA()!=null && service.geturlSV()!=null){
				 System.out.println("services id"+service.getIdService());

				if(service.geturlDA().equals(service.geturlSV())){
					try {
						 idForServices= GCPAppPoller.getIDForServices(service.geturlDA());
						 AlertsServices alertsServices=new AlertsServices();
						 alerts=getAlerts(idForServices);
						 if(!alerts.isEmpty()){
							 service.setIdResource(idForServices);
							 alertsServices.setAlerts(alerts);
							 alertsServices.setType(AlertType.SV);
							 addAlert(alertsServices, service.geturlSV());
							 service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
							 alertsServices.setServices(service);							 
						 }
//						 report(service, alerts, "SV", idForServices);
					} catch (IOException e) {
						
//						e.printStackTrace();
						logger.info("cannot found the services in manageengine for application"+service.getName());

					}
				}
			}else{
				 System.out.println("services id"+service.getIdService());

				try {
						idForServices=service.isEnableDA()?GCPAppPoller.getIDForServices(service.geturlDA()):-1;
						AlertsServices alertsServices=new AlertsServices();
						alerts=getAlerts(idForServices);
						if(!alerts.isEmpty()){
							service.setIdResource(idForServices);
							service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
							alertsServices.setServices(service);
							alertsServices.setAlerts(alerts);
							alertsServices.setType(AlertType.DA);
							addAlert(alertsServices, service.geturlSV());							
						}
				} catch (IOException e) {
//					e.printStackTrace();
					logger.info("cannot found the services in manageengine for application"+service.getName());

				}
				
				try {
						idForServices=service.isEnableSV()?GCPAppPoller.getIDForServices(service.geturlSV()):-1;
						AlertsServices alertsServices=new AlertsServices();
						alerts=getAlerts(idForServices);
						if(!alerts.isEmpty()){
							service.setIdResource(idForServices);
							service.setLastIdConsult(alerts.get(alerts.size()-1).getIdAlert());
							alertsServices.setServices(service);
							alertsServices.setAlerts(alerts);
							alertsServices.setType(AlertType.SV);
							addAlert(alertsServices, service.geturlSV());							
						}
//						report(service, alerts, "SV", idForServices);
				} catch (IOException e) {
//					e.printStackTrace();
					logger.info("cannot found the services in manageengine for application"+service.getName());

				}
			}
		}
		
		return alerByService;
	}
	
	
	public static void addAlert(AlertsServices alertsServices,String serviceUrl){
		if(serviceUrl!=null && alertsServices!=null)
			alerByService.put(serviceUrl, alertsServices);
	}
	
	
	public static ArrayList<AlertObject> getAlerts(long idForService){
		ArrayList<AlertObject> allAlertForidResources=new ArrayList<AlertObject>();		
		allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idForService);	
		return allAlertForidResources;
		
	}
	
	public static HashMap<String, AlertsServices> getAlertByServices(){
		return alerByService;
	}
	public static void main(String[] args) {
		AlertPolertGCP alert=new AlertPolertGCP();
		alert.firtsExecution();
//		alert.startTimerTask();
//		alert.startScanFoNewServices();
	}
	
//	private void report(Service service,ArrayList<AlertObject> alerts,String category,long objectid) throws RemoteException, ServiceException{
//		System.out.println("***Services"+service.getIdService()+"num alerts "+alerts.size());
//		Data_ticket_into dataInfo=new Data_ticket_into();
//		for (AlertObject alertObject : alerts) {
//			System.out.println("category"+category+"servideid"+service.getIdService()+"childerobj"+objectid+"time:2016-04-27 19:17 messsage :"+dataInfo.getMessage()+"typeAlert "+alertObject.getTypeAlert());
//			dataInfo.setCategory(category);
//			dataInfo.setService(Integer.parseInt(""+service.getIdService()));
//			dataInfo.setChildService(""+objectid);
//			dataInfo.setDatetime("2016-08-03 11:14");
//			dataInfo.setMessage(alertObject.getMessage());
//			dataInfo.setRobot(0);
//			dataInfo.setTypeAlert(""+alertObject.getTypeAlert());
//			CqSamygPreReportTickectWebServiceLocator servicews=new CqSamygPreReportTickectWebServiceLocator();
//			CqSamygPreReportTickectWebServicePortType port = servicews.getCqSamygPreReportTickectWebServicePort();
//			TicketCqResult wsManageTicketIndex = port.wsManageTicketIndex(dataInfo);
//			System.out.println("repsonse WS: "+wsManageTicketIndex.getResult());
//		}
//	
//	}
	private void sendReport(){
		Set<String> keySet = alerByService.keySet();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		System.out.println("sending report");
		for (String string : keySet) {
			System.out.println(string);
			AlertsServices alertsServices = alerByService.get(string);
			executor.execute(new ServicesReporter(alertsServices));
		}
		executor.shutdown();
		while(!executor.isTerminated()){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finished all threads");
	}
	
	
	public void startTimerTask(){
		Timer timer=new Timer();
	    timer.scheduleAtFixedRate(new ServicesScanerTask(alerByService,alertsByServicesObjectMangeEng), TimeUnit.MINUTES.toMillis(1),TimeUnit.MINUTES.toMillis(1) );

	}
	
	
	public void startScanFoNewServices(){
		Timer timer=new Timer();
	    timer.scheduleAtFixedRate(new ServicesGestor(), TimeUnit.MINUTES.toMillis(2),TimeUnit.SECONDS.toMillis(30) );
	}
}

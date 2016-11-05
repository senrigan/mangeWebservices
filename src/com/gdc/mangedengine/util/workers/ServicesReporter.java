package com.gdc.mangedengine.util.workers;

import java.util.ArrayList;

import com.gdc.mangedengine.util.AlertObject;
import com.gdc.mangedengine.util.AlertsServices;

import wsManageTicket.CqSamygPreReportTickectWebServiceLocator;
import wsManageTicket.CqSamygPreReportTickectWebServicePortType;
import wsManageTicket.Data_ticket_into;
import wsManageTicket.TicketCqResult;


public class ServicesReporter  implements Runnable{
//	private Long idResource;
//	private Long lastId;
//	private ArrayList<AlertObject> alert;
	private AlertsServices service;
	
//	public  ServicesReporter(long idResource) {
//		this.idResource=idResource;
//	}	
//	
//	public ServicesReporter(long idResoruce,long lastId){
//		this.idResource=idResoruce;
//		this.lastId=lastId;
//	}
//	
//	
//	public ServicesReporter(ArrayList<AlertObject> alerts){
//		this.alert=alerts;
//	}
	
	public ServicesReporter(AlertsServices service){
		this.service=service;
	}
	
	public void run() {
		try{
			Data_ticket_into dataInfo=new Data_ticket_into();
			ArrayList<AlertObject> alerts = service.getAlerts();
			for (AlertObject alertObject : alerts) {
//				System.out.println("category"+category+"servideid"+service.getIdService()+"childerobj"+objectid+"time:2016-04-27 19:17 messsage :"+dataInfo.getMessage()+"typeAlert "+alertObject.getTypeAlert());
				try{
					System.out.println("**AlertObject"+alertObject);
					dataInfo.setCategory(service.getType().toString());
					dataInfo.setService(Integer.parseInt(""+service.getService().getIdService()));
					dataInfo.setChildService(""+alertObject.getIdSource());
//					dataInfo.setDatetime(alertObject.getDateFormate());
					dataInfo.setDatetime(alertObject.getModTimeFornate());
					dataInfo.setMessage(alertObject.getMessage());
					dataInfo.setRobot(0);
					dataInfo.setTypeAlert(""+alertObject.getTypeAlert());
					System.out.println("sending to webservices"+dataInfo);
					
					CqSamygPreReportTickectWebServiceLocator servicews=new CqSamygPreReportTickectWebServiceLocator();
					CqSamygPreReportTickectWebServicePortType port = servicews.getCqSamygPreReportTickectWebServicePort();
					TicketCqResult wsManageTicketIndex = port.wsManageTicketIndex(dataInfo);
					System.out.println("repsonse WS: "+wsManageTicketIndex.getResult());
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}catch(Exception ex){
			
		}
//		if(alert!=null && !alert.isEmpty()){
//			sendToWebServices(alert);
//		}else{
//			
//			ArrayList<AlertObject> allAlertForidResources=null;
//			if(lastId!=null){
//				allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idResource, lastId);
//			}else{
//				allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idResource);
//				
//			}
//			if(allAlertForidResources!=null && !allAlertForidResources.isEmpty()){
//				sendToWebServices(allAlertForidResources);
//			}
//		}
		

		
		
	}
	
	
}

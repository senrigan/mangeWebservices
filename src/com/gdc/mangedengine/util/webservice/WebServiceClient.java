package com.gdc.mangedengine.util.webservice;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;


import wsManageTicket2.CqSamygPreReportTickectWebServiceLocator;
import wsManageTicket2.CqSamygPreReportTickectWebServicePortType;
import wsManageTicket2.Data_ticket_into;
import wsManageTicket2.TicketCqResult;

public class WebServiceClient {
	public static void main(String[] args) throws RemoteException, ServiceException {
//		CqSamygPreReportTickectWebServiceLocator serviceLocator=null;
//		CqSamygPreReportTickectWebServicePortType port=null;
//		CqSamygPreReportTickectWebService data=null;
//		try{
//			serviceLocator=new CqSamygPreReportTickectWebServiceLocator();
//			port =serviceLocator.getCqSamygPreReportTickectWebServicePort();
			Data_ticket_into dataInfo=new Data_ticket_into();
			dataInfo.setCategory("sv");
			dataInfo.setService(12);
			dataInfo.setChildService("2");
			dataInfo.setDatetime("2016-04-27 19:17");
			dataInfo.setMessage("OK");
			dataInfo.setRobot("hostNameTest");
			dataInfo.setTypeAlert("5");
//			
//			TicketCqResult wsManageTicketIndex = port.wsManageTicketIndex(dataInfo);
////			System.out.println(TicketCqResult.getTypeDesc().toString());
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		CqSamygPreReportTickectWebServiceLocator service=new CqSamygPreReportTickectWebServiceLocator();
		CqSamygPreReportTickectWebServicePortType port = service.getCqSamygPreReportTickectWebServicePort();
		TicketCqResult wsManageTicketIndex = port.wsManageTicketIndex(dataInfo);
		System.out.println("response"+wsManageTicketIndex.getResult());
	}
}

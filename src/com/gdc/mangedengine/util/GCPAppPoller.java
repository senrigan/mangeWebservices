package com.gdc.mangedengine.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jdk.management.resource.ResourceId;



public class GCPAppPoller {
	
	
	
	public ArrayList<Service> getAllServices(){
		ArrayList<Service> services=new ArrayList<Service>();
		GcpAplicationConector conector=new GcpAplicationConector();
		Connection connection = conector.getConnection();
		System.out.println("connection is null"+connection);
		String query="SELECT NAME,ACTIVE,DA,SV,EU,CENTRAL_URL_APPMGR_SV,CENTRAL_URL_APPMGR_DA FROM gcpAPPLICATION_NEW WHERE ACTIVE='1' AND (DA='1' OR SV='1' )";
		ResultSet executeQuery = conector.executeQuery(connection, query);
		try {
			
			while(executeQuery.next()){
				Service serv=new Service();
				serv.setName(executeQuery.getString("NAME"));
				
				String da=executeQuery.getString("DA");
				if(da.equals("1")){
					serv.setDa(true);
				}
				
				String sv=executeQuery.getString("SV");
				if(sv.equals("1")){
					serv.setSv(true);
				}
				
				
//				String eu = executeQuery.getString("EU");
//				if(eu.equals("1")){
//					serv.se
//				}
				serv.setUrlSV(executeQuery.getString("CENTRAL_URL_APPMGR_SV"));
				serv.setUrlDA(executeQuery.getString("CENTRAL_URL_APPMGR_DA"));
				services.add(serv);
				
			}
			executeQuery.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				executeQuery.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return services;
	}
	
	
	private ArrayList<AlertObject> getAllAlertsForServices(String  url,long lastId){
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		ArrayList<AlertObject> alerts=new ArrayList<AlertObject>();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert ");
			executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(executeQuery.getString("source"));
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				alerts.add(alert);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return alerts;
	}
	
	
	private static  ArrayList<AlertObject> getAllAlertForidResources(long idResource){
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		ArrayList<AlertObject> alerts=new ArrayList<AlertObject>();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert where source="+idResource);
			executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(executeQuery.getString("source"));
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				alerts.add(alert);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return alerts;
	}
	
	
	
	private static  ArrayList<AlertObject> getAllAlertForidResources(long idResource,long lastId){
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		ArrayList<AlertObject> alerts=new ArrayList<AlertObject>();
		try {
			System.out.println("SELECT id,severity,createtime,modtime,mmessage,source from alert where source="+idResource+"' AND id >'"+lastId+"'");
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert where source='"+idResource+"' AND id >'"+lastId+"'");
			executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(executeQuery.getString("source"));
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				alert.setIdAlert(executeQuery.getLong("id"));
				alerts.add(alert);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return alerts;
	}
	
	
	private  long getIDForServices(String url) throws IOException{
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT resourceid,resourcename from am_managedobject where resourcename='"+url+"'");
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				return executeQuery.getLong("resourceid");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		throw new IOException("cannot find the especific service name");
	}
	
	
	
	private ArrayList<AlertObject> getAllAlertsForResourceId(ArrayList<Service> services){
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (Service service : services) {
			
		}
		return null;
		
	}
	
	
	
	public ArrayList<AlertObject> getAllAlerts(){
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		ArrayList<AlertObject> alerts=new ArrayList<AlertObject>();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert ");
			executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(executeQuery.getString("source"));
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				alerts.add(alert);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return alerts;
	}
	
	
	
	public static void main(String[] args) {
		GCPAppPoller poller=new GCPAppPoller();
		ArrayList<Service> allServices = poller.getAllServices();
		for (Service service : allServices) {
			System.out.println(service);
		}
		
		
//		System.out.println("\n\n ***");
//		ArrayList<AlertObject> allAlerts = poller.getAllAlerts();
//		for (AlertObject alertObject : allAlerts) {
//			System.out.println(alertObject);
//		}
		ArrayList<AlertObject> allAlertForidResources = GCPAppPoller.getAllAlertForidResources(20000098L,20222736L);
		for (AlertObject alertObject : allAlertForidResources) {
			System.out.println(alertObject);
		}
		
	}
	
	


	private class InserterWebservices implements  Runnable {
		private Long idResource;
		private Long lastId;
		
		public InserterWebservices(long idResoruce){
			this.idResource=idResoruce;
		}
		
		public InserterWebservices(long idResoruce,long lastId){
			idResoruce=idResoruce;
			this.lastId=lastId;
		}
		
		public void run() {
			if(lastId!=null){
				
			}else{
				ArrayList<AlertObject> allAlertForidResources = GCPAppPoller.getAllAlertForidResources(idResource);
				
			}
		}
		
		private void sendToWebServices(ArrayList<AlertObject> alerts){
			
		}
	}
	
	
	
	
}

package com.gdc.mangedengine.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class GCPAppPoller {
	
	
	
	public ArrayList<Service> getAllServices(){
		ArrayList<Service> services=new ArrayList<Service>();
		GcpAplicationConector conector=new GcpAplicationConector();
		Connection connection = conector.getConnection();
		System.out.println("connection is null"+connection);
		String query="SELECT ID,NAME,ACTIVE,DA,SV,EU,CENTRAL_URL_APPMGR_SV,CENTRAL_URL_APPMGR_DA FROM gcpAPPLICATION_NEW WHERE ACTIVE='1' AND (DA='1' OR SV='1' )";
		ResultSet executeQuery = conector.executeQuery(connection, query);
		try {
			
			while(executeQuery.next()){
				Service serv=new Service();
				serv.setIdService(executeQuery.getLong("ID"));
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
	
	
	public ArrayList<AlertObject> getAllAlertsForServices(String  url,long lastId){
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
				alert.setModTime(new Date(executeQuery.getLong("modTime")));
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
	
	public static Connection getManageEngineConector(){
		ManageEngineConector conector=new ManageEngineConector();
		return conector.getConnection();
	}
	public static Connection getManageEngine2Conector(){
		ManageEngineConector conector = new ManageEngineConector();
		conector.setIp("192.168.207.182");
		return conector.getConnection();
	}
	
	
	
	public static Connection getManageEngine3Conector(){
		ManageEngineConector conector = new ManageEngineConector();
		conector.setIp("192.168.207.183");
		return conector.getConnection();
	}
	
	public static HashMap<String  , HashSet<AlertObject>> getAllAlerts(Connection conector){
		try {
			PreparedStatement prepareStatement = conector.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert order by asc");
			ResultSet executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
			HashMap<String, HashSet<AlertObject>> alertMap=new HashMap<String, HashSet<AlertObject>>();
			HashSet<AlertObject> alertList=new HashSet<AlertObject>();
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setModTime(new Date(executeQuery.getLong("modTime")));
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(executeQuery.getString("source"));
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				alert.setIdAlert(executeQuery.getLong("id"));
				alertList.add(alert);
				alertMap.put(alert.getIdSource(), alertList);
			}
			return alertMap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static HashMap<String , HashSet<AlertObject>> getAllAlertsAllManageEngine(){
		Connection manageEnigne1 = getManageEngineConector();
		HashMap<String, HashSet<AlertObject>> allAlerts=new HashMap<String, HashSet<AlertObject>>();
		HashMap<String, HashSet<AlertObject>> alerts1 = getAllAlerts(manageEnigne1);
		if(alerts1!=null)
			allAlerts.putAll(alerts1);
		Connection manageEngine2 = getManageEngine2Conector();
		HashMap<String, HashSet<AlertObject>> alerts2 = getAllAlerts(manageEngine2);
		if(alerts2!=null)
			allAlerts.putAll(alerts2);
		Connection manageEngine3Conector = getManageEngine3Conector();
		HashMap<String, HashSet<AlertObject>> alerts3 = getAllAlerts(manageEngine3Conector);
		if(alerts3!=null)
			allAlerts.putAll(alerts3);
		return allAlerts;
	}
	
	
	
	public static  ArrayList<AlertObject> getAllAlertForidResources(long idResource){
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		ArrayList<AlertObject> alerts=new ArrayList<AlertObject>();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert where source="+idResource+" order by id asc");
			executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setModTime(new Date(executeQuery.getLong("modTime")));
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
	
	
	
	public  static  ArrayList<AlertObject> getAllAlertForidResources(long idResource,long lastId){
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		ArrayList<AlertObject> alerts=new ArrayList<AlertObject>();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert where source='"+idResource+"' AND id >'"+lastId+"' order by id asc");
			executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setModTime(new Date(executeQuery.getLong("modtime")));
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
	
	

	
	/**
	 * 
	 * @param url
	 * @return the resourcesid for the url
	 * @throws IOException
	 */
	
	public static  long getIDForServices(String url) throws IOException{
		System.out.println("incoming url"+url);
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
				alert.setModTime(new Date(executeQuery.getLong("modtime")));
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
	
	
	
	
	

	
	
	
}

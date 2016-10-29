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
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class GCPAppPoller {
	
	
	public static void main(String[] args) {
		Connection conector = getManageEngineConector();
		try {
			long initTime = System.currentTimeMillis();
			System.out.println("**");
			PreparedStatement prepareStatement = conector.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert order  by id asc ");
			System.out.println("executing queryt");
			ResultSet executeQuery = prepareStatement.executeQuery();
			System.out.println("fecth size"+prepareStatement.getFetchSize());

			System.out.println("execute quer"+executeQuery);
			AlertObject alert=null;
			HashMap<String, HashSet<AlertObject>> alertMap=new HashMap<String, HashSet<AlertObject>>();
			HashSet<AlertObject> alertList=new HashSet<AlertObject>();
			int count=0;
			long lastId=0;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setModTime(new Date(executeQuery.getLong("modTime")));
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(executeQuery.getString("source"));
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				long idAlert = executeQuery.getLong("id");
				if(idAlert>lastId){
					lastId=idAlert;
				}
				alert.setIdAlert(idAlert);
				count++;
			}
			long finishTime=System.currentTimeMillis();
			System.out.println("total "+count);
			System.out.println("total time "+(finishTime-initTime));
			System.out.println("lasID "+lastId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		conector = getManageEngineConector();
		try {
			long initTime = System.currentTimeMillis();
			conector.setAutoCommit(false);
			System.out.println("**");
			PreparedStatement prepareStatement = conector.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert order  by id asc ");
			System.out.println("fecth size"+prepareStatement.getFetchSize());
			prepareStatement.setFetchSize(10000);
			System.out.println("fecth size"+prepareStatement.getFetchSize());

			System.out.println("executing queryt");
			ResultSet executeQuery = prepareStatement.executeQuery();
			System.out.println("execute quer"+executeQuery);
			AlertObject alert=null;
			HashMap<String, HashSet<AlertObject>> alertMap=new HashMap<String, HashSet<AlertObject>>();
			HashSet<AlertObject> alertList=new HashSet<AlertObject>();
			int count=0;
			long lastId=0;
			while(executeQuery.next()){
				alert=new AlertObject();
				alert.setModTime(new Date(executeQuery.getLong("modTime")));
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(executeQuery.getString("source"));
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				long idAlert = executeQuery.getLong("id");
				if(idAlert>lastId){
					lastId=idAlert;
				}
				alert.setIdAlert(idAlert);
				count++;
			}
			long finishTime=System.currentTimeMillis();
			System.out.println("total "+count);
			System.out.println("total time "+(finishTime-initTime));
			System.out.println("lasID"+lastId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Service> getAllServices(){
		ArrayList<Service> services=new ArrayList<Service>();
		GcpAplicationConector conector=new GcpAplicationConector();
		Connection connection = conector.getConnection();
		System.out.println("connection is null"+connection);
		String query="SELECT ID,NAME,ACTIVE,DA,SV,EU,CENTRAL_URL_APPMGR_SV,CENTRAL_URL_APPMGR_DA FROM gcpAPPLICATION_NEW WHERE ACTIVE='1' AND (DA='1' OR SV='1' )";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(query);
			connection.setAutoCommit(false);
			prepareStatement.setFetchSize(100);
			ResultSet executeQuery = prepareStatement.executeQuery();
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
				serv.setUrlSV(executeQuery.getString("CENTRAL_URL_APPMGR_SV"));
				serv.setUrlDA(executeQuery.getString("CENTRAL_URL_APPMGR_DA"));
				services.add(serv);
				
			}
			executeQuery.close();
			
		} catch (SQLException e3) {
			e3.printStackTrace();
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
			System.out.println("**");
			PreparedStatement prepareStatement = conector.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from alert order  by id asc ");
			System.out.println("executing queryt");
			ResultSet executeQuery = prepareStatement.executeQuery();
			System.out.println("execute quer"+executeQuery);
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
				System.out.println("alert"+alert);
				alertMap.put(alert.getIdSource(), alertList);
			}
			return alertMap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static HashMap<String , HashSet<AlertObject>> getAllAlertsAllManageEngine(){
		System.out.println("*");
		Connection manageEnigne1 = getManageEngineConector();
		System.out.println(manageEnigne1);
		HashMap<String, HashSet<AlertObject>> allAlerts=new HashMap<String, HashSet<AlertObject>>();
		HashMap<String, HashSet<AlertObject>> alerts1 = getAllAlerts(manageEnigne1);
		if(alerts1!=null)
			allAlerts.putAll(alerts1);
//		Connection manageEngine2 = getManageEngine2Conector();
//		HashMap<String, HashSet<AlertObject>> alerts2 = getAllAlerts(manageEngine2);
//		System.out.println("alerts 2"+alerts2);
//
//		if(alerts2!=null)
//			allAlerts.putAll(alerts2);
//		Connection manageEngine3Conector = getManageEngine3Conector();
//		HashMap<String, HashSet<AlertObject>> alerts3 = getAllAlerts(manageEngine3Conector);
//		System.out.println("alerts 3"+alerts2);
//
//		if(alerts3!=null)
//			allAlerts.putAll(alerts3);
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
	
	
	public static void  getListObjectServices(){
		HashMap<String,Long> listServices=new HashMap<String,Long>();
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT resourceid,resourcename from am_managedobject ");
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				long resoucerId = executeQuery.getLong("resourceid");
				long long1 = executeQuery.getLong("resourcename");
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
//		throw new IOException("cannot find the especific service name");
		return listServices;
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

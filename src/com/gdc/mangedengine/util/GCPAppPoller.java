package com.gdc.mangedengine.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.gdc.mangedengine.util.AlertsServices.AlertType;
import com.gdc.mangedengine.util.info.ManageEngineInfo;
import com.gdc.mangedengine.util.workers.ServicesReporter;




public class GCPAppPoller {
	
	private static Long lastAlertIdMng1=0L;
//	private static Long lastAlertIdMng2=0L;
	private static String ipManage1="192.168.207.181";
	private static String ipManage2="192.168.207.182";
	private static String ipManage3="192.168.207.183";
	private static String ipManage4="192.168.207.248";
	private static String portManager1="15435";
	private static String portManager2="15434";
	private static String portManager3="15457";
	private static String portManager4="15435";
	private static String fileConfig="consultInfo.conf";
	
	
	
	public static Long getLastAlertIdManageEngine1(){
		return lastAlertIdMng1;
	}
	
	public static void setLastAlertIdManageEngine1(Long lastAlertId){
		lastAlertIdMng1=lastAlertId;
	}
	public static void main(String[] args) {
		createLastManageEngineAlertConsultFile();
		ManageEngineInfo[] lastManagedEngienAlertConsult = getManagaEngineAlertConsultFile();
		System.out.println(Arrays.toString(lastManagedEngienAlertConsult));
		
		
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
	
	public HashMap<String , Service> getAllServicesMap(){
		HashMap<String,Service> services=new HashMap<String,Service>();
		GcpAplicationConector conector=new GcpAplicationConector();
		Connection connection = conector.getConnection();
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
				serv.setUrlSV(executeQuery.getString("CENTRAL_URL_APPMGR_SV"));
				serv.setUrlDA(executeQuery.getString("CENTRAL_URL_APPMGR_DA"));
				if(da.equals("1")){
//					String da_url = executeQuery.getString("CENTRAL_URL_APPMGR_DA");
					serv.setDa(true);
					if(serv.geturlDA()!=null && serv.geturlDA()!="")
						services.put(serv.geturlDA(), serv);
				}
				String sv=executeQuery.getString("SV");
				if(sv.equals("1")){
//					String sv_url = executeQuery.getString("CENTRAL_URL_APPMGR_SV");
					serv.setSv(true);
					if(serv.geturlSV()!=null && serv.geturlSV()!="")
						services.put(serv.geturlSV(), serv);
				}
			}
			executeQuery.close();
			
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		System.out.println(services);
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
	
	public static Connection getManageEngineConector(ManageEngineInfo info){
		ManageEngineConector conector=new ManageEngineConector();
		conector.setIp(info.getIpDatabase());
		conector.setPort(info.getPortDatabase());
		return conector.getConnection();
	}
	
//	
//	public static Connection getManageEngine2Conector(){
//		ManageEngineConector conector = new ManageEngineConector();
//		conector.setIp("192.168.207.182");
//		return conector.getConnection();
//	}
//	
//	
//	
//	public static Connection getManageEngine3Conector(){
//		ManageEngineConector conector = new ManageEngineConector();
//		conector.setIp("192.168.207.183");
//		return conector.getConnection();
//	}
	
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
	
	
	public static Long reportAllAlertsMap(Connection conector,Long lastID){
		Long lastId=0L;
		try {
			PreparedStatement prepareStatement = conector.prepareStatement("SELECT id,severity,createtime,modtime,mmessage,source from  alert where id > "+lastID+" order  by id asc ");
			ResultSet executeQuery = prepareStatement.executeQuery();
			AlertObject alert=null;
//			HashMap<String, HashSet<AlertObject>> alertMap=new HashMap<String, HashSet<AlertObject>>();
//			HashSet<AlertObject> alertList=new HashSet<AlertObject>();
//			ArrayList<AlertService> alerts=new ArrayList<AlertService>();
			HashMap<Long, String> listObjectService = AlertPolertGCP.getListObjectService();
//			System.out.println("list object"+listObjectService+"***----\n");
			HashMap<String, Service> allServicesMap = AlertPolertGCP.getAllServicesMap();
//			System.out.println("list object"+allServicesMap.keySet()+"***----\n");

//			HashMap<String,AlertsServices> alertByServices=new HashMap<String,AlertsServices>();
			ExecutorService executor = Executors.newFixedThreadPool(10);
			while(executeQuery.next()){
				
				AlertsServices alertServices=new AlertsServices();
				String idSource = executeQuery.getString("source");
				Long alertId=executeQuery.getLong("id");
				alert=new AlertObject();
				alert.setModTime(new Date(executeQuery.getLong("modTime")));
				alert.setCreationTime(new Date(executeQuery.getLong("createtime")));
				alert.setIdSource(idSource);
				alert.setMessage(executeQuery.getString("mmessage"));
				alert.setTypeAlert(executeQuery.getLong("severity"));
				alert.setIdAlert(alertId);
				if(lastId<alertId){
					lastId=alertId;
				}
				String serviceUrlName = listObjectService.get(Long.parseLong(idSource));
				if(serviceUrlName!=null){
//					System.out.println(serviceUrlName+"id source "+idSource);
					Service service = allServicesMap.get(serviceUrlName);
					ArrayList<AlertObject> alertsNews=null;
//					if(alertByServices.containsKey(idSource)){
//						 alertsNews = alertByServices.get(idSource).getAlerts();
//					}else{
						alertsNews=new ArrayList<AlertObject>();
//					}
						
					if(service!=null){
						alertsNews.add(alert);
						alertServices.setServices(service);
						if(service.geturlDA()!=null &&service.geturlDA().equals(serviceUrlName)){
							alertServices.setType(AlertType.DA);
							
						}
						if(service.geturlSV()!=null && service.geturlSV().equals(serviceUrlName)){
							alertServices.setType(AlertType.SV);
						}
						alertServices.setAlerts(alertsNews);
//						alertByServices.put(alert.getIdSource(), alertServices);
						executor.execute(new ServicesReporter(alertServices));
					}
					
				}else{
//					System.out.println("id alert no enter"+alertId+" from source"+idSource);
				}
			}
				executor.shutdown();
				while(!executor.isTerminated()){
					try {
						Thread.sleep(TimeUnit.SECONDS.toMillis(20));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return lastId;
				
//				alertList.add(alert);
//				System.out.println("alert"+alert);
//				alertMap.put(alert.getIdSource(), alertList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastId;
		
	
	}
	public static ManageEngineInfo[] getManagaEngineAlertConsultFile(){
		BufferedReader br=null;
		ManageEngineInfo [] manageInfo=new ManageEngineInfo[5];
		try{
			String currentLine;
			br= new BufferedReader(new FileReader(fileConfig));
			int count=0;
			while((currentLine = br.readLine())!=null){
				String[] split = currentLine.split(",");
				ManageEngineInfo manInfo = new ManageEngineInfo();
				for(int i=0;i<split.length;i++){
					String[] values = split[i].split("=");
					if(values[0].contains("appmanage")){
						manInfo.setManageName(values[0]);
						manInfo.setLasIdConsult(values[1]);
					}else if(values[0].contains("ipdatabase")){
						manInfo.setIpDatabase(values[1]);
					}else if(values[0].contains("portdatabase")){
						manInfo.setPortDatabase(values[1]);
					}
				}
				manageInfo[count]=manInfo;
				count++;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return manageInfo;
	}
	
	private static void createLastManageEngineAlertConsultFile(){
		File file =new File(fileConfig);
		try {
			if(!file.exists()){
				System.out.println("suceful to create");
				if(file.createNewFile()){
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw =new BufferedWriter(fw);
					bw.write(createLasManageEnginesAlertsConsultValues(null));
					bw.close();
				}
				
			}else{
				System.out.println("file already exist");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String createLasManageEnginesAlertsConsultValues(ManageEngineInfo []  managesEngines){
		StringBuilder builder=new StringBuilder();
		if(managesEngines==null){
			builder.append("appmanage1=0,ipdatabase="+ipManage1+",portdatabase="+portManager1+"\n");
			builder.append("appmanage2=0,ipdatabase="+ipManage2+",portdatabase="+portManager2+"\n");
			builder.append("appmanage3=0,ipdatabase="+ipManage3+",portdatabase="+portManager3+"\n");
			builder.append("appmanage4=0,ipdatabase="+ipManage4+",portdatabase="+portManager4+"\n");
			builder.append("appmanage5=0,ipdatabase=0,portdatabase=0\n");
			
		}else{
			for (ManageEngineInfo manageEngineInfo : managesEngines) {
				builder.append(manageEngineInfo.getManageName()+"="+manageEngineInfo.getLasIdConsult()+
						",ipdatabase="+manageEngineInfo.getIpDatabase()+",portdatabase="+manageEngineInfo.getPortDatabase()+"\n");
			}
			
		}
		return builder.toString();
	}
	
	
	
	public static void reportAllAlertManageEngines(){
		createLastManageEngineAlertConsultFile();
		ManageEngineInfo[] managesEnginesInfos = getManagaEngineAlertConsultFile();
//		ManageEngineInfo manageEngineInfo = managesEnginesInfos[0];
//		if(manageEngineInfo.getManageName().equals("appmanage1")){
//			Connection manageEnigne1 = getManageEngineConector(manageEngineInfo);
//			lastAlertIdMng1=reportAllAlertsMap(manageEnigne1, Long.parseLong(manageEngineInfo.getLasIdConsult()));
//			setLastAlertIdManageEngine1(lastAlertIdMng1);
//			System.out.println("updating lastidcount"+lastAlertIdMng1);
//			manageEngineInfo.setLasIdConsult(""+lastAlertIdMng1);
//			managesEnginesInfos[0]=manageEngineInfo;
//		}
		
		for (int i=1;i<managesEnginesInfos.length;i++) {
			try{
				ManageEngineInfo manageEngineInfo = managesEnginesInfos[i];
				Connection manageEngine =getManageEngineConector(manageEngineInfo);
				Long lastAlertManage = reportAllAlertsMap(manageEngine, Long.parseLong(manageEngineInfo.getLasIdConsult()));
				manageEngineInfo.setLasIdConsult(""+lastAlertManage);
				managesEnginesInfos[i]=manageEngineInfo;
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		updateFileConfig(managesEnginesInfos);
		
	}
	
	

	
	private static void  updateFileConfig(ManageEngineInfo []  manageEngines){
		File file =new File(fileConfig);
		try {
			
			if(file.exists()){
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw =new BufferedWriter(fw);
				bw.write(createLasManageEnginesAlertsConsultValues(manageEngines));
				bw.close();
			}else{
				System.out.println("file already exist");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	
	public  IndexerManagedObject  getListObjectServices(HashMap<String,Service> services){
		IndexerManagedObject indexerManagedObject=new IndexerManagedObject();
		HashMap<Long,String> listServices=new HashMap<Long,String>();
		 HashMap<Long, String>  listServiceNotDetected = new  HashMap<Long, String> ();
		ResultSet executeQuery=null;
		ManageEngineConector conector=new ManageEngineConector();
		Connection connection = conector.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT resourceid,resourcename from am_managedobject ");
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				long resoucerId = executeQuery.getLong("resourceid");
				String resourceName = executeQuery.getString("resourcename");
//				System.out.println("containing resorce name"+resourceName);
				if(services.containsKey(resourceName)){
					listServices.put(resoucerId, resourceName);
				}else{
					listServiceNotDetected.put(resoucerId, resourceName);
				}
				
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
		indexerManagedObject.setServicesDetectedResourceID(listServices);
		indexerManagedObject.setServicesNotMatched(listServiceNotDetected);
		return indexerManagedObject;
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

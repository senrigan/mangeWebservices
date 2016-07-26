package com.gdc.mangedengine.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GCPAppPoller {
	private GcpAplicationConector conector;
	public ArrayList<Service> getAllServices(){
		ArrayList<Service> services=new ArrayList<Service>();
		GcpAplicationConector conector=new GcpAplicationConector();
		Connection connection = conector.getConnection();
		String query="SELECT NAME,ACTIVE,DA,SV,EU,CENTRAL_URL_APPMGR_SV,CENTRAL_URL_APPMGR_DA FROM gcpAPPLICATION_NEW WHERE ACTIVE='1'";
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
				
				executeQuery.getString("CENTRAL_URL_APPMGR_SV");
				executeQuery.getString("CENTRAL_URL_APPMGR_DA");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

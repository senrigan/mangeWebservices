package com.gdc.mangedengine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ManageEngineConector {
	private String ip="192.168.207.181";
	private String databaseName="amdb";
	private String user="postgres";
	private String password="appmanager";
	private String port="15435";
	
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://192.168.207.18:3306/portal_samyg","root", "s3gur0");

			connection= DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+databaseName,user, password);
//			connection = DriverManager.getConnection(
//					"jdbc:mysql://"+ip+":"+port+"/"+databaseName, user,
//					password);
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return connection;
	}
	
	
	
	
	
	public String getIp() {
		return ip;
	}





	public void setIp(String ip) {
		this.ip = ip;
	}





	public String getDatabaseName() {
		return databaseName;
	}





	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}





	public String getUser() {
		return user;
	}





	public void setUser(String user) {
		this.user = user;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getPort() {
		return port;
	}





	public void setPort(String port) {
		this.port = port;
	}





	public static void main(String[] args) {
		ManageEngineConector conector=new ManageEngineConector();
		conector.getConnection();
	}

}

package com.gdc.mangedengine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GcpAplicationConector {
	
	private String ip="192.168.207.184";
	private String databaseName="portal_samyg";
	private String user="root";
	private String password="s3gur0";
	private String port="3306";
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://"+ip+":"+port+"/"+databaseName, user,
					password);
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;

		}
		return connection;
	}
	
	
	
	public ResultSet executeQuery(Connection connection,String query){
		ResultSet executeQuery=null;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,entity,createtime,modtime,mmessage,source from alert ");
			executeQuery = prepareStatement.executeQuery();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return executeQuery;
	}
	
	
	
//	public static void main(String[] args) {
//		System.out.println("-------- PostgreSQL "
//				+ "JDBC Connection Testing ------------");
//
//		try {
//
//			Class.forName("org.postgresql.Driver");
//
//		} catch (ClassNotFoundException e) {
//
//			System.out.println("Where is your PostgreSQL JDBC Driver? "
//					+ "Include in your library path!");
//			e.printStackTrace();
//			return;
//
//		}
//
//		System.out.println("PostgreSQL JDBC Driver Registered!");
//
//		Connection connection = null;
//
//		try {
//
//			connection = DriverManager.getConnection(
//					"jdbc:postgresql://192.168.207.181:15434/amdb", "postgres",
//					"appmanager");
//
//		} catch (SQLException e) {
//
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			return;
//
//		}
//
//		if (connection != null) {
//			System.out.println("You made it, take control your database now!");
//		} else {
//			System.out.println("Failed to make connection!");
//		}
//		
//		
//	}
}

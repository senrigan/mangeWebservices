package com.gdc.mangedengine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.applet.Main;

public class GcpAplicationConector {
	
	private String ip="192.168.207.184";
	private String databaseName="portal_samyg";
	private String user="root";
	private String password="s3gur0";
	private String port="3306";
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.207.184:3306/portal_samyg","root", "s3gur0");

//			connection= DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+databaseName,user, password);
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
	
	
	
	
	
	
	
	public ResultSet executeQuery(Connection connection,String query){
		ResultSet executeQuery=null;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			executeQuery = prepareStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return executeQuery;
	}
	public static void main(String[] args) {
		GcpAplicationConector conector=new GcpAplicationConector();
		Connection connection = conector.getConnection();
		System.out.println(connection);
	}
	
	

}

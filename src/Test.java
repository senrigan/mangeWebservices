import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;



public class Test {
	public static void main(String[] args) {
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
//		try {
//			PreparedStatement prepareStatement = connection.prepareStatement("SELECT id,entity,createtime,modtime,mmessage,source from alert ");
//			ResultSet executeQuery = prepareStatement.executeQuery();
//			while(executeQuery.next()){
//				System.out.println(executeQuery.getString("entity"));
//				System.out.println(executeQuery.getString("createTime"));
//				System.out.println(executeQuery.getString("modTime"));
//				System.out.println(executeQuery.getString("mmessage"));
//				System.out.println(executeQuery.getString("source"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(Boolean.parseBoolean("1"));
		System.out.println(Boolean.parseBoolean("0"));

	}
	
}

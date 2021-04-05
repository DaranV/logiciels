package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnectionGenerator {
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String URL_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "SYSTEM";
	private static final String PASSWORD = "toto";

	private static Connection connection = null;
	
	
	public static Connection getConnectionInstance() {
		try {
			
			if(connection == null || connection.isClosed()) {
				connection =  DriverManager.getConnection(URL_CONNECTION,USERNAME,PASSWORD);	
			}
			
			return connection;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}

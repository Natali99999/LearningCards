package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/learning_cards";
	public static final String TABLE_NAME = "cards";
	public static final String TABLE_THEMES = "card_groups";

	private static Connection conn;
	private static DBConnection instance = null;
	
	private DBConnection() throws DBException {
		try {
			conn = DriverManager.getConnection(URL, "root", "");
			System.out.println("DB connection successful..");
		} catch (SQLException e) {
			System.out.println("Connection Fehler");
			throw new DBException("No DB connection");
			//e.printStackTrace();
			
		}
	}
	
	public synchronized static DBConnection getInstance() throws DBException { // synchronized == threadsicher
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}
	
	public Connection connection() { // synchronized == threadsicher
		return conn;
	}
	
	public static Connection getConnection() {
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(URL, "root", "");
				System.out.println("DB connection successful..");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}

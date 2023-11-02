package Server;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DBConnector {
	private static Connection dbConn = null;

	public static Connection getDatabaseConnection() {
		if (dbConn != null) {
			return dbConn;
		} else {
			String url = "jdbc:mysql://localhost:3306/geeproject";
			try {
				dbConn = DriverManager.getConnection(url, "root", "");
				if (dbConn != null) {
					System.out.println("Database Connected");
					JOptionPane.showMessageDialog(null, "Connected to local server", "JDBC Coneection status", JOptionPane.INFORMATION_MESSAGE);		
					return dbConn;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dbConn;
		}
	}
	
    public static void main(String[] args) {
        {
        	getDatabaseConnection();
        }
    }
	
}
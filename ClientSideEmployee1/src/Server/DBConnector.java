package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnector {

    private static Connection dbConn;

    public static Connection getDatabaseConnection() {
        if (dbConn == null) {
            String url = "jdbc:mysql://localhost:3308/geeproject";
            try {
                dbConn = DriverManager.getConnection(url, "root", "");
                JOptionPane.showMessageDialog(null, "Connected To Database", "Connection Status",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                System.err.println("SQL Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Connection Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Already Connected", "Connection Status",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        return dbConn;
    }

    public static void closeConnection() {
        try {
            if (dbConn != null && !dbConn.isClosed()) {
                dbConn.close();
                dbConn = null;
                JOptionPane.showMessageDialog(null, "Database Connection Closed", "Connection Status",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
    	getDatabaseConnection();
    }
}

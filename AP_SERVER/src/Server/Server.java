package Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Customer;
import model.Equipment;
import model.Messages;
import model.Staff;

public class Server {
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private static Connection dbConn = null;
    private java.sql.Statement stmt;
    private ResultSet result = null;

    public Server() {
        this.createConnection();
        this.waitForRequests();
    }

    private void createConnection() {
        try {
            // Create a new instance of the serverSocket listening on port 8888
            serverSocket = new ServerSocket(8888);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void configureStreams() {
        try {
            // Instantiate the output stream
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());

            // Instantiate the input stream
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    
    private static Connection getDatabaseConnection() {
    	if(dbConn==null) {
        try {
        	String url = "jdbc:mysql://localhost:3306/geeproject";
            dbConn = DriverManager.getConnection(url, "root", "");
            
            JOptionPane.showMessageDialog(null, "DB Connection Established","CONNECTION STATUS",
            		JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "could not connect to database\n"+ex,"Connection Failure",
            		JOptionPane.ERROR_MESSAGE);
        }
    }
		return dbConn;
}
    
    
    
    
    
    private void closeConnection() {
        try {
            objOs.close();
            objIs.close();
            connectionSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

 //----------------------------ADD TO FILE -----------------------------------------------------  
	
    private void addCustomerToFile(Customer customer) {
        String sql = "INSERT INTO geeproject.customer(customer_id,customer_name,contact,customer_password,accountbal)" 
                    + "VALUES ('" + customer.getCustomer_id()+ "','"+customer.getCustomer_name()
                    + "','" + customer.getContact()  + "','" + customer.getCustomer_password() + "','" 
                    + customer.getAccountbal() + "');";     
        try {
        	stmt = dbConn.createStatement();
            if ((stmt.executeUpdate(sql) == 1)){
                objOs.writeObject(true);
            } else {
                objOs.writeObject(false);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();     
         } catch (SQLException e) {
        e.printStackTrace();
    }
    }


    private void addStaffToFile(Staff staff) {
        String sql = "INSERT INTO geeproject.staff(employee_id,employee_name,contact,employee_password)" 
                    + "VALUES ('" + staff.getEmployee_id()+ "','"+staff.getEmployee_name()
                    + "','" + staff.getContact()  + "','" + staff.getEmployee_password() + "');";     
        try {
        	stmt = dbConn.createStatement();
            if ((stmt.executeUpdate(sql) == 1)){
                objOs.writeObject(true);
            } else {
                objOs.writeObject(false);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();     
         } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    
    private void addEquipmentToFile(Equipment equipment) {
        String sql = "INSERT INTO geeproject.equipment(equipment_id,equipment_name,equipment_category,equipment_cost_per_day)" 
                    + "VALUES ('" + equipment.getEquipment_id()+ "','"+ equipment.getEquipment_name()
                    + "','" + equipment.getEquipment_category()  + "','" + equipment.getEquipment_cost_per_day() + "');";     
        try {
        	stmt = dbConn.createStatement();
            if ((stmt.executeUpdate(sql) == 1)){
                objOs.writeObject(true);
            } else {
                objOs.writeObject(false);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();     
         } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    

    private void addMessagesToFile(Messages messages) {
        String sql = "INSERT INTO geeproject.messages(message_id,customer_id,message)" 
                    + "VALUES ('" + messages.getMessage_id()+ "','"+ messages.getCustomer_id()
                    + "','" + messages.getMessage()  +"');";     
        try {
        	stmt = dbConn.createStatement();
            if ((stmt.executeUpdate(sql) == 1)){
                objOs.writeObject(true);
            } else {
                objOs.writeObject(false);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();     
         } catch (SQLException e) {
        e.printStackTrace();
    }
    }  
    
   
    //--------------------------FIND BY ID ------------------------------------
    
    
    private Customer findCustomerById(String customer_id) {
    	 Customer cusObj = new Customer();
        String query = "SELECT * FROM geeproject.customer WHERE customer_id = " + customer_id;
        try  {
        	stmt= dbConn.createStatement();
        	result= stmt.executeQuery(query);       
                if (result.next()) {
                	
                	cusObj.setCustomer_id(Integer.parseInt(result.getString(2)));
                	cusObj.setCustomer_name(result.getString(3));
                	cusObj.setContact(result.getString(4));
                	cusObj.setCustomer_password(result.getString(5));
                	cusObj.setAccountbal(Integer.parseInt(result.getString(2)));
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cusObj;
    }

    
    
    private Staff findStaffById(String employee_id) {
    	 Staff staObj = new Staff();
        String query = "SELECT * FROM geeproject.customer WHERE employee_id = " + employee_id;
        try  {
        	stmt= dbConn.createStatement();
        	result= stmt.executeQuery(query);       
                if (result.next()) {
                	
                	staObj.setEmployee_id(result.getString(2));
                	staObj.setEmployee_name(result.getString(3));
                	staObj.setContact(result.getString(4));
                	staObj.setEmployee_password(result.getString(5));
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staObj;
    }
    
 
    
    private Equipment findEquipmentById(String equipment_id) {
    	 Equipment equObj = new Equipment();
        String query = "SELECT * FROM geeproject.equipment WHERE equipment_id = " + equipment_id;
        try  {
        	stmt= dbConn.createStatement();
        	result= stmt.executeQuery(query);       
                if (result.next()) {
                         	
                	equObj.setEquipment_id(result.getString(3));
                	equObj.setEquipment_name(result.getString(4));
                	equObj.setEquipment_category(result.getString(5));
                	equObj.setEquipment_cost_per_day(Integer.parseInt(result.getString(6)));                         
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equObj ;
    }
    
    
    private Messages findMessagesById(String message_id) {
    	Messages mesObj = new Messages();
        String query = "SELECT * FROM geeproject.messages WHERE message_id = " + message_id;
        try  {
        	stmt= dbConn.createStatement();
        	result= stmt.executeQuery(query);       
                if (result.next()) {
                     
                	mesObj.setMessage_id(result.getString(2));
                	mesObj.setCustomer_id(result.getString(3));
                	mesObj.setMessage(result.getString(4));                       
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesObj ;
    }
    
    
    
    
    
    
    public void waitForRequests() {
        String action = "";
        getDatabaseConnection();
        Customer cusObj =null;
        Staff staObj =null;
        Equipment equObj =null;
        Messages mesObj =null;
        try {
            while (true) {
                connectionSocket = serverSocket.accept();
                this.configureStreams();
                try {
                    action = (String) objIs.readObject();
 //----------------------------CUSTOMER------------------------------------------
                    if (action.equals("Add Customer")) {
                    	cusObj = (Customer) objIs.readObject();
                        addCustomerToFile(cusObj);
                        objOs.writeObject(true);
                    } else if (action.equals("Find Customer")) {
                        String customer_id = (String) objIs.readObject();
                        // Call method to find customer based on ID number
                        cusObj = findCustomerById(customer_id);
                        objOs.writeObject(cusObj);
                    }
//---------------------------STAFF-------------------------------------------------                    
                    else  if (action.equals("Add Staff")) {
                    	staObj = (Staff) objIs.readObject();
                        addStaffToFile(staObj);
                        objOs.writeObject(true);
                    } else if (action.equals("Find Staff")) {
                        String employee_id = (String) objIs.readObject();
                        // Call method to find staff based on ID number
                        staObj = findStaffById(employee_id);
                        objOs.writeObject(staObj);
                    }
//---------------------------EQUIPMENT-------------------------------------------------                    
                    else  if (action.equals("Add Equipment")) {
                    	equObj = (Equipment) objIs.readObject();
                        addEquipmentToFile(equObj);
                        objOs.writeObject(true);
                    } else if (action.equals("Find Equipment")) {
                        String equipment_id = (String) objIs.readObject();
                        // Call method to find Equipment based on ID number
                        equObj = findEquipmentById(equipment_id);
                        objOs.writeObject(equObj);
                    }
 //---------------------------MESSAGES-------------------------------------------------                    
                    else  if (action.equals("Add Messages")) {
                    	mesObj = (Messages) objIs.readObject();
                        addMessagesToFile(mesObj);
                        objOs.writeObject(true);
                    } else if (action.equals("Find Messages")) {
                        String messages_id = (String) objIs.readObject();
                        // Call method to find Equipment based on ID number
                        mesObj = findMessagesById(messages_id);
                        objOs.writeObject(mesObj);
                    }                                     
                    
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }catch (ClassCastException ex) {
                    ex.printStackTrace();
                }
                this.closeConnection();
            }
        } catch (EOFException ex) {
            System.out.println("Client has terminated with the server");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

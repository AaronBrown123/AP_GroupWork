package Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import javax.swing.JOptionPane;

import model.Customer;
import model.Equipment;
import model.Messages;
import model.Staff;
import model.Transactions;
//import view.Client;
import model.ScheduleEquipment;

public class Server {
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private static Connection dbConn = null;
    private java.sql.Statement stmt;
    private ResultSet result = null;
  //  private static final Logger logger = LogManager.getLogger(Server.class);
    private boolean logInSuccessful;
    private boolean AvailabilityCheckSuccessful;
    
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
        String sql = "INSERT INTO geeproject.equipment (equipment_id, equipment_name, equipment_category, availability_status, equipment_cost_per_day) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.setString(1, equipment.getEquipment_id());
            preparedStatement.setString(2, equipment.getEquipment_name());
            preparedStatement.setString(3, equipment.getEquipment_category());
            preparedStatement.setString(4, equipment.getAvailability_status());
            preparedStatement.setDouble(5, equipment.getEquipment_cost_per_day());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
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
    private void addScheduleEquipmentToFile(ScheduleEquipment scheduleEquipment) {
        String sql = "INSERT INTO geeproject.scheduledequipment(schedule_id, date, equipment_name, customer_id, equipment_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.setString(1, scheduleEquipment.getSchedule_id());
            preparedStatement.setString(2, scheduleEquipment.getDate());
            preparedStatement.setString(3, scheduleEquipment.getEquipment_name());
            preparedStatement.setString(4, scheduleEquipment.getCustomer_id());
            preparedStatement.setString(5, scheduleEquipment.getEquipment_id());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                objOs.writeObject(true);
            } else {
                objOs.writeObject(false);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    

    private void addMessagesToFile(Messages messages) {
        String sql = "INSERT INTO geeproject.messages(message_id,customer_id,message,date)" 
                    + "VALUES ('" + messages.getMessage_id()+ "','"+ messages.getCustomer_id()
                    + "','" + messages.getMessage()  +"','" + messages.getDate() + "');";     
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
    private void addTransactionsToFile(Transactions transactions) {
        String sql = "INSERT INTO geeproject.transactions(transaction_id, customer_id, amountPaid, equipment_name, paymentDate) " +
                     "VALUES ('" + transactions.getTransaction_id() + "','" + transactions.getCustomer_id() + "','" + 
                     transactions.getAmountPaid() + "','" + transactions.getEquipment_name() + "','" + transactions.getPaymentDate() + "')";
        try {
            stmt = dbConn.createStatement();
            if (stmt.executeUpdate(sql) == 1) {
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
                	
                	cusObj.setCustomer_id(result.getString(2));
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
    private Transactions findTransactionsById(String transaction_id) {
   	 Transactions transObj = new Transactions();
       String query = "SELECT * FROM geeproject.transactions WHERE transaction_id = " + transaction_id;
       try  {
       	stmt= dbConn.createStatement();
       	result= stmt.executeQuery(query);       
               if (result.next()) {
               	
               	transObj.setTransaction_id(result.getString(2));
               	transObj.setCustomer_id(result.getString(3));
               	transObj.setAmountPaid(result.getDouble(4));
               	transObj.setEquipment_name(result.getString(5));
               	transObj.setPaymentDate(result.getString(6));
               }
           
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return transObj;
   }
    
    private void loginCustomer(String cust_id, String cust_password) {
    	try {
    		String query = "SELECT * FROM geeproject.customer WHERE customer_id = ? AND customer_password = ?";
    		PreparedStatement preparedStatement = dbConn.prepareStatement(query);
    		preparedStatement.setString(1, cust_id);
    		preparedStatement.setString(2, cust_password);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			objOs.writeObject(true);
    			logInSuccessful = false;
    		}else {
    			objOs.writeObject(false);
    			logInSuccessful = false;
    		}
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    private void loginEmployee(String emp_id, String emp_password) {
    	try {
    		String query = "SELECT * FROM geeproject.customer WHERE employee_id = ? AND employee_password = ?";
    		PreparedStatement preparedStatement = dbConn.prepareStatement(query);
    		preparedStatement.setString(1, emp_id);
    		preparedStatement.setString(2, emp_password);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) {
    			objOs.writeObject(true);
    			logInSuccessful = false;
    		}else {
    			objOs.writeObject(false);
    			logInSuccessful = false;
    		}
    	}catch(SQLException ex) {
    		ex.printStackTrace();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    private Queue<Transactions> viewAllTransactions(String customerId)
	{
		int value = 0;
		Transactions transactions = new Transactions();
		Queue<Transactions> allTransactions = new LinkedList<Transactions>();
		String query = "SELECT * FROM transactions WHERE customer_id  = '"+customerId+"' ";
		try
		{
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			
			while(result.next())
			{
				transactions.setTransaction_id(result.getString(1));
				transactions.setCustomer_id(result.getString(2));
				transactions.setAmountPaid(result.getDouble(3));
				transactions.setEquipment_name(result.getString(4));
				transactions.setPaymentDate(result.getString(5));
				
				if(transactions.getCustomer_id().equals(customerId))
				{
					value = 1;
				}
				allTransactions.add(transactions);
				transactions = new Transactions();
			}
			if(value == 0)
			{
				JOptionPane.showMessageDialog(null, "No Transaction(s) Found!","View Status", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Transaction(s) Found!","View Status", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			//logger.error("SQL Exception on Viewing Past Transactions");
		}
		return allTransactions;
	}
    private Queue<Transactions> viewSingleTransactions(String transactionId)
   	{
   		int value = 0;
   		Transactions transactions = new Transactions();
   		Queue<Transactions> SingleTransactions = new LinkedList<Transactions>();
   		String query = "SELECT * FROM transactions WHERE transaction_id  = '"+transactionId+"' ";
   		try
   		{
   			stmt = dbConn.createStatement();
   			result = stmt.executeQuery(query);
   			
   			while(result.next())
   			{
   				transactions.setTransaction_id(result.getString(1));
   				transactions.setCustomer_id(result.getString(2));
   				transactions.setAmountPaid(result.getDouble(3));
   				transactions.setEquipment_name(result.getString(4));
   				transactions.setPaymentDate(result.getString(5));
   				
   				if(transactions.getTransaction_id().equals(transactionId))
   				{
   					value = 1;
   				}
   				SingleTransactions.add(transactions);
   				transactions = new Transactions();
   			}
   			if(value == 0)
   			{
   				JOptionPane.showMessageDialog(null, "No Transaction(s) Found!","View Status", JOptionPane.ERROR_MESSAGE);
   			}
   			else
   			{
   				JOptionPane.showMessageDialog(null, "Transaction(s) Found!","View Status", JOptionPane.INFORMATION_MESSAGE);
   			}
   		}
   		catch(SQLException e)
   		{
   			e.printStackTrace();
   		//	logger.error("SQL Exception on Viewing Past Payments");
   		}
   		return SingleTransactions;
   	}
    private Queue<Equipment> viewEquipmentByCategory(String category)
   	{
   		int count = 0;
   		boolean found = false;
   		Equipment equipment = new Equipment();
   		Queue<Equipment> allEquipment = new LinkedList<Equipment>();
   		String query = "SELECT * FROM equipment WHERE equipment_category  = '"+category+"' ";

   		try
   		{
   			stmt = dbConn.createStatement();
   			result = stmt.executeQuery(query);
   			
   			while(result.next())
   			{
   				equipment.setEquipment_id(result.getString(1));
   				equipment.setEquipment_name(result.getString(2));
   				equipment.setEquipment_category(result.getString(3));
   				equipment.setAvailability_status(result.getString(4));
   				equipment.setEquipment_cost_per_day(result.getDouble(5));
   				
   				allEquipment.add(equipment);
   				if(equipment.getEquipment_category().equals(category))
   				{
   					found = true;
   				}
   				equipment = new Equipment();
   			}
   			if(found==true)
   			{
   				JOptionPane.showMessageDialog(null, "Equipment(s)Found!","View Status", JOptionPane.INFORMATION_MESSAGE);
   			}
   			else
   			{
   				JOptionPane.showMessageDialog(null, "No Equipment(s) Found!","View Status", JOptionPane.ERROR_MESSAGE);
   			}
   		}
   		catch(SQLException e)
   		{
   			e.printStackTrace();
   		}
   		return allEquipment;
   	}
    
    
    private void CheckAvailability(String equipment_id, String date) {
        try {
            // Check if equipment is scheduled for the given date
            String availabilityQuery = "SELECT se.*, e.equipment_cost_per_day AS cost " +
                                       "FROM geeproject.scheduledequipment se " +
                                       "JOIN geeproject.equipment e ON se.equipment_id = e.equipment_id " +
                                       "WHERE se.equipment_id = ? AND se.date = ?";

            try (PreparedStatement availabilityStatement = dbConn.prepareStatement(availabilityQuery)) {
                availabilityStatement.setString(1, equipment_id);
                availabilityStatement.setString(2, date);

                try (ResultSet availabilityResultSet = availabilityStatement.executeQuery()) {
                    if (availabilityResultSet.next()) {
                        // Equipment is not available on the given date
                        objOs.writeObject(false);
                        AvailabilityCheckSuccessful = false;
                        return;
                    }
                }
            }

            // If the function hasn't returned, the equipment is available, so retrieve the cost
            String costQuery = "SELECT equipment_cost_per_day FROM geeproject.equipment WHERE equipment_id = ?";
            try (PreparedStatement costStatement = dbConn.prepareStatement(costQuery)) {
                costStatement.setString(1, equipment_id);

                try (ResultSet costResultSet = costStatement.executeQuery()) {
                    if (costResultSet.next()) {
                        double cost = costResultSet.getDouble("equipment_cost_per_day");
                        objOs.writeObject(cost);
                        AvailabilityCheckSuccessful = true;
                    } else {
                        // Handle the case where cost is not found for the equipment_id
                        objOs.writeObject(null);
                        AvailabilityCheckSuccessful = false;
                    }
                }
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            // Handle the exception according to your application's error-handling strategy
        }
    }


    
    
    
    
    
    public void waitForRequests() {
        String action = "";
        String category = "";
        getDatabaseConnection();
        Customer cusObj =null;
        Staff staObj =null;
        Equipment equObj =null;
        ScheduleEquipment schequObj = null;
        Messages mesObj =null;
        Transactions transObj =null;
        Transactions transactions = new Transactions();
        Queue<Transactions> allTransactions = new LinkedList<Transactions>();
        Queue<Transactions> SingleTransactions = new LinkedList<Transactions>();
        Queue<Equipment> allEquipment = new LinkedList<Equipment>();
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
                    else if (action.equals("Schedule Equipment")) {
                    	schequObj= (ScheduleEquipment) objIs.readObject();
                        addScheduleEquipmentToFile(schequObj);
                        objOs.writeObject(true);
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
                    if (action.equals("Add Transactions")) {
                    	transObj = (Transactions) objIs.readObject();
                        addTransactionsToFile(transObj);
                        objOs.writeObject(true);
                    } else if (action.equals("Find Transactions")) {
                        String transaction_id = (String) objIs.readObject();
                        // Call method to find customer based on ID number
                        transObj = findTransactionsById(transaction_id);
                        objOs.writeObject(transObj);
                    }
                    ///Authentication
                    if(action.equalsIgnoreCase("LoginCustomer")) {
                    	String cust_id = (String) objIs.readObject();
                    	String cust_password = (String) objIs.readObject();
                    	loginCustomer(cust_id,cust_password);
                    }
                    else if(action.equals("LoginEmployee")) {
                    	String emp_id = (String) objIs.readObject();
                    	String emp_password = (String) objIs.readObject();
                    	loginEmployee(emp_id, emp_password);
                    }
                    //Transactions
                    if(action.equals("SingleTransactions"))
					{
						String transaction_id  = (String)objIs.readObject();
						SingleTransactions = viewSingleTransactions(transaction_id);
						objOs.writeObject(SingleTransactions);
					//	logger.info("Server Fullfilled View Past Payments Request");
					}
                    if(action.equals("AllTransactions"))
					{
						String customer_id  = (String)objIs.readObject();
						allTransactions = viewAllTransactions(customer_id);
						objOs.writeObject(allTransactions);
					//	logger.info("Server Fullfilled View Past Payments Request");
					}
                    else if (action.equals("ByCategory"))
					{
						category  = (String)objIs.readObject();
						allEquipment = viewEquipmentByCategory(category);
						objOs.writeObject(allEquipment);
						//logger.info("Server Fullfilled View Complaints By Category Request");
					}
                    if(action.equalsIgnoreCase("Check Availability")) {
                    	String equipment_id = (String) objIs.readObject();
                    	String date = (String) objIs.readObject();
                    	CheckAvailability(equipment_id,date);
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
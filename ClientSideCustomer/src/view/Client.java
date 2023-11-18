package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Customer;
import model.Equipment;
import model.Messages;
import model.ScheduleEquipment;
import model.Staff;
import model.Transactions;
import view.ViewTransactions;
import view.ViewSingleTransactions;
import model.RentalRequest;




public class Client {
    private ObjectInputStream objIs;
    private ObjectOutputStream objOs;
    private Socket connectionSocket;
    private String action = "";
    private boolean logInSuccessful;
    private boolean availabilitySuccessful;
    Transactions transactions = new Transactions();
    Queue<Transactions> allTransactions = new LinkedList<Transactions>();
    Queue<Transactions> singleTransactions = new LinkedList<Transactions>();
    Queue<Equipment> allEquipment = new LinkedList<Equipment>();
    private static final Logger logger = LogManager.getLogger(Client.class);

    public Client() {
        this.createConnection();
        this.configureStreams();
    }

    private void createConnection() {
    	 try {
    	        // Create a socket to connect to the server
    	        connectionSocket = new Socket("127.0.0.1", 8888);
    	        System.out.println("Connection established with the server.");
    	    } catch (IOException ex) {
    	        System.err.println("Error creating the socket: " + ex.getMessage());
    	        ex.printStackTrace();
    	    }
    	
    }

    private void configureStreams() {
        try {
            if (connectionSocket != null && connectionSocket.isConnected()) {
                // Create an input stream to receive data from the server
                objIs = new ObjectInputStream(connectionSocket.getInputStream());

                // Create an output stream to send data to the server
                objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
                System.out.println("Streams configured successfully.");
            } else {
                System.err.println("Socket is not connected or is null.");
            }
        } catch (IOException ex) {
            System.err.println("Error configuring streams: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void closeConnection() {
        try {
            objOs.close();
            objIs.close();
            connectionSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void sendAction(String action) {
        this.action = action;
        try {
            objOs.writeObject(action);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  

    //--------------------------SEND CUSTOMER/STAFF/EQUIPMEMT/MESSAGE OBJECT-----------------------------------
    public void sendCustomer(Customer cusObj) {
        try {
            objOs.writeObject(cusObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendStaff(Staff staObj) {
        try {
            objOs.writeObject(staObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void sendEquipment(Equipment equObj) {
        try {
            objOs.writeObject(equObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendScheduleEquipment(ScheduleEquipment schequObj) {
        try {
            objOs.writeObject(schequObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendRentalRequest(RentalRequest rentObj) {
        try {
            objOs.writeObject(rentObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMessages(Messages mesObj) {
        try {
            objOs.writeObject(mesObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendTransactions(Transactions transObj) {
        try {
            objOs.writeObject(transObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
  //--------------------------SEND CUSTOMER/STAFF/EQUIPMEMT/MESSAGE ID -----------------------------------
      
   
    public void sendCustomerId(String cusObj) {
        try {
            objOs.writeObject(cusObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    public void sendStaffId(String staObj) {
        try {
            objOs.writeObject(staObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void sendEquipmentId(String equObj) {
        try {
            objOs.writeObject(equObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    public void sendMessageId(String mesObj) {
        try {
            objOs.writeObject(mesObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendTransactionsId(String transObj) {
        try {
            objOs.writeObject(transObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendLoginStaff(String emp_id, String emp_password) {
        try {
            objOs.writeObject(emp_id);
            objOs.writeObject(emp_password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendLoginCustomer(String cust_id, String cust_password) {
        try {
            objOs.writeObject(cust_id);
            objOs.writeObject(cust_password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendCategory(String category)
	{
		try
		{
			objOs.writeObject(category);
			logger.info("Client Sent Complaint Category to Server");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			logger.error("Input/Output Exception Sending Category To Server");
		}
	}
    public void sendAvailability(String equipmentId, String date) {
        try {
            objOs.writeObject(equipmentId);
            objOs.writeObject(date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double getEquipmentCost(String equipmentId) {
        double equipmentCost = 0.0;

        try {
            objOs.writeObject("CheckAvailability");
            objOs.writeObject(equipmentId); // Send the equipment name to check availability

            boolean isAvailable = (boolean) objIs.readObject(); // Check if the equipment is available

            if (isAvailable) {
                equipmentCost = (double) objIs.readObject(); // Receive the equipment cost/quotation
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return equipmentCost;
    }
  	
    
    public void receiveResponse() {
        try {
            if (action.equalsIgnoreCase("Add Customer")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag== true) {
                	JOptionPane.showMessageDialog(null,"Record Added Succesfully","Add Record Status",
                			JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            if (action.equalsIgnoreCase("Find Customer")) {
            	Customer customer = new Customer();
            	customer  = (Customer) objIs.readObject();
                if (customer == null) {
                	JOptionPane.showMessageDialog(null,"Record Could not be found","Find Record Status",
                			JOptionPane.ERROR_MESSAGE);
                	return;
                } 
            }
            
            if (action.equalsIgnoreCase("Add Staff")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag== true) {
                	JOptionPane.showMessageDialog(null,"Record Added Succesfully","Add Record Status",
                			JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            if (action.equalsIgnoreCase("Find Staff")) {
            	Staff staff = new Staff();
            	staff  = (Staff) objIs.readObject();
                if (staff == null) {
                	JOptionPane.showMessageDialog(null,"Record Could not be found","Find Record Status",
                			JOptionPane.ERROR_MESSAGE);
                	return;
                } 
            }   
                     
            if (action.equalsIgnoreCase("Add Equipment")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag== true) {
                	JOptionPane.showMessageDialog(null,"Record Added Succesfully","Add Record Status",
                			JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            if (action.equalsIgnoreCase("Find Equipment")) {
            	Equipment equipment = new Equipment();
            	equipment  = (Equipment) objIs.readObject();
                if (equipment == null) {
                	JOptionPane.showMessageDialog(null,"Record Could not be found","Find Record Status",
                			JOptionPane.ERROR_MESSAGE);
                	return;
                } 
            }
            if (action.equalsIgnoreCase("Schedule Equipment")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag== true) {
                	JOptionPane.showMessageDialog(null,"Equipment Scheduled","Schedule Status",
                			JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            if (action.equalsIgnoreCase("Add Request")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag== true) {
                	JOptionPane.showMessageDialog(null,"Your Request Have been Added","Request Status",
                			JOptionPane.INFORMATION_MESSAGE);
                }
            }
         
            if (action.equalsIgnoreCase("Add Messages")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag== true) {
                	JOptionPane.showMessageDialog(null,"Record Added Succesfully","Add Record Status",
                			JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            if (action.equalsIgnoreCase("Find Messages")) {
            	Messages messages = new Messages();
            	messages  = (Messages) objIs.readObject();
                if (messages == null) {
                	JOptionPane.showMessageDialog(null,"Record Could not be found","Find Record Status",
                			JOptionPane.ERROR_MESSAGE);
                	return;
                } 
            }
            if (action.equalsIgnoreCase("Add Transactions")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag== true) {
                	JOptionPane.showMessageDialog(null,"Record Added Succesfully","Add Record Status",
                			JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            if (action.equalsIgnoreCase("Find Transactions")) {
            	Transactions transactions = new Transactions();
            	transactions  = (Transactions) objIs.readObject();
                if (transactions == null) {
                	JOptionPane.showMessageDialog(null,"Single Transactions Could not be found","Find Record Status",
                			JOptionPane.ERROR_MESSAGE);
                	return;
                }
            }
            if(action.equalsIgnoreCase("LoginCustomer") || action.equalsIgnoreCase("LoginEmployee")) {
            	Boolean flag = (Boolean) objIs.readObject();
            	logInSuccessful= flag;
            	if(logInSuccessful) {
            		JOptionPane.showMessageDialog(null, "Login is successful", "Successful Login", JOptionPane.INFORMATION_MESSAGE);
            		
            	}
            }
            if(action.equals("AllTransactions"))
			{
				logger.error("Request To View Past Payments Unsuccessful");
				allTransactions =  (Queue<Transactions>) objIs.readObject();
				
				if(allTransactions == null)
				{
					return;
				}
				else
				{
					System.out.println("Your request to View Past Transactions Successful");
					logger.info("Request To View Past Payments Successful");
					ViewTransactions viewAll = new ViewTransactions();
					viewAll.table(allTransactions);
				}
			}
            if(action.equals("SingleTransactions"))
			{
				logger.error("Request To View Single Transaction Unsuccessful");
				singleTransactions =  (Queue<Transactions>) objIs.readObject();
				
				if(singleTransactions == null)
				{
					return;
				}
				else
				{
					System.out.println("Your request to View Single Transaction Successful");
					logger.info("Request To View Past Payments Successful");
					ViewSingleTransactions viewSingleTransactions = new ViewSingleTransactions();
					viewSingleTransactions.table(singleTransactions);
				}
			}
            if(action.equals("ByCategory"))
			{
				allEquipment=null;
				allEquipment =  (Queue<Equipment>) objIs.readObject();
				
				if (allEquipment != null)
				{
					logger.info("Request To View Complaints By Category Successful");
					ViewByCategory viewAll = new ViewByCategory();
					viewAll.table(allEquipment);
				}
			}
            else if(action.equalsIgnoreCase("Check Availability")) {
            	Object response = objIs.readObject();
                if (response instanceof String) {
                    String message = (String) response;
                    JOptionPane.showMessageDialog(null, message, "Equipment Availability", JOptionPane.INFORMATION_MESSAGE);
                } else if (response instanceof Boolean) {
                    // Handle boolean responses if required
                    boolean boolResponse = (boolean) response;
                    // Perform actions based on boolean response
                } 
            }

        } catch (ClassCastException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (  IOException ex) {
            ex.printStackTrace();
        }
        
    }
    public boolean isLogInSuccessful() {
    	return logInSuccessful;
    }
    public boolean availabilitySuccessful() {
    	return availabilitySuccessful;
    }
 
}

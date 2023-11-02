package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import model.Customer;
import model.Equipment;
import model.Messages;
import model.Staff;

public class Client {
    private ObjectInputStream objIs;
    private ObjectOutputStream objOs;
    private Socket connectionSocket;
    private String action = "";

    public Client() {
        this.createConnection();
        this.configureStreams();
    }

    private void createConnection() {
        try {
            // Create a socket to connect to the server
            connectionSocket = new Socket("127.0.0.1", 8888);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void configureStreams() {
        try {
            // Create an input stream to receive data from the server
            objIs = new ObjectInputStream(connectionSocket.getInputStream());

            // Create an output stream to send data to the server
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException ex) {
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
    
    public void sendMessages(Messages mesObj) {
        try {
            objOs.writeObject(mesObj);
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
            
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (  IOException ex) {
            ex.printStackTrace();
        }
        
    }

 
}

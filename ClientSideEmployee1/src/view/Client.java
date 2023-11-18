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

public class Client {
    private ObjectInputStream objIs;
    private ObjectOutputStream objOs;
    private Socket connectionSocket;
    private String action = "";
    private boolean logInSuccessful;
    private Queue<Transactions> allTransactions = new LinkedList<>();
    private Queue<Transactions> singleTransactions = new LinkedList<>();
    private Queue<Equipment> allEquipment = new LinkedList<>();
    private static final Logger logger = LogManager.getLogger(Client.class);

    public Client() {
        createConnection();
        configureStreams();
    }

    private void createConnection() {
        try {
            connectionSocket = new Socket("127.0.0.1", 8888);
        } catch (IOException ex) {
            handleIOException("Error creating connection", ex);
        }
    }

    private void configureStreams() {
        try {
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException ex) {
            handleIOException("Error configuring streams", ex);
        }
    }

    private void handleIOException(String message, IOException ex) {
        ex.printStackTrace();
        // Handle IOException, log, or display error message as needed
    }

    public void closeConnection() {
        try {
            objOs.close();
            objIs.close();
            connectionSocket.close();
        } catch (IOException ex) {
            handleIOException("Error closing connection", ex);
        }
    }

    public void sendAction(String action) {
        this.action = action;
        try {
            objOs.writeObject(action);
        } catch (IOException e) {
            handleIOException("Error sending action", e);
        }
    }

    public void sendCustomer(Customer cusObj) {
        try {
            objOs.writeObject(cusObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    private void handleButtonClick(String buttonText) {
//        switch (buttonText) {
//            // Existing cases...
//
//            case "Create Quotation":
//                String quotationId = createQuotation();
//                if (quotationId != null) {
//                } else {
//                }
//                break;
//
//       
//    }

    private String createQuotation(/* relevant parameters */) {
        try (Socket socket = new Socket("localhost", 8888);
             ObjectOutputStream objOs = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objIs = new ObjectInputStream(socket.getInputStream())) {

            // Send a request to the server to create a quotation
            objOs.writeObject("CreateQuotation");
 

            // Receive the quotation ID from the server
            return (String) objIs.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void receiveResponse() {
        try {
            if (action.equalsIgnoreCase("Add Customer")) {
                handleAddCustomerResponse();
            } else {
                System.out.println("Unsupported action: " + action);
            }
        } catch (ClassCastException | ClassNotFoundException | IOException ex) {
            handleException(ex);
        }
    }

    private void handleAddCustomerResponse() throws IOException, ClassNotFoundException {
        Boolean flag = (Boolean) objIs.readObject();
        if (flag) {
            JOptionPane.showMessageDialog(null, "Record Added Successfully", "Add Record Status",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void handleException(Exception ex) {
        ex.printStackTrace();
    }

    public boolean isLogInSuccessful() {
        return logInSuccessful;
    }


    public void close() {
        closeConnection();
    }
}

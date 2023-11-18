package Server;

import model.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private static final Logger logger = LogManager.getLogger(Server.class);

    public Server() {
        try {
            serverSocket = new ServerSocket(8888);
            threadPool = Executors.newCachedThreadPool();
            System.out.println("Server is listening on port 8888");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error creating server socket: " + ex.getMessage());
        }
    }

    private static Connection getDatabaseConnection() {
        Connection dbConn = null;
        try {
            String url = "jdbc:mysql://localhost:3308/geeproject";
            dbConn = DriverManager.getConnection(url, "root", "");
            logger.info("DB Connection Established");
        } catch (SQLException ex) {
            logger.error("Could not connect to the database", ex);
        }
        return dbConn;
    }

    private void closeConnection(Socket connectionSocket) {
        try {
            connectionSocket.close();
            logger.info("Connection closed successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("Error closing connection", ex);
        }
    }

    private void handleAddCustomer(Socket connectionSocket) {
        try (
            ObjectOutputStream objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream objIs = new ObjectInputStream(connectionSocket.getInputStream())
        ) {
            Customer customer = (Customer) objIs.readObject();
            addCustomerToDatabase(customer);
            objOs.writeObject(true); 
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addCustomerToDatabase(Customer customer) {
        String sql = "INSERT INTO geeproject.customer(customer_id, customer_name, contact, customer_password, accountbal)"
                + "VALUES (?,?,?,?,?)";
        try (Connection dbConn = getDatabaseConnection();
             PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getCustomer_id());
            preparedStatement.setString(2, customer.getCustomer_name());
            preparedStatement.setString(3, customer.getContact());
            preparedStatement.setString(4, customer.getCustomer_password());
            preparedStatement.setDouble(5, customer.getAccountbal());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                logger.info("Customer added successfully");
            } else {
                logger.error("Failed to add customer to the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void waitForRequests() {
        try {
            while (true) {
                Socket connectionSocket = serverSocket.accept();
                System.out.println("Client connected: " + connectionSocket.getInetAddress().getHostName());

                threadPool.submit(() -> {
                    try {
                        configureStreams(connectionSocket);
                        handleClientRequests(connectionSocket);
                    } finally {
                        closeConnection(connectionSocket);
                    }
                });
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error accepting client connection: " + ex.getMessage());
        }
    }

    private void configureStreams(Socket connectionSocket) {
        try (
            ObjectOutputStream objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream objIs = new ObjectInputStream(connectionSocket.getInputStream())
        ) {
            logger.info("Streams configured successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("Error configuring streams", ex);
        }
    }

    private List<RentalRequest> fetchRentalRequestsFromDatabase() {
        List<RentalRequest> rentalRequests = new ArrayList<>();

        try (Connection dbConn = getDatabaseConnection();
             PreparedStatement preparedStatement = dbConn.prepareStatement("SELECT * FROM rental_requests");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                RentalRequest rentalRequest = new RentalRequest(
                        resultSet.getString("customer_id"),
                        resultSet.getString("equipment_name"),
                        resultSet.getString("event_date")
                );
                rentalRequest.setRequest_id(resultSet.getString("request_id"));

                rentalRequests.add(rentalRequest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalRequests;
    }

    private void handleRespondToCustomer(Socket connectionSocket) {
        try (
            ObjectOutputStream objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream objIs = new ObjectInputStream(connectionSocket.getInputStream())
        ) {
            String customerId = (String) objIs.readObject();
            String response = (String) objIs.readObject();

            if (respondToCustomer(customerId, response)) {
                objOs.writeObject(true);  // Successfully responded to the customer
            } else {
                objOs.writeObject(false); // Unable to respond to the customer
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean respondToCustomer(String customerId, String response) {
        try (Socket socket = new Socket("localhost", 8888);
             ObjectOutputStream objOs = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objIs = new ObjectInputStream(socket.getInputStream())) {

            Messages responseMessage = new Messages();
            responseMessage.setCustomer_id(customerId);
            responseMessage.setMessage(response);

            objOs.writeObject("RespondToCustomer");
            objOs.writeObject(responseMessage);

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendRentalRequestsToClient(Socket connectionSocket) {
        try (ObjectOutputStream objOs = new ObjectOutputStream(connectionSocket.getOutputStream())) {
            List<RentalRequest> rentalRequests = fetchRentalRequestsFromDatabase();
            objOs.writeObject(rentalRequests);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCreateQuotation(Socket connectionSocket) {
        try (
            ObjectOutputStream objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream objIs = new ObjectInputStream(connectionSocket.getInputStream())
        ) {
            String customerId = (String) objIs.readObject();
            String equipmentId = (String) objIs.readObject();

            Quotation quotation = createQuotation(customerId, equipmentId);
            boolean success = storeQuotationInDatabase(quotation);

            if (success) {
                objOs.writeObject(quotation.getQuotationId());
            } else {
                objOs.writeObject(null);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Quotation createQuotation(String customerId, String equipmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String generateQuotationId() {
        long timestamp = System.currentTimeMillis();
        int randomSuffix = (int) (Math.random() * 10000); 
        return "Q" + timestamp + "-" + randomSuffix;
    }

    private boolean storeQuotationInDatabase(Quotation quotation) {
        
        String sql = "INSERT INTO Quotations (customerId, equipmentId, quotationId) VALUES (?, ?, ?)";
        
        try (Connection dbConn = getDatabaseConnection();
             PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.setString(1, quotation.getCustomerId());
            preparedStatement.setString(2, quotation.getEquipmentId());
            preparedStatement.setString(3, quotation.getQuotationId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void handleClientRequests(Socket connectionSocket) {
        try (
            ObjectOutputStream objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            ObjectInputStream objIs = new ObjectInputStream(connectionSocket.getInputStream())
        ) {
            String action;

            while (true) {
                try {
                    action = (String) objIs.readObject();

                    switch (action) {
                        case "Add Customer":
                            handleAddCustomer(connectionSocket);
                            break;
                        case "Respond To Customer":
                            handleRespondToCustomer(connectionSocket);
                            break;
                        case "Send Rental Requests":
                            sendRentalRequestsToClient(connectionSocket);
                            break;
                        case "Create Quotation":
                            handleCreateQuotation(connectionSocket);
                            break;
                        default:
                            System.out.println("Unsupported action: " + action);
                            break;
                    }
                } catch (ClassNotFoundException | ClassCastException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error handling client requests: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server().waitForRequests();
    }
}

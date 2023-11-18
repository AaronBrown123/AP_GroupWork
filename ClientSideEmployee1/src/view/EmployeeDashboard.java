package view;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel title;
    private JButton viewRentalRequestsBtn;
    private JButton equipmentInventoryBtn;
    private JButton scheduleEquipmentBtn;
    private JButton respondToCustomersBtn;
    private JButton createQuotationBtn;
    
    private EquipmentInventoryGUI equipmentInventoryGUI;

    public EmployeeDashboard() {
        setTitle("Employee Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(173, 216, 230)); 
        setResizable(false);
        setBounds(700, 300, 584, 531);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        try {
            setContentPane(new JLabel(new ImageIcon("bin/images/office.jpg")));
        } catch (Exception e) {
            e.printStackTrace();
        }


        title = new JLabel();
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.BLACK);
        title.setText("Employee Dashboard");
        title.setBounds(150, 20, 480, 60);
        add(title);

        // Dropdown menu on the top-left
        String[] dashboardOptions = {"Dashboard", "View Rental Requests", "Equipment Inventory", "Schedule Equipment", "Respond to Customers", "Create Quotation"};
        JComboBox<String> dashboardDropdown = new JComboBox<>(dashboardOptions);
        dashboardDropdown.setFont(new Font("Arial", Font.BOLD, 16));
        dashboardDropdown.setForeground(Color.GRAY);
//        dashboardDropdown.setBackground(new Color(173, 216, 230));
        dashboardDropdown.setBounds(10, 10, 150, 30);
        dashboardDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDropdownSelection(dashboardDropdown.getSelectedItem().toString());
            }
        });
        add(dashboardDropdown);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10)); 
        buttonPanel.setBounds(150, 105, 280, 250);

        viewRentalRequestsBtn = createButton("View Rental Requests");
        equipmentInventoryBtn = createButton("Equipment Inventory");
        scheduleEquipmentBtn = createButton("Schedule Equipment");
        respondToCustomersBtn = createButton("Respond to Customers");
        createQuotationBtn = createButton("Create Quotation");

        buttonPanel.add(viewRentalRequestsBtn);
        buttonPanel.add(equipmentInventoryBtn);
        buttonPanel.add(scheduleEquipmentBtn);
        buttonPanel.add(respondToCustomersBtn);
        buttonPanel.add(createQuotationBtn);

        add(buttonPanel);
        setVisible(true);
    }
    
    private List<RentalRequest> generateMockRentalRequests() {
        List<RentalRequest> rentalRequests = new ArrayList<>();

        // Add some mock rental requests to the list
        rentalRequests.add(new RentalRequest("C1", "Sound Equipment", "2023-01-01"));
        rentalRequests.add(new RentalRequest("C2", "Staging Equipment", "2023-02-01"));
        rentalRequests.add(new RentalRequest("C3", "Lighting Equipment", "2023-03-01"));


        return rentalRequests;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 0, 139)); 
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false); 
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(text);
            }
        });

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.RED); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 139)); 
            }
        });

        return button;
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "View Rental Requests":
                List<RentalRequest> rentalRequests = fetchRentalRequestsFromServer();
                if (rentalRequests != null) {
                	displayRentalRequests(rentalRequests);
                } else {
                    System.out.println("Failed to fetch rental requests from the server.");
                }
                break;
            case "Equipment Inventory":
                equipmentInventoryGUI = new EquipmentInventoryGUI();
                equipmentInventoryGUI.setVisible(true);
                break;
            case "Schedule Equipment":
                Equipment selectedEquipment = equipmentInventoryGUI.getSelectedEquipment();
                if (selectedEquipment != null) {

                    if (scheduleEquipment(selectedEquipment)) {
                        JOptionPane.showMessageDialog(this, "Equipment scheduled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to schedule equipment. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select an equipment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Respond to Customers":
            	displayEquipmentAvailabilityResponses();
                String customerId = getSelectedCustomerIdFromUI(); // Implement this method
                String response = getResponseFromUI(); // Implement this method

                if (customerId != null && response != null) {
                    if (respondToCustomer(customerId, response)) {
                        JOptionPane.showMessageDialog(this, "Response sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to send response. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a customer and provide a response.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Create Quotation":
//                new CreateQuotation();
                dispose();
                break;
        }
    }
    
    private String getResponseFromUI() {
		// TODO Auto-generated method stub
		return null;
	}

	private String getSelectedCustomerIdFromUI() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean respondToCustomer(String customerId, String response) {
        try (Socket socket = new Socket("localhost", 8888);
             ObjectOutputStream objOs = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objIs = new ObjectInputStream(socket.getInputStream())) {

            Messages responseMessage = new Messages();
            responseMessage.setCustomer_id(customerId);
            responseMessage.setMessage(response);

            // Send the response message to the server
            objOs.writeObject("RespondToCustomer");
            objOs.writeObject(responseMessage);

            // Return true if successfully responded
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	private List<EquipmentAvailabilityResponse> generateMockEquipmentAvailabilityResponses() {
	    List<EquipmentAvailabilityResponse> responses = new ArrayList<>();

	    // Add some mock responses to the list
	    responses.add(new EquipmentAvailabilityResponse("E1", "Available"));
	    responses.add(new EquipmentAvailabilityResponse("E2", "Booked"));
	    responses.add(new EquipmentAvailabilityResponse("E3", "Available"));

	    return responses;
	}
	private void displayEquipmentAvailabilityResponses() {
	    List<EquipmentAvailabilityResponse> responses = generateMockEquipmentAvailabilityResponses();

	    // Display the equipment availability responses in the GUI
	    for (EquipmentAvailabilityResponse response : responses) {
	        System.out.println("Equipment ID: " + response.getEquipmentId());
	        System.out.println("Availability: " + response.getAvailabilityStatus());
	        System.out.println("------------");
	    }
	}

    private void displayRentalRequests(List<RentalRequest> rentalRequests) {
        JFrame rentalRequestsFrame = new JFrame("Rental Requests");
        rentalRequestsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rentalRequestsFrame.setSize(600, 400);

        // Create a table to display rental requests
        String[] columnNames = {"Request ID", "Customer ID", "Equipment Name", "Event Date"};
        Object[][] data = new Object[rentalRequests.size()][4];

        for (int i = 0; i < rentalRequests.size(); i++) {
            RentalRequest request = rentalRequests.get(i);
            data[i][0] = request.getRequest_id();
            data[i][1] = request.getCustomer_id();
            data[i][2] = request.getEquipment_name();
            data[i][3] = request.getEvent_date();
        }

        JTable rentalRequestsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(rentalRequestsTable);
        rentalRequestsFrame.add(scrollPane);

        rentalRequestsFrame.setVisible(true);
    }
    
//    private List<RentalRequest> fetchRentalRequestsFromServer() {
//        try (Socket socket = new Socket("localhost", 8888);
//             ObjectOutputStream objOs = new ObjectOutputStream(socket.getOutputStream());
//             ObjectInputStream objIs = new ObjectInputStream(socket.getInputStream())) {
//
//            // Send a request to the server to fetch rental requests
//            objOs.writeObject("FetchRentalRequests");
//
//            // Receive the list of rental requests from the server
//            return (List<RentalRequest>) objIs.readObject();
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
    
    private List<RentalRequest> fetchRentalRequestsFromServer() {
        return generateMockRentalRequests();
    }
    private void displayEquipmentInformation(List<Equipment> equipmentList) {
        JFrame equipmentFrame = new JFrame("Equipment Inventory");
        equipmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        equipmentFrame.setSize(600, 400);

        // Create a table to display equipment information
        String[] columnNames = {"Equipment ID", "Name", "Category", "Status", "Cost per Day"};
        Object[][] data = new Object[equipmentList.size()][5];

        for (int i = 0; i < equipmentList.size(); i++) {
            Equipment equipment = equipmentList.get(i);
            data[i][0] = equipment.getEquipment_id();
            data[i][1] = equipment.getEquipment_name();
            data[i][2] = equipment.getEquipment_category();
            data[i][3] = equipment.getAvailability_status();
            data[i][4] = equipment.getEquipment_cost_per_day();
        }

        JTable equipmentTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        equipmentFrame.add(scrollPane);

        equipmentFrame.setVisible(true);
    }

    private boolean scheduleEquipment(Equipment equipment) {
        try (Socket socket = new Socket("localhost", 8888);
             ObjectOutputStream objOs = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objIs = new ObjectInputStream(socket.getInputStream())) {

            // Send a request to the server to schedule equipment
            objOs.writeObject("ScheduleEquipment");
            objOs.writeObject(equipment);

            // Receive the scheduling result from the server
            return (boolean) objIs.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
    


    private void handleDropdownSelection(String selectedOption) {
        switch (selectedOption) {
            case "Dashboard":
                break;
            case "View Rental Requests":
                List<RentalRequest> rentalRequests = fetchRentalRequestsFromServer();
                if (rentalRequests != null) {
                	displayRentalRequests(rentalRequests);
                } else {
                    System.out.println("Failed to fetch rental requests from the server.");
                }
                break;
            case "Equipment Inventory":
                equipmentInventoryGUI = new EquipmentInventoryGUI();
                equipmentInventoryGUI.setVisible(true);
                break;
            case "Schedule Equipment":
                Equipment selectedEquipment = equipmentInventoryGUI.getSelectedEquipment();
                if (selectedEquipment != null) {

                    if (scheduleEquipment(selectedEquipment)) {
                        JOptionPane.showMessageDialog(this, "Equipment scheduled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to schedule equipment. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select an equipment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Respond to Customers":
                String customerId = getSelectedCustomerIdFromUI(); // Implement this method
                String response = getResponseFromUI(); // Implement this method

                if (customerId != null && response != null) {
                    if (respondToCustomer(customerId, response)) {
                        JOptionPane.showMessageDialog(this, "Response sent successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to send response. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a customer and provide a response.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Create Quotation":
//                new CreateQuotation();
                dispose();
                break;
        }
    }
    

    public static void main(String args[]) {
//        new EmployeeDashboard();
    	 List<RentalRequest> mockRentalRequests = new EmployeeDashboard().generateMockRentalRequests();
         new EmployeeDashboard().displayRentalRequests(mockRentalRequests);
    }
}

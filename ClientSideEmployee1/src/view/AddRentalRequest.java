package view;

import javax.swing.*;

import model.RentalRequest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRentalRequest extends JFrame {
    private JTextField customerIDField;
    private JTextField equipmentNameField;
    private JTextField eventDateField;

    public AddRentalRequest() {
        initialize();
    }

    private void initialize() {
        setTitle("Add Rental Request");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        customerIDField = new JTextField(20);
        equipmentNameField = new JTextField(20);
        eventDateField = new JTextField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        addLabelTextFieldPair(panel, "Customer ID:", customerIDField, constraints);
        addLabelTextFieldPair(panel, "Equipment Name:", equipmentNameField, constraints);
        addLabelTextFieldPair(panel, "Event Date:", eventDateField, constraints);

        JButton submitButton = new JButton("Submit");
        constraints.gridwidth = 2;
        constraints.gridy++;
        panel.add(submitButton, constraints);

        JButton cancelButton = new JButton("Cancel");
        constraints.gridwidth = 2;
        constraints.gridy++;
        panel.add(cancelButton, constraints);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmission();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel);
    }

    private void handleSubmission() {

        String customerID = customerIDField.getText();
        String equipmentName = equipmentNameField.getText();
        String eventDate = eventDateField.getText();


        // Perform network operations in a separate thread
        sendRentalRequestToServer(customerID, equipmentName, eventDate);

        // Clear input fields
        customerIDField.setText("");
        equipmentNameField.setText("");
        eventDateField.setText("");

        dispose();
    }

    private void sendRentalRequestToServer(String customerID, String equipmentName, String eventDate) {
    	  Client client = new Client();
    	  RentalRequest rentalRequest1 = new model.RentalRequest(customerID, equipmentName, eventDate);


           client.sendAction("Add Rental Request");
           System.out.println("Message sent to server");

           client.sendRentalRequest(rentalRequest1);
           System.out.println("Record sent to server");

           client.receiveResponse();
        System.out.println("Rental Request Submitted: " + rentalRequest1.toString());
    }

    private void addLabelTextFieldPair(JPanel panel, String labelText, JTextField textField, GridBagConstraints constraints) {
        JLabel label = new JLabel(labelText);
        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(label, constraints);

        constraints.gridx = 1;
        panel.add(textField, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddRentalRequest().setVisible(true);
            }
        });
    }
}

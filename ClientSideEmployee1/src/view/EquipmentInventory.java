package view;

import javax.swing.*;

import model.Equipment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentInventory extends JFrame {
    private JTextField equipmentIDField;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField statusField;
    private JTextField costField;

    public EquipmentInventory() {
        initialize();
    }

    private void initialize() {
        setTitle("Equipment Inventory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250); // Adjusted height
        equipmentIDField = new JTextField(20);
        nameField = new JTextField(20);
        categoryField = new JTextField(20);
        statusField = new JTextField(20);
        costField = new JTextField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        addLabelTextFieldPair(panel, "Equipment ID:", equipmentIDField, constraints);
        addLabelTextFieldPair(panel, "Name:", nameField, constraints);
        addLabelTextFieldPair(panel, "Category:", categoryField, constraints);
        addLabelTextFieldPair(panel, "Status:", statusField, constraints);
        addLabelTextFieldPair(panel, "Cost:", costField, constraints);

        JButton addButton = new JButton("Add Equipment");
        constraints.gridwidth = 2;
        constraints.gridy++;
        panel.add(addButton, constraints);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String equipmentID = equipmentIDField.getText();
                String name = nameField.getText();
                String category = categoryField.getText();
                String status = statusField.getText();
                double cost = Double.parseDouble(costField.getText());

                // Perform network operations in a separate thread
                sendEquipmentToServer(equipmentID, name, category, status, cost);

                equipmentIDField.setText("");
                nameField.setText("");
                categoryField.setText("");
                statusField.setText("");
                costField.setText("");
            }
        });

        add(panel);
    }

    private void sendEquipmentToServer(String equipmentID, String name, String category, String status, double cost) {
        try {
            Client client = new Client();
            Equipment equipment = new Equipment(equipmentID, name, category, status, cost);

            client.sendAction("Add Equipment");
            System.out.println("Message sent to server");

            client.sendObject(equipment);
            System.out.println("Record sent to server");

            client.receiveResponse();
            System.out.println("Response received from server");

            // Close the client connection after use
            client.closeConnection();
        } catch (Exception e) {
            System.err.println("Error in sending equipment to server: " + e.getMessage());
            e.printStackTrace();
        }
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
                new EquipmentInventory().setVisible(true);
            }
        });
    }
}

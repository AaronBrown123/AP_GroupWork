package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEquipment extends JFrame {
    private JTextField equipmentNameField;
    private JTextField categoryField;
    private JTextField availabilityField;
    private JTextField costPerDayField;

    public AddEquipment() {
        super("Add Equipment");

        // Create components
        JLabel nameLabel = new JLabel("Equipment Name:");
        JLabel categoryLabel = new JLabel("Category:");
        JLabel availabilityLabel = new JLabel("Availability:");
        JLabel costPerDayLabel = new JLabel("Cost Per Day:");

        equipmentNameField = new JTextField(20);
        categoryField = new JTextField(20);
        availabilityField = new JTextField(20);
        costPerDayField = new JTextField(20);

        JButton addButton = new JButton("Add Equipment");

        // Set layout
        setLayout(new GridLayout(5, 2));

        // Add components to the frame
        add(nameLabel);
        add(equipmentNameField);
        add(categoryLabel);
        add(categoryField);
        add(availabilityLabel);
        add(availabilityField);
        add(costPerDayLabel);
        add(costPerDayField);
        add(new JLabel()); // Empty label for spacing
        add(addButton);

        // Set up button action listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEquipmentToDatabase();
            }
        });

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void addEquipmentToDatabase() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/ritz", "root", "");

            // Prepare the SQL statement
            String sql = "INSERT INTO Equipment (equipment_name, equipment_category, availability_status, equipment_cost_per_day) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set values from the GUI input fields
            preparedStatement.setString(1, equipmentNameField.getText());
            preparedStatement.setString(2, categoryField.getText());
            preparedStatement.setString(3, availabilityField.getText());
            preparedStatement.setDouble(4, Double.parseDouble(costPerDayField.getText()));

            // Execute the update
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();

            // Show success message
            JOptionPane.showMessageDialog(this, "Equipment added successfully!");

            // Clear input fields
            equipmentNameField.setText("");
            categoryField.setText("");
            availabilityField.setText("");
            costPerDayField.setText("");
        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            // Show error message
            JOptionPane.showMessageDialog(this, "Error adding equipment. Please check your input and try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddEquipment();
            }
        });
    }
}

package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployee extends JFrame {
    private JTextField employeeNameField;
    private JTextField contactField;
    private JTextField passwordField;

    public AddEmployee() {
        super("Add Employee");

        // Create components
        JLabel nameLabel = new JLabel("Employee Name:");
        JLabel contactLabel = new JLabel("Contact:");
        JLabel passwordLabel = new JLabel("Password:");

        employeeNameField = new JTextField(20);
        contactField = new JTextField(20);
        passwordField = new JTextField(20);

        JButton addButton = new JButton("Add Employee");

        // Set layout
        setLayout(new GridLayout(4, 2));

        // Add components to the frame
        add(nameLabel);
        add(employeeNameField);
        add(contactLabel);
        add(contactField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(addButton);

        // Set up button action listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeToDatabase();
            }
        });

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void addEmployeeToDatabase() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/ritz", "root", "");

            // Prepare the SQL statement
            String sql = "INSERT INTO Employee (employee_name, contact, employee_password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set values from the GUI input fields
            preparedStatement.setString(1, employeeNameField.getText());
            preparedStatement.setString(2, contactField.getText());
            preparedStatement.setString(3, passwordField.getText());

            // Execute the update
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();

            // Show success message
            JOptionPane.showMessageDialog(this, "Employee added successfully!");

            // Clear input fields
            employeeNameField.setText("");
            contactField.setText("");
            passwordField.setText("");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Show error message
            JOptionPane.showMessageDialog(this, "Error adding employee. Please check your input and try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddEmployee();
            }
        });
    }
}

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee extends JFrame {
    private JTextField employeeIDField;
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JTextField salaryField;
    private JTextField usernameField;
    private JPasswordField passwordField; // Use JPasswordField for secure password input

    public AddEmployee() {
        initialize();
    }

    private void initialize() {
        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Labels and Text Fields
        addLabelTextFieldPair(panel, "Employee ID:", employeeIDField, constraints);
        addLabelTextFieldPair(panel, "First Name:", firstnameField, constraints);
        addLabelTextFieldPair(panel, "Last Name:", lastnameField, constraints);
        addLabelTextFieldPair(panel, "Salary:", salaryField, constraints);
        addLabelTextFieldPair(panel, "Username:", usernameField, constraints);
        addLabelTextFieldPair(panel, "Password:", passwordField, constraints);

        // Add Button
        JButton addButton = new JButton("Add Employee");
        constraints.gridwidth = 2; // Span two columns for the button
        constraints.gridy++; // Move to the next row
        panel.add(addButton, constraints);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle adding employee logic here
                String employeeID = employeeIDField.getText();
                String firstName = firstnameField.getText();
                String lastName = lastnameField.getText();
                String salary = salaryField.getText();
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // You can use the entered information to create a new Employee object or
                // perform other actions.

                // For example:
                // Employee employee = new Employee(Integer.parseInt(employeeID), firstName, lastName, Double.parseDouble(salary));
                // Then, you can add this employee to your data model or perform other actions.

                // Clear the fields after adding an employee
                employeeIDField.setText("");
                firstnameField.setText("");
                lastnameField.setText("");
                salaryField.setText("");
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        add(panel);
    }

    private void addLabelTextFieldPair(JPanel panel, String labelText, JTextField textField, GridBagConstraints constraints) {
        JLabel label = new JLabel(labelText);
        constraints.gridy++; // Move to the next row
        constraints.gridx = 0; // Label column
        panel.add(label, constraints);

        textField = new JTextField(20);
        constraints.gridx = 1; // Text field column
        panel.add(textField, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddEmployee().setVisible(true);
            }
        });
    }
}

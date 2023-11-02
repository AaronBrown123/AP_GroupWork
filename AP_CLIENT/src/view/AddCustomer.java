package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends JFrame {
    private JTextField customerIDField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField dobField;
    private JTextField usernameField;
    private JPasswordField passwordField; // Use JPasswordField for secure password input

    public AddCustomer() {
        initialize();
    }

    private void initialize() {
        setTitle("Add Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350); // Increased height to accommodate username and password fields

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Labels and Text Fields
        addLabelTextFieldPair(panel, "Customer ID:", customerIDField, constraints);
        addLabelTextFieldPair(panel, "Name:", nameField, constraints);
        addLabelTextFieldPair(panel, "Phone:", phoneField, constraints);
        addLabelTextFieldPair(panel, "Date of Birth (DOB):", dobField, constraints);
        addLabelTextFieldPair(panel, "Username:", usernameField, constraints);
        addLabelTextFieldPair(panel, "Password:", passwordField, constraints);

        // Add Button
        JButton addButton = new JButton("Add Customer");
        constraints.gridwidth = 2; // Span two columns for the button
        constraints.gridy++; // Move to the next row
        panel.add(addButton, constraints);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle adding customer logic here
                String customerID = customerIDField.getText();
                String name = nameField.getText();
                String phone = phoneField.getText();
                String dob = dobField.getText();
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // You can use the entered information to create a new Customer object or
                // perform other actions.

                // For example:
                // Customer customer = new Customer(Integer.parseInt(customerID), name, phone, dob);
                // Then, you can add this customer to your data model or perform other actions.

                // Clear the fields after adding a customer
                customerIDField.setText("");
                nameField.setText("");
                phoneField.setText("");
                dobField.setText("");
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
                new AddCustomer().setVisible(true);
            }
        });
    }
}

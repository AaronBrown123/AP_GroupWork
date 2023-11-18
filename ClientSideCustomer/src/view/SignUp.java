package view;

import javax.swing.*;
import model.Customer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignUp extends JFrame {
    private JTextField customerIDField;
    private JTextField nameField;
    private JPasswordField passwordField; // Use JPasswordField for secure password input
    private JTextField contactField;
    private Random rand;

    public SignUp() {
        initialize();
    }

    private void initialize() {
    	rand = new Random();
        setTitle("Add Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Adjusted height
        setLocationRelativeTo(null); // Center the frame
        getContentPane().setBackground(new Color(160, 160, 160)); // Set background color

        customerIDField = new JTextField(20);
        nameField = new JTextField(20);
        contactField = new JTextField(20);
        passwordField = new JPasswordField(20); // Use JPasswordField

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(160, 160, 160)); // Match the background color

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 10); // Adjusted insets
        customerIDField.setText(String.format("%04d",rand.nextInt(10000)));
        customerIDField.setEditable(false);

        addLabelTextFieldPair(panel, "Customer ID:", customerIDField, constraints);
        addLabelTextFieldPair(panel, "Name:", nameField, constraints);
        addLabelTextFieldPair(panel, "Contact:", contactField, constraints);
        addLabelTextFieldPair(panel, "Password:", passwordField, constraints);

        JButton addButton = new JButton("Add Customer");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        constraints.gridwidth = 2;
        constraints.gridy++;
        panel.add(addButton, constraints);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerID = customerIDField.getText();
                String name = nameField.getText();
                String contact = contactField.getText();
                String password = new String(passwordField.getPassword()); // Retrieving password securely

                double accountBal = 0.0;

                // Perform network operations in a separate thread
                sendCustomerToServer(customerID, name, contact, password, accountBal);

                // Clear fields after adding customer
                customerIDField.setText("");
                nameField.setText("");
                contactField.setText("");
                passwordField.setText("");
            }
        });

        add(panel);
        setVisible(true);
    }

    private void sendCustomerToServer(String customerID, String name, String contact, String password, double accountBal) {
    	Client client = new Client();
        Customer customer = new Customer(customerID, name, contact, password, accountBal);

        client.sendAction("Add Customer");
        System.out.println("Message sent to server");

        client.sendCustomer(customer);
        System.out.println("Record sent to server");

        client.receiveResponse();
        System.out.println("Response received from server");

    }

    private void addLabelTextFieldPair(JPanel panel, String labelText, JTextField textField, GridBagConstraints constraints) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE); // Match the label text color
        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(label, constraints);

        constraints.gridx = 1;
        panel.add(textField, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignUp();
            }
        });
    }
}
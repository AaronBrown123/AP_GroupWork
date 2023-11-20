package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Model.Customer;

public class AddCustomer extends JFrame {
    private JTextField customerIDField;
    private JTextField nameField;
    private JTextField passwordField;
    private JTextField contactField;

    public AddCustomer() {
        initialize();
    }

    private void initialize() {
        setTitle("Add Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        customerIDField = new JTextField(20);
        nameField = new JTextField(20);
        contactField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        addLabelTextFieldPair(panel, "Customer ID:", customerIDField, constraints);
        addLabelTextFieldPair(panel, "Name:", nameField, constraints);
        addLabelTextFieldPair(panel, "Contact:", contactField, constraints);
        addLabelTextFieldPair(panel, "Password:", passwordField, constraints);

        JButton addButton = new JButton("Add Customer");
        constraints.gridwidth = 2;
        constraints.gridy++;
        panel.add(addButton, constraints);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerID = customerIDField.getText();
                String name = nameField.getText();
                String contact = contactField.getText();
                String password = passwordField.getText();

                double accountBal = 0.0;

                sendCustomerToServer(customerID, name, contact, password, accountBal);

                customerIDField.setText("");
                nameField.setText("");
                contactField.setText("");
                passwordField.setText("");
            }
        });

        add(panel);
    }

    private void sendCustomerToServer(String customerID, String name, String contact, String password, double accountBal) {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            Customer customer = new Customer(customerID, name, contact, password, accountBal);

            // Send customer to the server
            oos.writeObject(customer);
            System.out.println("Customer sent to server");

            // TODO: Handle the response from the server if needed

        } catch (IOException ex) {
            ex.printStackTrace();
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
                new AddCustomer().setVisible(true);
            }
        });
    }
}

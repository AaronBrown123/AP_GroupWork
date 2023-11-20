package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDashboard extends JFrame {

    public EmployeeDashboard() {
        super("Employee Dashboard");

        // Create buttons for different employee services
        JButton viewRequestsButton = new JButton("View Rental Requests");
        JButton viewInventoryButton = new JButton("View Inventory");
        JButton scheduleEquipmentButton = new JButton("Schedule Equipment");
        JButton respondToCustomersButton = new JButton("Respond to Customers");
        JButton createDocumentsButton = new JButton("Create Documents");

        // Set layout
        setLayout(new GridLayout(5, 1));

        // Add buttons to the frame
        add(viewRequestsButton);
        add(viewInventoryButton);
        add(scheduleEquipmentButton);
        add(respondToCustomersButton);
        add(createDocumentsButton);

        // Set up button action listeners
        viewRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle viewing rental requests
                JOptionPane.showMessageDialog(EmployeeDashboard.this, "Viewing Rental Requests");
            }
        });

        viewInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle viewing inventory
                JOptionPane.showMessageDialog(EmployeeDashboard.this, "Viewing Inventory");
            }
        });

        scheduleEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle scheduling equipment
                JOptionPane.showMessageDialog(EmployeeDashboard.this, "Scheduling Equipment");
            }
        });

        respondToCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle responding to customers
                JOptionPane.showMessageDialog(EmployeeDashboard.this, "Responding to Customers");
            }
        });

        createDocumentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle creating documents
                JOptionPane.showMessageDialog(EmployeeDashboard.this, "Creating Documents");
            }
        });

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeDashboard();
            }
        });
    }
}

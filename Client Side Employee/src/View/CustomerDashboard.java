package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboard extends JFrame {

    public CustomerDashboard() {
        super("Customer Dashboard");

        // Create buttons for different customer services
        JButton viewAvailableEquipmentButton = new JButton("View Available Equipment");
        JButton viewTransactionHistoryButton = new JButton("View Transaction History");
        JButton viewSingleTransactionButton = new JButton("View Single Transaction");
        JButton leaveMessageButton = new JButton("Leave Message");

        // Set layout
        setLayout(new GridLayout(4, 1));

        // Add buttons to the frame
        add(viewAvailableEquipmentButton);
        add(viewTransactionHistoryButton);
        add(viewSingleTransactionButton);
        add(leaveMessageButton);

        // Set up button action listeners
        viewAvailableEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle viewing available equipment
                JOptionPane.showMessageDialog(CustomerDashboard.this, "Viewing Available Equipment");
            }
        });

        viewTransactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle viewing transaction history
                JOptionPane.showMessageDialog(CustomerDashboard.this, "Viewing Transaction History");
            }
        });

        viewSingleTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle viewing a single transaction
                JOptionPane.showMessageDialog(CustomerDashboard.this, "Viewing Single Transaction");
            }
        });

        leaveMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle leaving a message
                JOptionPane.showMessageDialog(CustomerDashboard.this, "Leaving a Message");
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
                new CustomerDashboard();
            }
        });
    }
}

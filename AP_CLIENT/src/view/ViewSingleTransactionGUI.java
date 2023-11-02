package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewSingleTransactionGUI extends JFrame {
    private JComboBox<String> transactionComboBox;
    private JTextArea transactionDetailsTextArea;

    public ViewSingleTransactionGUI() {
        setTitle("View Single Past Transaction");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JLabel transactionLabel = new JLabel("Select a Past Transaction:");
        String[] transactions = {"Transaction 1", "Transaction 2", "Transaction 3"}; // Replace with actual transaction IDs or names
        transactionComboBox = new JComboBox<>(transactions);

        JButton viewButton = new JButton("View Transaction Details");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement logic to fetch details of the selected transaction.
                // Display the result in the transactionDetailsTextArea.
                String selectedTransaction = (String) transactionComboBox.getSelectedItem();
                
                // For demonstration purposes, display a sample transaction details.
                String transactionDetails = "Transaction ID: " + selectedTransaction + "\n"
                        + "Date of Rental: 01/15/2023\n"
                        + "Equipment Rented: Equipment 1, Equipment 2\n"
                        + "Total Amount Paid: $250";
                transactionDetailsTextArea.setText(transactionDetails);
            }
        });

        transactionDetailsTextArea = new JTextArea();
        transactionDetailsTextArea.setEditable(false);

        panel.add(transactionLabel);
        panel.add(transactionComboBox);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(transactionDetailsTextArea), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewSingleTransactionGUI().setVisible(true);
            }
        });
    }
}

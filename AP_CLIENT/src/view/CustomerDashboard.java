package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboard extends JFrame {
    public CustomerDashboard() {
        setTitle("Customer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        
        JButton viewAvailableEquipmentButton = new JButton("View Available Equipment");
        viewAvailableEquipmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the View Available Equipment screen
            	 ViewAvailableEquipmentGUI viewEquipmentScreen = new ViewAvailableEquipmentGUI();
                 viewEquipmentScreen.setVisible(true);
            }
        });
         
        
        JButton viewPastTransactionsButton = new JButton("View Past Transactions");
        viewPastTransactionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the View Past Transactions screen
            	 ViewPastTransactionsGUI viewPastTransactionScreen = new ViewPastTransactionsGUI();
            	 viewPastTransactionScreen.setVisible(true);
            
            }
        });
        
        JButton viewSingleTransactionButton = new JButton("View Single Past Transaction");
        viewSingleTransactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the View Single Past Transaction screen
            	 ViewSingleTransactionGUI viewSingleTransactionScreen = new ViewSingleTransactionGUI();
            	 viewSingleTransactionScreen.setVisible(true);
            }
        });
        
        JButton leaveMessageButton = new JButton("Leave a Message");
        leaveMessageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Leave a Message screen
            	 LeaveMessageGUI LeaveMessageScreen = new LeaveMessageGUI();
            	 LeaveMessageScreen.setVisible(true);  
            }
        });

        panel.add(viewAvailableEquipmentButton);
        panel.add(viewPastTransactionsButton);
        panel.add(viewSingleTransactionButton);
        panel.add(leaveMessageButton);

        add(panel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomerDashboard().setVisible(true);
            }
        });
    }
}

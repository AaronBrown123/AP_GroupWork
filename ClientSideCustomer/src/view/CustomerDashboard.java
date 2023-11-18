package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.UserSession;

public class CustomerDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel title;
    private JButton lodgeBtn;
    private JButton viewSingleTransactions;
    private JButton viewTransactions;
    private JButton rentalRequest;

    public CustomerDashboard() {
        String userId = UserSession.getInstance().getUserId();

        setTitle("Customer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(160, 160, 160));
        setResizable(false);
        setBounds(700, 300, 584, 531);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        title = new JLabel();
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setText("Customer Dashboard");
        title.setBounds(150, 20, 480, 60);
        add(title);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical arrangement
        buttonPanel.setBounds(150, 105, 280, 150);

        lodgeBtn = createButton("Leave Message");
        viewSingleTransactions = createButton("View Single Transactions");
        viewTransactions = createButton("View Past Transactions");
        rentalRequest = createButton("Rent Equipment(s)");

        buttonPanel.add(lodgeBtn);
        buttonPanel.add(viewSingleTransactions);
        buttonPanel.add(viewTransactions);
        buttonPanel.add(rentalRequest);

        add(buttonPanel);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false); // Remove button focus border
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height)); // Set maximum width
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Align buttons to center horizontally
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(text);
            }
        });
        return button;
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "Leave Message":
                new LeaveMessage();
                dispose();
                break;
            case "View Single Transactions":
                new ViewSingleTransactions();
                dispose();
                break;
            case "View Past Transactions":
                ViewTransactions view = new ViewTransactions();
                dispose();
                break;
            case "Rent Equipment(s)":
                new ViewByCategory();
                dispose();
                break;
            // Add more cases as needed for additional buttons
        }
    }

    public static void main(String args[]) {
        new CustomerDashboard();
    }
}
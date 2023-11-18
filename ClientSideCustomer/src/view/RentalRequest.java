package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.UserSession;

public class RentalRequest {
    private JFrame frame = new JFrame("Rental Request");

    public JMenuBar serviceBar;
    public JMenu serviceMenu;
    public JMenuItem menuItem;
    public JPanel panel = new JPanel();

    JLabel titleLbl, equipmentNameLbl, eventDateLbl;
    JTextField equipmentNameField, eventDateField;
    JButton requestBtn;

    public RentalRequest() {
    	String userId = UserSession.getInstance().getUserId();

        frame.setResizable(false);
        frame.setBounds(700, 300, 980, 591);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // Center output on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(160, 160, 160));

        titleLbl = new JLabel("Rental Request");
        titleLbl.setBounds(20, 25, 250, 40);
        titleLbl.setFont(new Font("Serif", Font.BOLD, 18));
        titleLbl.setForeground(Color.black);
        frame.add(titleLbl);

        equipmentNameLbl = new JLabel("Equipment Name:");
        equipmentNameLbl.setBounds(20, 70, 150, 30);
        equipmentNameLbl.setFont(new Font("Serif", Font.BOLD, 14));
        frame.add(equipmentNameLbl);

        equipmentNameField = new JTextField(30);
        equipmentNameField.setBounds(180, 70, 200, 30);
        frame.add(equipmentNameField);

        eventDateLbl = new JLabel("Event Date:");
        eventDateLbl.setBounds(20, 120, 150, 30);
        eventDateLbl.setFont(new Font("Serif", Font.BOLD, 14));
        frame.add(eventDateLbl);

        eventDateField = new JTextField(30);
        eventDateField.setBounds(180, 120, 200, 30);
        frame.add(eventDateField);

        requestBtn = new JButton("Request");
        requestBtn.setFont(new Font("Serif", Font.BOLD, 16));
        requestBtn.setForeground(Color.white);
        requestBtn.setBorderPainted(false);
        requestBtn.setBounds(20, 170, 120, 35);
        requestBtn.setBackground(new Color(96, 96, 96));
        requestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action when the "Request" button is clicked
                // You can add relevant functionality here
            }
        });
        frame.add(requestBtn);

        frame.setVisible(true);
        navbar();
    }

    public void navbar() {
        serviceBar = new JMenuBar();
        serviceMenu = new JMenu("Services");
        serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
        // Add menu items similar to the existing GUI
        // ...

        // Code for other menu items

        serviceBar.add(serviceMenu);

        serviceMenu = new JMenu("Back");
        // Back menu functionality
        // ...

        serviceBar.add(serviceMenu);

        serviceMenu = new JMenu("Log Out");
        // Log Out menu functionality
        // ...

        serviceBar.add(serviceMenu);

        frame.add(serviceBar);
        frame.setJMenuBar(serviceBar);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new RentalRequest();
    }
}
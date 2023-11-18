package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton signUpBtn;
    private JButton customerBtn;
    private JLabel title, title2;
    private TrayIcon trayIcon;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Image image = toolkit.getImage("C:/Users/PC/eclipse-workspace/ClientSideCustomer/src/images/Grizzley.png");

    // Adding a simple PopupMenu
    private PopupMenu popup = new PopupMenu();
    private MenuItem exitItem = new MenuItem("Exit");

    public WelcomeWindow() {
        setTitle("Grizzly Entertainment Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(160, 160, 160));
        setResizable(false);
        setBounds(700, 300, 584, 531);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        // Create an ImageIcon from the image for better scaling
        try {
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(50, 30, 484, 150); // Adjust these values based on your image size
            add(imageLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        title = new JLabel();
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setText("WELCOME TO GRIZZLEY ENT.");
        title.setBounds(50, 200, 500, 40);
        add(title);

        title2 = new JLabel();
        title2.setFont(new Font("Arial", Font.BOLD, 32));
        title2.setForeground(Color.WHITE);
        title2.setText("WE VALUE YOUR SERVICE.");
        title2.setBounds(90, 240, 500, 40);
        add(title2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(null);

        signUpBtn = new JButton("SIGN UP");
        customerBtn = new JButton("LOGIN");

        signUpBtn.setFont(new Font("Arial", Font.BOLD, 18));
        signUpBtn.setBackground(Color.BLACK);
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setBorderPainted(false);
        signUpBtn.setFocusPainted(false);

        customerBtn.setFont(new Font("Arial", Font.BOLD, 18));
        customerBtn.setForeground(Color.WHITE);
        customerBtn.setBackground(Color.BLACK);
        customerBtn.setBorderPainted(false);
        customerBtn.setFocusPainted(false);

        signUpBtn.setBounds(20, 20, 180, 50);
        customerBtn.setBounds(240, 20, 180, 50);

        buttonPanel.setBounds(80, 300, 460, 90);
        buttonPanel.add(signUpBtn);
        buttonPanel.add(customerBtn);
        add(buttonPanel);

        // Initialize the PopupMenu
        popup.add(exitItem);

        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
      
                // TODO: Add logic for sign up
                new SignUp();
            }
        });

        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomerLoginWindow();
            }
        });

        // Add the ActionListener for the exitItem in the PopupMenu
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Initialize the TrayIcon
        trayIcon = new TrayIcon(image, "Grizzly Entertainment", popup);
        trayIcon.setImageAutoSize(true);

        // Check if the SystemTray is supported before adding the TrayIcon
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            try {
                tray.add(trayIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setVisible(true);
    }

    public static void main(String args[]) {
        new WelcomeWindow();
    }
}
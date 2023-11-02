package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton; // New button for Sign Up

    public LoginGUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 30, 100, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 70, 100, 25);

        usernameField = new JTextField();
        usernameField.setBounds(160, 30, 150, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 70, 150, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(160, 110, 80, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (isValidCustomer(username, password)) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Welcome, Customer!");
                } else if (isValidEmployee(username, password)) {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Welcome, Employee!");
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Invalid login credentials");
                }
            }
        });

        signUpButton = new JButton("Sign Up"); // Create the Sign Up button
        signUpButton.setBounds(250, 110, 80, 30); // Set the position and size
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Sign Up button action here
            	UserSelectionGUI userSelection = new UserSelectionGUI();            
                // Add your Sign Up logic here
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton); // Add the Sign Up button to the panel
        add(panel);

        setLocationRelativeTo(null);
    }

    // isValidCustomer and isValidEmployee methods remain the same

    private boolean isValidCustomer(String username, String password) {
        // Replace this with your customer authentication logic
        return username.equals("customer") && password.equals("customer123");
    }

    private boolean isValidEmployee(String username, String password) {
        // Replace this with your employee authentication logic
        return username.equals("employee") && password.equals("employee123");
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }
}

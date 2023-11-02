package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaveMessageGUI extends JFrame {
    private JTextField nameField;
    private JTextField contactField;
    private JTextArea messageTextArea;

    public LeaveMessageGUI() {
        setTitle("Leave a Message");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel nameLabel = new JLabel("Your Name:");
        nameField = new JTextField();

        JLabel contactLabel = new JLabel("Contact Information:");
        contactField = new JTextField();

        JLabel messageLabel = new JLabel("Message:");
        messageTextArea = new JTextArea();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement logic to save the message to your system.
                String name = nameField.getText();
                String contact = contactField.getText();
                String message = messageTextArea.getText();

                // For demonstration purposes, display a confirmation message.
                String confirmationMessage = "Message Submitted:\n\n"
                        + "Name: " + name + "\n"
                        + "Contact Information: " + contact + "\n"
                        + "Message: " + message;
                JOptionPane.showMessageDialog(LeaveMessageGUI.this, confirmationMessage);

                // You can save the message to your database or perform other actions as needed.
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(contactLabel);
        panel.add(contactField);
        panel.add(messageLabel);
        panel.add(new JScrollPane(messageTextArea));
        panel.add(submitButton);

        add(panel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LeaveMessageGUI().setVisible(true);
            }
        });
    }
}

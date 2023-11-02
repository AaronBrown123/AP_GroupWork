package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MessagingGUI extends JFrame {
    private JList<String> messagesList;
    private DefaultListModel<String> listModel;
    private JTextArea messageTextArea;
    private JButton sendButton;

    public MessagingGUI() {
        setTitle("Messaging System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Customer Messages");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        messagesList = new JList<>(listModel);
        messagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(messagesList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        messageTextArea = new JTextArea();
        inputPanel.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement logic to send the message to the customer.
                String message = messageTextArea.getText();
                
                // For demonstration purposes, display the sent message in the list.
                listModel.addElement("Employee: " + message);
                messageTextArea.setText("");
                
                // You would typically send the message to the customer and receive their response.
                // Handle incoming customer messages in a similar way.
            }
        });
        inputPanel.add(sendButton, BorderLayout.SOUTH);

        panel.add(inputPanel, BorderLayout.SOUTH);

        // Simulated customer messages (replace with actual data)
        ArrayList<String> customerMessages = new ArrayList<>();
        customerMessages.add("Customer: Do you have stage equipment available?");
        customerMessages.add("Customer: How much is the lighting equipment rental?");
        customerMessages.add("Customer: When can I schedule the power equipment?");

        for (String message : customerMessages) {
            listModel.addElement(message);
        }

        add(panel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MessagingGUI().setVisible(true);
            }
        });
    }
}

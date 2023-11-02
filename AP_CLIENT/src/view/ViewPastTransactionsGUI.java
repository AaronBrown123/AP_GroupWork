package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPastTransactionsGUI extends JFrame {
    private JList<String> transactionList;
    private DefaultListModel<String> listModel;

    public ViewPastTransactionsGUI() {
        setTitle("Past Transactions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        listModel = new DefaultListModel<>();
        transactionList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(transactionList);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fetch and update the list of past transactions.
                // You should replace this sample data with actual data.
                listModel.clear(); // Clear the current list
                listModel.addElement("Date: 2023-10-01, Equipment: Staging, Money Paid: $100");
                listModel.addElement("Date: 2023-10-05, Equipment: Lighting, Money Paid: $150");
                listModel.addElement("Date: 2023-10-10, Equipment: Power, Money Paid: $120");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewPastTransactionsGUI().setVisible(true);
            }
        });
    }
}


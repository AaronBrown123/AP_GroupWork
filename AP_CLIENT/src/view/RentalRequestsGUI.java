package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RentalRequestsGUI extends JFrame {
    private JList<String> rentalRequestsList;
    private DefaultListModel<String> listModel;

    public RentalRequestsGUI() {
        setTitle("View Rental Requests");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Rental Requests");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        rentalRequestsList = new JList<>(listModel);
        rentalRequestsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(rentalRequestsList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Simulated rental requests (replace with actual data)
        ArrayList<String> rentalRequestData = new ArrayList<>();
        rentalRequestData.add("Request 1 - Customer: John Doe, Equipment: Staging, Date: 2023-11-15");
        rentalRequestData.add("Request 2 - Customer: Jane Smith, Equipment: Lighting, Date: 2023-11-20");

        for (String request : rentalRequestData) {
            listModel.addElement(request);
        }

        add(panel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RentalRequestsGUI().setVisible(true);
            }
        });
    }
}


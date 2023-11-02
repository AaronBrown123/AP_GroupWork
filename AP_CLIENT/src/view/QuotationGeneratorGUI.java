package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuotationGeneratorGUI extends JFrame {
    private JTextField customerNameField;
    private JTextField equipmentField;
    private JTextField rentalDurationField;
    private JTextArea quotationTextArea;

    public QuotationGeneratorGUI() {
        setTitle("Create Quotation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel titleLabel = new JLabel("Quotation Generator");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField();
        panel.add(customerNameLabel);
        panel.add(customerNameField);

        JLabel equipmentLabel = new JLabel("Equipment:");
        equipmentField = new JTextField();
        panel.add(equipmentLabel);
        panel.add(equipmentField);

        JLabel rentalDurationLabel = new JLabel("Rental Duration (days):");
        rentalDurationField = new JTextField();
        panel.add(rentalDurationLabel);
        panel.add(rentalDurationField);

        JButton generateButton = new JButton("Generate Quotation");
        panel.add(generateButton);

        quotationTextArea = new JTextArea();
        quotationTextArea.setEditable(false);
        panel.add(new JScrollPane(quotationTextArea));

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameField.getText();
                String equipment = equipmentField.getText();
                int rentalDuration = Integer.parseInt(rentalDurationField.getText());

                // Generate the quotation based on the input.
                double equipmentCost = calculateEquipmentCost(equipment, rentalDuration);
                double totalCost = equipmentCost;

                String quotation = "Quotation for " + customerName + "\n\n"
                        + "Equipment: " + equipment + "\n"
                        + "Rental Duration: " + rentalDuration + " days\n"
                        + "Equipment Cost: $" + equipmentCost + "\n"
                        + "Total Cost: $" + totalCost;

                quotationTextArea.setText(quotation);
            }
        });

        add(panel);
        setLocationRelativeTo(null);
    }

    // Method to calculate equipment cost (replace with actual logic)
    private double calculateEquipmentCost(String equipment, int rentalDuration) {
        // Replace with your pricing logic based on the equipment type and duration.
        return 100.0 * rentalDuration; // Example pricing: $100 per day
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuotationGeneratorGUI().setVisible(true);
            }
        });
    }
}


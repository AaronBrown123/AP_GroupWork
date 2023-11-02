package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAvailableEquipmentGUI extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JTextField dateField;
    private JTextArea resultTextArea;

    public ViewAvailableEquipmentGUI() {
        setTitle("View Available Equipment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel categoryLabel = new JLabel("Select Equipment Category:");
        String[] categories = {"Staging", "Lighting", "Power", "Sound"};
        categoryComboBox = new JComboBox<>(categories);

        JLabel dateLabel = new JLabel("Enter Date (MM/DD/YYYY):");
        dateField = new JTextField();

        JButton viewButton = new JButton("View Available Equipment");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement logic to fetch available equipment for the selected category and date.
                // Display the result in the resultTextArea.
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                String selectedDate = dateField.getText();
                
                // For demonstration purposes, display a sample result.
                String result = "Available equipment for " + selectedCategory + " on " + selectedDate + ":\n"
                        + "Equipment 1: $100 per day\n"
                        + "Equipment 2: $150 per day";
                resultTextArea.setText(result);
            }
        });

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        panel.add(categoryLabel);
        panel.add(categoryComboBox);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(viewButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewAvailableEquipmentGUI().setVisible(true);
            }
        });
    }
}

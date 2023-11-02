package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class EquipmentInventoryGUI extends JFrame {
    private JTable equipmentTable;
    private DefaultTableModel tableModel;

    public EquipmentInventoryGUI() {
        setTitle("View Equipment Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Equipment Inventory");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create a table model with columns for Equipment Name, Category, and Rental Status
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Equipment Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Rental Status");

        equipmentTable = new JTable(tableModel);
        equipmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Simulated equipment inventory data (replace with actual data)
        ArrayList<String[]> equipmentData = new ArrayList<>();
        equipmentData.add(new String[]{"Staging Equipment 1", "Staging", "Available"});
        equipmentData.add(new String[]{"Lighting Equipment 2", "Lighting", "Booked"});
        equipmentData.add(new String[]{"Power Equipment 3", "Power", "Available"});
        equipmentData.add(new String[]{"Sound Equipment 4", "Sound", "Available"});

        for (String[] equipment : equipmentData) {
            tableModel.addRow(equipment);
        }

        add(panel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EquipmentInventoryGUI().setVisible(true);
            }
        });
    }
}

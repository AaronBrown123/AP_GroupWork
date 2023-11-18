package view;

import model.Equipment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class EquipmentInventoryGUI extends JFrame {
    private JTable equipmentTable;
    private DefaultTableModel tableModel;

    public EquipmentInventoryGUI() {
        setTitle("Equipment Inventory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 400);

        String[] columnNames = {"Equipment ID", "Equipment Name", "Category", "Availability", "Cost per Day"};
        tableModel = new DefaultTableModel(columnNames, 0);

        equipmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        add(scrollPane, BorderLayout.CENTER);

        List<Equipment> equipmentList = createSampleEquipmentData();
        displayEquipmentInformation(equipmentList);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private List<Equipment> createSampleEquipmentData() {
        List<Equipment> equipmentList = new ArrayList<>();

        // Add some sample equipment data
        equipmentList.add(new Equipment("E001", "Sound System", "Sound", "Available", 50.0));
        equipmentList.add(new Equipment("E002", "Projector", "Visual", "Booked", 30.0));
        equipmentList.add(new Equipment("E003", "Stage Lights", "Lighting", "Available", 40.0));

        return equipmentList;
    }
    public Equipment getSelectedEquipment() {
        int selectedRowIndex = equipmentTable.getSelectedRow();

        if (selectedRowIndex != -1) {
            // Get data from the selected row
            DefaultTableModel tableModel = (DefaultTableModel) equipmentTable.getModel();
            String equipmentId = (String) tableModel.getValueAt(selectedRowIndex, 0);
            String equipmentName = (String) tableModel.getValueAt(selectedRowIndex, 1);
            String equipmentCategory = (String) tableModel.getValueAt(selectedRowIndex, 2);
            String availabilityStatus = (String) tableModel.getValueAt(selectedRowIndex, 3);
            double equipmentCostPerDay = (double) tableModel.getValueAt(selectedRowIndex, 4);

            // Create and return Equipment object
            Equipment selectedEquipment = new Equipment(equipmentId, equipmentName, equipmentCategory,
                    availabilityStatus, equipmentCostPerDay);
            return selectedEquipment;
        } else {
            // No row selected, return null or handle accordingly
            return null;
        }
    }

    private void displayEquipmentInformation(List<Equipment> equipmentList) {
        for (Equipment equipment : equipmentList) {
            Object[] rowData = {
                    equipment.getEquipment_id(),
                    equipment.getEquipment_name(),
                    equipment.getEquipment_category(),
                    equipment.getAvailability_status(),
                    equipment.getEquipment_cost_per_day()
            };
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EquipmentInventoryGUI());
    }
}

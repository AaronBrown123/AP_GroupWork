package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ScheduleEquipmentGUI extends JFrame {
    private JComboBox<String> equipmentComboBox;
    private JTextField eventDateField;
    private JTextArea scheduleTextArea;
    private Set<String> scheduledEquipment;

    public ScheduleEquipmentGUI() {
        setTitle("Schedule Equipment for an Event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Schedule Equipment for an Event");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        equipmentComboBox = new JComboBox<>();
        eventDateField = new JTextField();
        scheduleTextArea = new JTextArea();
        scheduledEquipment = new HashSet<>();

        JLabel equipmentLabel = new JLabel("Select Equipment:");
        panel.add(equipmentLabel);
        panel.add(equipmentComboBox);

        JLabel dateLabel = new JLabel("Event Date (MM/DD/YYYY):");
        panel.add(dateLabel);
        panel.add(eventDateField);

        JButton scheduleButton = new JButton("Schedule Equipment");
        panel.add(scheduleButton);

        scheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String equipment = (String) equipmentComboBox.getSelectedItem();
                String eventDate = eventDateField.getText();

                if (!equipment.isEmpty() && !eventDate.isEmpty()) {
                    if (!scheduledEquipment.contains(equipment)) {
                        scheduledEquipment.add(equipment);
                        scheduleTextArea.append("Scheduled " + equipment + " for " + eventDate + "\n");
                    } else {
                        JOptionPane.showMessageDialog(ScheduleEquipmentGUI.this,
                                "Equipment is already scheduled for an event.");
                    }
                } else {
                    JOptionPane.showMessageDialog(ScheduleEquipmentGUI.this,
                            "Please select equipment and enter an event date.");
                }
            }
        });

        panel.add(new JScrollPane(scheduleTextArea));

        // Simulated equipment data (replace with actual data)
        ArrayList<String> equipmentList = new ArrayList<>();
        equipmentList.add("Staging Equipment 1");
        equipmentList.add("Lighting Equipment 2");
        equipmentList.add("Power Equipment 3");
        equipmentList.add("Sound Equipment 4");

        for (String equipment : equipmentList) {
            equipmentComboBox.addItem(equipment);
        }

        add(panel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScheduleEquipmentGUI().setVisible(true);
            }
        });
    }
}

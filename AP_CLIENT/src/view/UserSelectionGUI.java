package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSelectionGUI {
    public UserSelectionGUI() {
        JFrame frame = new JFrame("User Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel label = new JLabel("Select User Type:");
        frame.add(label);

        JRadioButton employeeRadio = new JRadioButton("Employee");
        JRadioButton customerRadio = new JRadioButton("Customer");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(employeeRadio);
        buttonGroup.add(customerRadio);

        frame.add(employeeRadio);
        frame.add(customerRadio);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeRadio.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Employee selected");
                    // Add your employee-specific logic here
                    AddEmployee add = new AddEmployee(); // Create an instance of AddCustomer GUI
                    add.setVisible(true);
                } else if (customerRadio.isSelected()) {
                   // JOptionPane.showMessageDialog(frame, "Customer selected");
                    // Add your customer-specific logic here   
                          AddCustomer add = new AddCustomer(); // Create an instance of AddCustomer GUI
                         add.setVisible(true);
                  
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a user type.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserSelectionGUI();
            }
        });
    }
}

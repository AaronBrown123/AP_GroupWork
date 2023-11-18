package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Equipment;
import model.RentalRequest;
import model.ScheduleEquipment;
import model.UserSession;
import view.Client;

public class ViewByCategory {
    // ... (existing code)
	private JFrame frame = new JFrame("View Available Equipment By Category");
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
	public JTextArea textArea;
	public JTextField searchField = new JTextField(30);
    public JButton searchBtn;
    public JTable result = new JTable();
	public JPanel panel = new JPanel();
    public JScrollPane scrollPane = new JScrollPane(result);
    
    JLabel titleLbl;
	String category[]= {"Sound", "Staging", "Lighting", "Power"};
	JComboBox<String> categoryBox = new JComboBox<>(category);
	String categorySelected = "";
	
	String userId = UserSession.getInstance().getUserId();

	
	public ViewByCategory() 
	{
		//String userId = UserSession.getInstance().getUserId();

		frame.setResizable(false);
		frame.setBounds(700, 300, 980, 591);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));

		titleLbl = new JLabel();
		titleLbl = new JLabel("Select a Category: ");
	    titleLbl.setBounds(20,25,250,40);
	    titleLbl.setFont(new Font("Serif", Font.BOLD, 18));
	    titleLbl.setForeground(Color.black);
	    frame.add(titleLbl);
	    
		categoryBox.setSelectedIndex(0);
		categoryBox.setBounds(170,33,200,30);
		categoryBox.setFont(new Font("Serif", Font.PLAIN, 14));
        categoryBox.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                Object selected = categoryBox.getSelectedItem();
                if(selected.toString().equals("Sound"))
                {
                	categorySelected = "Sound";
                }
                else if(selected.toString().equals("Staging"))
                {
                	categorySelected = "Staging";
                }  else if(selected.toString().equals("Lighting"))
                {
                	categorySelected = "Lighting";
                }  if(selected.toString().equals("Power"))
                {
                	categorySelected = "Power";
                }
            }
        });
		frame.add(categoryBox);
		
		searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Serif", Font.BOLD, 16));
		searchBtn.setForeground(Color.white);
		searchBtn.setBorderPainted(false);
		searchBtn.setBounds(390, 33, 80, 35);
		searchBtn.setBackground(new Color(96, 96, 96));
		//frame.getContentPane().add(searchBtn);
		searchBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Client client = new Client();
				client.sendAction("ByCategory");
				client.sendCategory(categorySelected);
				client.receiveResponse();
			}
	});
		frame.add(searchBtn);
		frame.setVisible(true);
	    navbar();
	 }
	public void navbar() 
	{
		//Create the Menu Bar
		serviceBar = new JMenuBar();
		serviceMenu = new JMenu("Services");
		serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
		serviceMenu.setMnemonic(KeyEvent.VK_A);
		serviceMenu.getAccessibleContext().setAccessibleDescription(null);
		serviceMenu.setBounds(250,70,50,15);
	    serviceMenu.setOpaque(true);
	    serviceBar.add(serviceMenu);
		
	  //menu items
	  		menuItem = new JMenuItem("Lodge New Message", KeyEvent.VK_T);
	  		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
	  		menuItem.setBackground(new Color(255, 255, 255));
	  		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
	  		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
	  		menuItem.addActionListener(new ActionListener()
	  		{
	  			@Override
	  			public void actionPerformed(ActionEvent e) {
	  				frame.dispose();
	  				new LeaveMessage();
	  			}	
	  		});
	  		serviceMenu.add(menuItem);		
	  		
	  		menuItem = new JMenuItem("View Single Transaction", KeyEvent.VK_T);
	  		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
	  		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
	  		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
	  		menuItem.setBackground(new Color(255, 255, 255));
	  		menuItem.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e) 
	  			{
	  				frame.dispose();
	  				new ViewSingleTransactions();
	  			}			
	  		});
	  		serviceMenu.add(menuItem);
	  		
	  		menuItem = new JMenuItem("View Past Transactions", KeyEvent.VK_T);
	  		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
	  		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
	  		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
	  		menuItem.setBackground(new Color(255, 255, 255));
	  		menuItem.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e)
	  			{
	  				frame.dispose();
	  				new ViewTransactions();
	  			}
	  		});
	  		serviceMenu.add(menuItem);
	  		
	  		menuItem = new JMenuItem("Rent Equipment(s)", KeyEvent.VK_T);
	  		menuItem.setFont(new Font("Serif", Font.BOLD, 14));
	  		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
	  		menuItem.getAccessibleContext().setAccessibleDescription("Complaint");
	  		menuItem.setBackground(new Color(255, 255, 255));
	  		menuItem.addActionListener(new ActionListener(){
	  			@Override
	  			public void actionPerformed(ActionEvent e)
	  			{
	  				frame.dispose();
	  				new ViewByCategory();
	  			}
	  		});
	  		
	  		serviceMenu.add(menuItem);
	  	
	  		serviceMenu = new JMenu("Back");
	  		serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
	  		serviceMenu.setMnemonic(KeyEvent.VK_A);
	  		serviceMenu.getAccessibleContext().setAccessibleDescription(null);
	  		serviceMenu.addMouseListener(new MouseListener()
	  		{
	  			@Override
	  			public void mouseClicked(MouseEvent e) {
	  				frame.dispose();
	  				new CustomerDashboard();
	  			}
	  			@Override
	  			public void mousePressed(MouseEvent e) {
	  				// TODO Auto-generated method stub
	  			}
	  			@Override
	  			public void mouseReleased(MouseEvent e) {
	  				// TODO Auto-generated method stub
	  			}
	  			@Override
	  			public void mouseEntered(MouseEvent e) {
	  				// TODO Auto-generated method stub
	  			}
	  			@Override
	  			public void mouseExited(MouseEvent e) {
	  				// TODO Auto-generated method stub
	  			}
	  			});
	  		serviceBar.add(serviceMenu);
		
		serviceMenu = new JMenu("Log Out");
		serviceMenu.setFont(new Font("Serif", Font.BOLD, 14));
		serviceMenu.setMnemonic(KeyEvent.VK_A);
		serviceMenu.getAccessibleContext().setAccessibleDescription(null);
		serviceMenu.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new CustomerLoginWindow();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			});
			serviceBar.add(serviceMenu);
	    
		class MenuListener
		{
		  MenuListener listener =  new MenuListener();
		}
		frame.add(serviceBar);
		frame.setJMenuBar(serviceBar); 
		frame.setVisible(true);
	}
	public void callClient()
	{
		Client client = new Client();
		client.sendAction("ByCategory");
		client.sendCategory(categorySelected);
		client.receiveResponse();
	}

    public void table(Queue<Equipment> allEquipment) {
        // existing code
    	Object[] columns = {"Equipment_Id", "Name", "Category", "Availability Status", "Cost/Day", "Select"};
        JTable table = new JTable();
        DefaultTableModel mode = (DefaultTableModel) table.getModel();
        table.setFont(new Font("Serif", Font.PLAIN, 14));
        mode.setColumnIdentifiers(columns);

        for (Equipment equipment : allEquipment) {
            Object[] row = {equipment.getEquipment_id(), equipment.getEquipment_name(), equipment.getEquipment_category(),
                    equipment.getAvailability_status(), equipment.getEquipment_cost_per_day(), "Select"};
            mode.addRow(row);
        }

        table.setModel(mode);

        // Add button column
        ButtonColumn buttonColumn = new ButtonColumn(table, new ButtonColumn.Action() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                // Handle the button click for the selected row
                // You can access the data in the selected row using table.getModel().getValueAt(row, columnIndex)
                //JOptionPane.showMessageDialog(frame, "Button clicked for row " + row);
            }
        }, columns.length - 1,frame); // Assuming the "Select" button is the last column

        // existing code
        table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.lightGray);
		table.setSelectionForeground(Color.white);
		table.setGridColor(Color.red);
		table.setRowHeight(30);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setForeground(Color.lightGray);
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(20,80,910,350);
		
		frame.add(scroll);
		frame.setVisible(true);
    }

    // ... (existing code)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewByCategory();
        });
    }
    
}

class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton renderButton;
    private JButton editButton;
    private JTable table;
    private Action action;
    private JFrame frame;
    String userId = UserSession.getInstance().getUserId();

    public ButtonColumn(JTable table, Action action, int column,JFrame frame) {
        this.table = table;
        this.action = action;
        renderButton = new JButton();
        //this.frame = frame;
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        renderButton.setText(value == null ? "" : value.toString());
        return renderButton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        editButton.setText(value == null ? "" : value.toString());
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getEditingRow());
        if (e.getSource() == renderButton) {
            // Handle the button click for rendering (optional)
        } else if (e.getSource() == editButton) {
            // Handle the button click for editing
            showDateInputDialog(row);
        }
        fireEditingStopped();
        action.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row));
    }

    private void showDateInputDialog(int row) {
        // Display a pop-up window to enter a date
    	try {
        String date = JOptionPane.showInputDialog(frame, "Enter Event Date (YYYY-MM-DD):", "Enter Event Date", JOptionPane.PLAIN_MESSAGE);
        if (date != null && !date.isEmpty()) {
            // Get the equipment_id from the selected row in the table
            Object equipmentIdObj = table.getModel().getValueAt(row, 0); // Assuming equipment_id is in the first column
            if (equipmentIdObj instanceof String) {
                String equipment_id = (String) equipmentIdObj;

                // Communicate with the server
                Client client = new Client();
                client.sendAction("Check Availability");
                System.out.println("Message sent to server");
                client.sendAvailability(equipment_id, date);
                System.out.println("Record sent to server");
                client.receiveResponse();
                System.out.println("Response received from server");
                
                if (client.availabilitySuccessful()) {
	                JOptionPane.showMessageDialog(null, "Already BOOKED", "Status", JOptionPane.INFORMATION_MESSAGE);
	                
	            } else {
	            	int choice = JOptionPane.showConfirmDialog(frame, "Do you want to proceed with the rental request?", "Equipment Available", JOptionPane.YES_NO_OPTION);
	                if (choice == JOptionPane.YES_OPTION) {
	                	  addRequest(equipment_id,date);
	                  
	                    
	                } else {
	                    // If user cancels the rental request or selects 'No'
	                    // Optionally, you can provide feedback or take specific actions
	                }
	            	addRequest(equipment_id,date);
	            	scheduleEquipment(equipment_id,date,row);
	            }

                // Handle the server response as needed
                // For example:
                // String serverResponse = client.receiveResponse();
                // System.out.println("Server response: " + serverResponse);

                // Debugging: Print a message to verify the action event firing
                //System.out.println("Action event fired for row: " + row);
            } else {
                System.out.println("Invalid equipment ID type");
            }
        } else {
            System.out.println("No date entered");
        }
        // Fire the action event to notify listeners (if any)
        action.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    public void addRequest(String equipment_id, String date) {
    	Client client = new Client();
    	Random rand = new Random();
    	String requestId = String.format("%04d", rand.nextInt(10000));
    	RentalRequest rentalRequest = new RentalRequest(requestId,userId,equipment_id,date);
        client.sendAction("Add Request");
        System.out.println("Message sent to server");
        client.sendRentalRequest(rentalRequest);
        System.out.println("Record sent to server");
        client.receiveResponse();
        System.out.println("Response received from server");
    }
    
    public void scheduleEquipment(String equipment_id, String date,int row) {
    	Client client = new Client();
    	Random rand = new Random();
    	String scheduleId = String.format("%04d", rand.nextInt(10000));
    	Object equipmentNameObj = table.getModel().getValueAt(row, 1);
        if (equipmentNameObj instanceof String) {
        	String equipment_Name = (String) equipmentNameObj;
        	ScheduleEquipment scheduleEquipment = new ScheduleEquipment(scheduleId,date,equipment_Name,userId,equipment_id);
    		client.sendAction("Schedule Equipment");
            System.out.println("Message sent to server");
            client.sendScheduleEquipment(scheduleEquipment);
            System.out.println("Record sent to server");
            client.receiveResponse();
            System.out.println("Response received from server");
        }
    }
    	

    public interface Action {
        void actionPerformed(ActionEvent e);
    }
   
}
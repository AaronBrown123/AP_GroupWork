package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import view.Client;
import model.Transactions;
import model.UserSession;

public class ViewSingleTransactions {
    private JFrame frame = new JFrame("View Single Transaction");
    public JMenuBar serviceBar;
    public JMenu serviceMenu, subMenu;
    public JMenuItem menuItem;
    public JPanel panel = new JPanel();
    private JButton bckBtn;
    private JLabel titleLbl;
    private JTable result = new JTable();
    private JScrollPane scrollPane;
    JTextField transactionIdField = new JTextField();

    public ViewSingleTransactions() {
    	String userId = UserSession.getInstance().getUserId();
        frame.setResizable(false);
        frame.setBounds(700, 300, 980, 591);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // center output on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(160, 160, 160));
        frame.setVisible(true);

        titleLbl = new JLabel("Transaction ID: ");
        titleLbl.setBounds(20, 10, 290, 35); // x, y, width, length
        titleLbl.setFont(new Font("Serif", Font.BOLD, 16));
        frame.getContentPane().add(titleLbl);

        JTextField transactionIdField = new JTextField();
        transactionIdField.setFont(new Font("Serif", Font.PLAIN, 14));
        transactionIdField.setBounds(155, 10, 130, 30);
        frame.getContentPane().add(transactionIdField);

        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Serif", Font.BOLD, 14));
        searchBtn.setForeground(Color.white);
        searchBtn.setBorderPainted(false);
        searchBtn.setBounds(300, 10, 80, 30);
        searchBtn.setBackground(new Color(96, 96, 96));
        frame.getContentPane().add(searchBtn);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (transactionIdField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Transaction ID Missing!", "View Status", JOptionPane.WARNING_MESSAGE);
                } else {
                	String TransactionId =  transactionIdField.getText();
                    Client client = new Client();                
                    client.sendAction("SingleTransactions");
                    System.out.println("Message sent to server");
                    client.sendTransactionsId(TransactionId);
                    System.out.println("Record sent to server");
                    client.receiveResponse();
                    System.out.println("Response received from server");
                    //displayTransactionDetails(singleTransaction);
                }
            }
        });

        bckBtn = new JButton("Back");
        bckBtn.setFont(new Font("Serif", Font.BOLD, 14));
        bckBtn.setForeground(Color.white);
        bckBtn.setBorderPainted(false);
        bckBtn.setBounds(400, 10, 80, 30);
        bckBtn.setBackground(new Color(96, 96, 96));
        bckBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // You might want to navigate back to the previous screen or dashboard.
            }
        });
        frame.getContentPane().add(bckBtn);
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
        navbar();
    }

    public void navbar() {
        // Code for the menu bar as in the previous class - if required
        // Ensure to add the menu bar to the frame.
    }

    public void displayTransactionDetails(Queue<Transactions> singleTransaction) {
        Object[] columns = {"Transaction_id", "CustomerId", "AmountPaid", "EquipmentName", "PaymentDate"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        for (Transactions transaction : singleTransaction) {
        	transactionIdField.setText(transaction.getCustomer_id());
            Object[] row = {transaction.getTransaction_id(), transaction.getCustomer_id(), transaction.getAmountPaid(),
                    transaction.getEquipment_name(), transaction.getPaymentDate()};
            model.addRow(row);
        }

        result.setModel(model);
        result.setBackground(Color.white);
        result.setForeground(Color.black);
        result.setSelectionBackground(Color.lightGray);
        result.setSelectionForeground(Color.white);
        result.setGridColor(Color.red);
        result.setRowHeight(30);

        scrollPane = new JScrollPane(result);
        scrollPane.setForeground(Color.lightGray);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBounds(20, 80, 930, 350);

        frame.add(scrollPane);
        frame.setVisible(true);
    }
    public void table(Queue<Transactions> singleTransactions) 
	{	
		Object[]columns = {"Transaction_id", "CustomerId", "AmountPaid", "EquipmentName", "PaymentDate"};
		JTable table = new JTable();
		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		mode.setColumnIdentifiers(columns);
	
		for (Transactions transactions: singleTransactions) 
		{
			transactionIdField.setText(transactions.getTransaction_id());
            Object[] row = {transactions.getTransaction_id(),transactions.getCustomer_id(),transactions.getAmountPaid(),transactions.getEquipment_name(),transactions.getPaymentDate()};
			mode.addRow(row);
        }

		table.setModel(mode);
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.lightGray);
		table.setSelectionForeground(Color.white);
		table.setGridColor(Color.red);
		table.setRowHeight(30);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setForeground(Color.lightGray);
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(20,80,930,350);

		frame.add(scroll);
		frame.setVisible(true);
	}
    

    public static void main(String[] args) {
        new ViewSingleTransactions();
    }
}
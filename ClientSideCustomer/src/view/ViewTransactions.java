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
import javax.swing.JCheckBoxMenuItem;
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
import javax.swing.table.DefaultTableModel;

import view.Client;
import model.Transactions;
import model.UserSession;

public class ViewTransactions 
{
	private JFrame frame = new JFrame("View Past Transactions");
	public JMenuBar serviceBar;
	public JMenu serviceMenu, subMenu;
	public JMenuItem menuItem;
	public JTextArea textArea;
	public JTextField searchField = new JTextField(30);
    public JButton searchB = new JButton ("Search");
    public JTable result = new JTable();
	public JPanel panel = new JPanel();
    public JScrollPane scrollPane = new JScrollPane(result);
    private JButton bckBtn;
    private JLabel titleLbl;
    
    //Complaints complaint = new Complaints();
	
	public ViewTransactions() 
	{
		String userId = UserSession.getInstance().getUserId();

		frame.setResizable(false);
		frame.setBounds(700, 300, 980, 591);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); //center output on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(160, 160, 160));
		frame.setVisible(true);
		
		titleLbl = new JLabel("Customer Id: ");
		titleLbl.setBounds(20, 10, 290, 35); //x, y, width, length
	    titleLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    frame.getContentPane().add(titleLbl);
	    
		searchField = new JTextField();
		searchField.setFont(new Font("Serif", Font.PLAIN, 14));
		searchField.setBounds(117, 10, 130, 30);
		searchField.setText(userId);
	    searchField.setEditable(false);
		frame.getContentPane().add(searchField );
		
		searchB = new JButton("Search");
		searchB.setFont(new Font("Serif", Font.BOLD, 14));
		searchB.setForeground(Color.white);
		searchB.setBorderPainted(false);
		searchB.setBounds(265, 10, 80, 30);
		searchB.setBackground(new Color(96, 96, 96));
		frame.getContentPane().add(searchB);
		searchB.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(searchField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Customer Id Missing!","View Status", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Client client = new Client();
					client.sendAction("AllTransactions");
					client.sendCustomerId(searchField.getText());
					client.receiveResponse();
				}
			}
	});
		
		bckBtn = new JButton("Back");
		bckBtn.setFont(new Font("Serif", Font.BOLD, 14));
		bckBtn.setForeground(Color.white);
		bckBtn.setBorderPainted(false);
		bckBtn.setBounds(365, 10, 80, 30);
		bckBtn.setBackground(new Color(96, 96, 96));
		bckBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//new CustomerDashboard();
				frame.dispose();
			}
		});
		frame.getContentPane().add(bckBtn);
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

	public void table(Queue<Transactions> allTransactions) 
	{	
		Object[]columns = {"Transaction_id", "CustomerId", "AmountPaid", "EquipmentName", "PaymentDate"};
		JTable table = new JTable();
		DefaultTableModel mode = (DefaultTableModel) table.getModel();
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		mode.setColumnIdentifiers(columns);
	
		for (Transactions transactions: allTransactions) 
		{
			searchField.setText(transactions.getCustomer_id());
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
		public static void main(String[] args) 
		{
			new ViewTransactions();
		}
}
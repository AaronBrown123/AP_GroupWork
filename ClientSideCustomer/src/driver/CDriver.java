package driver;


import javax.swing.JOptionPane;

import model.Customer;
import model.Equipment;
import model.Messages;
import model.RentalRequest;
import model.ScheduleEquipment;
import model.Staff;
import model.Transactions;
import view.Client;

public class CDriver {

	public static void main(String[] args) {
		// Instantiate the Client
		
      /*Client client = new Client();
       	Equipment equipment = new Equipment("4535","Hacksaw","Staging","Available",10000.00);
        client.sendAction("Add Equipment");
        System.out.println("Message sent to server");
        client.sendEquipment(equipment);
        System.out.println("Record sent to server");
        client.receiveResponse();
        System.out.println("Response received from server");*/
		
		/*Client client = new Client();
		ScheduleEquipment scheduleEquipment = new ScheduleEquipment("4657","2023/11/14","Hacksawzyer","5678","6789");
		client.sendAction("Schedule Equipment");
        System.out.println("Message sent to server");
        client.sendScheduleEquipment(scheduleEquipment);
        System.out.println("Record sent to server");
        client.receiveResponse();
        System.out.println("Response received from server");*/
       /* Client client = new Client();
        String equipment_id = "4535";
        String date= "2023/11/16";
        client.sendAction("Check Availability");
        System.out.println("Message sent to server");
        client.sendAvailability(equipment_id,date);
        System.out.println("Record sent to server");
        client.receiveResponse();
        System.out.println("Response received from server");*/
        /*Transactions transactions = new Transactions("496868","756768",867.87,"Speaker","12/04/2020");
        client.sendAction("Add Transactions");
        System.out.println("Message sent to server");
        client.sendTransactions(transactions);
        System.out.println("Record sent to server");
        client.receiveResponse();
        System.out.println("Response received from server");*/
        
        /*String customerId = "756768";
        client.sendAction("AllTransactions");
        System.out.println("Message sent to server");
        client.sendCustomerId(customerId);
        System.out.println("Record sent to server");
        client.receiveResponse();
        System.out.println("Response received from server");*/
		
		//Client client = new Client();
       	/*RentalRequest rentalRequest = new RentalRequest("35464","5678","36567","11/17/2024");
        client.sendAction("Add Request");
        System.out.println("Message sent to server");
        client.sendRentalRequest(rentalRequest);
        System.out.println("Record sent to server");
        client.receiveResponse();
        System.out.println("Response received from server");*/
        
        

        // Simulating a customer trying to log in
        /*String customerId = "061"; // Replace with the actual customer ID
        String password = "jesus12345"; // Replace with the actual password

        // Sending login action and credentials to the server
        client.sendAction("LoginCustomer");
        System.out.println("Message sent to server");
        
        client.sendLoginCustomer(customerId,password);
        System.out.println("Credentials sent to server");

        // Requesting server to process the login action
        client.receiveResponse();
        System.out.println("Response received from server");
        // Checking the login status
        if (client.isLogInSuccessful()) {
            JOptionPane.showMessageDialog(null, "Customer login successful", "Login Status", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid customer credentials", "Login Status", JOptionPane.ERROR_MESSAGE);
        }

        // Close the connection
        client.closeConnection();*/
    
      
}

}
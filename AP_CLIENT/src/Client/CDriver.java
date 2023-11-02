package Client;


import model.Messages;

public class CDriver {

	public static void main(String[] args) {
	Client client = new Client();
    Messages messages = new Messages ("061","John","Public");
   
    
    client.sendAction("Add Messages");
    System.out.println("Message sent to server");
    client.sendMessages(messages);
    
    System.out.println("Record sent to server"); 
    client.receiveResponse();
    System.out.println("Response received from server"); 
}

}
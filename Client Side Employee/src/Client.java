import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.Serializable;

import Model.Customer;

public class Client implements Serializable {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Create a Customer object (you can get the data from your GUI)
            Customer customer = new Customer("123", "John Doe", "123456789", "password", 0.0);

            // Send customer to the server
            oos.writeObject(customer);

            // Receive response from the server
            String response = (String) ois.readObject();
            System.out.println("Server response: " + response);

            // Close connections
            oos.close();
            ois.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}

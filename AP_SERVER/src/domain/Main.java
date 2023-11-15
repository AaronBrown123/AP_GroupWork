package domain;

/*import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Server.MultiThreader;
import model.Count;

public class Main {
    private Socket connection;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Main() {
        this.createConnection();
        // this.waitForRequests();
    }

    private void createConnection() {
        // try {
        // serverSocket = new ServerSocket(8000);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Serving on port 5000...");
            // int counter = 0;
            while (true) {
                if (Count.getCount() <= 0) {
                    // counter++;
                    Count.addOneCount();
                } else {
                    System.out.println("Client Connected: " + Count.getCount());
                    // counter++;
                    Count.addOneCount();
                }
                // Socket connection = serverSocket.accept();
                // MultiThreader threader = new MultiThreader(connection);
                // threader.start();
                new MultiThreader(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Server Exception " + e.getMessage());
        }

    }

    private void configureStream() {
        try {
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            // objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
            inputStream = new ObjectInputStream(connection.getInputStream());
            // objectInputStream = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            outputStream.close();
            inputStream.close();
            connection.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}*/

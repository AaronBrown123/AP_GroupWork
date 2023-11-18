package Server;

/*import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Count;
import model.Customer;
import model.Equipment;
import model.Staff;

public class MultiThreader extends Thread {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    public MultiThreader(Socket socket) {
        this.socket = socket;
        configureStream();
    }


    private void configureStream() {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void waitForRequests() {
        String action = "";
        System.out.println("waiting...");
        try {
            while (true) {
               
                do {
                    try {
                        action = (String) objectInputStream.readObject();
                        System.out.println("String: " + action + " received from Client");

                        if (action.equalsIgnoreCase("addCustomer")) {

                            Customer customer = null;
                            customer = (Customer) objectInputStream.readObject();

                            if (new DBController().addCustomer(customer)) {

                                Boolean flag = true;
                                objectOutputStream.writeObject(flag);
                            } else {
                                Boolean flag = false;
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("allCustomers")) {

                            String[][] array = new DBController().getAllCustomers();
                            if (array != null) {
                                objectOutputStream.writeObject(array);
                                Boolean flag = true;
                                objectOutputStream.writeObject(flag);
                            } else {
                                Boolean flag = false;
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("updateCustomer")) {
                            Customer customer = null;
                            customer = (Customer) objectInputStream.readObject();

                            Boolean flag = new DBController().updateCustomer(customer);
                            if (flag == true) {
                                objectOutputStream.writeObject(flag);
                            } else {
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("deleteCustomer")) {

                            String id = (String) objectInputStream.readObject();
                            Boolean flag = new DBController().deleteCustomer(Integer.parseInt(id.trim()));
                            if (flag == true) {
                                objectOutputStream.writeObject(flag);
                            } else {
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("addStaff")) {

                            Staff staff = null;
                            staff = (Staff) objectInputStream.readObject();

                            if (new DBController().addStaff(staff)) {

                                Boolean flag = true;
                                objectOutputStream.writeObject(flag);
                            } else {
                                Boolean flag = false;
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("allStaff")) {

                            String[][] array = new DBController().getAllStaff();
                            if (array != null) {
                                objectOutputStream.writeObject(array);
                                Boolean flag = true;
                                objectOutputStream.writeObject(flag);
                            } else {
                                Boolean flag = false;
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("updateStaff")) {
                            Staff staff = null;
                            staff = (Staff) objectInputStream.readObject();

                            Boolean flag = new DBController().updateStaff(staff);
                            if (flag == true) {
                                objectOutputStream.writeObject(flag);
                            } else {
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("deleteStaff")) {

                            String id = (String) objectInputStream.readObject();
                            Boolean flag = new DBController().deleteStaff(Integer.parseInt(id.trim()));
                            if (flag == true) {
                                objectOutputStream.writeObject(flag);
                            } else {
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("addEquipment")) {
                 
                            Equipment Equipment = null;
                            Equipment = (Equipment) objectInputStream.readObject();

                            if (new DBController().addEquipment(Equipment)) {

                                Boolean flag = true;
                                objectOutputStream.writeObject(flag);
                            } else {
                                Boolean flag = false;
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("allEquipment")) {

                            String[][] array = new DBController().getAllEquipment();
                            if (array != null) {
                                objectOutputStream.writeObject(array);
                                Boolean flag = true;
                                objectOutputStream.writeObject(flag);
                            } else {
                                Boolean flag = false;
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("updateEquipment")) {
                            Equipment Equipment = null;
                            Equipment = (Equipment) objectInputStream.readObject();

                            Boolean flag = new DBController().updateEquipment(Equipment);
                            if (flag == true) {
                                objectOutputStream.writeObject(flag);
                            } else {
                                objectOutputStream.writeObject(flag);
                            }
                        }
                        if (action.equalsIgnoreCase("deleteEquipment")) {

                            String id = (String) objectInputStream.readObject();
                            Boolean flag = new DBController().deleteEquipment(Integer.parseInt(id.trim()));
                            if (flag == true) {
                                objectOutputStream.writeObject(flag);
                            } else {
                                objectOutputStream.writeObject(flag);
                            }
                        }
                       
                        // Count.subOneCount();
                    } catch (ClassNotFoundException cnfe) {
                        cnfe.printStackTrace();
                    } catch (ClassCastException cce) {
                        cce.printStackTrace();
                    }
                } while (!action.equals("Exit") || !action.equals(null));
            }
        } catch (EOFException eofe) {
            eofe.printStackTrace();
            Count.subOneCount();
            System.out.println("Connection lost");
        } catch (IOException ioe) {
            System.out.println("IOE");
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        waitForRequests();
    }
}
*/
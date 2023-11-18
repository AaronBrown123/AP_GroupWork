package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.*;

public class DBController {
    private static Connection dbConn = null;
    private static Statement statement = null;
    private static ResultSet result = null;
    private static int affectedRows = 0;

    public DBController() {
        dbConn = DBConnector.getDatabaseConnection();
    }


    public boolean addCustomer(Customer customer) {
        String id = customer.getCustomer_id();
        String name = customer.getCustomer_name();
        String phone = customer.getContact();
        String password = customer.getCustomer_password();
        double accountBalance = customer.getAccountbal();

        String insertQuery = "INSERT INTO dblab.customer (`customer_id`, `customer_name`, `contact`, `customer_password`, `accountbal`)"
                + " VALUES ('" + id + "' ,'" + name + "' , '" + phone + "' , '" + password + "', '" + accountBalance + "')";
        System.out.println(insertQuery + "\n\n");
        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(insertQuery);
            if (affectedRows > 0) {
                System.out.println("Customer Added");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Customer Not Added");
        }
        return false;
    }
    
    

    public String[][] getAllCustomers() {
        String getQuery = "SELECT * FROM `customer`";
        try {
            statement = dbConn.createStatement();
            result = statement.executeQuery(getQuery);
            int count = 0;
            int acount = 0;
            while (result.next()) {
                acount++;
            }
            result = statement.executeQuery(getQuery);
            String[][] customerArray = new String[acount][5]; // 5 attributes in Customer class
            while (result.next()) {
                String id = result.getString("customer_id");
                String name = result.getString("customer_name");
                String contact = result.getString("contact");
                String password = result.getString("customer_password");
                double accountBalance = result.getDouble("accountbal");

                String[] customerData = {id, name, contact, password, String.valueOf(accountBalance)};
                customerArray[count] = customerData;
                count++;
            }
            return customerArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer getCustomer(int id) {
        String getQuery = "SELECT * FROM geeproject.customer WHERE `customer_id` = " + id;
        try {
            System.out.println("Sent SQL statement to database");
            statement = dbConn.createStatement();
            System.out.println("Received result from database");
            result = statement.executeQuery(getQuery);
            Customer customer = new Customer();
            while (result.next()) {
                String customerId = result.getString("customer_id");
                String customerName = result.getString("customer_name");
                String contact = result.getString("contact");
                String password = result.getString("customer_password");
                double accountBalance = result.getDouble("accountbal");

                customer.setCustomer_id(customerId);
                customer.setCustomer_name(customerName);
                customer.setContact(contact);
                customer.setCustomer_password(password);
                customer.setAccountbal(accountBalance);
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public boolean updateCustomer(Customer customer) {
        String id = customer.getCustomer_id();
        String name = customer.getCustomer_name();
        String phone = customer.getContact();
        String password = customer.getCustomer_password();
        double accountBalance = customer.getAccountbal();

        String updateQuery = "UPDATE geeproject.customer SET `customer_name` = '" + name + "', `contact` = '" + phone + "', `customer_password` = '"
                + password + "', `accountbal` = '" + accountBalance + "' WHERE `customer_id` = '" + id + "'";

        System.out.println(updateQuery);

        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(updateQuery);
            if (affectedRows > 0) {
                System.out.println("Customer update Statement succeeded");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Customer update Statement failed");
        }
        return false;
    }

    
    public boolean deleteCustomer(int id) {
        String deleteQuery = "DELETE FROM geeproject.customer WHERE `customer_id` = '" + id + "'";

        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(deleteQuery);
            if (affectedRows > 0) {
                System.out.println("Customer delete Statement succeeded");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Customer delete Statement failed");
        }
        return false;
    }

    
   
    public boolean addStaff(Staff staff) {
        String employee_id = staff.getEmployee_id();
        String employee_name = staff.getEmployee_name();
        String contact = staff.getContact();
        String employee_password = staff.getEmployee_password();

        String insertQuery = "INSERT INTO your_database_name.staff_table_name " +
                "(`employee_id`, `employee_name`, `contact`, `employee_password`) " +
                "VALUES ('" + employee_id + "' ,'" + employee_name + "' , '" + contact + "' , '" + employee_password + "')";
        try {
            statement = dbConn.createStatement();
            int affectedRows = statement.executeUpdate(insertQuery);
            if (affectedRows > 0) {
                System.out.println("Staff added successfully");
                return true;
            }
        } catch (Exception e) {
            System.err.println("Failed to add staff");
            e.printStackTrace();
        }
        return false;
    }

    
    
    public String[][] getAllStaff() {
        String getQuery = "SELECT * FROM `staff`";
        try {
            statement = dbConn.createStatement();
            result = statement.executeQuery(getQuery);
            int count = 0;
            int acount = 0;
            while (result.next()) {
                acount++;
            }
            result = statement.executeQuery(getQuery);
            String[][] staffArray = new String[acount][8];
            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String contact = result.getString("contact");
                String staff_password = result.getString("password");
    
                String[] staffData = { id, name, contact, staff_password };
                staffArray[count] = staffData;
                count++;

            }

            return staffArray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
/*
    public boolean updateStaff(Staff staff) {
    	String id = staff.getEmployee_id();
        String firstName = staff.getFirstName();
        String lastName = staff.getLastName();
        double salary = staff.getSalary();


        String updateQuery = "UPDATE dblab.staff  SET  `id` = '" + id + "', `firstname` = '" + firstName
                + "', `lastname` = '" + lastName + "', `salary` = '" + salary + "' WHERE `id` = '"
                + id
                + "'";

        System.out.println(updateQuery);

        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(updateQuery);
            if (affectedRows > 0) {
                System.out.println("Staff update Statement succeeded");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Staff update Statement failed");
        }
        return false;

    }

    public boolean deleteStaff(int id) {
        String deleteQuery = "DELETE FROM dblab.staff WHERE `id` = '" + id + "'";

        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(deleteQuery);
            if (affectedRows > 0) {
                System.out.println("Staff delete Statement succeeded");
            }
            return true;
        } catch (Exception e) {
            System.err.println("Staff delete Statement succeeded");
        }
        return false;
    }

    
    
    public boolean addEquipment(Equipment Equipment) {
          
        int  E_id = Equipment.getE_id();
        String name = Equipment.getName();
        double rentalRate = Equipment.getRentalRate();


        String insertQuery = "INSERT INTO dblab.Equipment (`Equipment_Id`, `name`, `rentalRate`)"
                + " VALUES ('" + E_id + "' , '" + name + "' , '" + rentalRate + "')";
        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(insertQuery);
            if (affectedRows > 0) {
                System.out.println(printerMethod("Equipment", 1));
            }
            return true;
        } catch (Exception e) {
            System.err.println(printerMethod("Equipment", 1));
        }
        return false;
    }

    public String[][] getAllEquipment() {
        String getQuery = "SELECT * FROM `Equipment`";
        try {
            statement = dbConn.createStatement();
            result = statement.executeQuery(getQuery);
            int count = 0;
            int acount = 0;
            while (result.next()) {
                acount++;
            }
            result = statement.executeQuery(getQuery);
            String[][] EquipmentArray = new String[acount][8];
            while (result.next()) {

                String  E_id = result.getString("Equipment_Id");
                String name = result.getString("name");
                String rentalRate = result.getString("rentalRate");



                String[] EquipmentData = { E_id, name, rentalRate };
                EquipmentArray[count] = EquipmentData;
                count++;

            }

            return EquipmentArray;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateEquipment(Equipment Equipment) {
      
    	int  E_id = Equipment.getE_id();
        String name = Equipment.getName();
        double rentalRate = Equipment.getRentalRate();

        String updateQuery = "UPDATE dblab.Equipment  SET  `name` = '" + name + "',  `rentalRate` = '"
                + rentalRate
                + "' WHERE `Equipment_id` = '" + E_id + "'";

        System.out.println(updateQuery);

        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(updateQuery);
            if (affectedRows > 0) {
                System.out.println("Equipment update Statement succeeded");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Equipment update Statement failed");
        }
        return false;

    }

    public boolean deleteEquipment(int E_id) {
        String deleteQuery = "DELETE FROM dblab.Equipment WHERE `Equipment_id` = '" + E_id + "'";

        try {
            statement = dbConn.createStatement();
            affectedRows = statement.executeUpdate(deleteQuery);
            if (affectedRows > 0) {
                System.out.println("Equipment delete Statement succeeded");
            }
            return true;
        } catch (Exception e) {
            System.err.println("Equipment delete Statement succeeded");
        }
        return false;
    }
    public boolean authenticateCustomer(String customerId, String password) {
        String query = "SELECT `id` FROM `customer` WHERE `id` = '" + customerId + "' AND `password` = '" + password + "'";

        try {
            statement = dbConn.createStatement();
            result = statement.executeQuery(query);
            return result.next(); // If there's a matching record, the customer is authenticated.
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Customer authentication failed.");
            return false;
        }
    }
    public boolean authenticateStaff(int staffId, String password) {
        String query = "SELECT `id` FROM `staff` WHERE `id` = " + staffId + " AND `password` = '" + password + "'";
        
        try {
            statement = dbConn.createStatement();
            result = statement.executeQuery(query);
            return result.next(); // If there's a matching record, the staff is authenticated.
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Staff authentication failed.");
            return false;
        }
    }
    */

    public static void generateInvoice() {

    }

}

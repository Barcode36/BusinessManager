/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Erik PC
 */
public class Customer  implements Runnable {
    
    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;
    
    public Customer(int sleepInterval) {
        interval = sleepInterval;
    }
  
    public void start() {
        worker = new Thread(this);
        worker.start();
    }
  
    public void stop() {
        running.set(false);
    }
 
    @Override
    public void run() { 
        while (running.get()) {
            try { 
                Thread.sleep(interval); 
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println(
                  "Thread was interrupted, Failed to complete operation");
            }
            // do something here 
         } 
    } 
    

    private SimpleStringProperty customer_lastName, customer_firstName, customer_dateCreated, customer_mail, customer_phone, customer_address, customer_city, customer_zipCode, customer_country, customer_company, customer_comment;
    private SimpleIntegerProperty customer_id, customer_orderCount;
    private SimpleDoubleProperty customer_ordersPrice;

    public Customer(SimpleStringProperty customer_lastName, SimpleStringProperty customer_firstName, SimpleStringProperty customer_dateCreated, SimpleStringProperty customer_mail, SimpleStringProperty customer_phone, SimpleStringProperty customer_address, SimpleStringProperty customer_city, SimpleStringProperty customer_zipCode, SimpleStringProperty customer_country, SimpleStringProperty customer_company, SimpleStringProperty customer_comment, SimpleIntegerProperty customer_id, SimpleIntegerProperty customer_orderCount, SimpleDoubleProperty cusotmer_ordersPrice) {
        this.customer_lastName = customer_lastName;
        this.customer_firstName = customer_firstName;
        this.customer_dateCreated = customer_dateCreated;
        this.customer_mail = customer_mail;
        this.customer_phone = customer_phone;
        this.customer_address = customer_address;
        this.customer_city = customer_city;
        this.customer_zipCode = customer_zipCode;
        this.customer_country = customer_country;
        this.customer_company = customer_company;
        this.customer_comment = customer_comment;
        this.customer_id = customer_id;
        this.customer_orderCount = customer_orderCount;
        this.customer_ordersPrice = cusotmer_ordersPrice;
    }

    public SimpleStringProperty getCustomer_lastName() {
        return customer_lastName;
    }

    public void setCustomer_lastName(SimpleStringProperty customer_lastName) {
        this.customer_lastName = customer_lastName;
    }

    public SimpleStringProperty getCustomer_firstName() {
        return customer_firstName;
    }

    public void setCustomer_firstName(SimpleStringProperty customer_firstName) {
        this.customer_firstName = customer_firstName;
    }

    public SimpleStringProperty getCustomer_dateCreated() {
        return customer_dateCreated;
    }

    public void setCustomer_dateCreated(SimpleStringProperty customer_dateCreated) {
        this.customer_dateCreated = customer_dateCreated;
    }

    public SimpleStringProperty getCustomer_mail() {
        return customer_mail;
    }

    public void setCustomer_mail(SimpleStringProperty customer_mail) {
        this.customer_mail = customer_mail;
    }

    public SimpleStringProperty getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(SimpleStringProperty customer_phone) {
        this.customer_phone = customer_phone;
    }

    public SimpleStringProperty getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(SimpleStringProperty customer_address) {
        this.customer_address = customer_address;
    }

    public SimpleStringProperty getCustomer_city() {
        return customer_city;
    }

    public void setCustomer_city(SimpleStringProperty customer_city) {
        this.customer_city = customer_city;
    }

    public SimpleStringProperty getCustomer_zipCode() {
        return customer_zipCode;
    }

    public void setCustomer_zipCode(SimpleStringProperty customer_zipCode) {
        this.customer_zipCode = customer_zipCode;
    }

    public SimpleStringProperty getCustomer_country() {
        return customer_country;
    }

    public void setCustomer_country(SimpleStringProperty customer_country) {
        this.customer_country = customer_country;
    }

    public SimpleStringProperty getCustomer_company() {
        return customer_company;
    }

    public void setCustomer_company(SimpleStringProperty customer_company) {
        this.customer_company = customer_company;
    }

    public SimpleStringProperty getCustomer_comment() {
        return customer_comment;
    }

    public void setCustomer_comment(SimpleStringProperty customer_comment) {
        this.customer_comment = customer_comment;
    }

    public SimpleIntegerProperty getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(SimpleIntegerProperty customer_id) {
        this.customer_id = customer_id;
    }

    public SimpleIntegerProperty getCustomer_orderCount() {
        return customer_orderCount;
    }

    public void setCustomer_orderCount(SimpleIntegerProperty customer_orderCount) {
        this.customer_orderCount = customer_orderCount;
    }

    public SimpleDoubleProperty getCustomer_ordersPrice() {
        return customer_ordersPrice;
    }

    public void setCustomer_ordersPrice(SimpleDoubleProperty customer_ordersPrice) {
        this.customer_ordersPrice = customer_ordersPrice;
    }

    


    public static List<Customer> getCustomers(User user){
            
        //Create list
        List<Customer> customersList = new ArrayList<>();
        
        //Create query
        String query = "SELECT Customers.DateCreated, Customers.LastName, Customers.FirstName, Customers.Comment, Customers.Mail, Customers.Phone, Customers.Address, Customers.City, Customers.ZipCode , Countries.CountryName, Customers.Company, Customers.CustomerID FROM Customers JOIN Countries ON Customers.CountryID=Countries.CountryID";
                
        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://" + user.getAddress() + "/" + user.getDbName();

        //  Database credentials
        String USER = user.getName();
        String PASS = user.getPass();


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleStringProperty lastName, firstName, mail, phone, address, city, zipCode, country, company, comment, dateCreated;
                SimpleIntegerProperty id, orderCount;
                SimpleDoubleProperty ordersPrice;
                
                lastName = new SimpleStringProperty(rs.getString("LastName"));
                firstName = new SimpleStringProperty(rs.getString("FirstName"));
                dateCreated = new SimpleStringProperty(rs.getString("DateCreated"));
                comment = new SimpleStringProperty(rs.getString("Comment"));
                mail = new SimpleStringProperty(rs.getString("Mail"));
                phone = new SimpleStringProperty(rs.getString("Phone"));                
                address = new SimpleStringProperty(rs.getString("Address"));
                city = new SimpleStringProperty(rs.getString("City"));
                zipCode = new SimpleStringProperty(rs.getString("ZipCode"));
                country = new SimpleStringProperty(rs.getString("CountryName"));
                company = new SimpleStringProperty(rs.getString("Company"));
                
                id = new SimpleIntegerProperty(rs.getInt("CustomerID"));
                
                orderCount = new SimpleIntegerProperty(getOrderCount(id, user));
                ordersPrice = new SimpleDoubleProperty(getOrdersPrice(id, user));
                
                
                Customer customer = new Customer(lastName, firstName, dateCreated, mail, phone, address, city, zipCode, country, company, comment, id, orderCount, ordersPrice);
                
                customersList.add(customer);
            }

            rs.close();
        } catch (SQLNonTransientConnectionException se) {            
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (ClassNotFoundException se) {
            //Handle errors for Class.forName
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        
        
        return customersList;
    }
    
    
    private static int getOrderCount(SimpleIntegerProperty id, User user) {
        int orderCount = 0;
        
        //Create query
        String query = "SELECT Orders.OrderID FROM Orders WHERE CustomerID=" + id.get();
                
        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://" + user.getAddress() + "/" + user.getDbName();

        //  Database credentials
        String USER = user.getName();
        String PASS = user.getPass();


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                orderCount++;
                
            }

            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (ClassNotFoundException se) {
            //Handle errors for Class.forName
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        
        return orderCount;
    }

    private static double getOrdersPrice(SimpleIntegerProperty id, User user) {
        double ordersPrice = 0;
        
        //Create query
        String query = "SELECT SUM(OrderPrice) AS 'OrderPrice' FROM Orders WHERE CustomerID=" + id.get();
                
        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://" + user.getAddress() + "/" + user.getDbName();

        //  Database credentials
        String USER = user.getName();
        String PASS = user.getPass();


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                ordersPrice = rs.getDouble("OrderPrice");
                
            }

            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (ClassNotFoundException se) {
            //Handle errors for Class.forName
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        
        return ordersPrice;
    }
}

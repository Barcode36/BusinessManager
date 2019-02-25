/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

/**
 *
 * @author Erik PC
 */
public class Customer  {
    
    private SimpleStringProperty customer_lastName, customer_firstName, customer_dateCreated, customer_mail, customer_phone, customer_address, customer_city, customer_zipCode, customer_country, customer_company, customer_comment;
    private SimpleIntegerProperty customer_id, customer_id_company, customer_id_country, customer_orderCount;
    private SimpleDoubleProperty customer_ordersPrice;

    public Customer(SimpleStringProperty customer_lastName, SimpleStringProperty customer_firstName, SimpleStringProperty customer_dateCreated, SimpleStringProperty customer_mail, SimpleStringProperty customer_phone, SimpleStringProperty customer_address, SimpleStringProperty customer_city, SimpleStringProperty customer_zipCode, SimpleStringProperty customer_country, SimpleStringProperty customer_company, SimpleStringProperty customer_comment, SimpleIntegerProperty customer_id, SimpleIntegerProperty customer_id_company, SimpleIntegerProperty customer_id_country, SimpleIntegerProperty customer_orderCount, SimpleDoubleProperty customer_ordersPrice) {
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
        this.customer_id_company = customer_id_company;
        this.customer_id_country = customer_id_country;
        this.customer_orderCount = customer_orderCount;
        this.customer_ordersPrice = customer_ordersPrice;
    }

    public SimpleIntegerProperty getCustomer_id_country() {
        return customer_id_country;
    }

    public void setCustomer_id_country(SimpleIntegerProperty customer_id_country) {
        this.customer_id_country = customer_id_country;
    }
    
    public SimpleIntegerProperty getCustomer_id_company() {
        return customer_id_company;
    }

    public void setCustomer_id_company(SimpleIntegerProperty customer_id_company) {
        this.customer_id_company = customer_id_company;
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

    public static List<Customer> getCustomers(List<SimpleTableObject> commonCustomerProperties,HikariDataSource ds){
            
        //Create list
        List<Customer> customersList = new ArrayList<>();
        
        //Create query
        String query = "SELECT SUM(OrderPrice) AS 'OrderPrice', COUNT(Orders.CustomerID) AS 'OrderCount', Customers.* FROM Customers LEFT JOIN Orders ON Orders.CustomerID = Customers.CustomerID GROUP BY Customers.CustomerID ASC";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleStringProperty customer_lastName, customer_firstName, customer_dateCreated, customer_mail, customer_phone, customer_address, customer_city, customer_zipCode, customer_country, customer_company, customer_comment;
                SimpleIntegerProperty customer_id, customer_id_company, customer_id_country, customer_orderCount;
                SimpleDoubleProperty customer_ordersPrice;
                                
                customer_lastName = new SimpleStringProperty(rs.getString("LastName"));
                customer_firstName = new SimpleStringProperty(rs.getString("FirstName"));
                customer_dateCreated = new SimpleStringProperty(rs.getString("DateCreated"));
                customer_comment = new SimpleStringProperty(rs.getString("Comment"));
                customer_mail = new SimpleStringProperty(rs.getString("Mail"));
                customer_phone = new SimpleStringProperty(rs.getString("Phone"));                
                customer_address = new SimpleStringProperty(rs.getString("Address"));
                customer_city = new SimpleStringProperty(rs.getString("City"));
                customer_zipCode = new SimpleStringProperty(rs.getString("ZipCode"));
                customer_country = getCommonCustomerPropertiesByID(commonCustomerProperties, rs.getInt("CountryID")).getProperty_name();
                customer_company = getCommonCustomerPropertiesByID(commonCustomerProperties, rs.getInt("CompanyID")).getProperty_name();
                
                customer_id = new SimpleIntegerProperty(rs.getInt("CustomerID"));
                customer_id_company = getCommonCustomerPropertiesByID(commonCustomerProperties, rs.getInt("CompanyID")).getProperty_id();
                customer_id_country = getCommonCustomerPropertiesByID(commonCustomerProperties, rs.getInt("CountryID")).getProperty_id();
                
               
                customer_orderCount = new SimpleIntegerProperty(rs.getInt("OrderCount"));                
                customer_ordersPrice = new SimpleDoubleProperty(rs.getDouble("OrderPrice"));                
                
                Customer customer = new Customer(customer_lastName, customer_firstName, customer_dateCreated, customer_mail, customer_phone, customer_address, customer_city, customer_zipCode, customer_country, customer_company, customer_comment, customer_id, customer_id_company, customer_id_country, customer_orderCount, customer_ordersPrice);
                
                customersList.add(customer);
            }

            rs.close();
        } catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
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

    public static List<SimpleTableObject> getCommonCustomerProperties(HikariDataSource ds) {
        
        //Create list
        List<SimpleTableObject> properties = new ArrayList<>();
        
        //Create query
        String query = "select * from CommonCustomerProperties";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            
            if(conn.isValid(10) == false) {
                MngApi obj = new MngApi();
                obj.alertConnectionLost();
            }
            
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleIntegerProperty property_id, property_type_id;
                SimpleStringProperty property_type_name;
                
                property_id = new SimpleIntegerProperty(rs.getInt("PropertyID"));
                property_type_id = new SimpleIntegerProperty(rs.getInt("PropertyTypeID"));
                property_type_name = new SimpleStringProperty(rs.getString("PropertyName"));
                
                SimpleTableObject obj = new SimpleTableObject(property_id, property_type_id, property_type_name);
                
                properties.add(obj);
                
            }

            rs.close();
        } catch (SQLNonTransientConnectionException se) {
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
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
        
    return properties;
    }
        
//    public static void insertNewCustomer(Customer newCustomer, Label info, HikariDataSource ds){
//        
//        String updateQuery = "INSERT INTO Customers VALUES (null,'" + newCustomer.getCustomer_firstName().get() + "','" + newCustomer.getCustomer_lastName().get() + "','" + newCustomer.getCustomer_dateCreated().get() + "','" + newCustomer.getCustomer_comment().get() + "','" + newCustomer.getCustomer_phone().get() + "','" + newCustomer.getCustomer_address().get() + "','" + newCustomer.getCustomer_city().get() + "','" + newCustomer.getCustomer_mail().get() + "','" + newCustomer.getCustomer_zipCode().get() + "'," + newCustomer.getCustomer_id_country().get() + "," + newCustomer.getCustomer_id_company().get() + ")";
//        MngApi.performUpdateQuery(updateQuery, info, ds);                
//        
//    }
    
    public static void insertNewCustomer(Customer customer, HikariDataSource ds){
        
        //Create query
        String updateQuery;

        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            //STEP 4: Execute a query
	    
                updateQuery = "INSERT INTO Customers (CustomerID,FirstName,LastName,DateCreated,Comment,Phone,Address,City,Mail,ZipCode,CountryID,CompanyID)" +
                              "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" + 
                              "ON DUPLICATE KEY UPDATE CustomerID=?,FirstName=?,LastName=?,DateCreated=?,Comment=?,Phone=?,Address=?,City=?,Mail=?,ZipCode=?,CountryID=?,CompanyID=?";
                stmt = conn.prepareStatement(updateQuery);
                
                int i = 1;
                
                stmt.setInt(i, customer.getCustomer_id().get());i++;
                stmt.setString(i, customer.getCustomer_firstName().get());i++;
                stmt.setString(i, customer.getCustomer_lastName().get());i++;
                stmt.setString(i, customer.getCustomer_dateCreated().get());i++;
                stmt.setString(i, customer.getCustomer_comment().get());i++;
                stmt.setString(i, customer.getCustomer_phone().get());i++;
                stmt.setString(i, customer.getCustomer_address().get());i++;
                stmt.setString(i, customer.getCustomer_city().get());i++;
                stmt.setString(i, customer.getCustomer_mail().get());i++;
                stmt.setString(i, customer.getCustomer_zipCode().get());i++;
                stmt.setInt(i, customer.getCustomer_id_country().get());i++;
                stmt.setInt(i, customer.getCustomer_id_company().get());i++;
                
                
                stmt.setInt(i, customer.getCustomer_id().get());i++;
                stmt.setString(i, customer.getCustomer_firstName().get());i++;
                stmt.setString(i, customer.getCustomer_lastName().get());i++;
                stmt.setString(i, customer.getCustomer_dateCreated().get());i++;
                stmt.setString(i, customer.getCustomer_comment().get());i++;
                stmt.setString(i, customer.getCustomer_phone().get());i++;
                stmt.setString(i, customer.getCustomer_address().get());i++;
                stmt.setString(i, customer.getCustomer_city().get());i++;
                stmt.setString(i, customer.getCustomer_mail().get());i++;
                stmt.setString(i, customer.getCustomer_zipCode().get());i++;
                stmt.setInt(i, customer.getCustomer_id_country().get());i++;
                stmt.setInt(i, customer.getCustomer_id_company().get());i++;
                
                
                stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        } catch (SQLNonTransientConnectionException se) {
            MngApi obj2 = new MngApi();
            obj2.alertConnectionLost();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
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
    } 
     
     
    public static void deleteCustomers(ObservableList<Customer> customers, Label info, HikariDataSource ds){
        
        for (int i = 0; i < customers.size(); i++) {
            
            int id = customers.get(i).getCustomer_id().get();
            String query = "DELETE FROM Customers WHERE CustomerID=" + id;
            MngApi.performUpdateQuery(query, info, ds);   
            
        }        
    }
    
    private static SimpleTableObject getCommonCustomerPropertiesByID(List<SimpleTableObject> commonCustomerProperties, int id){
        return commonCustomerProperties.get(id-1);
    }
}

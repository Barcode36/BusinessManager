/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.tables;

import classes.MngApi;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author edemko
 */
public class Customers {
 
    private SimpleIntegerProperty customerID;
    private SimpleIntegerProperty countryID;
    private SimpleIntegerProperty companyID;

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty dateCreated;
    private SimpleStringProperty comment;
    private SimpleStringProperty Pphone;
    private SimpleStringProperty address;
    private SimpleStringProperty city;
    private SimpleStringProperty mail;
    private SimpleStringProperty zipCode;

    public Customers(SimpleIntegerProperty customerID, SimpleIntegerProperty countryID, SimpleIntegerProperty companyID, SimpleStringProperty firstName, SimpleStringProperty lastName, SimpleStringProperty dateCreated, SimpleStringProperty comment, SimpleStringProperty Pphone, SimpleStringProperty address, SimpleStringProperty city, SimpleStringProperty mail, SimpleStringProperty zipCode) {
        this.customerID = customerID;
        this.countryID = countryID;
        this.companyID = companyID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateCreated = dateCreated;
        this.comment = comment;
        this.Pphone = Pphone;
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.zipCode = zipCode;
    }

    public SimpleIntegerProperty getCustomerID() {
        return customerID;
    }

    public void setCustomerID(SimpleIntegerProperty customerID) {
        this.customerID = customerID;
    }

    public SimpleIntegerProperty getCountryID() {
        return countryID;
    }

    public void setCountryID(SimpleIntegerProperty countryID) {
        this.countryID = countryID;
    }

    public SimpleIntegerProperty getCompanyID() {
        return companyID;
    }

    public void setCompanyID(SimpleIntegerProperty companyID) {
        this.companyID = companyID;
    }

    public SimpleStringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public SimpleStringProperty getLastName() {
        return lastName;
    }

    public void setLastName(SimpleStringProperty lastName) {
        this.lastName = lastName;
    }

    public SimpleStringProperty getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(SimpleStringProperty dateCreated) {
        this.dateCreated = dateCreated;
    }

    public SimpleStringProperty getComment() {
        return comment;
    }

    public void setComment(SimpleStringProperty comment) {
        this.comment = comment;
    }

    public SimpleStringProperty getPphone() {
        return Pphone;
    }

    public void setPphone(SimpleStringProperty Pphone) {
        this.Pphone = Pphone;
    }

    public SimpleStringProperty getAddress() {
        return address;
    }

    public void setAddress(SimpleStringProperty address) {
        this.address = address;
    }

    public SimpleStringProperty getCity() {
        return city;
    }

    public void setCity(SimpleStringProperty city) {
        this.city = city;
    }

    public SimpleStringProperty getMail() {
        return mail;
    }

    public void setMail(SimpleStringProperty mail) {
        this.mail = mail;
    }

    public SimpleStringProperty getZipCode() {
        return zipCode;
    }

    public void setZipCode(SimpleStringProperty zipCode) {
        this.zipCode = zipCode;
    }

   
    
    public static List<Customers> getCustomers(HikariDataSource ds) {
        
        //Create list
        List<Customers> customers = new ArrayList<>();
        
        //Create query        
        String query = "SELECT * FROM Customers";

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
                
                SimpleIntegerProperty customerID = new SimpleIntegerProperty(rs.getInt("CustomerID"));
                SimpleIntegerProperty countryID = new SimpleIntegerProperty(rs.getInt("CountryID"));
                SimpleIntegerProperty companyID = new SimpleIntegerProperty(rs.getInt("CompanyID"));
                
                SimpleStringProperty firstName = new SimpleStringProperty(rs.getString("FirstName"));
                SimpleStringProperty lastName = new SimpleStringProperty(rs.getString("LastName"));
                SimpleStringProperty dateCreated = new SimpleStringProperty(rs.getString("DateCreated"));
                SimpleStringProperty comment = new SimpleStringProperty(rs.getString("Comment"));
                SimpleStringProperty phone = new SimpleStringProperty(rs.getString("Phone"));
                SimpleStringProperty address = new SimpleStringProperty(rs.getString("Address"));
                SimpleStringProperty city = new SimpleStringProperty(rs.getString("City"));
                SimpleStringProperty mail = new SimpleStringProperty(rs.getString("Mail"));
                SimpleStringProperty zipCode = new SimpleStringProperty(rs.getString("ZipCode"));

                
                Customers customer = new Customers(customerID, countryID, companyID, firstName, lastName, dateCreated, comment, phone, address, city, mail, zipCode);
                
                customers.add(customer);
                
            }

            rs.close();
        } catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
        } catch (SQLNonTransientConnectionException se) {
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
            se.printStackTrace();
        } catch (SQLException se) {
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
        
        return customers;
    }
    
    
    
}

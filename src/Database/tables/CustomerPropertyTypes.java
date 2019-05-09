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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author edemko
 */
public class CustomerPropertyTypes {
    
    private SimpleIntegerProperty propertyTypeID;
    
    private SimpleStringProperty propertyTypeName;

    public CustomerPropertyTypes(SimpleIntegerProperty propertyTypeID, SimpleStringProperty PropertyTypeName) {
        this.propertyTypeID = propertyTypeID;
        this.propertyTypeName = PropertyTypeName;
    }

    public SimpleIntegerProperty getPropertyTypeID() {
        return propertyTypeID;
    }

    public void setPropertyTypeID(SimpleIntegerProperty propertyTypeID) {
        this.propertyTypeID = propertyTypeID;
    }

    public SimpleStringProperty getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(SimpleStringProperty PropertyTypeName) {
        this.propertyTypeName = PropertyTypeName;
    }
    
    public static List<CustomerPropertyTypes> getCustomerPropertyTypes(HikariDataSource ds) {
        
        //Create list
        List<CustomerPropertyTypes> customerPropertyTypes = new ArrayList<>();
        
        //Create query        
        String query = "SELECT * FROM CustomerPropertyTypes";

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
                
                SimpleIntegerProperty propertyTypeID = new SimpleIntegerProperty(rs.getInt("PropertyTypeID"));
                
                SimpleStringProperty propertyTypeName = new SimpleStringProperty(rs.getString("PropertyTypeName"));
                
                CustomerPropertyTypes property = new CustomerPropertyTypes(propertyTypeID, propertyTypeName);
                
                customerPropertyTypes.add(property);
                
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
        
        return customerPropertyTypes;
    }
    
}

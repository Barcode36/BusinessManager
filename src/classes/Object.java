/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Erik PC
 */
public class Object {
    
    private SimpleStringProperty  object_name, object_stlLink, object_buildTime_formated;
    private SimpleIntegerProperty object_id, object_buildTime, object_SoldCount;
    private SimpleDoubleProperty object_supportWeight, object_weight;

    public Object(SimpleStringProperty object_name, SimpleStringProperty object_stlLink, SimpleStringProperty object_buildTime_formated, SimpleIntegerProperty object_id, SimpleIntegerProperty object_buildTime, SimpleIntegerProperty object_SoldCount, SimpleDoubleProperty object_supportWeight, SimpleDoubleProperty object_weight) {
        this.object_name = object_name;
        this.object_stlLink = object_stlLink;
        this.object_buildTime_formated = object_buildTime_formated;
        this.object_id = object_id;
        this.object_buildTime = object_buildTime;
        this.object_SoldCount = object_SoldCount;
        this.object_supportWeight = object_supportWeight;
        this.object_weight = object_weight;
    }

    public SimpleStringProperty getObject_name() {
        return object_name;
    }

    public void setObject_name(SimpleStringProperty object_name) {
        this.object_name = object_name;
    }

    public SimpleStringProperty getObject_stlLink() {
        return object_stlLink;
    }

    public void setObject_stlLink(SimpleStringProperty object_stlLink) {
        this.object_stlLink = object_stlLink;
    }

    public SimpleStringProperty getObject_buildTime_formated() {
        return object_buildTime_formated;
    }

    public void setObject_buildTime_formated(SimpleStringProperty object_buildTime_formated) {
        this.object_buildTime_formated = object_buildTime_formated;
    }

    public SimpleIntegerProperty getObject_id() {
        return object_id;
    }

    public void setObject_id(SimpleIntegerProperty object_id) {
        this.object_id = object_id;
    }

    public SimpleIntegerProperty getObject_buildTime() {
        return object_buildTime;
    }

    public void setObject_buildTime(SimpleIntegerProperty object_buildTime) {
        this.object_buildTime = object_buildTime;
    }

    public SimpleIntegerProperty getObject_SoldCount() {
        return object_SoldCount;
    }

    public void setObject_SoldCount(SimpleIntegerProperty object_SoldCount) {
        this.object_SoldCount = object_SoldCount;
    }

    public SimpleDoubleProperty getObject_supportWeight() {
        return object_supportWeight;
    }

    public void setObject_supportWeight(SimpleDoubleProperty object_supportWeight) {
        this.object_supportWeight = object_supportWeight;
    }

    public SimpleDoubleProperty getObject_weight() {
        return object_weight;
    }

    public void setObject_weight(SimpleDoubleProperty object_weight) {
        this.object_weight = object_weight;
    }

    
    
    public static List<Object> getObjects(User user){
        
        //Create list
        List<Object> objectList = new ArrayList<>();
        
        //Create query
        String query = "SELECT * FROM Objects";
                
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
                
               SimpleStringProperty  object_name, object_stlLink, object_buildTime_formated;
               SimpleIntegerProperty object_id, object_buildTime, object_soldCount;
               SimpleDoubleProperty object_supportWeight, object_weight;
               
               object_name = new SimpleStringProperty(rs.getString("ObjectName"));
               object_stlLink = new SimpleStringProperty(rs.getString("StlLink"));
               
               object_id = new SimpleIntegerProperty(rs.getInt("ObjectID"));
               object_buildTime = new SimpleIntegerProperty(rs.getInt("BuildTime"));
                    object_buildTime_formated = MngApi.convertToHours(object_buildTime.get());
               
               object_soldCount = new SimpleIntegerProperty(getSoldCount(object_id, user));
               
               object_supportWeight = new SimpleDoubleProperty(rs.getDouble("SupportWeight"));
               object_weight = new SimpleDoubleProperty(rs.getDouble("ObjectWeight"));
               
               Object object = new Object(object_name, object_stlLink, object_buildTime_formated,object_id, object_buildTime, object_soldCount, object_supportWeight, object_weight);
                
                
               objectList.add(object);
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
        
        return objectList;        
    }
    
    private static int getSoldCount(SimpleIntegerProperty object_id, User user){
        
        //Create list
        int soldCount = 0;
        
        //Create query
        String query = "SELECT SUM(ItemQuantity) AS 'SoldCount' FROM OrderItems WHERE ObjectID=" + object_id.get();
                
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
                
               soldCount = rs.getInt("SoldCount");
                
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
        
        return soldCount;  
        
    }
    
 }
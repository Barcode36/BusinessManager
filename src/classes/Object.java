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
public class Object {
    
    private SimpleStringProperty  object_name, object_stlLink, object_buildTime_formated, object_comment;
    private SimpleIntegerProperty object_id, object_buildTime, object_SoldCount;
    private SimpleDoubleProperty object_supportWeight, object_weight, object_soldPrice, object_costs;

    public Object(SimpleStringProperty object_name, SimpleStringProperty object_stlLink, SimpleStringProperty object_buildTime_formated, SimpleStringProperty object_comment, SimpleIntegerProperty object_id, SimpleIntegerProperty object_buildTime, SimpleIntegerProperty object_SoldCount, SimpleDoubleProperty object_supportWeight, SimpleDoubleProperty object_weight, SimpleDoubleProperty object_soldPrice, SimpleDoubleProperty object_costs) {
        this.object_name = object_name;
        this.object_stlLink = object_stlLink;
        this.object_buildTime_formated = object_buildTime_formated;
        this.object_comment = object_comment;
        this.object_id = object_id;
        this.object_buildTime = object_buildTime;
        this.object_SoldCount = object_SoldCount;
        this.object_supportWeight = object_supportWeight;
        this.object_weight = object_weight;
        this.object_soldPrice = object_soldPrice;
        this.object_costs = object_costs;
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

    public SimpleStringProperty getObject_comment() {
        return object_comment;
    }

    public void setObject_comment(SimpleStringProperty object_comment) {
        this.object_comment = object_comment;
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

    public SimpleDoubleProperty getObject_soldPrice() {
        return object_soldPrice;
    }

    public void setObject_soldPrice(SimpleDoubleProperty object_soldPrice) {
        this.object_soldPrice = object_soldPrice;
    }

    public SimpleDoubleProperty getObject_costs() {
        return object_costs;
    }

    public void setObject_costs(SimpleDoubleProperty object_costs) {
        this.object_costs = object_costs;
    }

   

    public static List<Object> getObjects(HikariDataSource ds){
        
        //Create list
        List<Object> objectList = new ArrayList<>();
        
        //Create query
        String query = "SELECT Objects.*, SUM(ItemQuantity) AS 'SoldCount', SUM(ItemPrice) AS 'SoldPrice', SUM(ItemCosts) AS 'ItemCosts' FROM Objects LEFT JOIN OrderItems ON OrderItems.ObjectID = Objects.ObjectID GROUP BY Objects.ObjectID";

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
                
               SimpleStringProperty  object_name, object_stlLink, object_buildTime_formated, object_comment;
               SimpleIntegerProperty object_id, object_buildTime, object_soldCount;
               SimpleDoubleProperty object_supportWeight, object_weight, object_soldPrice, object_costs;
               
               object_name = new SimpleStringProperty(rs.getString("ObjectName"));
               object_stlLink = new SimpleStringProperty(rs.getString("StlLink"));
               object_comment = new SimpleStringProperty(rs.getString("Comment"));
               
               object_id = new SimpleIntegerProperty(rs.getInt("ObjectID"));
               object_buildTime = new SimpleIntegerProperty(rs.getInt("BuildTime"));
               object_buildTime_formated = MngApi.convertToFormattedTime(object_buildTime.get());
               
               object_supportWeight = new SimpleDoubleProperty(rs.getDouble("SupportWeight"));
               object_weight = new SimpleDoubleProperty(rs.getDouble("ObjectWeight"));               
               object_soldPrice = new SimpleDoubleProperty(rs.getDouble("SoldPrice"));
               object_costs = new SimpleDoubleProperty(MngApi.round(rs.getDouble("ItemCosts"), 2));
               object_soldCount = new SimpleIntegerProperty(rs.getInt("SoldCount")); 
               
               Object object = new Object(object_name, object_stlLink, object_buildTime_formated, object_comment, object_id, object_buildTime, object_soldCount, object_supportWeight, object_weight, object_soldPrice, object_costs);
               
               objectList.add(object);
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
        
        return objectList;        
    }
    
   
    public static void insertNewObject(classes.Object obj, HikariDataSource ds){
        
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
	    
                
                updateQuery = "INSERT INTO Objects (ObjectID,ObjectName,ObjectWeight,SupportWeight,BuildTime,StlLink,Comment, ProjectID) VALUES (?,?,?,?,?,?,?,?) "
                    + "ON DUPLICATE KEY UPDATE ObjectID=?,ObjectName=?,ObjectWeight=?,SupportWeight=?,BuildTime=?,StlLink=?,Comment=?,ProjectID=?";    
                stmt = conn.prepareStatement(updateQuery);
               
                int i = 1;                
                stmt.setInt(i, obj.getObject_id().get());i++;
                stmt.setString(i, obj.getObject_name().get());i++;
                stmt.setDouble(i, obj.getObject_weight().get());i++;
                stmt.setDouble(i,obj.getObject_supportWeight().get());i++;
                stmt.setInt(i, obj.getObject_buildTime().get());i++;
                stmt.setString(i, obj.getObject_stlLink().get());i++;
                stmt.setString(i, obj.getObject_comment().get());i++;
                stmt.setInt(i, 1);i++;
                
                stmt.setInt(i, obj.getObject_id().get());i++;
                stmt.setString(i, obj.getObject_name().get());i++;
                stmt.setDouble(i, obj.getObject_weight().get());i++;
                stmt.setDouble(i,obj.getObject_supportWeight().get());i++;
                stmt.setInt(i, obj.getObject_buildTime().get());i++;
                stmt.setString(i, obj.getObject_stlLink().get());i++;
                stmt.setString(i, obj.getObject_comment().get());i++;
                stmt.setInt(i, 1);
                                
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
  
    public static void deleteObjects(ObservableList<classes.Object> objects, Label info, HikariDataSource ds){
        
        for (int i = 0; i < objects.size(); i++) {
            
            int id = objects.get(i).getObject_id().get();
            String query = "DELETE FROM Objects WHERE ObjectID=" + id;
            MngApi.performUpdateQuery(query, info, ds);
            
        }        
    }
    
 }

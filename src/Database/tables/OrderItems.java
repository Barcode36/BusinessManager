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
public class OrderItems {
    
    private SimpleIntegerProperty orderItemID;
    private SimpleIntegerProperty orderID;
    private SimpleIntegerProperty objectID;
    private SimpleIntegerProperty itemMaterialID;
    private SimpleIntegerProperty itemBuildTime;
    private SimpleIntegerProperty itemQuantity;
    private SimpleIntegerProperty printerID;

    private SimpleDoubleProperty itemWeight;
    private SimpleDoubleProperty itemSupportWeight;
    private SimpleDoubleProperty itemPrice;
    private SimpleDoubleProperty itemCosts;

    public OrderItems(SimpleIntegerProperty orderItemID, SimpleIntegerProperty orderID, SimpleIntegerProperty objectID, SimpleIntegerProperty itemMaterialID, SimpleIntegerProperty itemBuildTime, SimpleIntegerProperty itemQuantity, SimpleIntegerProperty printerID, SimpleDoubleProperty itemWeight, SimpleDoubleProperty itemSupportWeight, SimpleDoubleProperty itemPrice, SimpleDoubleProperty itemCosts) {
        this.orderItemID = orderItemID;
        this.orderID = orderID;
        this.objectID = objectID;
        this.itemMaterialID = itemMaterialID;
        this.itemBuildTime = itemBuildTime;
        this.itemQuantity = itemQuantity;
        this.printerID = printerID;
        this.itemWeight = itemWeight;
        this.itemSupportWeight = itemSupportWeight;
        this.itemPrice = itemPrice;
        this.itemCosts = itemCosts;
    }

    public SimpleIntegerProperty getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(SimpleIntegerProperty orderItemID) {
        this.orderItemID = orderItemID;
    }

    public SimpleIntegerProperty getOrderID() {
        return orderID;
    }

    public void setOrderID(SimpleIntegerProperty orderID) {
        this.orderID = orderID;
    }

    public SimpleIntegerProperty getObjectID() {
        return objectID;
    }

    public void setObjectID(SimpleIntegerProperty objectID) {
        this.objectID = objectID;
    }

    public SimpleIntegerProperty getItemMaterialID() {
        return itemMaterialID;
    }

    public void setItemMaterialID(SimpleIntegerProperty itemMaterialID) {
        this.itemMaterialID = itemMaterialID;
    }

    public SimpleIntegerProperty getItemBuildTime() {
        return itemBuildTime;
    }

    public void setItemBuildTime(SimpleIntegerProperty itemBuildTime) {
        this.itemBuildTime = itemBuildTime;
    }

    public SimpleIntegerProperty getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(SimpleIntegerProperty itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public SimpleIntegerProperty getPrinterID() {
        return printerID;
    }

    public void setPrinterID(SimpleIntegerProperty printerID) {
        this.printerID = printerID;
    }

    public SimpleDoubleProperty getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(SimpleDoubleProperty itemWeight) {
        this.itemWeight = itemWeight;
    }

    public SimpleDoubleProperty getItemSupportWeight() {
        return itemSupportWeight;
    }

    public void setItemSupportWeight(SimpleDoubleProperty itemSupportWeight) {
        this.itemSupportWeight = itemSupportWeight;
    }

    public SimpleDoubleProperty getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(SimpleDoubleProperty itemPrice) {
        this.itemPrice = itemPrice;
    }

    public SimpleDoubleProperty getItemCosts() {
        return itemCosts;
    }

    public void setItemCosts(SimpleDoubleProperty itemCosts) {
        this.itemCosts = itemCosts;
    }
    
    public static List<OrderItems> getOrderItems(HikariDataSource ds) {
        
        //Create list
        List<OrderItems> orderItems = new ArrayList<>();
        
        //Create query        
        String query = "SELECT * FROM OrderItems";

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
                
                SimpleIntegerProperty orderItemID = new SimpleIntegerProperty(rs.getInt("OrderItemID"));
                SimpleIntegerProperty orderID = new SimpleIntegerProperty(rs.getInt("OrderID"));
                SimpleIntegerProperty objectID = new SimpleIntegerProperty(rs.getInt("ObjectID"));
                SimpleIntegerProperty itemMaterialID = new SimpleIntegerProperty(rs.getInt("ItemMaterialID"));
                SimpleIntegerProperty itemBuildTime = new SimpleIntegerProperty(rs.getInt("ItemBuildTime"));
                SimpleIntegerProperty itemQuantity = new SimpleIntegerProperty(rs.getInt("ItemQuantity"));
                SimpleIntegerProperty printerID = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                    
                SimpleDoubleProperty itemWeight = new SimpleDoubleProperty(rs.getDouble("ItemWeight"));
                SimpleDoubleProperty itemSupportWeight = new SimpleDoubleProperty(rs.getDouble("ItemSupportWeight"));
                SimpleDoubleProperty itemPrice = new SimpleDoubleProperty(rs.getDouble("ItemPrice"));
                SimpleDoubleProperty itemCosts = new SimpleDoubleProperty(rs.getDouble("ItemCosts"));

                OrderItems orderItem = new OrderItems(orderItemID, orderID, objectID, itemMaterialID, itemBuildTime, itemQuantity, printerID, itemWeight, itemSupportWeight, itemPrice, itemCosts);
                                
                orderItems.add(orderItem);
                
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
        
        return orderItems;
    }
    
}

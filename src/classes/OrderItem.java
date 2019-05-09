/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Database.tables.Printers;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

/**
 *
 * @author Erik PC
 */
public class OrderItem {
    
//    private SimpleStringProperty  object_name, object_buildTime_formated, printer_name, material_type, material_color;
//    private SimpleIntegerProperty orderItem_id, order_id, object_id, object_buildTime, quantity, printer_id, material_id;
//    private SimpleDoubleProperty object_supportWeight, object_weight, price, costs;
    
    private SimpleStringProperty  object_buildTime_formated;
    private SimpleIntegerProperty orderItem_id, order_id, quantity;
    private SimpleDoubleProperty price, costs;
    
    private Material material;//for material_type, material_color, material_id
    private Printer printer;//printer_name, printer_id
    private Object object;//object_name, object_id, object_buildTime, object_supportWeight, object_weight

    public OrderItem(SimpleIntegerProperty orderItem_id, SimpleStringProperty object_name, SimpleStringProperty object_buildTime_formated, SimpleStringProperty printer_name, SimpleStringProperty material_type, SimpleStringProperty material_color, SimpleIntegerProperty order_id, SimpleIntegerProperty object_id, SimpleIntegerProperty object_buildTime, SimpleIntegerProperty quantity, SimpleIntegerProperty printer_id, SimpleIntegerProperty material_id, SimpleDoubleProperty object_supportWeight, SimpleDoubleProperty object_weight, SimpleDoubleProperty price, SimpleDoubleProperty costs) {
        this.printer = Printers.getPrinterById(printers, printer_id);
        this.material = material;        
        this.orderItem_id = orderItem_id;
        this.object_name = object_name;
        this.object_buildTime_formated = object_buildTime_formated;
        this.printer_name = printer_name;
        this.material_type = material_type;
        this.material_color = material_color;
        this.order_id = order_id;
        this.object_id = object_id;
        this.object_buildTime = object_buildTime;
        this.quantity = quantity;
        this.printer_id = printer_id;
        this.material_id = material_id;
        this.object_supportWeight = object_supportWeight;
        this.object_weight = object_weight;
        this.price = price;
        this.costs = costs;        
    }
    
    public static void insertNewOrderItems(ObservableList<OrderItem> orderItems, HikariDataSource ds){ 
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
	    for (int i = 0; i < orderItems.size(); i++) {
                
                OrderItem orderItem = orderItems.get(i);
                               
//                if(orderItem.getOrderItem_id().get() == 0){
//                    updateQuery = "SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA ='" + ds.getUsername()+ "' AND   TABLE_NAME   ='OrderItems'";
//                    stmt = conn.prepareStatement(updateQuery);
//                    ResultSet rs = stmt.executeQuery(updateQuery);
//                
//                    while(rs.next()){
//                        orderItem.setOrderItem_id(new SimpleIntegerProperty(rs.getInt(1)));
//                    }
//                }
                
                updateQuery = "INSERT INTO OrderItems (OrderItemID,OrderID,ObjectID,ItemMaterialID,ItemWeight,ItemSupportWeight,ItemBuildTime,ItemPrice,ItemQuantity,PrinterID,ItemCosts) VALUES (?,?,?,?,?,?,?,?,?,?,?) "
                    + "ON DUPLICATE KEY UPDATE OrderID=?,ObjectID=?,ItemMaterialID=?,ItemWeight=?,ItemSupportWeight=?,ItemBuildTime=?,ItemPrice=?,ItemQuantity=?,PrinterID=?,ItemCosts=?";            
                stmt = conn.prepareStatement(updateQuery);
                
                stmt.setInt(1, orderItem.getOrderItem_id().get());
                stmt.setInt(2, orderItem.getOrder_id().get());
                stmt.setInt(3, orderItem.getObject_id().get());
                stmt.setInt(4, orderItem.getMaterial_id().get());
                stmt.setDouble(5, orderItem.getObject_weight().get());
                stmt.setDouble(6, orderItem.getObject_supportWeight().get());
                stmt.setInt(7, orderItem.getObject_buildTime().get());
                stmt.setDouble(8,orderItem.getPrice().get());
                stmt.setInt(9,orderItem.getQuantity().get());
                stmt.setInt(10,orderItem.getPrinter_id().get());
                stmt.setDouble(11, orderItem.getCosts().get());
                
                stmt.setInt(12, orderItem.getOrder_id().get());
                stmt.setInt(13, orderItem.getObject_id().get());
                stmt.setInt(14, orderItem.getMaterial_id().get());
                stmt.setDouble(15, orderItem.getObject_weight().get());
                stmt.setDouble(16, orderItem.getObject_supportWeight().get());
                stmt.setInt(17, orderItem.getObject_buildTime().get());
                stmt.setDouble(18,orderItem.getPrice().get());
                stmt.setInt(19,orderItem.getQuantity().get());
                stmt.setInt(20,orderItem.getPrinter_id().get());
                stmt.setDouble(21, orderItem.getCosts().get());
                                
                stmt.executeUpdate();
                
                stmt.close();
                
            }
            
          conn.close();  
        } catch (SQLNonTransientConnectionException se) {
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();            
        }finally {
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
    
    public static ObservableList<OrderItem> getOrderItemsByOrderId(SimpleIntegerProperty order_id, ObservableList<OrderItem> allOrderItems) {
        
        ObservableList<OrderItem> filteredItems = FXCollections.observableArrayList();
        
        //binary search: we are searching first orderItem with order_id and then we will find beginning of series (there could be multiple orderItems with same order_id
        int numberToGuess = order_id.get();
        int start = 1;
        int end = allOrderItems.size();
        int position = 0;
        
        while(start <= end){
            position = (start + end)/2;
            
            OrderItem item = allOrderItems.get(position);
            
            if (item.getOrderItem_id() == order_id)break;
            
            if(numberToGuess < item.getOrderItem_id().get()){
                
                end = position -1;                
                
            } else {
                
                start = position + 1;
                
            }            
        }
        
        //now that we have position of at least one orderItem with order_id, we can find first occurence of such a orderItem        
        while(allOrderItems.get(position).getOrder_id() == order_id){
            position--;
        }
        position++;
        
        //now we should be at the beggining of orderItems with same order_id, so we can add them to the list
        while(allOrderItems.get(position).getOrder_id() == order_id){
            filteredItems.add(allOrderItems.get(position));
        }
        
        return filteredItems;
    }
    
    public static ObservableList<OrderItem> getOrderItemsByMaterialId(SimpleIntegerProperty material_id, ObservableList<OrderItem> allOrderItems){
        
        ObservableList<OrderItem> filteredItems = FXCollections.observableArrayList();
        
        //binary search: we are searching first orderItem with material_id and then we will find beginning of series (there could be multiple orderItems with same material_id
        int numberToGuess = material_id.get();
        int start = 1;
        int end = allOrderItems.size();
        int position = 0;
        
        while(start <= end){
            position = (start + end)/2;
            
            OrderItem item = allOrderItems.get(position);
            
            if (item.getMaterial_id() == material_id)break;
            
            if(numberToGuess < item.getOrderItem_id().get()){
                
                end = position - 1;                
                
            } else {
                
                start = position + 1;
                
            }            
        }
        
        //now that we have position of at least one orderItem with material_id, we can find first occurence of such a orderItem        
        while(allOrderItems.get(position).getMaterial_id() == material_id){
            position--;
        }
        position++;
        
        //now we should be at the beggining of orderItems with same material_id, so we can add them to the list
        while(allOrderItems.get(position).getMaterial_id() == material_id){
            filteredItems.add(allOrderItems.get(position));
        }
        
        return filteredItems;
        
    }
    
    public static void deleteOrderItem(OrderItem orderItem, Label info, HikariDataSource ds){
        String query = "DELETE FROM OrderItems WHERE OrderItemID=" + orderItem.getOrderItem_id().get();
        MngApi.performUpdateQuery(query, info, ds);
    }
    
    public static void deleteOrderItem(List<Integer> orderItemIDs, Label info, HikariDataSource ds){
        //check if list is empty
        if(orderItemIDs.size() > 0){
            String query = "DELETE FROM OrderItems WHERE ";
            
            for (int i = 1; i <= orderItemIDs.size(); i++) {                
                if (i != orderItemIDs.size()){
                    query += " OrderItemID=" + orderItemIDs.get(i) + " OR ";
                } else {
                    query += " OrderItemID=" + orderItemIDs.get(i-1);
                }
            }
            
            MngApi.performUpdateQuery(query, info, ds);            
        }
    }
    
    public static List<Integer> getListOfIOrderItemIDs(Order order, HikariDataSource ds){
        
        //Create list
        List<Integer> orderItemsIDs = new ArrayList<>();
        
        //Create query
        String query = "SELECT OrderItemID FROM OrderItems WHERE OrderID=" + order.getOrder_id().get();

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
                int orderItemID = rs.getInt(1);                
                orderItemsIDs.add(orderItemID);                
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
        
        return orderItemsIDs;
    }
    
    //values in database are not multiplied by quantity - that means that total wieght is ObjectWeight*ItemQuantity
    public static List<OrderItem> getOrderItems(ObservableList<Material> materials, int order_id, HikariDataSource ds){
        
        //Create list
        List<OrderItem> itemList = new ArrayList<>();
        
        //Create query
        String query =  "SELECT Objects.ObjectID, Objects.ObjectName, OrderItems.*, Printers.PrinterID, Printers.PrinterName FROM OrderItems  JOIN Objects ON Objects.ObjectID = OrderItems.ObjectID  JOIN Printers ON Printers.PrinterID = OrderItems.PrinterID WHERE OrderID=" + order_id + " ORDER BY OrderItems.OrderItemID";
        
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
                
                SimpleStringProperty  object_name, object_buildTime_formated, printer_name, material_type, material_color;
                SimpleIntegerProperty orderItem_id, object_id, object_buildTime, quantity, printer_id, material_id;
                SimpleDoubleProperty object_supportWeight, object_weight, price, item_costs;
                
                material_id = new SimpleIntegerProperty(rs.getInt("ItemMaterialID"));
                
                Material material = Material.getMaterialByID(materials, material_id.get());
                
                object_name = new SimpleStringProperty(rs.getString("ObjectName"));                
                printer_name = new SimpleStringProperty(rs.getString("PrinterName"));
                material_type = material.getMaterial_type();
                material_color = material.getMaterial_color();
                                
                orderItem_id = new SimpleIntegerProperty(rs.getInt("OrderItemID"));
                object_id = new SimpleIntegerProperty(rs.getInt("ObjectID"));
                object_buildTime = new SimpleIntegerProperty(rs.getInt("ItemBuildTime"));
                    object_buildTime_formated = MngApi.convertToFormattedTime(object_buildTime.get());
                quantity = new SimpleIntegerProperty(rs.getInt("ItemQuantity"));
                printer_id = new SimpleIntegerProperty(rs.getInt("PrinterID"));                
                
                
               
                object_supportWeight = new SimpleDoubleProperty(rs.getDouble("ItemSupportWeight"));
                object_weight = new SimpleDoubleProperty(rs.getDouble("ItemWeight"));
                price = new SimpleDoubleProperty(rs.getDouble("ItemPrice"));
                
                //costs calculation
                    Double material_price, material_shipping, price_per_gram, total_weight, costs;
                    double material_weight;
                    
                    material_price = material.getMaterial_price().get();
                    material_shipping = material.getMaterial_shipping().get();
                    material_weight = material.getMaterial_weight().get();        
                    price_per_gram = (material_price+material_shipping)/material_weight;        
                    total_weight = object_weight.get() + object_supportWeight.get();                                                                    
                    costs = MngApi.round(price_per_gram*total_weight, 2);                                
                    item_costs = new SimpleDoubleProperty(costs);
                    
                    OrderItem item = new OrderItem(orderItem_id, object_name, object_buildTime_formated, printer_name, material_type, material_color, new SimpleIntegerProperty(order_id), object_id, object_buildTime, quantity, printer_id, material_id, object_supportWeight, object_weight, price, item_costs);
               
                itemList.add(item);
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
        
        return itemList; 
        
    }
    
    public SimpleIntegerProperty getOrderItem_id() {
        return orderItem_id;
    }

    public void setOrderItem_id(SimpleIntegerProperty orderItem_id) {
        this.orderItem_id = orderItem_id;
    }
    
    public SimpleIntegerProperty getOrder_id() {
        return order_id;
    }

    public void setOrder_id(SimpleIntegerProperty order_id) {
        this.order_id = order_id;
    }

    public SimpleStringProperty getObject_name() {
        return object_name;
    }

    public void setObject_name(SimpleStringProperty object_name) {
        this.object_name = object_name;
    }

    public SimpleStringProperty getObject_buildTime_formated() {
        return object_buildTime_formated;
    }

    public void setObject_buildTime_formated(SimpleStringProperty object_buildTime_formated) {
        this.object_buildTime_formated = object_buildTime_formated;
    }

    public SimpleStringProperty getPrinter_name() {
        return printer_name;
    }

    public void setPrinter_name(SimpleStringProperty printer_name) {
        this.printer_name = printer_name;
    }

    public SimpleStringProperty getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(SimpleStringProperty material_type) {
        this.material_type = material_type;
    }

    public SimpleStringProperty getMaterial_color() {
        return material_color;
    }

    public void setMaterial_color(SimpleStringProperty material_color) {
        this.material_color = material_color;
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

    public SimpleIntegerProperty getQuantity() {
        return quantity;
    }

    public void setQuantity(SimpleIntegerProperty qunatity) {
        this.quantity = qunatity;
    }

    public SimpleIntegerProperty getPrinter_id() {
        return printer_id;
    }

    public void setPrinter_id(SimpleIntegerProperty printer_id) {
        this.printer_id = printer_id;
    }

    public SimpleIntegerProperty getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(SimpleIntegerProperty material_id) {
        this.material_id = material_id;
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

    public SimpleDoubleProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleDoubleProperty price) {
        this.price = price;
    }

    public SimpleDoubleProperty getCosts() {
        return costs;
    }

    public void setCosts(SimpleDoubleProperty costs) {
        this.costs = costs;
    }    
 }

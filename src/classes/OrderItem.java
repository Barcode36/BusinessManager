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
//    private SimpleDoubleProperty orderItem_supportWeight, orderItem_weight, price, costs;
    
    //table fields
    private SimpleIntegerProperty orderItem_id, order_id, object_id, material_id, quantity, printer_id, orderItem_buildTime;
    private SimpleDoubleProperty orderItem_supportWeight, orderItem_weight, price;
    
    //additional fileds - included in object
    //private SimpleStringProperty object_name, object_buildTime_formated;
    //private SimpleDoubleProperty object_costs;
    private Object object;
    
    //additional fields - included in Material
    //private SimpleStringProperty material_type, material_color;
    private Material material;
    
    //additional fields - included in Printer
    //private SimpleStringProperty printer_name;
    private Printer printer;

    public OrderItem(SimpleIntegerProperty orderItem_id, SimpleIntegerProperty order_id, SimpleIntegerProperty object_id, SimpleIntegerProperty material_id, SimpleIntegerProperty quantity, SimpleIntegerProperty printer_id, SimpleIntegerProperty orderItem_buildTime, SimpleDoubleProperty object_supportWeight, SimpleDoubleProperty object_weight, SimpleDoubleProperty price) {
        this.orderItem_id = orderItem_id;
        this.order_id = order_id;
        this.object_id = object_id;
        this.material_id = material_id;
        this.quantity = quantity;
        this.printer_id = printer_id;
        this.orderItem_buildTime = orderItem_buildTime;
        this.orderItem_supportWeight = object_supportWeight;
        this.orderItem_weight = object_weight;
        this.price = price;
    }
    
    public static ObservableList<OrderItem> downloadOrderItemsTable(HikariDataSource ds){
        
        //Create list
        ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
        
        //Create query        
        String query = "SELECT * FROM OrderItems ORDER BY OrderID DESC";

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
                
                //table fields
                SimpleIntegerProperty orderItem_id, order_id, object_id, material_id, orderItem_buildTime, quantity, printer_id;
                SimpleDoubleProperty object_supportWeight, object_weight, price;
                
                orderItem_id = new SimpleIntegerProperty(rs.getInt("OrderItemID"));
                order_id = new SimpleIntegerProperty(rs.getInt("OrderID"));
                object_id = new SimpleIntegerProperty(rs.getInt("ObjectID"));
                material_id = new SimpleIntegerProperty(rs.getInt("ItemMaterialID"));
                orderItem_buildTime = new SimpleIntegerProperty(rs.getInt("ItemBuildTime"));
                quantity = new SimpleIntegerProperty(rs.getInt("ItemQuantity"));
                printer_id = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                                                
                object_supportWeight = new SimpleDoubleProperty(rs.getDouble("ItemSupportWeight"));
                object_weight = new SimpleDoubleProperty(rs.getDouble("ItemWeight"));
                price = new SimpleDoubleProperty(rs.getDouble("ItemPrice"));
                
                    
                OrderItem orderItem = new OrderItem(orderItem_id, order_id, object_id, material_id, quantity, printer_id, orderItem_buildTime, object_supportWeight, object_weight, price);
                                
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
                
                updateQuery = "INSERT INTO OrderItems (OrderItemID,OrderID,ObjectID,ItemMaterialID,ItemWeight,ItemSupportWeight,ItemBuildTime,ItemPrice,ItemQuantity,PrinterID,ItemCosts) VALUES (?,?,?,?,?,?,?,?,?,?,?) "
                    + "ON DUPLICATE KEY UPDATE OrderID=?,ObjectID=?,ItemMaterialID=?,ItemWeight=?,ItemSupportWeight=?,ItemBuildTime=?,ItemPrice=?,ItemQuantity=?,PrinterID=?,ItemCosts=?";            
                stmt = conn.prepareStatement(updateQuery);
                
                stmt.setInt(1, orderItem.getOrderItem_id().get());
                stmt.setInt(2, orderItem.getOrder_id().get());
                stmt.setInt(3, orderItem.getObject_id().get());
                stmt.setInt(4, orderItem.getMaterial_id().get());
                stmt.setDouble(5, orderItem.getOrderItem_weight().get());
                stmt.setDouble(6, orderItem.getOrderItem_supportWeight().get());
                stmt.setInt(7, orderItem.getObject().getObject_buildTime().get());
                stmt.setDouble(8,orderItem.getPrice().get());
                stmt.setInt(9,orderItem.getQuantity().get());
                stmt.setInt(10,orderItem.getPrinter_id().get());
                stmt.setDouble(11, orderItem.getObject().getObject_costs().get());
                
                stmt.setInt(12, orderItem.getOrder_id().get());
                stmt.setInt(13, orderItem.getObject_id().get());
                stmt.setInt(14, orderItem.getMaterial_id().get());
                stmt.setDouble(15, orderItem.getOrderItem_weight().get());
                stmt.setDouble(16, orderItem.getOrderItem_supportWeight().get());
                stmt.setInt(17, orderItem.getObject().getObject_buildTime().get());
                stmt.setDouble(18,orderItem.getPrice().get());
                stmt.setInt(19,orderItem.getQuantity().get());
                stmt.setInt(20,orderItem.getPrinter_id().get());
                stmt.setDouble(21, orderItem.getObject().getObject_costs().get());
                                
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
        
    public static ObservableList<OrderItem> getOrderItemsByMaterialId(SimpleIntegerProperty material_id, ObservableList<OrderItem> allOrderItemsTable){
        
        ObservableList<OrderItem> filteredItems = FXCollections.observableArrayList();
        
        //binary search: we are searching first orderItem with material_id and then we will find beginning of series (there could be multiple orderItems with same material_id
        int numberToGuess = material_id.get();
        int start = 0;
        int end = allOrderItemsTable.size() - 1;
        int position = 0;
        
        while(start <= end){
            position = (start + end)/2;
            
            OrderItem item = allOrderItemsTable.get(position);
            
            if (item.getMaterial_id() == material_id)break;
            
            if(numberToGuess < item.getOrderItem_id().get()){
                
                end = position - 1;                
                
            } else {
                
                start = position + 1;
                
            }            
        }
        
        //now that we have position of at least one orderItem with material_id, we can find first occurence of such a orderItem        
        while(allOrderItemsTable.get(position).getMaterial_id() == material_id){
            position--;
        }
        position++;
        
        //now we should be at the beggining of orderItems with same material_id, so we can add them to the list
        while(allOrderItemsTable.get(position).getMaterial_id() == material_id){
            filteredItems.add(allOrderItemsTable.get(position));
        }
        
        return filteredItems;
        
    }
        
    public static void deleteOrderItem(ObservableList<OrderItem> orderItems, Label info, HikariDataSource ds){
        
        String orderItemIDs = null;
        //prepare orderItems IDs from OrderItem list
        for (int i = 0; i < orderItems.size(); i++) {
            
            OrderItem item = orderItems.get(i);
            //if it is last item, add withou comma - we dont want comma at the end
            if (i == orderItems.size()) {
                orderItemIDs += item.getOrderItem_id();
            } else {
                orderItemIDs += item.getOrderItem_id() + ",";
            }
            
            
        }
        

        //check if list is empty
        if(!orderItems.isEmpty()){
            String query = "DELETE FROM OrderItems WHERE OrderItemID IN (" + orderItemIDs + ")";
            MngApi.performUpdateQuery(query, info, ds);            
        }
    }
        
    //in this method we will assign orderItems to particular OrderItem
    public static void getOrderItemsForOrders(ObservableList<Order> ordersTable, ObservableList<OrderItem> orderItemsTable, ObservableList<classes.Object> objectsTable, ObservableList<Material> materialsTable, ObservableList<SimpleTableObject> commonMaterialPropertiesTable, ObservableList<Printer> printersTable){
        
        OrderItem orderItem;
        ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
        
        //let's take orders one by one
        for (int i = 0; i < ordersTable.size(); i++) {
            
            Order order = ordersTable.get(i);            
            
            SimpleIntegerProperty order_id = order.getOrder_id();
            int j = 0;
            
            //now that we have order loaded we can scan orderItems list for orderItems with particular order_id  and add those orederItems to list
            //OrderItems list must be sorted by order_id so first object in OrderItems must belong to first order in Order list
                      
            try {
                while (orderItemsTable.get(j).getOrder_id().get() == order_id.get()){
                    orderItems.add(orderItemsTable.get(j));                    
                    j++;
                }                
            } catch (IndexOutOfBoundsException e) {
                break;
            }
            
            
            //now we should have Order loaded in "order" object we have created
            //we should also have list of orderItem bolinging to that particular order loaded in orderItems list we have created
            
            //before further proceeding we will delete "found" OrderItems so when next Order is loaded, there won't be
            //OrderItems with order_id of previous Order
            orderItemsTable.removeAll(orderItems);
                        
            //we now take every of that orderItems and set other details lika printer, material, object, etc.
            for (int k = 0; k < orderItems.size(); k++) {
                
                orderItem = orderItems.get(k);                
                orderItem.setPrinter(Printer.getPrinterById(printersTable, orderItem.getPrinter_id()));
                orderItem.setMaterial(Material.getMaterialByID(order_id, materialsTable, commonMaterialPropertiesTable, commonMaterialPropertiesTable));
                orderItem.setObject(Object.getObjectByID(order_id, objectsTable, orderItemsTable, orderItem.getMaterial()));
                
                //collecting some statistics about order
                //table view columns
                int totalQuantity = 0, totalBuildTime = 0;
                double totalCosts = 0, totalPrice = 0, totalWeight = 0, totalSupportWeight = 0;
            
                totalBuildTime += orderItem.getObject().getObject_buildTime().get();
                totalCosts += orderItem.getObject().getObject_costs().get();
                totalPrice += orderItem.getPrice().get();
                totalQuantity += orderItem.getQuantity().get();
                totalSupportWeight += orderItem.getOrderItem_supportWeight().get();
                totalWeight += orderItem.getOrderItem_weight().get();   
                
                order.setOrder_buildTime(new SimpleIntegerProperty(totalBuildTime));
                order.setOrder_buildTime_formated(MngApi.convertToFormattedTime(totalBuildTime));
                order.setOrder_costs(new SimpleDoubleProperty(totalCosts));
                order.setOrder_price(new SimpleDoubleProperty(totalPrice));
                order.setOrder_quantity(new SimpleIntegerProperty(totalQuantity));
                order.setOrder_weight(new SimpleDoubleProperty(totalWeight));
                order.setOrder_support_weight(new SimpleDoubleProperty(totalSupportWeight));
            }
        }
    }
    
    //values in database are not multiplied by quantity - that means that total wieght is ObjectWeight*ItemQuantity
    public static ObservableList<OrderItem> getOrderItems(ObservableList<Material> materials, int order_id, HikariDataSource ds){
        
        //Create list
        ObservableList<OrderItem> itemList = FXCollections.observableArrayList();
        
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
                    
                    OrderItem item = new OrderItem(orderItem_id, printer_id, object_id, material_id, quantity, printer_id, object_buildTime, object_supportWeight, object_weight, price);
               
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

    public SimpleIntegerProperty getObject_id() {
        return object_id;
    }

    public void setObject_id(SimpleIntegerProperty object_id) {
        this.object_id = object_id;
    }

    public SimpleIntegerProperty getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(SimpleIntegerProperty material_id) {
        this.material_id = material_id;
    }

    public SimpleIntegerProperty getQuantity() {
        return quantity;
    }

    public void setQuantity(SimpleIntegerProperty quantity) {
        this.quantity = quantity;
    }

    public SimpleIntegerProperty getPrinter_id() {
        return printer_id;
    }

    public void setPrinter_id(SimpleIntegerProperty printer_id) {
        this.printer_id = printer_id;
    }

    public SimpleIntegerProperty getOrderItem_buildTime() {
        return orderItem_buildTime;
    }

    public void setOrderItem_buildTime(SimpleIntegerProperty orderItem_buildTime) {
        this.orderItem_buildTime = orderItem_buildTime;
    }

    public SimpleDoubleProperty getOrderItem_supportWeight() {
        return orderItem_supportWeight;
    }

    public void setOrderItem_supportWeight(SimpleDoubleProperty orderItem_supportWeight) {
        this.orderItem_supportWeight = orderItem_supportWeight;
    }

    public SimpleDoubleProperty getOrderItem_weight() {
        return orderItem_weight;
    }

    public void setOrderItem_weight(SimpleDoubleProperty orderItem_weight) {
        this.orderItem_weight = orderItem_weight;
    }

    public SimpleDoubleProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleDoubleProperty price) {
        this.price = price;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
    
        
 }

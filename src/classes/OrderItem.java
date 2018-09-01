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
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Erik PC
 */
public class OrderItem {
    
    private SimpleStringProperty  object_name, object_buildTime_formated, printer_name, material_type, material_color;
    private SimpleIntegerProperty order_id, object_id, object_buildTime, quantity, printer_id, material_id;
    private SimpleDoubleProperty object_supportWeight, object_weight, price, costs;

    public OrderItem(SimpleStringProperty object_name, SimpleStringProperty object_buildTime_formated, SimpleStringProperty printer_name, SimpleStringProperty material_type, SimpleStringProperty material_color, SimpleIntegerProperty order_id, SimpleIntegerProperty object_id, SimpleIntegerProperty object_buildTime, SimpleIntegerProperty quantity, SimpleIntegerProperty printer_id, SimpleIntegerProperty material_id, SimpleDoubleProperty object_supportWeight, SimpleDoubleProperty object_weight, SimpleDoubleProperty price, SimpleDoubleProperty costs) {
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

    public static void insertNewOrderItem(OrderItem orderItem, User user){        
        String updateQuery = "INSERT INTO OrderItems VALUES (null," + orderItem.getOrder_id().get() + "," + orderItem.getObject_id().get() + "," + orderItem.getMaterial_id().get() + "," + orderItem.getObject_weight().get() + "," + orderItem.getObject_supportWeight().get() + "," + orderItem.getObject_buildTime().get() + "," + orderItem.getPrice().get() + "," + orderItem.getPrinter_id().get() + ")";
        MngApi.performUpdate(updateQuery, user);        
    }
    
    public static String generateUpdateQuery(OrderItem orderItem){
        return "INSERT INTO OrderItems VALUES (null," + orderItem.getOrder_id().get() + "," + orderItem.getObject_id().get() + "," + orderItem.getMaterial_id().get() + "," + orderItem.getObject_weight().get() + "," + orderItem.getObject_supportWeight().get() + "," + orderItem.getObject_buildTime().get() + "," + orderItem.getPrice().get() + "," + orderItem.getQuantity().get() + "," + orderItem.getPrinter_id().get() + ")";        
    }    
    
    public static void insertMultipleOrderItems(ObservableList<String> updateQueries, User user){
        MngApi.performMultipleUpdates(updateQueries, user);
    }
    
    public static List<OrderItem> getOrderItems(int order_id, User user){
        
        //Create list
        List<OrderItem> itemList = new ArrayList<>();
        
        //Create query
        String query = "SELECT Objects.ObjectID, Objects.ObjectName, OrderItems.ItemQuantity, Printers.PrinterID, Printers.PrinterName, OrderItems.ItemBuildTime, OrderItems.ItemMaterialID, MaterialTypes.MaterialType, MaterialColors.ColorName, OrderItems.ItemWeight, OrderItems.ItemSupportWeight, OrderItems.ItemPrice FROM OrderItems JOIN Objects ON Objects.ObjectID = OrderItems.ObjectID JOIN Printers ON Printers.PrinterID = OrderItems.PrinterID JOIN Materials ON Materials.MaterialID = OrderItems.ItemMaterialID JOIN MaterialTypes ON Materials.MaterialTypeID = MaterialTypes.MaterialTypeID JOIN MaterialColors ON Materials.ColorID = MaterialColors.ColorID WHERE OrderID=" + order_id + " ORDER BY OrderItems.OrderItemID";

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
                
                SimpleStringProperty  object_name, object_buildTime_formated, printer_name, material_type, material_color;
                SimpleIntegerProperty object_id, object_buildTime, quantity, printer_id, material_id;
                SimpleDoubleProperty object_supportWeight, object_weight, price, item_costs;
                
                object_name = new SimpleStringProperty(rs.getString("ObjectName"));                
                printer_name = new SimpleStringProperty(rs.getString("PrinterName"));
                material_type = new SimpleStringProperty(rs.getString("MaterialType"));
                material_color = new SimpleStringProperty(rs.getString("ColorName"));
                
                object_id = new SimpleIntegerProperty(rs.getInt("ObjectID"));
                object_buildTime = new SimpleIntegerProperty(rs.getInt("ItemBuildTime"));
                    object_buildTime_formated = MngApi.convertToHours(object_buildTime.get());
                quantity = new SimpleIntegerProperty(rs.getInt("ItemQuantity"));
                printer_id = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                material_id = new SimpleIntegerProperty(rs.getInt("ItemMaterialID"));
                //object_soldCount = new SimpleIntegerProperty(getSoldCount(object_id, user));               
               
                object_supportWeight = new SimpleDoubleProperty(rs.getDouble("ItemSupportWeight"));
                object_weight = new SimpleDoubleProperty(rs.getDouble("ItemWeight"));
                price = new SimpleDoubleProperty(rs.getDouble("ItemPrice"));
                
                //costs calculation
                    Double material_price, material_shipping, price_per_gram, total_weight, total_supportWeight, costs;
                    int material_weight;
                
                    Material material = Material.getMaterialByID(user, material_id);
                
                    material_price = material.getMaterial_price().get();
                    material_shipping = material.getMaterial_shipping().get();
                    material_weight = material.getMaterial_weight().get();        
                    price_per_gram = (material_price+material_shipping)/material_weight;        
                    total_weight = object_weight.get();
                    total_supportWeight = object_supportWeight.get();                        
                    total_weight = total_weight + total_supportWeight;                    
                    costs = price_per_gram*total_weight;                                
                    item_costs = new SimpleDoubleProperty(costs);
                    
                    OrderItem item = new OrderItem(object_name, object_buildTime_formated, printer_name, material_type, material_color, printer_id, object_id, object_buildTime, quantity, printer_id, material_id, object_supportWeight, object_weight, price, item_costs);
               
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
    
 }

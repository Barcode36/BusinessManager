/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
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
public class Order {
    
    //table columns
    private SimpleIntegerProperty order_id, order_customerID;    
    private SimpleStringProperty  order_status, order_comment, order_dateCreated, order_dueDate;
    
    //table view columns
    private SimpleIntegerProperty order_quantity, order_buildTime;
    private SimpleDoubleProperty order_costs, order_price, order_weight, order_support_weight;
    private SimpleStringProperty order_customer,order_buildTime_formated;
            
            
    private ObservableList<OrderItem> orderItems;
    private Customer customer;
    
    //simple constructor for database table objects

    public Order(SimpleIntegerProperty order_id, SimpleIntegerProperty order_customerID, SimpleStringProperty order_status, SimpleStringProperty order_comment, SimpleStringProperty order_dateCreated, SimpleStringProperty order_dueDate) {
        this.order_id = order_id;
        this.order_customerID = order_customerID;
        this.order_status = order_status;
        this.order_comment = order_comment;
        this.order_dateCreated = order_dateCreated;
        this.order_dueDate = order_dueDate;
    }

    public Order(SimpleIntegerProperty order_id, SimpleIntegerProperty order_customerID, SimpleStringProperty order_status, SimpleStringProperty order_comment, SimpleStringProperty order_dateCreated, SimpleStringProperty order_dueDate, SimpleIntegerProperty order_quantity, SimpleIntegerProperty order_buildTime, SimpleDoubleProperty order_costs, SimpleDoubleProperty order_price, SimpleDoubleProperty order_weight, SimpleDoubleProperty order_support_weight, SimpleStringProperty order_customer, SimpleStringProperty order_buildTime_formated, ObservableList<OrderItem> orderItems, Customer customer) {
        this.order_id = order_id;
        this.order_customerID = order_customerID;
        this.order_status = order_status;
        this.order_comment = order_comment;
        this.order_dateCreated = order_dateCreated;
        this.order_dueDate = order_dueDate;
        this.order_quantity = order_quantity;
        this.order_buildTime = order_buildTime;
        this.order_costs = order_costs;
        this.order_price = order_price;
        this.order_weight = order_weight;
        this.order_support_weight = order_support_weight;
        this.order_customer = order_customer;
        this.order_buildTime_formated = order_buildTime_formated;
        this.orderItems = orderItems;
        this.customer = customer;
    }
    
    public static ObservableList<Order> downloadOrdersTable(HikariDataSource ds) {
        
        //Create list
        ObservableList<Order> orders = FXCollections.observableArrayList();
        
        //Create query        
        String query = "SELECT * FROM Orders ORDER BY OrderID DESC";

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
                
                //private SimpleIntegerProperty order_id, order_customerID;    
                //private SimpleStringProperty  order_status, order_comment, order_dateCreated, order_dueDate;
                
                SimpleIntegerProperty order_id = new SimpleIntegerProperty(rs.getInt("OrderID"));
                SimpleIntegerProperty order_customerID = new SimpleIntegerProperty(rs.getInt("CustomerID"));
              
                SimpleStringProperty order_dateCreated = new SimpleStringProperty(rs.getString("DateCreated"));
                SimpleStringProperty order_status = new SimpleStringProperty(rs.getString("OrderStatus"));
                SimpleStringProperty order_dueDate = new SimpleStringProperty(rs.getString("DueDate"));
                SimpleStringProperty order_comment = new SimpleStringProperty(rs.getString("Comment"));

                Order order = new Order(order_id, order_customerID, order_status, order_comment, order_dateCreated, order_dueDate);
                                
                orders.add(order);
                
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
        
        return orders;
    }
    
    public static ObservableList<SimpleTableObject> downloadOrderStatusTable(HikariDataSource ds) {
        
        //Create list
        ObservableList<SimpleTableObject> customerPropertyTypes = FXCollections.observableArrayList();
        
        //Create query        
        String query = "SELECT * FROM OrderStatus";

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
                
                SimpleStringProperty propertyTypeName = new SimpleStringProperty(rs.getString("OrderStatus"));
                
                SimpleTableObject property = new SimpleTableObject(null, null, propertyTypeName);
                
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
    
    //this method should return a complete list of orders with all the information about order items, costs, and all the details
    //At the begginning it's only group of tables with basic information. Those information will merge and will return one complex list of orders with all the details
    public static ObservableList<Order> getOrders(ObservableList<Order> ordersTable, ObservableList<Customer> customersTable, ObservableList<OrderItem> orderItemsTable, ObservableList<classes.Object> objectsTable, ObservableList<Material> materialsTable, ObservableList<SimpleTableObject> commonMaterialPropertiesTable, ObservableList<Printer> printersTable) {
        
        //we are just modifying ordersTable. Currently it contains only data from database table and now we will add other information - we won't create
        //new list or whatsoever - we are just updating existing list
        
        //let's use other tables to complete the table of Orders like a puzzle
        //This method basically calculates all we need about order except for customer
        OrderItem.getOrderItemsForOrders(ordersTable, orderItemsTable, objectsTable, materialsTable, commonMaterialPropertiesTable, printersTable);
        
        
        for (int i = 0; i < ordersTable.size(); i++) {
            
            Order ordersTable_item = ordersTable.get(i);            
            
            //table columns - in database
            SimpleIntegerProperty order_customerID;                        
            SimpleStringProperty order_customer;
            order_customerID = ordersTable_item.getOrder_customerID();
                        
            //System.out.println("Number of OrderItems in OrderID " + order_id.get() + ": " + orderItems.size());            
            //we have list of items available, now we can calculate summary. We can start by defining variables for storing values for table columns
            
            Customer customer = Customer.getCustomerById(order_customerID, customersTable);
            order_customer = new SimpleStringProperty(customer.getCustomer_firstName().get() + " " + customer.getCustomer_lastName().get());                
            
            ordersTable_item.setCustomer(customer);
            ordersTable_item.setOrder_customer(order_customer);
        }
                
        return ordersTable;
    } 
   
    public static void insertNewOrder(Order order, Label info, HikariDataSource ds){
        
        String updateQuery = "INSERT INTO Orders (OrderID,CustomerID,DateCreated,OrderStatus,DueDate,Comment) " +
        "VALUES (" + 
                order.getOrder_id().get() + "," + 
                order.getOrder_customerID().get() + "," + 
                order.getOrder_dateCreated().get() + "','" + 
                order.getOrder_status().get() + "','" + 
                order.getOrder_dueDate().get() + "','" + 
                order.getOrder_comment().get() + "'," +                 
        ") " +
        "ON DUPLICATE KEY UPDATE " +        
                //"OrderID="+ order.getOrder_id().get() + 
                "CustomerID=" + order.getOrder_customerID().get() +                 
                ",DateCreated='" + order.getOrder_dateCreated().get() + 
                "',OrderStatus='" + order.getOrder_status().get() + 
                "',DueDate='" + order.getOrder_dueDate().get() + 
                "',Comment='" + order.getOrder_comment().get();
                
        MngApi.performUpdateQuery(updateQuery, info, ds);                
        
    }    
    
    public static void deleteOrders(ObservableList<Order> orders, Label info, HikariDataSource ds){      
    
        for (int i = 0; i < orders.size(); i++) {
            
            int id = orders.get(i).getOrder_id().get();
            String query = "DELETE FROM OrderItems WHERE OrderID=" + id;
            MngApi.performUpdateQuery(query, info, ds);
            query = "DELETE FROM Orders WHERE OrderID=" + id;        
            MngApi.performUpdateQuery(query, info, ds);    
            
        }        
    }

    public SimpleStringProperty getOrder_customer() {
        return order_customer;
    }

    public void setOrder_customer(SimpleStringProperty order_customer) {
        this.order_customer = order_customer;
    }

    public SimpleStringProperty getOrder_status() {
        return order_status;
    }

    public void setOrder_status(SimpleStringProperty order_status) {
        this.order_status = order_status;
    }

    public SimpleStringProperty getOrder_comment() {
        return order_comment;
    }

    public void setOrder_comment(SimpleStringProperty order_comment) {
        this.order_comment = order_comment;
    }

    public SimpleStringProperty getOrder_dateCreated() {
        return order_dateCreated;
    }

    public void setOrder_dateCreated(SimpleStringProperty order_dateCreated) {
        this.order_dateCreated = order_dateCreated;
    }

    public SimpleStringProperty getOrder_dueDate() {
        return order_dueDate;
    }

    public void setOrder_dueDate(SimpleStringProperty order_dueDate) {
        this.order_dueDate = order_dueDate;
    }

    public SimpleStringProperty getOrder_buildTime_formated() {
        return order_buildTime_formated;
    }

    public void setOrder_buildTime_formated(SimpleStringProperty order_buildTime_formated) {
        this.order_buildTime_formated = order_buildTime_formated;
    }

    public SimpleIntegerProperty getOrder_id() {
        return order_id;
    }

    public void setOrder_id(SimpleIntegerProperty order_id) {
        this.order_id = order_id;
    }

    public SimpleIntegerProperty getOrder_customerID() {
        return order_customerID;
    }

    public void setOrder_customerID(SimpleIntegerProperty order_customerID) {
        this.order_customerID = order_customerID;
    }

    public SimpleIntegerProperty getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(SimpleIntegerProperty order_quantity) {
        this.order_quantity = order_quantity;
    }

    public SimpleIntegerProperty getOrder_buildTime() {
        return order_buildTime;
    }

    public void setOrder_buildTime(SimpleIntegerProperty order_buildTime) {
        this.order_buildTime = order_buildTime;
    }

    public SimpleDoubleProperty getOrder_costs() {
        return order_costs;
    }

    public void setOrder_costs(SimpleDoubleProperty order_costs) {
        this.order_costs = order_costs;
    }

    public SimpleDoubleProperty getOrder_price() {
        return order_price;
    }

    public void setOrder_price(SimpleDoubleProperty order_price) {
        this.order_price = order_price;
    }

    public SimpleDoubleProperty getOrder_weighht() {
        return order_weight;
    }

    public void setOrder_weighht(SimpleDoubleProperty order_weighht) {
        this.order_weight = order_weighht;
    }

    public SimpleDoubleProperty getOrder_support_weight() {
        return order_support_weight;
    }

    public void setOrder_support_weight(SimpleDoubleProperty order_support_weight) {
        this.order_support_weight = order_support_weight;
    }

    public SimpleDoubleProperty getOrder_weight() {
        return order_weight;
    }

    public void setOrder_weight(SimpleDoubleProperty order_weight) {
        this.order_weight = order_weight;
    }

    public ObservableList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ObservableList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
    
}//class end

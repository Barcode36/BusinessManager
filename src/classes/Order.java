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
    
    public static List<Order> downloadOrdersTable(HikariDataSource ds) {
        
        //Create list
        List<Order> orders = new ArrayList<>();
        
        //Create query        
        String query = "SELECT * FROM Orders ORDER BY OrderID";

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
        
    public static List<Order> getOrders(ObservableList<Order> ordersTable, ObservableList<Customer> customers, ObservableList<OrderItem> allOrderItems) {
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
        for (int i = 0; i < ordersTable.size(); i++) {
            
            Order ordersTable_item = ordersTable.get(i);            
            
            //table columns - in database
            SimpleIntegerProperty order_id, order_customerID;    
                        
            //table view columns - in application
            SimpleIntegerProperty order_quantity, order_buildTime;
            SimpleDoubleProperty order_costs, order_price, order_weight, order_support_weight;
            SimpleStringProperty order_customer, order_buildTime_formated;
                
            
            //table columns
            order_id = ordersTable_item.getOrder_id();
            order_customerID = ordersTable_item.getOrder_customerID();
            
            //search for order Items belonging to this particular order
            ObservableList<OrderItem> orderItems = OrderItem.getOrderItemsByOrderId(order_id, allOrderItems);
            
            //table view columns
            int totalQuantity = 0, totalBuildTime = 0;
            double totalCosts = 0, totalPrice = 0, totalWeight = 0, totalSupportWeight = 0;
            
            for (int j = 0; j < orderItems.size(); j++) {
                
                OrderItem item = allOrderItems.get(j);
                
                totalBuildTime += item.getObject_buildTime().get();
                totalCosts += item.getCosts().get();
                totalPrice += item.getPrice().get();
                totalQuantity += item.getQuantity().get();
                totalSupportWeight += item.getObject_supportWeight().get();
                totalWeight += item.getObject_weight().get();
                
            }
            
            order_quantity = new SimpleIntegerProperty(totalQuantity);
            order_buildTime = new SimpleIntegerProperty(totalBuildTime);
            order_costs = new SimpleDoubleProperty(totalCosts);
            order_price = new SimpleDoubleProperty(totalPrice);
            order_weight = new SimpleDoubleProperty(totalWeight);
            order_support_weight = new SimpleDoubleProperty(totalSupportWeight);            
            
            order_buildTime_formated = MngApi.convertToFormattedTime(totalBuildTime);            
                
            Customer customer = Customer.getCustomerById(order_customerID, customers);
            order_customer = new SimpleStringProperty(customer.getCustomer_firstName().get() + " " + customer.getCustomer_lastName().get());                
            
            ordersTable_item.setOrder_quantity(order_quantity);
            ordersTable_item.setOrder_buildTime(order_buildTime);
            ordersTable_item.setOrder_costs(order_costs);
            ordersTable_item.setOrder_price(order_price);
            ordersTable_item.setOrder_weighht(order_weight);
            ordersTable_item.setOrder_buildTime_formated(order_buildTime_formated);
            ordersTable_item.setOrder_customer(order_customer);
            ordersTable_item.setOrder_support_weight(order_support_weight);
            
            //Order order = new Order(order_id, order_customerID, order_status, order_comment, order_dateCreated, order_dueDate, order_quantity, order_buildTime, order_costs, order_price, order_weight, order_support_weight, order_customer, order_buildTime_formated, orderItems, customer);
                
            ordersTable.add(ordersTable_item);
        }
                
        return orderList;
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
    
}//class end

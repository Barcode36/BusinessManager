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
public class Order {
    
    private SimpleStringProperty order_customer, order_status, order_comment, order_dateCreated, order_dueDate, order_buildTime_formated;    
    private SimpleIntegerProperty order_id, order_customerID, order_quantity, order_buildTime;
    private SimpleDoubleProperty order_costs, order_price, order_weight, order_support_weight;

    public Order(SimpleStringProperty order_customer, SimpleStringProperty order_status, SimpleStringProperty order_comment, SimpleStringProperty order_dateCreated, SimpleStringProperty order_dueDate, SimpleStringProperty order_buildTime_formated, SimpleIntegerProperty order_id, SimpleIntegerProperty order_customerID, SimpleIntegerProperty order_quantity, SimpleIntegerProperty order_buildTime, SimpleDoubleProperty order_costs, SimpleDoubleProperty order_price, SimpleDoubleProperty order_weighht, SimpleDoubleProperty order_support_weight) {
        this.order_customer = order_customer;
        this.order_status = order_status;
        this.order_comment = order_comment;
        this.order_dateCreated = order_dateCreated;
        this.order_dueDate = order_dueDate;
        this.order_buildTime_formated = order_buildTime_formated;
        this.order_id = order_id;
        this.order_customerID = order_customerID;
        this.order_quantity = order_quantity;
        this.order_buildTime = order_buildTime;
        this.order_costs = order_costs;
        this.order_price = order_price;
        this.order_weight = order_weighht;
        this.order_support_weight = order_support_weight;
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

    
    public static List<Order> getOrders(User user) {
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
        //Create query
        //String query = "SELECT Orders.OrderID, Orders.CustomerID, CONCAT(Customers.LastName, ' ', Customers.FirstName) AS Customer, Orders.OrderPrice, Orders.DueDate, Orders.DateCreated, Orders.OrderStatus, Orders.Comment FROM Orders JOIN Customers ON Orders.CustomerID = Customers.CustomerID ORDER BY Orders.OrderID DESC";
        String query = "SELECT Orders.OrderID, Orders.OrderQuantity, Orders.CustomerID, CONCAT(Customers.LastName, ' ', Customers.FirstName) AS Customer, Orders.OrderPrice, Orders.DueDate, Orders.DateCreated, Orders.OrderStatus, Orders.Comment, Orders.OrderCosts,Orders.OrderWeight,Orders.OrderSupportWeight,Orders.OrderBuildTime FROM Orders JOIN Customers ON Orders.CustomerID = Customers.CustomerID ORDER BY Orders.OrderID DESC";

        
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
                
                SimpleStringProperty customer, status, comment, dateCreated, dueDate, buildTime_formated;    
                SimpleIntegerProperty id, customer_id, totalQuantity, totalBuildTime;
                SimpleDoubleProperty totalCosts, totalPrice, totalWeight, totalSupportWeight;
                
                customer = new SimpleStringProperty(rs.getString("Customer"));
                status = new SimpleStringProperty(rs.getString("OrderStatus"));
                comment = new SimpleStringProperty(rs.getString("Comment"));
                dateCreated = new SimpleStringProperty(rs.getString("DateCreated"));                
                dueDate = new SimpleStringProperty(rs.getString("DateCreated"));
                
                id = new SimpleIntegerProperty(rs.getInt("OrderID"));
                customer_id = new SimpleIntegerProperty(rs.getInt("CustomerID"));
                
                //totalQuantity = new SimpleIntegerProperty(getTotalOrderQuantity(id, user));
                totalQuantity = new SimpleIntegerProperty(rs.getInt("OrderQuantity"));
                //totalBuildTime = new SimpleIntegerProperty(getTotalBuildTime(id, user));
                totalBuildTime = new SimpleIntegerProperty(rs.getInt("OrderBuildTime"));
                buildTime_formated = MngApi.convertToFormattedTime(totalBuildTime.get());
                
                //totalCosts = new SimpleDoubleProperty(getTotalCosts(id, user));
                totalCosts = new SimpleDoubleProperty(rs.getDouble("OrderCosts"));
                //totalPrice= new SimpleDoubleProperty(getTotalPrice(id, user));
                totalPrice = new SimpleDoubleProperty(rs.getDouble("OrderPrice"));
                //totalWeight = new SimpleDoubleProperty(getTotalWeight(id, user));
                totalWeight = new SimpleDoubleProperty(rs.getDouble("OrderWeight"));
                //totalSupportWeight = new SimpleDoubleProperty(getTotalSupportWeight(id, user));
                totalSupportWeight = new SimpleDoubleProperty(rs.getDouble("OrderSupportWeight"));
                
                Order order = new Order(customer, status, comment, dateCreated, dueDate, buildTime_formated, id, customer_id, totalQuantity, totalBuildTime, totalCosts, totalPrice, totalWeight, totalSupportWeight);
                
                orderList.add(order);
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
        
        return orderList;
    } 

    private static int getTotalOrderQuantity(SimpleIntegerProperty order_id, User user) {
        int itemQuantity = 1;
        
        //Create query
        String query = "SELECT SUM(ItemQuantity) FROM OrderItems WHERE OrderID=" + order_id.get();
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
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
                itemQuantity = rs.getInt("SUM(ItemQuantity)");                
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
            }
        }//end finally
        return itemQuantity;
    }
    
    private static int getTotalBuildTime(SimpleIntegerProperty order_id, User user) {
        int itemBuildTime = 1;
        
        //Create query
        String query = "SELECT SUM(ItemBuildTime) FROM OrderItems WHERE OrderID=" + order_id.get();
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
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
                itemBuildTime = rs.getInt("SUM(ItemBuildTime)");                
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
            }
        }//end finally
        return itemBuildTime;
    }
    
    private static double getTotalCosts(SimpleIntegerProperty order_id, User user) {
        double itemCosts = 0;
        
        //Create query
        String query = "SELECT SUM((Materials.MaterialPrice + Materials.MaterialShipping)/MaterialWeights.WeightValue*OrderItems.ItemWeight) AS 'Costs' FROM OrderItems JOIN Materials ON OrderItems.ItemMaterialID = Materials.MaterialID JOIN MaterialWeights ON Materials.WeightID = MaterialWeights.WeightID WHERE OrderItems.OrderID=" + order_id.get();
        //String query = "SELECT OrderCosts FROM Orders WHERE OrderId=" + order_id.get();
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
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
               itemCosts = rs.getDouble("Costs");
               //itemCosts = rs.getDouble("OrderCosts"); 
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
            }
        }//end finally
        return MngApi.round(itemCosts, 2);
    }
    
    private static double getTotalPrice(SimpleIntegerProperty order_id, User user) {
        double totalprice = 0;
        
        //Create query
        String query = "SELECT SUM(ItemPrice) FROM OrderItems WHERE OrderID=" + order_id.get();
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
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
                totalprice = rs.getDouble("SUM(ItemPrice)");                
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
            }
        }//end finally
        return totalprice;
    }
    
    private static double getTotalWeight(SimpleIntegerProperty order_id, User user) {
        double totalWeight = 0;
        
        //Create query
        String query = "SELECT SUM(ItemWeight) FROM OrderItems WHERE OrderID=" + order_id.get();
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
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
                totalWeight = rs.getInt("SUM(ItemWeight)");                
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
            }
        }//end finally
        return totalWeight;
    }
    
    private static double getTotalSupportWeight(SimpleIntegerProperty order_id, User user) {
        double totalSupportWeight = 0;
        
        //Create query
        String query = "SELECT SUM(ItemSupportWeight) FROM OrderItems WHERE OrderID=" + order_id.get();
        
        //Create list
        List<Order> orderList = new ArrayList<>();
        
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
                totalSupportWeight = rs.getInt("SUM(ItemSupportWeight)");                
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
            }
        }//end finally
        return totalSupportWeight;
    }
    
    public static void insertNewOrder(Order order, User user){
        
        String updateQuery = "INSERT INTO Orders (OrderID,CustomerID,OrderPrice,OrderQuantity,DateCreated,OrderStatus,DueDate,Comment,OrderCosts,OrderWeight,OrderSupportWeight,OrderBuildTime) " +
        "VALUES (" + 
                order.getOrder_id().get() + "," + 
                order.getOrder_customerID().get() + "," + 
                order.getOrder_price().get() + "," + 
                order.getOrder_quantity().get() + ",'" + 
                order.getOrder_dateCreated().get() + "','" + 
                order.getOrder_status().get() + "','" + 
                order.getOrder_dueDate().get() + "','" + 
                order.getOrder_comment().get() + "'," + 
                order.getOrder_costs().get() + "," + 
                order.getOrder_weighht().get() + "," + 
                order.getOrder_support_weight().get() + "," + 
                order.getOrder_buildTime().get() + 
        ") " +
        "ON DUPLICATE KEY UPDATE " +        
                //"OrderID="+ order.getOrder_id().get() + 
                "CustomerID=" + order.getOrder_customerID().get() + 
                ",OrderPrice=" + order.getOrder_price().get() + 
                ",OrderQuantity=" + order.getOrder_quantity().get() + 
                ",DateCreated='" + order.getOrder_dateCreated().get() + 
                "',OrderStatus='" + order.getOrder_status().get() + 
                "',DueDate='" + order.getOrder_dueDate().get() + 
                "',Comment='" + order.getOrder_comment().get() + 
                "',OrderCosts=" + order.getOrder_costs().get() + 
                ",OrderWeight=" + order.getOrder_weighht().get() + 
                ",OrderSupportWeight=" + order.getOrder_support_weight().get() + 
                ",OrderBuildTime=" + order.getOrder_buildTime().get();
        
        MngApi.performUpdate(updateQuery, user);                
        
    }    
    
    public static void deleteOrders(ObservableList<Order> orders, User user){      
    
        for (int i = 0; i < orders.size(); i++) {
            
            int id = orders.get(i).getOrder_id().get();
            String query = "DELETE FROM OrderItems WHERE OrderID=" + id;
            MngApi.performUpdate(query, user);
            query = "DELETE FROM Orders WHERE OrderID=" + id;        
            MngApi.performUpdate(query, user);    
            
        }        
    }
    
}//class end

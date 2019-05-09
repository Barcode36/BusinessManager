/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Database.tables.Costs;
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
import javafx.collections.ObservableList;
import javafx.scene.control.Label;


/**
 *
 * @author edemko
 */
public class Cost {
    
    //database table columns
    private SimpleIntegerProperty cost_id, cost_quantity, cost_printerID;
    private SimpleStringProperty cost_name, cost_purchaseDate, cost_comment;
    private SimpleDoubleProperty cost_shipping, cost_price;
    
    //additional fields
    private SimpleStringProperty cost_printer;
        
    //simple constructor for database table

    public Cost(SimpleIntegerProperty cost_id, SimpleIntegerProperty cost_quantity, SimpleIntegerProperty cost_printerID, SimpleStringProperty cost_name, SimpleStringProperty cost_purchaseDate, SimpleStringProperty cost_comment, SimpleDoubleProperty cost_shipping, SimpleDoubleProperty cost_price) {
        this.cost_id = cost_id;
        this.cost_quantity = cost_quantity;
        this.cost_printerID = cost_printerID;
        this.cost_name = cost_name;
        this.cost_purchaseDate = cost_purchaseDate;
        this.cost_comment = cost_comment;
        this.cost_shipping = cost_shipping;
        this.cost_price = cost_price;
    }
        
    //complex constructor for table view items
    public Cost(SimpleIntegerProperty cost_id, SimpleIntegerProperty cost_quantity, SimpleIntegerProperty cost_printerID, SimpleStringProperty cost_name, SimpleStringProperty cost_purchaseDate, SimpleStringProperty cost_comment, SimpleStringProperty cost_printer, SimpleDoubleProperty cost_shipping, SimpleDoubleProperty cost_price) {
        this.cost_id = cost_id;
        this.cost_quantity = cost_quantity;
        this.cost_printerID = cost_printerID;
        this.cost_name = cost_name;
        this.cost_purchaseDate = cost_purchaseDate;
        this.cost_comment = cost_comment;
        this.cost_printer = cost_printer;
        this.cost_shipping = cost_shipping;
        this.cost_price = cost_price;
    }
    
    
    public static List<Cost> getCosts(List<Cost> costsTable, List<Printers> printers) {
        
        for (int i = 0; i < costsTable.size(); i++) {
            
            Cost cost = costsTable.get(i);
            
            cost.setCost_printer(Printer.getPrinterById(cost.getCost_printerID()));
            
        }
        return costsTable;
    }
    
    public static List<Costs> getCostsTable(HikariDataSource ds) {
        
        //Create list
        List<Costs> costs = new ArrayList<>();
        
        //Create query        
        String query = "SELECT * FROM Costs";

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
                
                SimpleIntegerProperty costID = new SimpleIntegerProperty(rs.getInt("CostID"));
                SimpleIntegerProperty costQuantity = new SimpleIntegerProperty(rs.getInt("CostQantity"));
                SimpleIntegerProperty printerID = new SimpleIntegerProperty(rs.getInt("PrinterID"));
    
                SimpleDoubleProperty costShipping = new SimpleDoubleProperty(rs.getDouble("CostShipping"));
                SimpleDoubleProperty costPrice = new SimpleDoubleProperty(rs.getDouble("CostPrice"));
    
                SimpleStringProperty costName = new SimpleStringProperty(rs.getString("CostName"));
                SimpleStringProperty purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));
                SimpleStringProperty comment = new SimpleStringProperty(rs.getString("Comment"));
                
                Costs cost = new Costs(costID, costQuantity, printerID, costShipping, costPrice, costName, purchaseDate, comment);
                
                costs.add(cost);
                
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
        
        return costs;
    }
    
    public static void insertNewCost(Cost cost, HikariDataSource ds){
        
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
	    
                
                updateQuery = "INSERT INTO Costs (CostID,CostName,CostQuantity,CostShipping,Comment,CostPrice,PrinterID) VALUES (?,?,?,?,?,?,?) "
                    + "ON DUPLICATE KEY UPDATE CostID=?,CostName=?,CostQuantity=?,CostShipping=?,Comment=?,CostPrice=?,PrinterID=?";    
                stmt = conn.prepareStatement(updateQuery);
               
                                
                stmt.setInt(1, cost.getCost_id().get());//Cost id
                stmt.setString(2, cost.getCost_name().get());//Cost name
                stmt.setInt(3, cost.getCost_quantity().get());//quantity
                stmt.setDouble(4,cost.getCost_shipping().get());//shipping
                stmt.setString(5, cost.getCost_comment().get());//comment
                stmt.setDouble(6, cost.getCost_price().get());//price
                stmt.setInt(7, cost.getCost_printerID().get());//printerId
                                
                stmt.setInt(8, cost.getCost_id().get());//id
                stmt.setString(9, cost.getCost_name().get());//name
                stmt.setInt(10, cost.getCost_quantity().get());//quantity
                stmt.setDouble(11,cost.getCost_shipping().get());//shipping
                stmt.setString(12, cost.getCost_comment().get());//comment
                stmt.setDouble(13, cost.getCost_price().get());//price
                stmt.setInt(14, cost.getCost_printerID().get());//printer ID
                                                
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
    
    public static void deleteCosts(ObservableList<Cost> costs, Label info, HikariDataSource ds){
        
        for (int i = 0; i < costs.size(); i++) {
            
            int id = costs.get(i).getCost_id().get();
            String query = "DELETE FROM Costs WHERE CostID=" + id;
            MngApi.performUpdateQuery(query, info, ds);
            
        }
        
    }
    
    public SimpleIntegerProperty getCost_id() {
        return cost_id;
    }

    public void setCost_id(SimpleIntegerProperty cost_id) {
        this.cost_id = cost_id;
    }

    public SimpleIntegerProperty getCost_quantity() {
        return cost_quantity;
    }

    public void setCost_quantity(SimpleIntegerProperty cost_quantity) {
        this.cost_quantity = cost_quantity;
    }

    public SimpleIntegerProperty getCost_printerID() {
        return cost_printerID;
    }

    public void setCost_printerID(SimpleIntegerProperty cost_printerID) {
        this.cost_printerID = cost_printerID;
    }

    public SimpleStringProperty getCost_name() {
        return cost_name;
    }

    public void setCost_name(SimpleStringProperty cost_name) {
        this.cost_name = cost_name;
    }

    public SimpleStringProperty getCost_purchaseDate() {
        return cost_purchaseDate;
    }

    public void setCost_purchaseDate(SimpleStringProperty cost_purchaseDate) {
        this.cost_purchaseDate = cost_purchaseDate;
    }

    public SimpleStringProperty getCost_comment() {
        return cost_comment;
    }

    public void setCost_comment(SimpleStringProperty cost_comment) {
        this.cost_comment = cost_comment;
    }

    public SimpleStringProperty getCost_printer() {
        return cost_printer;
    }

    public void setCost_printer(SimpleStringProperty cost_printer) {
        this.cost_printer = cost_printer;
    }

    public SimpleDoubleProperty getCost_shipping() {
        return cost_shipping;
    }

    public void setCost_shipping(SimpleDoubleProperty cost_shipping) {
        this.cost_shipping = cost_shipping;
    }

    public SimpleDoubleProperty getCost_price() {
        return cost_price;
    }

    public void setCost_price(SimpleDoubleProperty cost_price) {
        this.cost_price = cost_price;
    }
}

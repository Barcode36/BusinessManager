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


/**
 *
 * @author edemko
 */
public class Cost {
    
    private SimpleIntegerProperty cost_id, cost_quantity, cost_printerID;
    private SimpleStringProperty cost_name, cost_purchaseDate, cost_comment, cost_printer;
    private SimpleDoubleProperty cost_shipping, cost_price;

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

    
    
    public static List<Cost> getCosts(User user) {
        
        //Create list
        List<Cost> allCostsList = new ArrayList<>();
        
        //Create query
        String query = "SELECT Costs.CostID, Costs.CostName, Costs.CostQuantity, Costs.CostShipping, Costs.PurchaseDate, Costs.Comment, Costs.CostPrice, Costs.PrinterID, Printers.PrinterName FROM Costs JOIN Printers ON Costs.PrinterID = Printers.PrinterID ORDER BY Costs.CostID ASC";

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
                
                SimpleIntegerProperty cost_id, cost_quantity, cost_printerID;
                SimpleStringProperty cost_name, cost_purchaseDate, cost_comment, cost_printer;
                SimpleDoubleProperty cost_shipping, cost_price;
                
                cost_id = new SimpleIntegerProperty(rs.getInt("CostID"));
                cost_quantity = new SimpleIntegerProperty(rs.getInt("CostQuantity"));
                cost_printerID = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                
                cost_name = new SimpleStringProperty(rs.getString("CostName"));
                cost_purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));
                cost_comment = new SimpleStringProperty(rs.getString("Comment"));
                cost_printer = new SimpleStringProperty(rs.getString("PrinterName"));
                
                cost_shipping = new SimpleDoubleProperty(rs.getDouble("CostShipping"));
                cost_price = new SimpleDoubleProperty(rs.getDouble("CostPrice"));
                
                Cost cost = new Cost(cost_id, cost_quantity, cost_printerID, cost_name, cost_purchaseDate, cost_comment, cost_printer, cost_shipping, cost_price);
                
                allCostsList.add(cost);
            }

            rs.close();
        }catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
        }catch (SQLNonTransientConnectionException se) {
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
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
    
        
    return allCostsList;
    }
    
    public static void insertNewCost(Cost newCost, User user){
        
        //Create query
        String updateQuery = "INSERT INTO Costs VALUES (null, '" + newCost.getCost_name().get() + "', " + newCost.getCost_quantity().get() + ", " + newCost.getCost_shipping().get() + ", '" + newCost.getCost_purchaseDate().get() + "','" + newCost.getCost_comment().get() + " '," + newCost.getCost_price().get() + ")";
        MngApi.performUpdate(updateQuery, user);

    }
    
}

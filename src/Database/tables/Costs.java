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
public class Costs {
    
    private SimpleIntegerProperty costID;
    private SimpleIntegerProperty costQuantity;
    private SimpleIntegerProperty printerID;
    
    private SimpleDoubleProperty costShipping;    
    private SimpleDoubleProperty costPrice;
    
    private SimpleStringProperty costName;
    private SimpleStringProperty purchaseDate;
    private SimpleStringProperty comment;

    public Costs(SimpleIntegerProperty costID, SimpleIntegerProperty costQuantity, SimpleIntegerProperty printerID, SimpleDoubleProperty costShipping, SimpleDoubleProperty costPrice, SimpleStringProperty costName, SimpleStringProperty purchaseDate, SimpleStringProperty comment) {
        this.costID = costID;
        this.costQuantity = costQuantity;
        this.printerID = printerID;
        this.costShipping = costShipping;
        this.costPrice = costPrice;
        this.costName = costName;
        this.purchaseDate = purchaseDate;
        this.comment = comment;
    }

    public SimpleIntegerProperty getCostID() {
        return costID;
    }

    public void setCostID(SimpleIntegerProperty costID) {
        this.costID = costID;
    }

    public SimpleIntegerProperty getCostQuantity() {
        return costQuantity;
    }

    public void setCostQuantity(SimpleIntegerProperty costQuantity) {
        this.costQuantity = costQuantity;
    }

    public SimpleIntegerProperty getPrinterID() {
        return printerID;
    }

    public void setPrinterID(SimpleIntegerProperty printerID) {
        this.printerID = printerID;
    }

    public SimpleDoubleProperty getCostShipping() {
        return costShipping;
    }

    public void setCostShipping(SimpleDoubleProperty costShipping) {
        this.costShipping = costShipping;
    }

    public SimpleDoubleProperty getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(SimpleDoubleProperty costPrice) {
        this.costPrice = costPrice;
    }

    public SimpleStringProperty getCostName() {
        return costName;
    }

    public void setCostName(SimpleStringProperty costName) {
        this.costName = costName;
    }

    public SimpleStringProperty getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(SimpleStringProperty purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public SimpleStringProperty getComment() {
        return comment;
    }

    public void setComment(SimpleStringProperty comment) {
        this.comment = comment;
    }
    
    
    public static List<Costs> getCosts(HikariDataSource ds) {
        
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
    
}

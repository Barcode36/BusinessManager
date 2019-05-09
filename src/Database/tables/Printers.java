/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.tables;


import classes.MngApi;
import classes.SimpleTableObject;
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
public class Printers {
    
    private SimpleIntegerProperty printerID;
    private SimpleIntegerProperty printerTypeID;
    
    private SimpleDoubleProperty duty;
    private SimpleDoubleProperty tax;
    private SimpleDoubleProperty printerPrice;
    private SimpleDoubleProperty printerShipping;
    
    private SimpleStringProperty printerName;    
    private SimpleStringProperty comment;    
    private SimpleStringProperty purchaseDate;

    public Printers(SimpleIntegerProperty printerID, SimpleIntegerProperty printerTypeID, SimpleDoubleProperty duty, SimpleDoubleProperty tax, SimpleDoubleProperty printerPrice, SimpleDoubleProperty printerShipping, SimpleStringProperty printerName, SimpleStringProperty comment, SimpleStringProperty purchaseDate) {
        this.printerID = printerID;
        this.printerTypeID = printerTypeID;
        this.duty = duty;
        this.tax = tax;
        this.printerPrice = printerPrice;
        this.printerShipping = printerShipping;
        this.printerName = printerName;
        this.comment = comment;
        this.purchaseDate = purchaseDate;
    }

    public SimpleIntegerProperty getPrinterID() {
        return printerID;
    }

    public void setPrinterID(SimpleIntegerProperty printerID) {
        this.printerID = printerID;
    }

    public SimpleIntegerProperty getPrinterTypeID() {
        return printerTypeID;
    }

    public void setPrinterTypeID(SimpleIntegerProperty printerTypeID) {
        this.printerTypeID = printerTypeID;
    }

    public SimpleDoubleProperty getDuty() {
        return duty;
    }

    public void setDuty(SimpleDoubleProperty duty) {
        this.duty = duty;
    }

    public SimpleDoubleProperty getTax() {
        return tax;
    }

    public void setTax(SimpleDoubleProperty tax) {
        this.tax = tax;
    }

    public SimpleDoubleProperty getPrinterPrice() {
        return printerPrice;
    }

    public void setPrinterPrice(SimpleDoubleProperty printerPrice) {
        this.printerPrice = printerPrice;
    }

    public SimpleDoubleProperty getPrinterShipping() {
        return printerShipping;
    }

    public void setPrinterShipping(SimpleDoubleProperty printerShipping) {
        this.printerShipping = printerShipping;
    }

    public SimpleStringProperty getPrinterName() {
        return printerName;
    }

    public void setPrinterName(SimpleStringProperty printerName) {
        this.printerName = printerName;
    }

    public SimpleStringProperty getComment() {
        return comment;
    }

    public void setComment(SimpleStringProperty comment) {
        this.comment = comment;
    }

    public SimpleStringProperty getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(SimpleStringProperty purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public static List<Printers> getPrinters(HikariDataSource ds) {
        
        //Create list
        List<Printers> printers = new ArrayList<>();
        
        //Create query        
        String query = "SELECT * FROM Printers";

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
                
                SimpleIntegerProperty printerID = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                SimpleIntegerProperty printerTypeID = new SimpleIntegerProperty(rs.getInt("PrinterTypeID"));
    
                SimpleDoubleProperty duty = new SimpleDoubleProperty(rs.getDouble("Duty"));
                SimpleDoubleProperty tax = new SimpleDoubleProperty(rs.getDouble("Tax"));
                SimpleDoubleProperty printerPrice = new SimpleDoubleProperty(rs.getDouble("PrinterPrice"));
                SimpleDoubleProperty printerShipping = new SimpleDoubleProperty(rs.getDouble("PrinterShipping"));
    
                SimpleStringProperty printerName = new SimpleStringProperty(rs.getString("PrinterName"));
                SimpleStringProperty comment = new SimpleStringProperty(rs.getString("Comment"));
                SimpleStringProperty purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));

                Printers printer = new Printers(printerID, printerTypeID, duty, tax, printerPrice, printerShipping, printerName, comment, purchaseDate);
                                
                printers.add(printer);
                
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
        
        return printers;
    }
    
    public static Printers getPrinterById(List<Printers> printers, SimpleIntegerProperty id){
        
        for (int i = 0; i < printers.size(); i++) {
            if(printers.get(i).getPrinterID() == id)return printers.get(i);
        }
        
        return null;
    }
    
    public static int getItemsSold(List<OrderItems> orderItems, SimpleIntegerProperty printer_id){
        
        int itemsPrinted = 0;
        
        for (int i = 0; i < orderItems.size(); i++) {
            
            if(orderItems.get(i).getPrinterID() == printer_id)return itemsPrinted + orderItems.get(1).getItemQuantity().get();
            
        }
        
        return itemsPrinted;
    }
    
    public static SimpleStringProperty getPrinterType(List<SimpleTableObject> commonMaterialProperties, SimpleIntegerProperty printer_type_id){
        
        for (int i = 0; i < commonMaterialProperties.size(); i++) {
            
            if(commonMaterialProperties.get(i).getProperty_id()== printer_type_id)return commonMaterialProperties.get(i).getPropertyName();
            
        } 
        return null;
    }
    
    //those are from the same table so why don't load them at once so we dont have to run through same table twice?
    public static double[] getPrinterIncomesAndMaterialExpenses(List<OrderItems> orderItems, SimpleIntegerProperty printer_id){
        
        double[] expenses = null;
        
        double incomes = 0, material_expenses = 0;
        
        for (int i = 0; i < orderItems.size(); i++) {            
            if(orderItems.get(i).getPrinterID() == printer_id) {
                incomes += orderItems.get(i).getItemPrice().get();
                material_expenses += orderItems.get(i).getItemCosts().get();
            }            
        }
        
        expenses[0] = incomes;
        expenses[1] = material_expenses;
        
        return expenses;        
    }
    
    public static double getPrinterCosts(List<Costs> costs, SimpleIntegerProperty printer_id){
        
        double sumOfCosts = 0;
        
        for (int i = 0; i < costs.size(); i++) {
            
            if(costs.get(i).getPrinterID() == printer_id){
                sumOfCosts += costs.get(i).getCostPrice().get();
                sumOfCosts += costs.get(i).getCostShipping().get();
            }
            
        }
        return sumOfCosts;
    }
    
}

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
 * @author Erik PC
 */
public class Printer {
    
    private SimpleIntegerProperty printer_id, printer_itemsSold, printer_printerID;
    private SimpleStringProperty printer_name, printer_purchaseDate, printer_comment, printer_type;
    private SimpleDoubleProperty printer_shipping, printer_price, printer_incomes, printer_expenses, printer_overallIncome;

    public Printer(SimpleIntegerProperty printer_id, SimpleIntegerProperty printer_itemsSold, SimpleIntegerProperty printer_printerID, SimpleStringProperty printer_name, SimpleStringProperty printer_purchaseDate, SimpleStringProperty printer_comment, SimpleStringProperty printer_type, SimpleDoubleProperty printer_shipping, SimpleDoubleProperty printer_price, SimpleDoubleProperty printer_incomes, SimpleDoubleProperty printer_expenses, SimpleDoubleProperty printer_overallIncome) {
        this.printer_id = printer_id;
        this.printer_itemsSold = printer_itemsSold;
        this.printer_printerID = printer_printerID;
        this.printer_name = printer_name;
        this.printer_purchaseDate = printer_purchaseDate;
        this.printer_comment = printer_comment;
        this.printer_type = printer_type;
        this.printer_shipping = printer_shipping;
        this.printer_price = printer_price;
        this.printer_incomes = printer_incomes;
        this.printer_expenses = printer_expenses;
        this.printer_overallIncome = printer_overallIncome;
    }

    public SimpleIntegerProperty getPrinter_id() {
        return printer_id;
    }

    public void setPrinter_id(SimpleIntegerProperty printer_id) {
        this.printer_id = printer_id;
    }

    public SimpleIntegerProperty getPrinter_itemsSold() {
        return printer_itemsSold;
    }

    public void setPrinter_itemsSold(SimpleIntegerProperty printer_itemsSold) {
        this.printer_itemsSold = printer_itemsSold;
    }

    public SimpleIntegerProperty getPrinter_printerID() {
        return printer_printerID;
    }

    public void setPrinter_printerID(SimpleIntegerProperty printer_printerID) {
        this.printer_printerID = printer_printerID;
    }

    public SimpleStringProperty getPrinter_name() {
        return printer_name;
    }

    public void setPrinter_name(SimpleStringProperty printer_name) {
        this.printer_name = printer_name;
    }

    public SimpleStringProperty getPrinter_purchaseDate() {
        return printer_purchaseDate;
    }

    public void setPrinter_purchaseDate(SimpleStringProperty printer_purchaseDate) {
        this.printer_purchaseDate = printer_purchaseDate;
    }

    public SimpleStringProperty getPrinter_comment() {
        return printer_comment;
    }

    public void setPrinter_comment(SimpleStringProperty printer_comment) {
        this.printer_comment = printer_comment;
    }

    public SimpleStringProperty getPrinter_type() {
        return printer_type;
    }

    public void setPrinter_type(SimpleStringProperty printer_type) {
        this.printer_type = printer_type;
    }

    public SimpleDoubleProperty getPrinter_shipping() {
        return printer_shipping;
    }

    public void setPrinter_shipping(SimpleDoubleProperty printer_shipping) {
        this.printer_shipping = printer_shipping;
    }

    public SimpleDoubleProperty getPrinter_price() {
        return printer_price;
    }

    public void setPrinter_price(SimpleDoubleProperty printer_price) {
        this.printer_price = printer_price;
    }

    public SimpleDoubleProperty getPrinter_incomes() {
        return printer_incomes;
    }

    public void setPrinter_incomes(SimpleDoubleProperty printer_incomes) {
        this.printer_incomes = printer_incomes;
    }

    public SimpleDoubleProperty getPrinter_expenses() {
        return printer_expenses;
    }

    public void setPrinter_expenses(SimpleDoubleProperty printer_expenses) {
        this.printer_expenses = printer_expenses;
    }

    public SimpleDoubleProperty getPrinter_overallIncome() {
        return printer_overallIncome;
    }

    public void setPrinter_overallIncome(SimpleDoubleProperty printer_overallIncome) {
        this.printer_overallIncome = printer_overallIncome;
    }
    
    
    
    
    public static List<String> getPrinters(User user) {
        
        //Create list
        List<String> allPrinters = new ArrayList<>();
        
        //Create query
        String query = "SELECT PrinterID, PrinterName FROM Printers ORDER BY PrinterID ASC";

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
                
                SimpleIntegerProperty id;
                SimpleStringProperty name;
                
                id = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                name = new SimpleStringProperty(rs.getString("PrinterName"));
                
                String printer = id.get() + ";" + name.get();
                
                allPrinters.add(printer);
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
    
        
    return allPrinters;
    }
}

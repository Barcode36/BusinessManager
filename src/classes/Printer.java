/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * @author Erik PC
 */
public class Printer {
    
    private SimpleIntegerProperty printer_id, printer_itemsSold, printer_type_id;
    private SimpleStringProperty printer_name, printer_purchaseDate, printer_comment, printer_type;
    private SimpleDoubleProperty printer_shipping, printer_price, printer_incomes, printer_expenses, printer_overallIncome, printer_duty, printer_tax;

    public Printer(SimpleIntegerProperty printer_id, SimpleIntegerProperty printer_itemsSold, SimpleIntegerProperty printer_type_id, SimpleStringProperty printer_name, SimpleStringProperty printer_purchaseDate, SimpleStringProperty printer_comment, SimpleStringProperty printer_type, SimpleDoubleProperty printer_shipping, SimpleDoubleProperty printer_price, SimpleDoubleProperty printer_incomes, SimpleDoubleProperty printer_expenses, SimpleDoubleProperty printer_overallIncome, SimpleDoubleProperty printer_duty, SimpleDoubleProperty printer_tax) {
        this.printer_id = printer_id;
        this.printer_itemsSold = printer_itemsSold;
        this.printer_type_id = printer_type_id;
        this.printer_name = printer_name;
        this.printer_purchaseDate = printer_purchaseDate;
        this.printer_comment = printer_comment;
        this.printer_type = printer_type;
        this.printer_shipping = printer_shipping;
        this.printer_price = printer_price;
        this.printer_incomes = printer_incomes;
        this.printer_expenses = printer_expenses;
        this.printer_overallIncome = printer_overallIncome;
        this.printer_duty = printer_duty;
        this.printer_tax = printer_tax;
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

    public SimpleIntegerProperty getPrinter_type_id() {
        return printer_type_id;
    }

    public void setPrinter_type_id(SimpleIntegerProperty printer_type_id) {
        this.printer_type_id = printer_type_id;
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

    public SimpleDoubleProperty getPrinter_duty() {
        return printer_duty;
    }

    public void setPrinter_duty(SimpleDoubleProperty printer_duty) {
        this.printer_duty = printer_duty;
    }

    public SimpleDoubleProperty getPrinter_tax() {
        return printer_tax;
    }

    public void setPrinter_tax(SimpleDoubleProperty printer_tax) {
        this.printer_tax = printer_tax;
    }

    //gets list of printer types as simple table objects - thus only id and name
    public static List<SimpleTableObject> getPrinterTypes(HikariDataSource ds) {
        
        //Create list
        List<SimpleTableObject> allPrinterTypes = new ArrayList<>();
        
        //Create query
        String query = "SELECT PrinterTypeID, PrinterType FROM PrinterTypes ORDER BY PrinterTypeID ASC";
       
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
                
                SimpleIntegerProperty id;
                SimpleStringProperty name;
                
                id = new SimpleIntegerProperty(rs.getInt("PrinterTypeID"));
                name = new SimpleStringProperty(rs.getString("PrinterType"));
                
                allPrinterTypes.add(new SimpleTableObject(id, name));
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
    
        
    return allPrinterTypes;
    }
    
    //gets list of printer as simple table objects - thus only id and name
    public static List<SimpleTableObject> getPrintersShort(HikariDataSource ds) {
        
        //Create list
        List<SimpleTableObject> allPrinters = new ArrayList<>();
        
        //Create query
        String query = "SELECT PrinterID, PrinterName FROM Printers ORDER BY PrinterID ASC";

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
                
                SimpleIntegerProperty id;
                SimpleStringProperty name;
                
                id = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                name = new SimpleStringProperty(rs.getString("PrinterName"));
                
                allPrinters.add(new SimpleTableObject(id, name));
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
    
    public static List<Printer> getPrintersLong(HikariDataSource ds) {
        
        //Create list
        List<Printer> printerList = new ArrayList<>();
        
        //Create query        
        String query = "SELECT Printers.PrinterID, Printers.PrinterName, Printers.PrinterPrice, Printers.Comment, Printers.PrinterShipping, Printers.PrinterTypeID, Printers.PurchaseDate, Printers.Duty, Printers.Tax, PrinterTypes.PrinterType FROM Printers  JOIN PrinterTypes ON Printers.PrinterTypeID = PrinterTypes.PrinterTypeID";
        
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
                
                SimpleIntegerProperty printer_id, printer_itemsSold, printer_type_id;
                SimpleStringProperty printer_name, printer_purchaseDate, printer_comment, printer_type;
                SimpleDoubleProperty printer_shipping, printer_price, printer_duty, printer_tax, printer_incomes, printer_expenses, printer_overallIncome;
                
                printer_id = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                printer_itemsSold = new SimpleIntegerProperty(MngApi.performIntegerQuery("SELECT SUM(ItemQuantity) FROM OrderItems WHERE PrinterID=" + printer_id.get(), ds));
                printer_type_id = new SimpleIntegerProperty(rs.getInt("PrinterTypeID"));
                
                printer_name = new SimpleStringProperty(rs.getString("PrinterName"));
                printer_purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));
                printer_comment = new SimpleStringProperty(rs.getString("Comment"));
                printer_type = new SimpleStringProperty(rs.getString("PrinterType"));
                
                printer_shipping = new SimpleDoubleProperty(MngApi.round(rs.getDouble("PrinterShipping"), 2));
                printer_price = new SimpleDoubleProperty(MngApi.round(rs.getDouble("PrinterPrice"), 2));
                printer_duty = new SimpleDoubleProperty(MngApi.round(rs.getDouble("Duty"), 2));
                printer_tax = new SimpleDoubleProperty(MngApi.round(rs.getDouble("Tax"), 2));
                printer_incomes = new SimpleDoubleProperty(MngApi.round(MngApi.performDoubleQuery("SELECT SUM(ItemPrice) FROM OrderItems WHERE PrinterID=" + printer_id.get(), ds), 2));
                
                double material_expenses, cost_expenses, printer_totalPrice;
                
                material_expenses = MngApi.performDoubleQuery("SELECT SUM(ItemCosts) FROM OrderItems WHERE PrinterID=" + printer_id.get(), ds);
                cost_expenses = MngApi.performDoubleQuery("SELECT SUM(CostPrice) FROM Costs WHERE PrinterID=" + printer_id.get(), ds);
                printer_totalPrice = printer_price.get() + printer_tax.get() + printer_duty.get();
                
                printer_expenses = new SimpleDoubleProperty(MngApi.round(material_expenses + cost_expenses + printer_totalPrice, 2));
                
                printer_overallIncome = new SimpleDoubleProperty(MngApi.round(printer_incomes.get() - printer_expenses.get(), 2));
                
                Printer printer = new Printer(printer_id, printer_itemsSold, printer_type_id, printer_name, printer_purchaseDate, printer_comment, printer_type, printer_shipping, printer_price, printer_incomes, printer_expenses, printer_overallIncome, printer_duty, printer_tax);
                
                printerList.add(printer);
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
        
        return printerList;
    } 
    
    public static void insertNewPrinter(Printer printer, HikariDataSource ds){
     
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
	    
                int i =1;
                updateQuery = "INSERT INTO Printers (PrinterID,PrinterName,PrinterPrice,Comment,PrinterShipping,PrinterTypeID,PurchaseDate,Duty,Tax) VALUES (?,?,?,?,?,?,?,?,?) "
                    + "ON DUPLICATE KEY UPDATE PrinterID=?,PrinterName=?,PrinterPrice=?,Comment=?,PrinterShipping=?,PrinterTypeID=?,PurchaseDate=?,Duty=?,Tax=?";    
                stmt = conn.prepareStatement(updateQuery);
               
                stmt.setInt(i, printer.getPrinter_id().get());i++;
                stmt.setString(i, printer.getPrinter_name().get());i++;
                stmt.setDouble(i, printer.getPrinter_price().get());i++;
                stmt.setString(i, printer.getPrinter_comment().get());i++;
                stmt.setDouble(i, printer.getPrinter_shipping().get());i++;
                stmt.setInt(i, printer.getPrinter_type_id().get());i++;
                stmt.setString(i, printer.getPrinter_purchaseDate().get());i++;
                stmt.setDouble(i, printer.getPrinter_duty().get());i++;
                stmt.setDouble(i, printer.getPrinter_tax().get());i++;
                
                stmt.setInt(i, printer.getPrinter_id().get());i++;
                stmt.setString(i, printer.getPrinter_name().get());i++;
                stmt.setDouble(i, printer.getPrinter_price().get());i++;
                stmt.setString(i, printer.getPrinter_comment().get());i++;
                stmt.setDouble(i, printer.getPrinter_shipping().get());i++;
                stmt.setInt(i, printer.getPrinter_type_id().get());i++;
                stmt.setString(i, printer.getPrinter_purchaseDate().get());i++;
                stmt.setDouble(i, printer.getPrinter_duty().get());i++;
                stmt.setDouble(i, printer.getPrinter_tax().get());i++;
                                                
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
    
    public static void deletePrinters(ObservableList<Printer> printers, Label info, HikariDataSource ds){
        
        for (int i = 0; i < printers.size(); i++) {
            
            int id = printers.get(i).getPrinter_id().get();
            String query = "DELETE FROM Printers WHERE PrinterID=" + id;
            MngApi.performUpdateQuery(query, info, ds);
            
        }
        
    }
}

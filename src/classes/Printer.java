/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class Printer {
    
    //table columns
    private SimpleIntegerProperty printer_id, printer_type_id;
    private SimpleStringProperty printer_name, printer_purchaseDate, printer_comment;
    private SimpleDoubleProperty printer_shipping, printer_price, printer_duty, printer_tax;
    
    //additional columns
    private SimpleIntegerProperty printer_itemsSold;
    private SimpleStringProperty printer_type;
    private SimpleDoubleProperty printer_overallIncome, printer_expenses, printer_incomes;

    public Printer(SimpleIntegerProperty printer_id, SimpleIntegerProperty printer_type_id, SimpleStringProperty printer_name, SimpleStringProperty printer_purchaseDate, SimpleStringProperty printer_comment, SimpleDoubleProperty printer_shipping, SimpleDoubleProperty printer_price, SimpleDoubleProperty printer_duty, SimpleDoubleProperty printer_tax) {
        this.printer_id = printer_id;
        this.printer_type_id = printer_type_id;
        this.printer_name = printer_name;
        this.printer_purchaseDate = printer_purchaseDate;
        this.printer_comment = printer_comment;
        this.printer_shipping = printer_shipping;
        this.printer_price = printer_price;
        this.printer_duty = printer_duty;
        this.printer_tax = printer_tax;
    }
    
    public static ObservableList<Printer> downloadPrintersTable(HikariDataSource ds) {
        //Create list
        ObservableList<Printer> printers = FXCollections.observableArrayList();
        
        //Create query        
        String query = "SELECT * FROM Printers ORDER BY PrinterID";

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
                
                SimpleIntegerProperty printer_id, printer_type_id;
                SimpleStringProperty printer_name, printer_purchaseDate, printer_comment;
                SimpleDoubleProperty printer_shipping, printer_price, printer_duty, printer_tax;
                
                printer_id = new SimpleIntegerProperty(rs.getInt("PrinterID"));
                printer_type_id = new SimpleIntegerProperty(rs.getInt("PrinterTypeID"));
                
                printer_name = new SimpleStringProperty(rs.getString("PrinterName"));
                printer_purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));
                printer_comment = new SimpleStringProperty(rs.getString("Comment"));
                
                printer_shipping = new SimpleDoubleProperty(rs.getDouble("PrinterShipping"));
                printer_price = new SimpleDoubleProperty(rs.getDouble("PrinterPrice"));
                printer_duty = new SimpleDoubleProperty(rs.getDouble("Duty"));
                printer_tax = new SimpleDoubleProperty(rs.getDouble("Tax"));
                    
                Printer printer = new Printer(printer_id, printer_type_id, printer_name, printer_purchaseDate, printer_comment, printer_shipping, printer_price, printer_duty, printer_tax);
                                
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
    
    public static ObservableList<Printer> getPrinters(ObservableList<Printer> printers, ObservableList<SimpleTableObject> commonMaterialProperties, ObservableList<OrderItem> orderItems, ObservableList<Cost> costs) {
        
        for (int i = 0; i < printers.size(); i++) {
            
            Printer currentPrinter = printers.get(i);
                        
            //additional columns
            SimpleIntegerProperty printer_itemsSold;
            SimpleStringProperty printer_type;
            SimpleDoubleProperty printer_overallIncome, printer_expenses, printer_incomes;
                
            final SimpleIntegerProperty printer_id = currentPrinter.getPrinter_id();
            final SimpleIntegerProperty printer_type_id = currentPrinter.getPrinter_type_id();
            
            printer_itemsSold = new SimpleIntegerProperty(Printer.getItemsSold(orderItems, printer_id));                
            printer_type = currentPrinter.getPrinter_type();
            
            double expenses[] = null;
            expenses[0] = Printer.getPrinterIncomesAndMaterialExpenses(orderItems, printer_id)[0];//load printer incomes
            expenses[1] = Printer.getPrinterIncomesAndMaterialExpenses(orderItems, printer_id)[1];//load material expenses
            
            printer_incomes = new SimpleDoubleProperty(expenses[0]);
                
            double material_expenses, cost_expenses;
                
            material_expenses = expenses[1];
            cost_expenses = getPrinterCosts(costs, printer_id);                
            printer_expenses = new SimpleDoubleProperty(MngApi.round(material_expenses + cost_expenses, 2));                
            printer_overallIncome = new SimpleDoubleProperty(MngApi.round(printer_incomes.get() - printer_expenses.get(), 2));
                
            currentPrinter.setPrinter_itemsSold(printer_itemsSold);
            currentPrinter.setPrinter_type(printer_type);
            currentPrinter.setPrinter_overallIncome(printer_overallIncome);
            currentPrinter.setPrinter_expenses(printer_expenses);
            currentPrinter.setPrinter_incomes(printer_incomes);
            
        }           
        return printers;
    } 
    
    public static double getPrinterCosts(ObservableList<Cost> costs, SimpleIntegerProperty printer_id){
        
        double sumOfCosts = 0;
        
        for (int i = 0; i < costs.size(); i++) {
            
            if(costs.get(i).getCost_printerID() == printer_id){
                sumOfCosts += costs.get(i).getCost_price().get();
                sumOfCosts += costs.get(i).getCost_shipping().get();
            }
            
        }
        return sumOfCosts;
    }
    
    public static Printer getPrinterById(/*ObservableList<SimpleTableObject> commonMaterialProperties, */ObservableList<Printer> printersTable, SimpleIntegerProperty id){
        
        for (int i = 0; i < printersTable.size(); i++) {
            if(printersTable.get(i).getPrinter_id().get() == id.get()){
                //need to add printer type
                //Printer printer = printersTable.get(i);                
                return printersTable.get(i);
            }
        }        
        return null;
    }
    
    public static int getItemsSold(ObservableList<OrderItem> orderItems, SimpleIntegerProperty printer_id){
        
        int itemsPrinted = 0;
        
        for (int i = 0; i < orderItems.size(); i++) {
            
            if(orderItems.get(i).getPrinter().getPrinter_id() == printer_id)return itemsPrinted + orderItems.get(i).getQuantity().get();
            
        }
        
        return itemsPrinted;
    }
    
    //those are from the same table so why don't load them at once so we dont have to run through same table twice?
    public static double[] getPrinterIncomesAndMaterialExpenses(ObservableList<OrderItem> orderItems, SimpleIntegerProperty printer_id){
        
        double[] expenses = null;
        
        double incomes = 0, material_expenses = 0;
        
        for (int i = 0; i < orderItems.size(); i++) {            
            if(orderItems.get(i).getPrinter().getPrinter_id() == printer_id) {
                incomes += orderItems.get(i).getPrice().get();
                material_expenses += orderItems.get(i).getObject().getObject_costs().get();
            }            
        }
        
        expenses[0] = incomes;
        expenses[1] = material_expenses;
        
        return expenses;        
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
            System.out.println(query);
            MngApi.performUpdateQuery(query, info, ds);
            
        }
        info.setText("Printer(s) deleted.");
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
}

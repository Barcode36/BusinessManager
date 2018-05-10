/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Modality;

/**
 * Created by erikd on 2/22/2017.
 */

//this class contains some auxiliary methods which location is not bonded to controller file nor PrintED.java file
public class MngApi {
    
    //opens windows from specific fxmlFile with specific title and dimensions
    public void openWindow(String fxmlFile, String title, int width, int height){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root1, width, height));//1058, 763
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.setResizable(false);            
            
            
        }catch (IOException e){

        }
    }
    
    public static boolean isNumber(String string){
        Boolean status;
        try{
             int a = Integer.parseInt(string);
             status = true;
        }catch (NumberFormatException e){
            status = false;
        }
    return status;
    }
    
    public static boolean isString(String string){
        Boolean status;
        try{
             int a = Integer.parseInt(string);
             status = true;
        }catch (NumberFormatException e){
            status = false;
        }
    return status;
    }
    
    //closes any window of specified component/child
    public static void closeWindow(Button btn){
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
    
    //method for rounding double numbers
    public static double round(double value, int places) {        
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    //converting minutes into minutes and hours
    public static SimpleStringProperty convertToHours (int time){
        SimpleStringProperty newTime;
        
        int hours = time / 60; //since both are ints, you get an int
        int minutes = time % 60;
        
        newTime = new SimpleStringProperty(String.format("%dh %02dm", hours, minutes));
        
        return newTime;
    }

    public static int getCurrentAutoIncrementValue(User user, String tableName){
        int currentAutoIncrementValue = 0;
        
        //Create query
        String query = "SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA ='" + user.getName() + "' AND   TABLE_NAME   ='" + tableName + "'";

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
                
                currentAutoIncrementValue = rs.getInt("AUTO_INCREMENT");
                
            }

            rs.close();
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
        
        return currentAutoIncrementValue;
    }
}


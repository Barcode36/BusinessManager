/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.zaxxer.hikari.HikariDataSource;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
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
    public static SimpleStringProperty convertToFormattedTime (int time){
        SimpleStringProperty newTime;
        
        int hours = time / 60; //since both are ints, you get an int
        int minutes = time % 60;
        
        newTime = new SimpleStringProperty(String.format("%dh %02dm", hours, minutes));
        
        return newTime;
    }
    
    public static int convertToMinutes(String time){ 
        
        String str1 = time.split(" ")[0], str2 = time.split(" ")[1];
        int newTime = 0;
        
        
        str1 = str1.substring(0, str1.length() - 1);
        str2 = str2.substring(0, str2.length() - 1);
        
        int hours = Integer.parseInt(str1);        
        int minutes = Integer.parseInt(str2);
        
        newTime = hours*60 + minutes;
        
        return newTime;
    }

    public static int getHours(int time){
        
        String formatted_time = convertToFormattedTime(time).get();
        String str1 = formatted_time.split(" ")[0], str2 = formatted_time.split(" ")[1];
        
        //remove letters
        str1 = str1.substring(0, str1.length() - 1);
        str2 = str2.substring(0, str2.length() - 1);
        
        
        int hours = Integer.parseInt(str1);        
        int minutes = hours*60 + Integer.parseInt(str2);
                
        return hours;        
    }
    
    public static int getMinutes(int time){
        
        String formatted_time = convertToFormattedTime(time).get();
        String str1 = formatted_time.split(" ")[0], str2 = formatted_time.split(" ")[1];
        
        //remove letters
        str1 = str1.substring(0, str1.length() - 1);
        str2 = str2.substring(0, str2.length() - 1);
        
        
        int hours = Integer.parseInt(str1);        
        int minutes = Integer.parseInt(str2);
        
        return minutes;
    }
    
    
    public static int getCurrentAutoIncrementValue(HikariDataSource ds, String tableName){
        int currentAutoIncrementValue = 0;
        
        //Create query
        String query = "SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA ='" + ds.getUsername() + "' AND   TABLE_NAME   ='" + tableName + "'";
        
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
    
    public static void performUpdateQuery(String query, Label info, HikariDataSource ds){
        //Create query
        String updateQuery = query;

        Connection conn = null;
        Statement stmt = null;
        
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            if(conn.isValid(15) == false) {
                System.out.print("Connection Lost");
            }
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            stmt.executeUpdate(updateQuery);
                       
            
        } catch (SQLIntegrityConstraintViolationException e){
            
            String[] message = e.getMessage().split("`");
            String table = message[3];
            table = table.replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
            info.setText("Remove related " + table + " first.");
            info.setTextFill(Color.web("#ff0000"));
            
        } catch (SQLNonTransientConnectionException se) {
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
    }
    
    public static void performMultipleUpdates(ObservableList<String> updateQueries, HikariDataSource ds){
 
        Connection conn = null;
        Statement stmt = null;
        
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            conn = ds.getConnection();
            if(conn.isValid(15) == false) {
                System.out.print("Connection Lost");
            }
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            for (int i = 0; i < updateQueries.size(); i++) {
                stmt.executeUpdate(updateQueries.get(i));
            }
            
                       
            
        } catch (SQLNonTransientConnectionException se) {
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
    }
    
    //diplays connetion lost dialog window
    public void alertConnectionLost(){
        try{            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ConnectionLost.fxml"));            
                Parent root1 = fxmlLoader.load();                
                Stage stage = new Stage();
                stage.setTitle("Connection Lost");
                stage.setAlwaysOnTop(true);
                stage.centerOnScreen();           
                stage.setScene(new Scene(root1));
                stage.setResizable(false);                
                stage.show();            
                
            }catch (IOException e){

            }  
    }
    
    public static boolean isTextFieldEmpty(TextField... textfields) {
        
        boolean isEmpty = false;
        
        for (int i = 0; i < textfields.length; i++) {
            TextField textfield = textfields[i];
            if(textfield.lengthProperty().get() == 0)isEmpty = true;
        }
        
        return isEmpty;
    }
    
    public static void checkApostrophe(TextField... textfields) {
        
        for (int i = 0; i < textfields.length; i++) {
            TextField textfield = textfields[i];
            String text = textfield.getText();            
            if(text.contains("'"))textfield.setText(text.replace("'", "''"));            
        }
                
    }
    
    public static boolean isComboBoxEmpty(ComboBox... comboboxes) {
        
        boolean isEmpty = false;
        
        for (int i = 0; i < comboboxes.length; i++) {
            ComboBox combobox = comboboxes[i];
            if(combobox.getSelectionModel().isEmpty())isEmpty = true;
        }
        
        return isEmpty;
    }
    
    public static void setActualDate(DatePicker datePicker) {
        datePicker.setValue(LocalDateTime.now().toLocalDate());
    }
   
    public static ListView<SimpleTableObject> convertToListView(List<SimpleTableObject> list){
        //Create list of orders
        ObservableList<SimpleTableObject> observableList = FXCollections.observableArrayList(list);
        
        ListView<SimpleTableObject> listView = new ListView<>(observableList);

        return listView;
    }
    
    //performs query which resul is integer and returns result
    public static int performIntegerQuery(String query, HikariDataSource ds){
        int result = 0;

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
                
                result = rs.getInt(1);
               
            }

            rs.close();
        } catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
        } catch (SQLNonTransientConnectionException se) {
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
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
            }//end finally try
        }//end try
        
        return result;
    }
    
    //performs query which resul is integer and returns result
    public static String performStringQuery(String query, HikariDataSource ds){
        String result = null;

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
                
                result = rs.getString(1);
               
            }

            rs.close();
        } catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
        } catch (SQLNonTransientConnectionException se) {
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
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
            }//end finally try
        }//end try
        
        return result;
    }
    
    //performs query which resul is integer and returns result
    public static double performDoubleQuery(String query, HikariDataSource ds){
        double result = 0;
        
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
                
                result = rs.getDouble(1);
               
            }

            rs.close();
        } catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
        } catch (SQLNonTransientConnectionException se) {
            MngApi obj = new MngApi();
            obj.alertConnectionLost();
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
            }//end finally try
        }//end try
        
        return result;
    }
    
}//end of class


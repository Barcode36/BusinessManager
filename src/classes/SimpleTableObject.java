/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author edemko
 */
public class SimpleTableObject {
    
    private SimpleIntegerProperty property_id, property_type_id;
    private SimpleStringProperty property_name;

    public SimpleTableObject(SimpleIntegerProperty property_id, SimpleIntegerProperty property_type_id, SimpleStringProperty property_type_name) {
        this.property_id = property_id;
        this.property_type_id = property_type_id;
        this.property_name = property_type_name;
    }
    
    public static ObservableList<SimpleTableObject> downloadCommonMaterialProperties(HikariDataSource ds) {
        
        //Create list
        ObservableList<SimpleTableObject> properties = FXCollections.observableArrayList();
        
        //Create query
        String query = "SELECT * FROM CommonMaterialProperties";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            
            if(conn.isValid(10) == false) {
                MngApi obj = new MngApi();
                obj.alertConnectionLost();
            }
            
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleIntegerProperty property_id, property_type_id;
                SimpleStringProperty property_type_name;
                
                property_id = new SimpleIntegerProperty(rs.getInt("PropertyID"));
                property_type_id = new SimpleIntegerProperty(rs.getInt("PropertyTypeID"));
                property_type_name = new SimpleStringProperty(rs.getString("PropertyName"));
                
                SimpleTableObject property = new SimpleTableObject(property_id, property_type_id, property_type_name);
                
                properties.add(property);
                
            }

            rs.close();
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
        
    return properties;
    }
    
    public static ObservableList<SimpleTableObject> downloadMaterialPropertyTypes(HikariDataSource ds) {
        
        //Create list
        ObservableList<SimpleTableObject> types = FXCollections.observableArrayList();
        
        //Create query
        String query = "SELECT * FROM MaterialPropertyTypes";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            
            if(conn.isValid(10) == false) {
                MngApi obj = new MngApi();
                obj.alertConnectionLost();
            }
            
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleIntegerProperty property_id, property_type_id;
                SimpleStringProperty property_type_name;
                
                property_id = new SimpleIntegerProperty(rs.getInt("PropertyTypeID"));                
                property_type_name = new SimpleStringProperty(rs.getString("PropertyTypeName"));
                
                SimpleTableObject property = new SimpleTableObject(property_id, null, property_type_name);
                
                types.add(property);
                
            }

            rs.close();
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
    return types;    
    }
    
    public static ObservableList<SimpleTableObject> downloadCommonCustomerProperties(HikariDataSource ds) {
        
        //Create list
        ObservableList<SimpleTableObject> properties = FXCollections.observableArrayList();
        
        //Create query
        String query = "select * from CommonCustomerProperties";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            
            if(conn.isValid(10) == false) {
                MngApi obj = new MngApi();
                obj.alertConnectionLost();
            }
            
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleIntegerProperty property_id, property_type_id;
                SimpleStringProperty property_type_name;
                
                property_id = new SimpleIntegerProperty(rs.getInt("PropertyID"));
                property_type_id = new SimpleIntegerProperty(rs.getInt("PropertyTypeID"));
                property_type_name = new SimpleStringProperty(rs.getString("PropertyName"));
                
                SimpleTableObject obj = new SimpleTableObject(property_id, property_type_id, property_type_name);
                
                properties.add(obj);
                
            }

            rs.close();
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
        
    return properties;
    }
    
    public static ObservableList<SimpleTableObject> downloadCustomerPropertyTypes(HikariDataSource ds) {
        
        //Create list
        ObservableList<SimpleTableObject> types = FXCollections.observableArrayList();
        
        //Create query
        String query = "select * from CustomerPropertyTypes";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = ds.getConnection();
            
            if(conn.isValid(10) == false) {
                MngApi obj = new MngApi();
                obj.alertConnectionLost();
            }
            
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleIntegerProperty property_id, property_type_id;
                SimpleStringProperty property_type_name;
                
                property_id = new SimpleIntegerProperty(rs.getInt("PropertyTypeID"));                
                property_type_name = new SimpleStringProperty(rs.getString("PropertyTypeName"));
                
                SimpleTableObject obj = new SimpleTableObject(property_id, null, property_type_name);
                
                types.add(obj);
                
            }

            rs.close();
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
    
        return types;    
    }
    
    public static SimpleStringProperty getPropertyByID(ObservableList<SimpleTableObject> propertiesTable, SimpleIntegerProperty property_id){
                
        SimpleTableObject property = null;
        
        //binary search: we are searching first orderItem with material_id and then we will find beginning of series (there could be multiple orderItems with same material_id
        int numberToGuess = property_id.get();
        int start = 1;
        int end = propertiesTable.size();
        int position;
        
        while(start <= end){
            position = (start + end)/2;
            
            property = propertiesTable.get(position);
            
            if (property.getProperty_id()== property_id)break;
            
            if(numberToGuess < property.getProperty_id().get()){
                
                end = position - 1;
                
            } else {
                
                start = position + 1;
                
            }            
        }
        
        return property.getProperty_name();
    }
    
    //get number of properties of particular type passed as parameter - used for statistic calculation like number of material types, number of colors, etc....
    public static int getNumberOfProperties(List<SimpleTableObject> list, int type_id){
        
        int counter = 0;
        
        for (int i = 0; i < list.size(); i++) {
            
            SimpleTableObject obj = list.get(i);
            
            if(obj.getProperty_type_id().get() == type_id)
            counter++;
        }        
        return counter;
    }
    
    public SimpleIntegerProperty getProperty_id() {
        return property_id;
    }

    public void setProperty_id(SimpleIntegerProperty property_id) {
        this.property_id = property_id;
    }

    public SimpleIntegerProperty getProperty_type_id() {
        return property_type_id;
    }

    public void setProperty_type_id(SimpleIntegerProperty property_type_id) {
        this.property_type_id = property_type_id;
    }

    public SimpleStringProperty getProperty_name() {
        return property_name;
    }

    public void setProperty_name(SimpleStringProperty property_name) {
        this.property_name = property_name;
    }
    
}

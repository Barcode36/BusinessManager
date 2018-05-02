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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author edemko
 */
public class Material {
    
    SimpleStringProperty materialManufacturerProperty,  materialTypeProperty,  materialColorProperty, materialPurchaseDateProperty,  materialSellerProperty, materialFinishedProperty, materialMaterialSpentProperty;        
    SimpleIntegerProperty materialIDProperty;     
    SimpleFloatProperty materialWeightProperty, materialPriceProperty, materialShippingProperty, materialTrashProperty, materialSpentProperty;

    public Material(SimpleStringProperty materialManufacturerProperty, SimpleStringProperty materialTypeProperty, SimpleStringProperty materialColorProperty, SimpleStringProperty materialPurchaseDateProperty, SimpleStringProperty materialSellerProperty, SimpleStringProperty materialFinishedProperty, SimpleIntegerProperty materialIDProperty, SimpleFloatProperty materialWeightProperty, SimpleFloatProperty materialPriceProperty, SimpleFloatProperty materialShippingProperty, SimpleStringProperty materialMaterialSpentProperty, SimpleFloatProperty materialTrashProperty, SimpleFloatProperty materialSpentProperty) {
        this.materialManufacturerProperty = materialManufacturerProperty;
        this.materialTypeProperty = materialTypeProperty;
        this.materialColorProperty = materialColorProperty;
        this.materialPurchaseDateProperty = materialPurchaseDateProperty;
        this.materialSellerProperty = materialSellerProperty;
        this.materialFinishedProperty = materialFinishedProperty;
        this.materialIDProperty = materialIDProperty;
        this.materialWeightProperty = materialWeightProperty;
        this.materialPriceProperty = materialPriceProperty;
        this.materialShippingProperty = materialShippingProperty;
        this.materialMaterialSpentProperty = materialMaterialSpentProperty;
        this.materialTrashProperty = materialTrashProperty;
        this.materialSpentProperty = materialSpentProperty;
    }

    public SimpleStringProperty getMaterialManufacturerProperty() {
        return materialManufacturerProperty;
    }

    public void setMaterialManufacturerProperty(SimpleStringProperty materialManufacturerProperty) {
        this.materialManufacturerProperty = materialManufacturerProperty;
    }

    public SimpleStringProperty getMaterialTypeProperty() {
        return materialTypeProperty;
    }

    
    public void setMaterialTypeProperty(SimpleStringProperty materialTypeProperty) {
        this.materialTypeProperty = materialTypeProperty;
    }

    public SimpleStringProperty getMaterialColorProperty() {
        return materialColorProperty;
    }

    public void setMaterialColorProperty(SimpleStringProperty materialColorProperty) {
        this.materialColorProperty = materialColorProperty;
    }

    public SimpleStringProperty getMaterialPurchaseDateProperty() {
        return materialPurchaseDateProperty;
    }

    public void setMaterialPurchaseDateProperty(SimpleStringProperty materialPurchaseDateProperty) {
        this.materialPurchaseDateProperty = materialPurchaseDateProperty;
    }

    public SimpleStringProperty getMaterialSellerProperty() {
        return materialSellerProperty;
    }

    public void setMaterialSellerProperty(SimpleStringProperty materialSellerProperty) {
        this.materialSellerProperty = materialSellerProperty;
    }

    public SimpleStringProperty getMaterialFinishedProperty() {
        return materialFinishedProperty;
    }

    public void setMaterialFinishedProperty(SimpleStringProperty materialFinishedProperty) {
        this.materialFinishedProperty = materialFinishedProperty;
    }

    public SimpleIntegerProperty getMaterialIDProperty() {
        return materialIDProperty;
    }

    public void setMaterialIDProperty(SimpleIntegerProperty materialIDProperty) {
        this.materialIDProperty = materialIDProperty;
    }

    public SimpleFloatProperty getMaterialWeightProperty() {
        return materialWeightProperty;
    }

    public SimpleFloatProperty getMaterialSpentProperty() {
        return materialSpentProperty;
    }    

    public void setMaterialWeightProperty(SimpleFloatProperty materialWeightProperty) {
        this.materialWeightProperty = materialWeightProperty;
    }

    public SimpleFloatProperty getMaterialPriceProperty() {
        return materialPriceProperty;
    }

    public void setMaterialPriceProperty(SimpleFloatProperty materialPriceProperty) {
        this.materialPriceProperty = materialPriceProperty;
    }

    public SimpleFloatProperty getMaterialShippingProperty() {
        return materialShippingProperty;
    }

    public void setMaterialShippingProperty(SimpleFloatProperty materialShippingProperty) {
        this.materialShippingProperty = materialShippingProperty;
    }

    public SimpleStringProperty getMaterialMaterialSpentProperty() {
        return materialMaterialSpentProperty;
    }

    public void setMaterialMaterialSpentProperty(SimpleStringProperty materialMaterialSpentProperty) {
        this.materialMaterialSpentProperty = materialMaterialSpentProperty;
    }

    public SimpleFloatProperty getMaterialTrashProperty() {
        return materialTrashProperty;
    }

    public void setMaterialTrashProperty(SimpleFloatProperty materialTrashProperty) {
        this.materialTrashProperty = materialTrashProperty;
    }

    public void setMaterialSpentProperty(SimpleFloatProperty materialSpentProperty) {
        this.materialSpentProperty = materialSpentProperty;
    }    
    
    //this method get list of materials - ONLY materials, without statistics
    public static List<Material> loadMaterialsList(String user, String pass, String address, String dbName, String query) {
        //Create list
        List<Material> allMaterialsList = new ArrayList<>();

        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://" + address + "/" + dbName;

        //  Database credentials
        String USER = user;
        String PASS = pass;


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
                
                SimpleIntegerProperty materialIDProperty = new SimpleIntegerProperty(rs.getInt("MaterialID"));
                
                SimpleStringProperty manufacturerProperty = new SimpleStringProperty(rs.getString("Manufacturer"));
                SimpleStringProperty materialTypeProperty = new SimpleStringProperty(rs.getString("Type"));
                SimpleStringProperty materialColorProperty = new SimpleStringProperty(rs.getString("Color"));
                SimpleStringProperty materialPurchaseDateProperty = new SimpleStringProperty(rs.getString("PurchaseDate"));
                SimpleStringProperty materialSellerProperty = new SimpleStringProperty(rs.getString("Seller"));
                SimpleStringProperty materialFinishedProperty = new SimpleStringProperty(rs.getString("Finished"));
                
                SimpleFloatProperty materialWeightProperty = new SimpleFloatProperty(rs.getFloat("MaterialWeight"));
                SimpleFloatProperty materialPriceProperty = new SimpleFloatProperty(rs.getFloat("MaterialPrice"));
                SimpleFloatProperty materialShippingProperty = new SimpleFloatProperty(rs.getFloat("MaterialShipping"));                
                SimpleFloatProperty materialTrashProperty = new SimpleFloatProperty(rs.getFloat("Trash"));
                
                String orderQuery = "SELECT Orders.ObjectID, Objects.ObjectWeight, Orders.Quantity, Orders.MaterialID, Materials.MaterialWeight, Materials.MaterialPrice FROM Orders JOIN Objects ON Orders.ObjectID = Objects.ObjectID JOIN Materials ON Orders.MaterialID = Materials.MaterialID WHERE Orders.MaterialID=" + materialIDProperty.get();
                String inStockQuery = "SELECT InStock.ObjectID, Objects.ObjectWeight, InStock.Quantity, InStock.MaterialID, Materials.MaterialWeight, Materials.MaterialPrice FROM InStock JOIN Objects ON InStock.ObjectID = Objects.ObjectID JOIN Materials ON InStock.MaterialID = Materials.MaterialID WHERE InStock.MaterialID=" + materialIDProperty.get();
                
                Float orderMaterialConsumption = loadMaterialConsuption(user, pass, address, dbName, orderQuery, materialIDProperty.get());
                Float inStockMaterialConsumption = loadMaterialConsuption(user, pass, address, dbName, inStockQuery, materialIDProperty.get());
                
                float materialConsumption = orderMaterialConsumption + inStockMaterialConsumption;
                
                float materialWeight = materialWeightProperty.get()*1000;
                float materialConsumptionPercentage = materialConsumption/materialWeight*100;
                
                SimpleStringProperty materialMaterialSpentProperty = new SimpleStringProperty(String.format("%.2f %%", materialConsumptionPercentage));
                
                Material material = new Material(manufacturerProperty, materialTypeProperty, materialColorProperty, materialPurchaseDateProperty, materialSellerProperty, materialFinishedProperty, materialIDProperty, materialWeightProperty, materialPriceProperty, materialShippingProperty, materialMaterialSpentProperty, materialTrashProperty, new SimpleFloatProperty(0));
                
                allMaterialsList.add(material);                
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
        
    return allMaterialsList;
    }
    
    public static Material loadMaterialID(String user, String pass, String address, String dbName, int materialID) {
        String query = "Select * from Materials WHERE MaterialID=" + materialID;        
        Material material = null;

        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://" + address + "/" + dbName;

        //  Database credentials
        String USER = user;
        String PASS = pass;


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
                
                SimpleIntegerProperty materialIDProperty = new SimpleIntegerProperty(rs.getInt("MaterialID"));
                
                SimpleStringProperty materialManufacturerProperty = new SimpleStringProperty(rs.getString("Manufacturer"));
                SimpleStringProperty materialTypeProperty = new SimpleStringProperty(rs.getString("Type"));
                SimpleStringProperty materialColorProperty = new SimpleStringProperty(rs.getString("Color"));
                SimpleStringProperty materialPurchaseDateProperty = new SimpleStringProperty(rs.getString("PurchaseDate"));
                SimpleStringProperty materialSellerProperty = new SimpleStringProperty(rs.getString("Seller"));
                SimpleStringProperty materialFinishedProperty = new SimpleStringProperty(rs.getString("Finished"));
                
                SimpleFloatProperty materialWeightProperty = new SimpleFloatProperty(rs.getFloat("MaterialWeight"));
                SimpleFloatProperty materialPriceProperty = new SimpleFloatProperty(rs.getFloat("MaterialPrice"));
                SimpleFloatProperty materialShippingProperty = new SimpleFloatProperty(rs.getFloat("MaterialShipping"));                
                SimpleFloatProperty materialTrashProperty = new SimpleFloatProperty(rs.getFloat("Trash"));
                
                String orderQuery = "SELECT Orders.ObjectID, Objects.ObjectWeight, Orders.Quantity, Orders.MaterialID, Materials.MaterialWeight, Materials.MaterialPrice FROM Orders JOIN Objects ON Orders.ObjectID = Objects.ObjectID JOIN Materials ON Orders.MaterialID = Materials.MaterialID WHERE Orders.MaterialID=" + materialIDProperty.get();
                String inStockQuery = "SELECT InStock.ObjectID, Objects.ObjectWeight, InStock.Quantity, InStock.MaterialID, Materials.MaterialWeight, Materials.MaterialPrice FROM InStock JOIN Objects ON InStock.ObjectID = Objects.ObjectID JOIN Materials ON InStock.MaterialID = Materials.MaterialID WHERE InStock.MaterialID=" + materialIDProperty.get();
                
                Float orderMaterialConsumption = loadMaterialConsuption(user, pass, address, dbName, orderQuery, materialIDProperty.get());
                Float inStockMaterialConsumption = loadMaterialConsuption(user, pass, address, dbName, inStockQuery, materialIDProperty.get());
                
                float materialConsumption = orderMaterialConsumption + inStockMaterialConsumption;
                
                float materialWeight = materialWeightProperty.get()*1000;
                float materialConsumptionPercentage = materialConsumption/materialWeight*100;

                SimpleStringProperty materialMaterialSpentProperty = new SimpleStringProperty(String.format("%.2f %%", materialConsumptionPercentage));
                
                material = new Material(materialManufacturerProperty, materialTypeProperty, materialColorProperty, materialPurchaseDateProperty, materialSellerProperty, materialFinishedProperty, materialIDProperty, materialWeightProperty, materialPriceProperty, materialShippingProperty, materialMaterialSpentProperty, materialTrashProperty, new SimpleFloatProperty(0));
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
        
    return material;
    }
    
    public static Float loadMaterialConsuption(String user, String pass, String address, String dbName, String query, int materialID) {        
                
        float totalObjectsWeight = 0;        
        
        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://" + address + "/" + dbName;

        //  Database credentials
        String USER = user;
        String PASS = pass;


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
            while(rs.next()){                
                float objectWeight = rs.getFloat("Quantity")*rs.getFloat("ObjectWeight");
                totalObjectsWeight = totalObjectsWeight + objectWeight;                
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
        
    return totalObjectsWeight;
    }
    
    public static Float getPricePerGram(SimpleIntegerProperty material_id, User user) {        
                
        float totalObjectsWeight = 0;
        String query = "SELECT Materials.MaterialWeight, SUM(Materials.MaterialPrice + MaterialShipping) AS 'MaterialPrice' FROM Materials";
        
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
            while(rs.next()){                
                float objectWeight = rs.getFloat("Quantity")*rs.getFloat("ObjectWeight");
                totalObjectsWeight = totalObjectsWeight + objectWeight;                
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
        
    return totalObjectsWeight;
    }
}

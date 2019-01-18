/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
 * @author edemko
 */
public class Material {
    
    SimpleStringProperty material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment;
    SimpleIntegerProperty material_id, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
    SimpleDoubleProperty material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_remaining, material_weight;

    public Material(SimpleStringProperty material_color, SimpleStringProperty material_manufacturer, SimpleStringProperty material_type, SimpleStringProperty material_finished, SimpleStringProperty material_distributor, SimpleStringProperty material_purchaseDate, SimpleStringProperty material_comment, SimpleIntegerProperty material_id, SimpleDoubleProperty material_weight, SimpleIntegerProperty material_id_manufacturer, SimpleIntegerProperty material_id_materialType, SimpleIntegerProperty material_id_color, SimpleIntegerProperty material_id_weight, SimpleIntegerProperty material_id_seller, SimpleIntegerProperty material_id_diameter, SimpleDoubleProperty material_diameter, SimpleDoubleProperty material_price, SimpleDoubleProperty material_shipping, SimpleDoubleProperty material_used, SimpleDoubleProperty material_trash, SimpleDoubleProperty material_soldFor, SimpleDoubleProperty material_profit, SimpleDoubleProperty material_remaining) {
        this.material_color = material_color;
        this.material_manufacturer = material_manufacturer;
        this.material_type = material_type;
        this.material_finished = material_finished;
        this.material_distributor = material_distributor;
        this.material_purchaseDate = material_purchaseDate;
        this.material_comment = material_comment;
        this.material_id = material_id;
        this.material_weight = material_weight;
        this.material_id_manufacturer = material_id_manufacturer;
        this.material_id_materialType = material_id_materialType;
        this.material_id_color = material_id_color;
        this.material_id_weight = material_id_weight;
        this.material_id_seller = material_id_seller;
        this.material_id_diameter = material_id_diameter;
        this.material_diameter = material_diameter;
        this.material_price = material_price;
        this.material_shipping = material_shipping;
        this.material_used = material_used;
        this.material_trash = material_trash;
        this.material_soldFor = material_soldFor;
        this.material_profit = material_profit;
        this.material_remaining = material_remaining;
    }

    public SimpleStringProperty getMaterial_color() {
        return material_color;
    }

    public void setMaterial_color(SimpleStringProperty material_color) {
        this.material_color = material_color;
    }

    public SimpleStringProperty getMaterial_manufacturer() {
        return material_manufacturer;
    }

    public void setMaterial_manufacturer(SimpleStringProperty material_manufacturer) {
        this.material_manufacturer = material_manufacturer;
    }

    public SimpleStringProperty getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(SimpleStringProperty material_type) {
        this.material_type = material_type;
    }

    public SimpleStringProperty getMaterial_finished() {
        return material_finished;
    }

    public void setMaterial_finished(SimpleStringProperty material_finished) {
        this.material_finished = material_finished;
    }

    public SimpleStringProperty getMaterial_distributor() {
        return material_distributor;
    }

    public void setMaterial_distributor(SimpleStringProperty material_distributor) {
        this.material_distributor = material_distributor;
    }

    public SimpleStringProperty getMaterial_purchaseDate() {
        return material_purchaseDate;
    }

    public void setMaterial_purchaseDate(SimpleStringProperty material_purchaseDate) {
        this.material_purchaseDate = material_purchaseDate;
    }

    public SimpleStringProperty getMaterial_comment() {
        return material_comment;
    }

    public void setMaterial_comment(SimpleStringProperty material_comment) {
        this.material_comment = material_comment;
    }

    public SimpleIntegerProperty getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(SimpleIntegerProperty material_id) {
        this.material_id = material_id;
    }

    public SimpleDoubleProperty getMaterial_weight() {
        return material_weight;
    }

    public void setMaterial_weight(SimpleDoubleProperty material_weight) {
        this.material_weight = material_weight;
    }

    public SimpleIntegerProperty getMaterial_id_manufacturer() {
        return material_id_manufacturer;
    }

    public void setMaterial_id_manufacturer(SimpleIntegerProperty material_id_manufacturer) {
        this.material_id_manufacturer = material_id_manufacturer;
    }

    public SimpleIntegerProperty getMaterial_id_materialType() {
        return material_id_materialType;
    }

    public void setMaterial_id_materialType(SimpleIntegerProperty material_id_materialType) {
        this.material_id_materialType = material_id_materialType;
    }

    public SimpleIntegerProperty getMaterial_id_color() {
        return material_id_color;
    }

    public void setMaterial_id_color(SimpleIntegerProperty material_id_color) {
        this.material_id_color = material_id_color;
    }

    public SimpleIntegerProperty getMaterial_id_weight() {
        return material_id_weight;
    }

    public void setMaterial_id_weight(SimpleIntegerProperty material_id_weight) {
        this.material_id_weight = material_id_weight;
    }

    public SimpleIntegerProperty getMaterial_id_seller() {
        return material_id_seller;
    }

    public void setMaterial_id_seller(SimpleIntegerProperty material_id_seller) {
        this.material_id_seller = material_id_seller;
    }

    public SimpleIntegerProperty getMaterial_id_diameter() {
        return material_id_diameter;
    }

    public void setMaterial_id_diameter(SimpleIntegerProperty material_id_diameter) {
        this.material_id_diameter = material_id_diameter;
    }

    public SimpleDoubleProperty getMaterial_diameter() {
        return material_diameter;
    }

    public void setMaterial_diameter(SimpleDoubleProperty material_diameter) {
        this.material_diameter = material_diameter;
    }

    public SimpleDoubleProperty getMaterial_price() {
        return material_price;
    }

    public void setMaterial_price(SimpleDoubleProperty material_price) {
        this.material_price = material_price;
    }

    public SimpleDoubleProperty getMaterial_shipping() {
        return material_shipping;
    }

    public void setMaterial_shipping(SimpleDoubleProperty material_shipping) {
        this.material_shipping = material_shipping;
    }

    public SimpleDoubleProperty getMaterial_used() {
        return material_used;
    }

    public void setMaterial_used(SimpleDoubleProperty material_used) {
        this.material_used = material_used;
    }

    public SimpleDoubleProperty getMaterial_trash() {
        return material_trash;
    }

    public void setMaterial_trash(SimpleDoubleProperty material_trash) {
        this.material_trash = material_trash;
    }

    public SimpleDoubleProperty getMaterial_soldFor() {
        return material_soldFor;
    }

    public void setMaterial_soldFor(SimpleDoubleProperty material_soldFor) {
        this.material_soldFor = material_soldFor;
    }

    public SimpleDoubleProperty getMaterial_profit() {
        return material_profit;
    }

    public void setMaterial_profit(SimpleDoubleProperty material_profit) {
        this.material_profit = material_profit;
    }

    public SimpleDoubleProperty getMaterial_remaining() {
        return material_remaining;
    }

    public void setMaterial_remaining(SimpleDoubleProperty material_remaining) {
        this.material_remaining = material_remaining;
    }

    
    //this method get list of materials - ONLY materials, without statistics
    public static List<Material> getMaterials(User user) {
        
        //Create list
        List<Material> allMaterialsList = new ArrayList<>();
        
        //Create query
        String query = "SELECT Materials.Comment, Materials.MaterialID, MaterialManufacturers.ManufacturerID, MaterialManufacturers.ManufacturerName AS 'Manufacturer', MaterialTypes.MaterialTypeID, MaterialTypes.MaterialType, MaterialColors.ColorID, MaterialColors.ColorName AS 'Color', MaterialWeights.WeightID, MaterialWeights.WeightValue, Materials.MaterialPrice, Materials.MaterialShipping, Materials.PurchaseDate, MaterialSellers.SellerID, MaterialSellers.SellerName AS 'Seller', Materials.Finished, Materials.Trash, MaterialDiameters.DiameterID, MaterialDiameters.DiameterValue FROM Materials JOIN MaterialTypes ON Materials.MaterialTypeID=MaterialTypes.MaterialTypeID JOIN MaterialManufacturers ON Materials.ManufacturerID = MaterialManufacturers.ManufacturerID JOIN MaterialSellers ON Materials.SellerID = MaterialSellers.SellerID JOIN MaterialColors ON Materials.ColorID = MaterialColors.ColorID JOIN MaterialWeights ON Materials.WeightID = MaterialWeights.WeightID JOIN MaterialDiameters ON Materials.DiameterID = MaterialDiameters.DiameterID ORDER BY Materials.MaterialID DESC";

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
                
                SimpleStringProperty material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment;
                SimpleIntegerProperty material_id, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
                SimpleDoubleProperty material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_weight, material_remaining;
                
                material_color = new SimpleStringProperty(rs.getString("Color"));
                material_manufacturer = new SimpleStringProperty(rs.getString("Manufacturer"));
                material_type = new SimpleStringProperty(rs.getString("MaterialType"));
                material_finished = new SimpleStringProperty(rs.getString("Finished"));
                material_distributor = new SimpleStringProperty(rs.getString("Seller"));
                material_purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));
                material_comment = new SimpleStringProperty(rs.getString("Comment"));                
                
                material_id = new SimpleIntegerProperty(rs.getInt("MaterialID"));
                material_id_manufacturer = new SimpleIntegerProperty(rs.getInt("ManufacturerID"));
                material_id_materialType = new SimpleIntegerProperty(rs.getInt("MaterialTypeID"));
                material_id_color = new SimpleIntegerProperty(rs.getInt("ColorID"));
                material_id_weight = new SimpleIntegerProperty(rs.getInt("WeightID"));
                material_id_seller = new SimpleIntegerProperty(rs.getInt("SellerID"));
                material_id_diameter = new SimpleIntegerProperty(rs.getInt("DiameterID"));
                
                material_diameter = new SimpleDoubleProperty(rs.getDouble("DiameterValue"));
                material_weight = new SimpleDoubleProperty(rs.getInt("WeightValue"));
                material_price = new SimpleDoubleProperty(rs.getDouble("MaterialPrice"));
                material_shipping = new SimpleDoubleProperty(rs.getDouble("MaterialShipping"));
                
                double material_used_absolute = getMaterialUsed(user, material_id);
                material_used = new SimpleDoubleProperty(MngApi.round(getMaterialUsed(user, material_id)/material_weight.get()*100, 2));
                material_trash = new SimpleDoubleProperty(rs.getDouble("Trash"));                    
                material_soldFor = new SimpleDoubleProperty(getMaterialSoldFor(user, material_id));
                double price_with_shipping = material_price.get() + material_shipping.get();
                material_profit = new SimpleDoubleProperty(MngApi.round(material_soldFor.get(), 2));
                material_remaining = new SimpleDoubleProperty(MngApi.round(material_weight.get() - material_used_absolute, 2));
                
                Material material = new Material(material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment, material_id, material_weight, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_remaining);
                
                allMaterialsList.add(material);
                
            }

            rs.close();
        }catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
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
        
    return allMaterialsList;
    }
    
    public static List<Material> getNotSpentMaterials(User user) {
        
        //Create list
        List<Material> allMaterialsList = new ArrayList<>();
        
        //Create query
        String query = "SELECT Materials.MaterialShipping, Materials.MaterialID, MaterialManufacturers.ManufacturerID, MaterialManufacturers.ManufacturerName AS 'Manufacturer', MaterialTypes.MaterialTypeID, MaterialTypes.MaterialType, MaterialColors.ColorID, MaterialColors.ColorName AS 'Color', MaterialWeights.WeightID, MaterialWeights.WeightValue, Materials.MaterialPrice, Materials.PurchaseDate, MaterialSellers.SellerID, MaterialSellers.SellerName AS 'Seller', MaterialDiameters.DiameterID, MaterialDiameters.DiameterValue FROM Materials JOIN MaterialTypes ON Materials.MaterialTypeID=MaterialTypes.MaterialTypeID JOIN MaterialManufacturers ON Materials.ManufacturerID = MaterialManufacturers.ManufacturerID JOIN MaterialSellers ON Materials.SellerID = MaterialSellers.SellerID JOIN MaterialColors ON Materials.ColorID = MaterialColors.ColorID JOIN MaterialWeights ON Materials.WeightID = MaterialWeights.WeightID JOIN MaterialDiameters ON Materials.DiameterID = MaterialDiameters.DiameterID WHERE Materials.Finished='No' ORDER BY Materials.MaterialID DESC";

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
            
            if(conn.isValid(10) == false) {
                MngApi obj2 = new MngApi();
                obj2.alertConnectionLost();
            }
            
            //STEP 4: Execute a query
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(query);            
            //Query is executed, resultSet saved. Now we need to process the data
            //rs.next() loads row            
            //in this loop we sequentialy add columns to list of Strings
            while(rs.next()){
                
                SimpleStringProperty material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment;
                SimpleIntegerProperty material_id, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
                SimpleDoubleProperty material_weight, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_remaining;
                
                material_color = new SimpleStringProperty(rs.getString("Color"));
                material_manufacturer = new SimpleStringProperty(rs.getString("Manufacturer"));
                material_type = new SimpleStringProperty(rs.getString("MaterialType"));
                material_finished = new SimpleStringProperty(" ");
                material_distributor = new SimpleStringProperty(rs.getString("Seller"));
                material_purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));
                material_comment = new SimpleStringProperty(" ");                
                
                material_id = new SimpleIntegerProperty(rs.getInt("MaterialID"));                
                material_id_manufacturer = new SimpleIntegerProperty(rs.getInt("ManufacturerID"));
                material_id_materialType = new SimpleIntegerProperty(rs.getInt("MaterialTypeID"));
                material_id_color = new SimpleIntegerProperty(rs.getInt("ColorID"));
                material_id_weight = new SimpleIntegerProperty(rs.getInt("WeightID"));
                material_id_seller = new SimpleIntegerProperty(rs.getInt("SellerID"));
                material_id_diameter = new SimpleIntegerProperty(rs.getInt("DiameterID"));
                
                material_weight = new SimpleDoubleProperty(rs.getInt("WeightValue"));
                material_diameter = new SimpleDoubleProperty(rs.getDouble("DiameterValue"));
                material_price = new SimpleDoubleProperty(rs.getDouble("MaterialPrice"));
                material_shipping = new SimpleDoubleProperty(rs.getDouble("MaterialShipping"));
                
                double material_used_absolute = getMaterialUsed(user, material_id);
                material_used = new SimpleDoubleProperty(MngApi.round(getMaterialUsed(user, material_id)/material_weight.get()*100, 2));
                material_trash = new SimpleDoubleProperty(0);                    
                material_soldFor = new SimpleDoubleProperty(0);
                material_profit = new SimpleDoubleProperty(0);
                material_remaining = new SimpleDoubleProperty(MngApi.round(material_weight.get() - material_used_absolute, 2));
                
                Material material = new Material(material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment, material_id, material_weight, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_remaining);
                                
                allMaterialsList.add(material);                
            }

            rs.close();
        }catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
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
        
    return allMaterialsList;
    }
    
    public static double getMaterialUsed(User user, SimpleIntegerProperty materialID) {        
                
        double used = 0;        
        
        String query = "SELECT SUM(ItemWeight)+SUM(ItemSupportWeight) AS ItemWeight FROM OrderItems WHERE ItemMaterialID=" + materialID.get();
        
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
                
                used = rs.getDouble("ItemWeight");
                        
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
        
    return used;
    }
    
    public static Double getMaterialSoldFor(User user, SimpleIntegerProperty materialID) {        
                
        double soldFor = 0;        
        
        String query = "SELECT SUM(ItemPrice) AS 'SoldFor' FROM OrderItems WHERE ItemMaterialID=" + materialID.get();
        
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
                                
                soldFor = rs.getDouble("SoldFor");
                        
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
        
    return soldFor;
    }
        
    public static List<SimpleTableObject> getMaterialTypes(User user) {
        
        //Create list
        List<SimpleTableObject> materialTypes = new ArrayList<>();
        
        //Create query
        String query = "SELECT MaterialTypeID, MaterialType FROM MaterialTypes ORDER BY MaterialType ASC";

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
                
                SimpleIntegerProperty id = new SimpleIntegerProperty(rs.getInt("MaterialTypeID"));
                SimpleStringProperty name = new SimpleStringProperty(rs.getString("MaterialType"));
                
                SimpleTableObject sto = new SimpleTableObject(id, name);
                
                materialTypes.add(sto);
                
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
        
    return materialTypes;
    }
    
    public static List<SimpleTableObject> getMaterialManufacturers(User user) {
        
        //Create list
        List<SimpleTableObject> materialManufacturers = new ArrayList<>();
        
        //Create query
        String query = "SELECT ManufacturerID, ManufacturerName FROM MaterialManufacturers ORDER BY ManufacturerName ASC";

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
                
                
                SimpleIntegerProperty id = new SimpleIntegerProperty(rs.getInt("ManufacturerID"));
                SimpleStringProperty name = new SimpleStringProperty(rs.getString("ManufacturerName"));
                
                SimpleTableObject sto = new SimpleTableObject(id, name);
                
                materialManufacturers.add(sto);
                
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
        
    return materialManufacturers;
    }
    
    public static List<SimpleTableObject> getMaterialSellers(User user) {
        
        //Create list
        List<SimpleTableObject> materialSellers = new ArrayList<>();
        
        //Create query
        String query = "SELECT SellerID, SellerName FROM MaterialSellers ORDER BY SellerName ASC";

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
                
                SimpleIntegerProperty id = new SimpleIntegerProperty(rs.getInt("SellerID"));
                SimpleStringProperty name = new SimpleStringProperty(rs.getString("SellerName"));
                
                SimpleTableObject sto = new SimpleTableObject(id, name);
                
                materialSellers.add(sto);
                
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
        
    return materialSellers;
    }
    
    public static List<SimpleTableObject> getMaterialColors(User user) {
        
        //Create list
        List<SimpleTableObject> materialColors = new ArrayList<>();
        
        //Create query
        String query = "SELECT ColorID, ColorName FROM MaterialColors ORDER BY ColorName ASC";

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
                
                SimpleIntegerProperty id = new SimpleIntegerProperty(rs.getInt("ColorID"));
                SimpleStringProperty name = new SimpleStringProperty(rs.getString("ColorName"));
                
                SimpleTableObject sto = new SimpleTableObject(id, name);
                materialColors.add(sto);
                
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
        
    return materialColors;
    }
    
     public static Material getMaterialByID(User user, SimpleIntegerProperty material_id) {
        
        Material material = null;
        
        //Create query
        String query = "SELECT Materials.Comment, Materials.MaterialID, MaterialManufacturers.ManufacturerID, MaterialManufacturers.ManufacturerName AS 'Manufacturer', MaterialTypes.MaterialTypeID, MaterialTypes.MaterialType, MaterialColors.ColorID, MaterialColors.ColorName AS 'Color', MaterialWeights.WeightID, MaterialWeights.WeightValue, Materials.MaterialPrice, Materials.MaterialShipping, Materials.PurchaseDate, MaterialSellers.SellerID, MaterialSellers.SellerName AS 'Seller', Materials.Finished, Materials.Trash, MaterialDiameters.DiameterID, MaterialDiameters.DiameterValue FROM Materials JOIN MaterialTypes ON Materials.MaterialTypeID=MaterialTypes.MaterialTypeID JOIN MaterialManufacturers ON Materials.ManufacturerID = MaterialManufacturers.ManufacturerID JOIN MaterialSellers ON Materials.SellerID = MaterialSellers.SellerID JOIN MaterialColors ON Materials.ColorID = MaterialColors.ColorID JOIN MaterialWeights ON Materials.WeightID = MaterialWeights.WeightID JOIN MaterialDiameters ON Materials.DiameterID = MaterialDiameters.DiameterID WHERE MaterialID="+ material_id.get() + " ORDER BY Materials.MaterialID DESC";

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
                
                SimpleStringProperty material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment;
                SimpleIntegerProperty material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
                SimpleDoubleProperty material_weight, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_remaining;
                
                material_color = new SimpleStringProperty(rs.getString("Color"));
                material_manufacturer = new SimpleStringProperty(rs.getString("Manufacturer"));
                material_type = new SimpleStringProperty(rs.getString("MaterialType"));
                material_finished = new SimpleStringProperty(rs.getString("Finished"));
                material_distributor = new SimpleStringProperty(rs.getString("Seller"));
                material_purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));
                material_comment = new SimpleStringProperty(rs.getString("Comment"));                
                                
                
                material_id_manufacturer = new SimpleIntegerProperty(rs.getInt("ManufacturerID"));
                material_id_materialType = new SimpleIntegerProperty(rs.getInt("MaterialTypeID"));
                material_id_color = new SimpleIntegerProperty(rs.getInt("ColorID"));
                material_id_weight = new SimpleIntegerProperty(rs.getInt("WeightID"));
                material_id_seller = new SimpleIntegerProperty(rs.getInt("SellerID"));
                material_id_diameter = new SimpleIntegerProperty(rs.getInt("DiameterID"));
                
                material_weight = new SimpleDoubleProperty(rs.getDouble("WeightValue"));
                material_diameter = new SimpleDoubleProperty(rs.getDouble("DiameterValue"));
                material_price = new SimpleDoubleProperty(rs.getDouble("MaterialPrice"));                
                material_shipping = new SimpleDoubleProperty(rs.getDouble("MaterialShipping"));
                
                double material_used_absolute = getMaterialUsed(user, material_id);
                material_used = new SimpleDoubleProperty(MngApi.round(getMaterialUsed(user, material_id)/material_weight.get()*100, 2));
                material_trash = new SimpleDoubleProperty(rs.getDouble("Trash"));                    
                material_soldFor = new SimpleDoubleProperty(getMaterialSoldFor(user, material_id));
                material_profit = new SimpleDoubleProperty(MngApi.round(material_soldFor.get() - material_price.get(), 2));
                material_remaining = new SimpleDoubleProperty(MngApi.round(material_weight.get() - material_used_absolute, 2));
                
                material = new Material(material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment, material_id, material_weight, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_remaining);                        
                
            }

            rs.close();
        }catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
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
        
    return material;
    }
    
    public static List<SimpleTableObject> getMaterialWeights(User user) {
        
        //Create list
        List<SimpleTableObject> materialWeights = new ArrayList<>();
        
        //Create query
        String query = "SELECT WeightID, WeightValue FROM MaterialWeights ORDER BY WeightValue ASC";

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
                
                
                SimpleIntegerProperty id = new SimpleIntegerProperty(rs.getInt("WeightID"));
                SimpleStringProperty name = new SimpleStringProperty(rs.getString("WeightValue"));
                
                SimpleTableObject sto = new SimpleTableObject(id, name); 
                
                materialWeights.add(sto);
                
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
        
    return materialWeights;
    }
    
    public static List<SimpleTableObject> getMaterialDiameters(User user) {
        
        //Create list
        List<SimpleTableObject> materialDiameters = new ArrayList<>();
        
        //Create query
        String query = "SELECT DiameterID, DiameterValue FROM MaterialDiameters ORDER BY DiameterValue ASC";

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
                
                
                
                SimpleIntegerProperty id = new SimpleIntegerProperty(rs.getInt("DiameterID"));
                SimpleStringProperty name = new SimpleStringProperty(rs.getString("DiameterValue"));
                
                SimpleTableObject sto = new SimpleTableObject(id, name); 
                
                materialDiameters.add(sto);
                
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
        
    return materialDiameters;
    }
    
     public static void insertNewMaterial(Material material, User user){
        
        //Create query
        String updateQuery;

        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String DB_URL = "jdbc:mariadb://" + user.getAddress() + "/" + user.getDbName();

        //  Database credentials
        String USER = user.getName();
        String PASS = user.getPass();


        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);            
            //STEP 4: Execute a query
	    
                updateQuery = "INSERT INTO Materials (MaterialID,ManufacturerID,MaterialTypeID,ColorID,WeightID,MaterialPrice,MaterialShipping,PurchaseDate,SellerID,Finished,Trash,DiameterID,Comment) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE MaterialID=?,ManufacturerID=?,MaterialTypeID=?,ColorID=?,WeightID=?,MaterialPrice=?,MaterialShipping=?,PurchaseDate=?,SellerID=?,Finished=?,Trash=?,DiameterID=?,Comment=?";                
                stmt = conn.prepareStatement(updateQuery);
                
                int i = 1;
                
                stmt.setInt(i, material.getMaterial_id().get());i++;
                stmt.setInt(i, material.getMaterial_id_manufacturer().get());i++;
                stmt.setInt(i, material.getMaterial_id_materialType().get());i++;
                stmt.setInt(i, material.getMaterial_id_color().get());i++;
                stmt.setInt(i, material.getMaterial_id_weight().get());i++;
                stmt.setDouble(i, material.getMaterial_price().get());i++;
                stmt.setDouble(i, material.getMaterial_shipping().get());i++;
                stmt.setString(i, material.getMaterial_purchaseDate().get());i++;
                stmt.setInt(i, material.getMaterial_id_seller().get());i++;
                stmt.setString(i, material.getMaterial_finished().get());i++;
                stmt.setDouble(i, material.getMaterial_trash().get());i++;
                stmt.setInt(i, material.getMaterial_id_diameter().get());i++;
                stmt.setString(i, material.getMaterial_comment().get());i++;
                
                stmt.setInt(i, material.getMaterial_id().get());i++;
                stmt.setInt(i, material.getMaterial_id_manufacturer().get());i++;
                stmt.setInt(i, material.getMaterial_id_materialType().get());i++;
                stmt.setInt(i, material.getMaterial_id_color().get());i++;
                stmt.setInt(i, material.getMaterial_id_weight().get());i++;
                stmt.setDouble(i, material.getMaterial_price().get());i++;
                stmt.setDouble(i, material.getMaterial_shipping().get());i++;
                stmt.setString(i, material.getMaterial_purchaseDate().get());i++;
                stmt.setInt(i, material.getMaterial_id_seller().get());i++;
                stmt.setString(i, material.getMaterial_finished().get());i++;
                stmt.setDouble(i, material.getMaterial_trash().get());i++;
                stmt.setInt(i, material.getMaterial_id_diameter().get());i++;
                stmt.setString(i, material.getMaterial_comment().get());i++;
                
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
     
     public static void deleteMaterials(ObservableList<Material> materials, Label info, User user){
         
         for (int i = 0; i < materials.size(); i++) {
            
            int id = materials.get(i).getMaterial_id().get();
            String query = "DELETE FROM Materials WHERE MaterialID=" + id;
            MngApi.performUpdateQuary(query, info, user);
            
        }
         
     }
}

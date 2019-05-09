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
    
    //table columns
    SimpleIntegerProperty material_id, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
    SimpleDoubleProperty material_price, material_shipping;    
    SimpleStringProperty material_comment, material_finished, material_purchaseDate;
            
    //additional columns
    SimpleStringProperty material_color, material_manufacturer, material_type, material_distributor;    
    SimpleDoubleProperty material_diameter, material_used, material_trash, material_soldFor, material_profit, material_remaining, material_weight;
    
    //simple constructor for database table

    public Material(SimpleIntegerProperty material_id, SimpleIntegerProperty material_id_manufacturer, SimpleIntegerProperty material_id_materialType, SimpleIntegerProperty material_id_color, SimpleIntegerProperty material_id_weight, SimpleIntegerProperty material_id_seller, SimpleIntegerProperty material_id_diameter, SimpleDoubleProperty material_price, SimpleDoubleProperty material_shipping, SimpleStringProperty material_comment, SimpleStringProperty material_finished, SimpleStringProperty material_purchaseDate) {
        this.material_id = material_id;
        this.material_id_manufacturer = material_id_manufacturer;
        this.material_id_materialType = material_id_materialType;
        this.material_id_color = material_id_color;
        this.material_id_weight = material_id_weight;
        this.material_id_seller = material_id_seller;
        this.material_id_diameter = material_id_diameter;
        this.material_price = material_price;
        this.material_shipping = material_shipping;
        this.material_comment = material_comment;
        this.material_finished = material_finished;
        this.material_purchaseDate = material_purchaseDate;
    }
        
    //complex constructor for table view
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
    
    public static List<Material> downloadMaterialsTable(HikariDataSource ds) {
        
        //Create list
        List<Material> materials = new ArrayList<>();
        
        //Create query        
        String query = "SELECT * FROM Materials";

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
                
                //table columns
                SimpleIntegerProperty material_id = new SimpleIntegerProperty(rs.getInt("MaterialID"));
                SimpleIntegerProperty material_id_manufacturer = new SimpleIntegerProperty(rs.getInt("ManufacturerID"));
                SimpleIntegerProperty material_id_materialType = new SimpleIntegerProperty(rs.getInt("MaterialTypeID"));
                SimpleIntegerProperty material_id_color = new SimpleIntegerProperty(rs.getInt("ColorID"));
                SimpleIntegerProperty material_id_weight = new SimpleIntegerProperty(rs.getInt("WeightID"));
                SimpleIntegerProperty material_id_diameter = new SimpleIntegerProperty(rs.getInt("DiameterID"));
                SimpleIntegerProperty material_id_seller = new SimpleIntegerProperty(rs.getInt("SellerID"));
                    
                SimpleDoubleProperty material_price = new SimpleDoubleProperty(rs.getDouble("MaterialPrice"));
                SimpleDoubleProperty material_shipping = new SimpleDoubleProperty(rs.getDouble("MaterialShipping"));
                

                SimpleStringProperty material_purchaseDate = new SimpleStringProperty(rs.getString("PurchaseDate"));                
                SimpleStringProperty material_finished = new SimpleStringProperty(rs.getString("Finished"));
                SimpleStringProperty material_comment = new SimpleStringProperty(rs.getString("Comment"));

                Material material = new Material(material_id, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter, material_price, material_shipping, material_comment, material_finished, material_purchaseDate);
                
                materials.add(material);
                
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
        
        return materials;
    }
    
    //this method get list of materials - ONLY materials, without statistics
    public static List<Material> getMaterials(List<SimpleTableObject> commonMaterialProperties, ObservableList<Material> materialsTable, ObservableList<OrderItem> allOrderItems) {
        
        //Create list
        List<Material> allMaterialsList = new ArrayList<>();
                
        for (int i = 0; i < materialsTable.size(); i++) {
                        
            Material currentMaterial = materialsTable.get(i);
            ObservableList<OrderItem> orderItems = OrderItem.getOrderItemsByMaterialId(currentMaterial.getMaterial_id(), allOrderItems);
            
//            SimpleStringProperty material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment;
//            SimpleIntegerProperty material_id, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
//            SimpleDoubleProperty material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_weight, material_remaining;
            
            //additional columns
            SimpleStringProperty material_color, material_manufacturer, material_type, material_distributor;    
            SimpleDoubleProperty material_diameter, material_used, material_trash, material_soldFor, material_profit, material_remaining, material_weight;

            SimpleTableObject weight, diameter;
            
            material_color = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_color().get()).getProperty_name();
            material_manufacturer = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_manufacturer().get()).getProperty_name();
            material_type = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_materialType().get()).getProperty_name();
            material_distributor = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_seller().get()).getProperty_name();
            
            diameter = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_diameter().get());
            weight = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_weight().get());
            
            material_diameter = new SimpleDoubleProperty(Double.parseDouble(diameter.getProperty_name().get()));
            material_weight =  new SimpleDoubleProperty(Double.parseDouble(weight.getProperty_name().get()));
            
            double material_used_absolute = 0, soldFor = 0;
            
            for (int j = 0; j < orderItems.size(); j++) {
                
                OrderItem item = orderItems.get(j);
                
                material_used_absolute += item.getObject_supportWeight().get() + item.getObject_weight().get();
                soldFor += item.getPrice().get();
                
            }
            material_used = new SimpleDoubleProperty(MngApi.round(material_used_absolute/material_weight.get()*100, 2));
            material_trash = new SimpleDoubleProperty(material_weight.get() - material_used_absolute);                    
            material_soldFor = new SimpleDoubleProperty(soldFor);
            material_profit = new SimpleDoubleProperty(MngApi.round(material_soldFor.get() - currentMaterial.getMaterial_price().get(), 2));
            material_remaining = new SimpleDoubleProperty(MngApi.round(material_weight.get() - material_used_absolute, 2));
            
            currentMaterial.setMaterial_used(material_used);
            currentMaterial.setMaterial_trash(material_trash);
            currentMaterial.setMaterial_soldFor(material_soldFor);
            currentMaterial.setMaterial_profit(material_profit);
            currentMaterial.setMaterial_remaining(material_remaining);
            
            allMaterialsList.add(currentMaterial);
                
        }
        
    return allMaterialsList;
    }
    
    public static List<Material> getNotSpentMaterials(List<SimpleTableObject> commonMaterialProperties, ObservableList<Material>materialsTable, ObservableList<OrderItem> allOrderItems) {
          
        //Create list
        List<Material> allMaterialsList = new ArrayList<>();
                
        for (int i = 0; i < materialsTable.size(); i++) {
                        
            Material currentMaterial = materialsTable.get(i);
            
            if(currentMaterial.getMaterial_finished().get().equals("Yes"))continue;
            
            ObservableList<OrderItem> orderItems = OrderItem.getOrderItemsByMaterialId(currentMaterial.getMaterial_id(), allOrderItems);
            
            SimpleStringProperty material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment;
            SimpleIntegerProperty material_id, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
            SimpleDoubleProperty material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_weight, material_remaining;
                
            SimpleTableObject manufacturer, materialType,color,weight,distributor,diameter;
                
            manufacturer = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_manufacturer().get());
            materialType = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_materialType().get());
            color = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_color().get());
            weight = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_weight().get());
            distributor = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_seller().get());
            diameter = getCommonMaterialPropertiesByID(commonMaterialProperties, currentMaterial.getMaterial_id_diameter().get());
                
                
            material_color = color.getProperty_name();
            material_manufacturer = manufacturer.getProperty_name();
            material_type = materialType.getProperty_name();
            material_finished = currentMaterial.getMaterial_finished();
            material_distributor = distributor.getProperty_name();
            material_purchaseDate = currentMaterial.getMaterial_purchaseDate();
            material_comment = currentMaterial.getMaterial_comment();
                
            material_id = currentMaterial.getMaterial_id();
            material_id_manufacturer = manufacturer.getProperty_id();
            material_id_materialType = materialType.getProperty_id();
            material_id_color = color.getProperty_id();
            material_id_weight = weight.getProperty_id();
            material_id_seller = distributor.getProperty_id();
            material_id_diameter = diameter.getProperty_id();
                
            material_diameter = new SimpleDoubleProperty(Double.parseDouble(diameter.getProperty_name().get()));
            material_weight =  new SimpleDoubleProperty(Double.parseDouble(weight.getProperty_name().get()));
            material_price = currentMaterial.getMaterial_price();
            material_shipping = currentMaterial.getMaterial_shipping();
            
            double material_used_absolute = 0, soldFor = 0;
            
            for (int j = 0; j < orderItems.size(); j++) {
                
                OrderItem item = orderItems.get(j);
                
                material_used_absolute += item.getObject_supportWeight().get() + item.getObject_weight().get();
                soldFor += item.getPrice().get();
                
            }
            material_used = new SimpleDoubleProperty(MngApi.round(material_used_absolute/material_weight.get()*100, 2));
            material_trash = new SimpleDoubleProperty(material_weight.get() - material_used_absolute);                    
            material_soldFor = new SimpleDoubleProperty(soldFor);
            material_profit = new SimpleDoubleProperty(MngApi.round(material_soldFor.get() - material_price.get(), 2));
            material_remaining = new SimpleDoubleProperty(MngApi.round(material_weight.get() - material_used_absolute, 2));
            
            Material material = new Material(material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment, material_id, material_weight, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit, material_remaining);
                
            allMaterialsList.add(material);
                
        }
        
        return allMaterialsList;
    }
    
    
    private static SimpleTableObject getCommonMaterialPropertiesByID(List<SimpleTableObject> commonMaterialProperties, int id){
        return commonMaterialProperties.get(id-1);
    }
    
    public static List<SimpleTableObject> getCommonMaterialProperties(HikariDataSource ds) {
        
        //Create list
        List<SimpleTableObject> properties = new ArrayList<>();
        
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
    
    public static Material getMaterialByID(ObservableList<Material> materials, int material_id){
        Material material = null;
        
        for (int i = 0; i < materials.size(); i++) {
            
            material = materials.get(i);
            
            if(material.getMaterial_id().get() == material_id)break;
            
        }
        
        return material;
    }
    
    public static Material getMaterialByID(SimpleIntegerProperty material_id, ObservableList<Material> allMaterials) {
        
        Material material = null;
        
        //binary search: we are searching first orderItem with material_id and then we will find beginning of series (there could be multiple orderItems with same material_id
        int numberToGuess = material_id.get();
        int start = 1;
        int end = allMaterials.size();
        int position = 0;
        
        while(start <= end){
            position = (start + end)/2;
            
            material = allMaterials.get(position);
            
            if (material.getMaterial_id() == material_id)break;
            
            if(numberToGuess < material.getMaterial_id().get()){
                
                end = position - 1;                
                
            } else {
                
                start = position + 1;
                
            }            
        }
        
        return material;
    }
    
    public static void insertNewMaterial(Material material, HikariDataSource ds){
        
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
	    
                updateQuery = "INSERT INTO Materials (MaterialID,ManufacturerID,MaterialTypeID,ColorID,WeightID,MaterialPrice,MaterialShipping,PurchaseDate,SellerID,Finished,DiameterID,Comment) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) "
                        + "ON DUPLICATE KEY UPDATE MaterialID=?,ManufacturerID=?,MaterialTypeID=?,ColorID=?,WeightID=?,MaterialPrice=?,MaterialShipping=?,PurchaseDate=?,SellerID=?,Finished=?,DiameterID=?,Comment=?";                
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
     
     public static void deleteMaterials(ObservableList<Material> materials, Label info, HikariDataSource ds){
         
         for (int i = 0; i < materials.size(); i++) {
            
            int id = materials.get(i).getMaterial_id().get();
            String query = "DELETE FROM Materials WHERE MaterialID=" + id;
            MngApi.performUpdateQuery(query, info, ds);
            
        }
         
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

}

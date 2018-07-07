/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.select;


import classes.Material;
import classes.MngApi;
import classes.OrderItem;

import classes.User;
import controllers.MainController;
import controllers.orders.NewOrderController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class SelectPrinterMaterialPriceController implements Initializable {
    
    private User user;
    
    private MainController mainController;
    
    private NewOrderController newOrderController;
    
    private OrderItem selectedObject;
    
    private Material material;
    
    
    @FXML
    private ComboBox<String> comboBox_printer;
    
    @FXML
    private TextField txtField_material, txtField_price, txtField_quantity, txtField_costs;
    
    @FXML
    private Button btn_selectMaterial, btn_assign, btn_cancel;
    
    @FXML
    private Label label_editedObject, label_info;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtField_quantity.textProperty().addListener((observable, oldValue, newValue) -> {
            
           setCosts();
           
        });
        
        txtField_material.textProperty().addListener((observable, oldValue, newValue) -> {
            
           setCosts();
           
        });
        
        btn_selectMaterial.setOnAction((event) -> {
            
            try {            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/select/SelectMaterial.fxml"));            
                Parent root1 = fxmlLoader.load();
                SelectMaterialController ctrl = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Select Material");
           
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.centerOnScreen();            
            
                stage.show();
                //stage.setAlwaysOnTop(true);            
                ctrl.setUser(user);
                ctrl.setSelectPrinterMaterialPriceController(this);
            
                ctrl.displayMaterials();
            }catch (IOException e){
            
            }
            
        });
        
        btn_assign.setOnAction((event) -> {
            
            boolean isEmpty = MngApi.isTextFieldEmpty(txtField_price, txtField_quantity, txtField_material);
            
            if (isEmpty == true){
                label_info.setText("Fields cannot be empty.");
                label_info.setTextFill(Color.web("#ff0000"));
                
            } else {
                try {
                
                int quantity = Integer.parseInt(txtField_quantity.getText());
            
                String[] printer = comboBox_printer.getValue().split(";");
                    String printer_id = printer[0];
                        int printerID = Integer.parseInt(printer_id);
                    String printer_name = printer[1];
            
                String[] material = txtField_material.getText().split(";");
                    String material_id = material[0];
                        int materialID = Integer.parseInt(material_id);                
                    String material_type = material[1] + " " + material[2];
                    String material_color = material[3];
                
                double price = Double.parseDouble(txtField_price.getText());
                double costs = Double.parseDouble(txtField_costs.getText());
                
                selectedObject.setQunatity(new SimpleIntegerProperty(quantity));
                selectedObject.setPrinter_id(new SimpleIntegerProperty(printerID));
                selectedObject.setPrinter_name(new SimpleStringProperty(printer_name));
                selectedObject.setMaterial_id(new SimpleIntegerProperty(materialID));
                selectedObject.setMaterial_type(new SimpleStringProperty(material_type));
                selectedObject.setMaterial_color(new SimpleStringProperty(material_color));
                selectedObject.setPrice(new SimpleDoubleProperty(price));
                selectedObject.setCosts(new SimpleDoubleProperty(costs));
                
                newOrderController.setSelectedObjects();
                newOrderController.refreshSelectedObjects();
                MngApi.closeWindow(btn_assign);
            
            } catch (NumberFormatException e) {                
                label_info.setText("Wrong number format, please check your fields.");
                label_info.setTextFill(Color.web("#ff0000"));
                //e.printStackTrace();
            }
            }
            
        });
        
        btn_cancel.setOnAction((event) -> {
            MngApi.closeWindow(btn_cancel);
        });
    }    
    
    public void setElementValues(){
        
        label_editedObject.setText(selectedObject.getObject_id().get() + "; " + selectedObject.getObject_name().get());
        
        ObservableList<String> printers = FXCollections.observableArrayList(getPrinters(user));
        comboBox_printer.setItems(printers);
        comboBox_printer.setVisibleRowCount(7);
        comboBox_printer.setValue(printers.get(0));
    }
    
    public void setCosts(){
        
        if (MngApi.isTextFieldEmpty(txtField_quantity)){
                
            txtField_quantity.setText("1");
                
        } else {
                
            if(MngApi.isTextFieldEmpty(txtField_material)){
                //return;
            } else {                    
                
                double costs, price_per_gram, total_weight, material_price, material_shipping, material_weight;
                
                try{
                    int quantity = Integer.parseInt(txtField_quantity.getText());
        
                    material_price = material.getMaterial_price().get();
                    material_shipping = material.getMaterial_shipping().get();
                    material_weight = material.getMaterial_weight().get();
        
                    price_per_gram = (material_price+material_shipping)/material_weight;
        
                    total_weight = selectedObject.getObject_weight().get() + selectedObject.getObject_supportWeight().get();
                    
                    costs = price_per_gram*total_weight*quantity;
        
                    txtField_costs.setText(String.format(Locale.US, "%.2f", costs));
                } catch (NumberFormatException e){
                    label_info.setText("Wrong number format, please check your fields.");
                    label_info.setTextFill(Color.web("#ff0000"));
                    //e.printStackTrace();
                }
                    
            }                
        }
        
    }
    
    
    public TextField getTxtField_quantity() {
        return txtField_quantity;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setNewOrderController(NewOrderController newOrderController) {
        this.newOrderController = newOrderController;
    }

    public void setSelectedObject(OrderItem selectedObject) {
        this.selectedObject = selectedObject;
    }

    
    public void setMaterialTxtField(Material material){
        this.material = material;
        
        int id = this.material.getMaterial_id().get();
        String type = this.material.getMaterial_manufacturer().get() + " " + this.material.getMaterial_type().get();
        String manufacturer = this.material.getMaterial_manufacturer().get();
        String color = this.material.getMaterial_color().get();
        
        txtField_material.setText(id + ";" + type + ";" + manufacturer + ";" + color);
    }
    
    private static List<String> getPrinters(User user) {
        
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

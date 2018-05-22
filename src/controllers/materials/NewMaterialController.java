/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.materials;


import classes.Material;
import classes.MngApi;
import classes.SimpleTableObject;
import classes.User;
import controllers.MainController;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class NewMaterialController implements Initializable {

    private User user;
    
    private MainController mainController;
    
    @FXML
    private Label material_label_id, material_label_info;
    
    @FXML
    private DatePicker material_datePicker_purchaseDate;
    
    @FXML
    private Button material_btn_create, material_btn_cancel;
    
    @FXML
    private TextField material_txtField_price, material_txtField_shipping, material_txtField_comment;
    
    @FXML
    private ComboBox<SimpleTableObject> material_comboBox_diameter, material_comboBox_weight, material_comboBox_type, material_comboBox_color, material_comboBox_manufacturer, material_comboBox_distributor;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        material_btn_create.setOnAction((event) -> {
            boolean isEmpty = MngApi.isTextFieldEmpty(material_txtField_price, material_txtField_shipping, material_txtField_comment);
            
            if (isEmpty == true || MngApi.isComboBoxEmpty(material_comboBox_diameter, material_comboBox_weight, material_comboBox_type, material_comboBox_color, material_comboBox_manufacturer, material_comboBox_distributor)){
                material_label_info.setText("Fields cannot be empty.");
                material_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            SimpleStringProperty material_comment, material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate;
            SimpleIntegerProperty material_id, material_weight, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter;
            SimpleDoubleProperty material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit;
            
            try {
                
                material_color = new SimpleStringProperty(material_comboBox_color.getSelectionModel().getSelectedItem().getName().get());
                material_manufacturer = new SimpleStringProperty(material_comboBox_manufacturer.getSelectionModel().getSelectedItem().getName().get());
                material_type = new SimpleStringProperty(material_comboBox_type.getSelectionModel().getSelectedItem().getName().get());
                material_finished = new SimpleStringProperty("No");
                material_distributor = new SimpleStringProperty(material_comboBox_distributor.getSelectionModel().getSelectedItem().getName().get());
                material_purchaseDate = new SimpleStringProperty(material_datePicker_purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                material_comment = new SimpleStringProperty(material_txtField_comment.getText());
                
                material_id = new SimpleIntegerProperty(getMaterial_label_id_value());
                material_weight = new SimpleIntegerProperty(Integer.parseInt(material_comboBox_weight.getSelectionModel().getSelectedItem().getName().get()));
                material_id_manufacturer = new SimpleIntegerProperty(material_comboBox_manufacturer.getSelectionModel().getSelectedItem().getId().get());
                material_id_materialType = new SimpleIntegerProperty(material_comboBox_type.getSelectionModel().getSelectedItem().getId().get());
                material_id_color = new SimpleIntegerProperty(material_comboBox_color.getSelectionModel().getSelectedItem().getId().get());
                material_id_weight = new SimpleIntegerProperty(material_comboBox_weight.getSelectionModel().getSelectedItem().getId().get());
                material_id_seller = new SimpleIntegerProperty(material_comboBox_distributor.getSelectionModel().getSelectedItem().getId().get());
                material_id_diameter = new SimpleIntegerProperty(material_comboBox_diameter.getSelectionModel().getSelectedItem().getId().get());
                
                
                material_diameter = new SimpleDoubleProperty(Double.parseDouble(material_comboBox_weight.getSelectionModel().getSelectedItem().getName().get()));
                material_price = new SimpleDoubleProperty(Double.parseDouble(material_txtField_price.getText()));
                material_shipping = new SimpleDoubleProperty(Double.parseDouble(material_txtField_shipping.getText()));
                
                material_used = new SimpleDoubleProperty(0);
                material_trash = new SimpleDoubleProperty(0);                    
                material_soldFor = new SimpleDoubleProperty(0);
                material_profit = new SimpleDoubleProperty(0);
                
                
                Material newMaterial = new Material(material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_comment, material_id, material_weight, material_id_manufacturer, material_id_materialType, material_id_color, material_id_weight, material_id_seller, material_id_diameter, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit);
                
                Material.insertNewMaterial(newMaterial, user);
            
            MngApi.closeWindow(material_btn_create);            
            mainController.runService(mainController.getService_refreshMaterials());
            
            } catch (NumberFormatException e) {
                material_label_info.setText("Wrong number format, please check your fields.");
                material_label_info.setTextFill(Color.web("#ff0000"));
            }
            
            
            
        });
        
        material_btn_cancel.setOnAction((event) -> {            
            MngApi.closeWindow(material_btn_cancel);
        });
    }    
    
    public void setComboBoxes(){
        
        ObservableList<SimpleTableObject> types = FXCollections.observableArrayList(Material.getMaterialTypes(user));
        ObservableList<SimpleTableObject> colors = FXCollections.observableArrayList(Material.getMaterialColors(user));
        ObservableList<SimpleTableObject> manufacturers = FXCollections.observableArrayList(Material.getMaterialManufacturers(user));        
        ObservableList<SimpleTableObject> distributors = FXCollections.observableArrayList(Material.getMaterialSellers(user));        
        
        ObservableList<SimpleTableObject> diameters = FXCollections.observableArrayList(Material.getMaterialDiameters(user));
        
        ObservableList<SimpleTableObject> weights = FXCollections.observableArrayList(Material.getMaterialWeights(user));
        
        material_comboBox_type.setItems(types);
        material_comboBox_type.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
      
        
        
        material_comboBox_color.getItems().addAll(colors);
        material_comboBox_color.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        material_comboBox_diameter.getItems().addAll(diameters);
        material_comboBox_diameter.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        material_comboBox_weight.getItems().addAll(weights);
        material_comboBox_weight.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        material_comboBox_manufacturer.getItems().addAll(manufacturers);
        material_comboBox_manufacturer.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        material_comboBox_distributor.getItems().addAll(distributors);
        material_comboBox_distributor.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        material_comboBox_diameter.setVisibleRowCount(5);
        material_comboBox_weight.setVisibleRowCount(5);
        material_comboBox_type.setVisibleRowCount(5);
        material_comboBox_color.setVisibleRowCount(5);
        material_comboBox_manufacturer.setVisibleRowCount(5);
        material_comboBox_distributor.setVisibleRowCount(5);
        
    }    

    public DatePicker getMaterial_datePicker_purchaseDate() {
        return material_datePicker_purchaseDate;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setMaterial_label_id_value(int id) {
        this.material_label_id.setText(String.valueOf(id));        
    }
    
    public int getMaterial_label_id_value(){
        return Integer.parseInt(this.material_label_id.getText());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

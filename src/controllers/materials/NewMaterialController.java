/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.materials;

import classes.Cost;
import classes.Material;
import static classes.Material.getMaterialSoldFor;
import static classes.Material.getMaterialUsed;
import classes.MngApi;
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
    private ComboBox<String> material_comboBox_type, material_comboBox_color, material_comboBox_manufacturer, material_comboBox_distributor;
    
    @FXML
    private ComboBox<Integer> material_comboBox_weight;
            
    @FXML
    private ComboBox<Double> material_comboBox_diameter;
    
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
            SimpleIntegerProperty material_id, material_weight;
            SimpleDoubleProperty material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit;
            
            try {
                
                material_color = new SimpleStringProperty(material_comboBox_color.getSelectionModel().getSelectedItem());
                material_manufacturer = new SimpleStringProperty(material_comboBox_manufacturer.getSelectionModel().getSelectedItem());
                material_type = new SimpleStringProperty(material_comboBox_type.getSelectionModel().getSelectedItem());
                material_finished = new SimpleStringProperty("No");
                material_distributor = new SimpleStringProperty(material_comboBox_distributor.getSelectionModel().getSelectedItem());
                material_purchaseDate = new SimpleStringProperty(material_datePicker_purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                material_comment = new SimpleStringProperty(material_txtField_comment.getText());
                
                material_id = new SimpleIntegerProperty(getMaterial_label_id_value());
                material_weight = new SimpleIntegerProperty(material_comboBox_weight.getSelectionModel().getSelectedItem());
                
                material_diameter = new SimpleDoubleProperty(material_comboBox_diameter.getSelectionModel().getSelectedItem());                
                material_price = new SimpleDoubleProperty(Double.parseDouble(material_txtField_price.getText()));
                material_shipping = new SimpleDoubleProperty(Double.parseDouble(material_txtField_shipping.getText()));
                
                material_used = new SimpleDoubleProperty(0);
                material_trash = new SimpleDoubleProperty(0);                    
                material_soldFor = new SimpleDoubleProperty(0);
                material_profit = new SimpleDoubleProperty(0);
                
                Material newMaterial = new Material(material_comment, material_color, material_manufacturer, material_type, material_finished, material_distributor, material_purchaseDate, material_id, material_weight, material_diameter, material_price, material_shipping, material_used, material_trash, material_soldFor, material_profit);
                
            
            MngApi.closeWindow(material_btn_create);            
            mainController.runService(mainController.getService_refreshMaterials());
            
            } catch (NumberFormatException e) {
                material_label_info.setText("Wrong number format, please check your fields.");
                material_label_info.setTextFill(Color.web("#ff0000"));
            }
            
            
            
        });
        
        material_btn_cancel.setOnAction((event) -> {
            System.out.print(material_comboBox_color.getSelectionModel().getSelectedItem());
            MngApi.closeWindow(material_btn_cancel);
        });
    }    
    
    public void setComboBoxes(){
        
        ObservableList<String> types = FXCollections.observableArrayList(Material.getMaterialTypes(user));
        ObservableList<String> colors = FXCollections.observableArrayList(Material.getMaterialColors(user));
        ObservableList<String> manufacturers = FXCollections.observableArrayList(Material.getMaterialManufacturers(user));        
        ObservableList<String> distributors = FXCollections.observableArrayList(Material.getMaterialSellers(user));        
        
        ObservableList<Double> diameters = FXCollections.observableArrayList(Material.getMaterialDiameters(user));
        
        ObservableList<Integer> weights = FXCollections.observableArrayList(Material.getMaterialWeights(user));
        
        material_comboBox_type.setItems(types);
        material_comboBox_color.setItems(colors);
        material_comboBox_manufacturer.setItems(manufacturers);
        material_comboBox_distributor.setItems(distributors);
        material_comboBox_diameter.setItems(diameters);
        material_comboBox_weight.setItems(weights);
        
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

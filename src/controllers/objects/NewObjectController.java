/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.objects;

import classes.Cost;
import classes.MngApi;
import classes.User;
import controllers.MainController;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author Erik PC
 */
public class NewObjectController implements Initializable {

    private User user;
    
    private MainController mainController;
    
    @FXML
    private Label object_label_id, object_label_info;
    
    @FXML
    private TextField object_txtField_name, object_txtField_weight, object_txtField_supportWeight, object_txtField_hours, object_txtField_minutes, object_txtField_stlLink, object_txtField_comment;
    
    @FXML
    private Button object_btn_create, object_btn_cancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
     public void setObject_label_id_value(int id) {
        this.object_label_id.setText(String.valueOf(id));        
    }
    
    public int getObject_label_id_value(){
        return Integer.parseInt(this.object_label_id.getText());
    }
}

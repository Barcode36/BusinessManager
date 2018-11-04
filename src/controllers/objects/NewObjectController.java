/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.objects;

import classes.MngApi;
import classes.User;
import controllers.MainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        
        object_btn_create.setOnAction((event) -> {            
            boolean isEmpty = MngApi.isTextFieldEmpty(object_txtField_name, object_txtField_weight, object_txtField_supportWeight, object_txtField_hours, object_txtField_minutes);
            
            if (isEmpty == true){
                object_label_info.setText("Fields cannot be empty.");
                object_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            if (Integer.parseInt(object_txtField_minutes.getText()) > 59){
                object_label_info.setText("Max value allowed is 59.");
                object_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            classes.Object newObject;
            
            try {
                
                SimpleStringProperty  object_name, object_stlLink, object_buildTime_formated, object_comment;
                SimpleIntegerProperty object_id, object_buildTime, object_soldCount;
                SimpleDoubleProperty object_supportWeight, object_weight;
               
                object_name = new SimpleStringProperty(object_txtField_name.getText());
                object_stlLink = new SimpleStringProperty(object_txtField_stlLink.getText());
                object_comment = new SimpleStringProperty(object_txtField_comment.getText());
                
                object_id = new SimpleIntegerProperty(getObject_label_id_value());

            
                object_buildTime = new SimpleIntegerProperty(MngApi.convertToMinutes(object_txtField_hours.getText() + " " + object_txtField_minutes.getText()));
                object_buildTime_formated = MngApi.convertToHours(object_buildTime.get());
               
                object_soldCount = new SimpleIntegerProperty(0);
               
                object_supportWeight = new SimpleDoubleProperty(Double.parseDouble(object_txtField_supportWeight.getText()));
                object_weight = new SimpleDoubleProperty(Double.parseDouble(object_txtField_weight.getText()));
            
                newObject = new classes.Object(object_name, object_stlLink, object_buildTime_formated, object_comment, object_id, object_buildTime, object_soldCount, object_supportWeight, object_weight, object_weight, new SimpleDoubleProperty(0));
            
                classes.Object.insertNewObject(newObject, user);
                
                MngApi.closeWindow(object_btn_create);            
                mainController.runService(mainController.getService_refreshObjects());
            
            } catch (NumberFormatException e) {
                object_label_info.setText("Wrong number format, please check your fields.");
                object_label_info.setTextFill(Color.web("#ff0000"));
            }
            
        });
        
        object_btn_cancel.setOnAction((event) -> {
            MngApi.closeWindow(object_btn_cancel);
        });
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

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
    private Label object_label_id, object_label_info, object_label_title;
    
    @FXML
    private TextField object_txtField_name, object_txtField_weight, object_txtField_supportWeight, object_txtField_hours, object_txtField_minutes, object_txtField_stlLink, object_txtField_comment;
    
    @FXML
    private Button object_btn_create, object_btn_cancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        object_btn_create.setOnAction((event) -> {            
            createOrUpdateObject();
        });
        
        object_btn_cancel.setOnAction((event) -> {
            MngApi.closeWindow(object_btn_cancel);
        });
    }    
    
    public void createOrUpdateObject(){
        
        boolean isEmpty = MngApi.isTextFieldEmpty(object_txtField_name, object_txtField_weight, object_txtField_supportWeight, object_txtField_hours, object_txtField_minutes);
            
            if (isEmpty == true){
                object_label_info.setText("Fields cannot be empty.");
                object_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            MngApi.checkApostrophe(object_txtField_name, object_txtField_stlLink, object_txtField_comment);
            
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

            
                object_buildTime = new SimpleIntegerProperty(MngApi.convertToMinutes(object_txtField_hours.getText() + "h " + object_txtField_minutes.getText() + "m"));
                object_buildTime_formated = MngApi.convertToFormattedTime(object_buildTime.get());
               
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
                e.printStackTrace();
            }
            
    }
    
    public void setEditFields(classes.Object obj){
        
        int time = obj.getObject_buildTime().get();
        
        object_label_title.setText("Edit Object");
        object_label_id.setText(String.valueOf(obj.getObject_id().get()));
        
        object_txtField_name.setText(obj.getObject_name().get());
        object_txtField_weight.setText(String.valueOf(obj.getObject_weight().get()));
        object_txtField_supportWeight.setText(String.valueOf(obj.getObject_supportWeight().get()));
        object_txtField_hours.setText(String.valueOf(MngApi.getHours(time)));
        object_txtField_minutes.setText(String.valueOf(MngApi.getMinutes(time)));
        object_txtField_stlLink.setText(obj.getObject_stlLink().get());
        object_txtField_comment.setText(obj.getObject_comment().get());
        
        object_btn_create.setText("Update");
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
    
    public void setCreteButtonText(String text){
        object_btn_create.setText(text);
    } 
     
    public int getObject_label_id_value(){
        return Integer.parseInt(this.object_label_id.getText());
    }
    
    
}

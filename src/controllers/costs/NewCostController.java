/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.costs;

import classes.Cost;
import classes.MngApi;
import classes.User;
import controllers.MainController;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
 * @author edemko
 */
public class NewCostController implements Initializable {

    private User user;
    
    private MainController mainController;
    
    @FXML
    private Label cost_label_id, cost_label_info;
    
    @FXML
    private TextField cost_txtField_name, cost_txtField_quantity, cost_txtField_price, cost_txtField_shipping, cost_txtField_comment;
    
    @FXML
    private DatePicker cost_datePicker_purchaseDate;
    
    @FXML
    private Button cost_btn_create, cost_btn_cancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cost_btn_create.setOnAction((event) -> {
            boolean isEmpty = MngApi.isTextFieldEmpty(cost_txtField_name, cost_txtField_quantity, cost_txtField_price, cost_txtField_shipping, cost_txtField_comment);
            
            if (isEmpty == true){
                cost_label_info.setText("Fields cannot be empty.");
                cost_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            SimpleIntegerProperty cost_id, cost_quantity;
            SimpleStringProperty cost_name, cost_purchaseDate, cost_comment;
            SimpleDoubleProperty cost_shipping, cost_price;
            
            Cost newCost;
            
            try {
            cost_id = new SimpleIntegerProperty(getCost_label_id_value());
            cost_quantity = new SimpleIntegerProperty(Integer.parseInt(cost_txtField_quantity.getText()));
            
            cost_name = new SimpleStringProperty(cost_txtField_name.getText());            
            cost_purchaseDate = new SimpleStringProperty(cost_datePicker_purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cost_comment = new SimpleStringProperty(cost_txtField_comment.getText());
            
            cost_price = new SimpleDoubleProperty(Double.parseDouble(cost_txtField_price.getText()));
            cost_shipping = new SimpleDoubleProperty(Double.parseDouble(cost_txtField_shipping.getText()));
            
            newCost = new Cost(cost_id, cost_quantity, cost_name, cost_purchaseDate, cost_comment, cost_shipping, cost_price);
            
            Cost.insertNewCost(newCost, user);
            
            MngApi.closeWindow(cost_btn_create);            
            mainController.runService(mainController.getService_refreshCosts());
            
            } catch (NumberFormatException e) {
                cost_label_info.setText("Wrong number format, please check your fields.");
                cost_label_info.setTextFill(Color.web("#ff0000"));
            }
            
            
            
        });
        
        cost_btn_cancel.setOnAction((event) -> {
            MngApi.closeWindow(cost_btn_cancel);
        });
    }    

    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setCost_label_id_value(int id) {
        this.cost_label_id.setText(String.valueOf(id));        
    }
    
    public int getCost_label_id_value(){
        return Integer.parseInt(this.cost_label_id.getText());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setDefaultPurchaseDate(){
        this.cost_datePicker_purchaseDate.setValue(LocalDateTime.now().toLocalDate());
    }
    
    
    
}

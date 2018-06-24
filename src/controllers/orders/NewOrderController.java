/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.orders;

import classes.Order;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class NewOrderController implements Initializable {

    
    @FXML
    private Label label_orderID, label_weight, label_supportWeight, label_weightSum, label_quantity, label_buildTime, label_price, label_costs, label_profit;
    
    @FXML
    private TextField txtField_customer, txtField_comment, txtField_price;
    
    @FXML
    private Button btn_selectCustomer, btn_addObject, btn_removeSelected, btn_calculatePrices, btn_create, btn_cancel;
    
    @FXML
    private RadioButton radioBtn_Sold, radioBtn_NotSold;
    
    @FXML
    private DatePicker datePicker_dateCreated, datePicker_dueDate;
    
    @FXML
    private TableView<Order> tv_selectedObjects;
    
    @FXML
    private TableColumn<Order, Integer> col_objectID, col_quantity, col_buldTime, col_materialID;
    
    @FXML
    private TableColumn<Order, String> col_objectName, col_printer, col_materialType, col_materialColor;
    
    @FXML
    private TableColumn<Order, Double> col_weight, col_supportWeight, col_objectPrice,col_objectCosts;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_selectCustomer.setOnAction((event) -> {
            
            
            
        });
        
    }    
    
    public void setOrder_label_id_value(int id) {
        this.label_orderID.setText(String.valueOf(id));        
    }
    
}

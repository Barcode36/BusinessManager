/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.orders;

import classes.Customer;
import classes.MngApi;
import classes.Order;
import classes.User;
import controllers.MainController;
import controllers.select.SelectCustomerController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class NewOrderController implements Initializable {
    
    private User user;
    
    private MainController mainController;
    
    private Customer selectedCustomer;
    
    
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
            
            try {            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/select/SelectCustomer.fxml"));            
            Parent root1 = fxmlLoader.load();
            SelectCustomerController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Select Customer");
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();            
            
            stage.show();
            stage.setAlwaysOnTop(true);            
            ctrl.setUser(user);            
            ctrl.setMainController(mainController);
            ctrl.setNewOrderController(this);
            
            ctrl.displayCustomers();
        }catch (IOException e){
            
        }
            
        });
        
    }    

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
    
    
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void setOrder_label_id_value(int id) {
        this.label_orderID.setText(String.valueOf(id));        
    }
    
    
    
}

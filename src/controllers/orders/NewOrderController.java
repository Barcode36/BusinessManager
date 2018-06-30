/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.orders;

import classes.Customer;
import classes.MngApi;
import classes.Object;
import classes.Order;
import classes.User;
import controllers.MainController;
import controllers.select.SelectCustomerController;
import controllers.select.SelectObjectController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
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
    
    private ObservableList<classes.Object> selectedObjects;
    
    
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
    private TableView<classes.Object> tv_selectedObjects;
    
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
            //stage.setAlwaysOnTop(true);            
            ctrl.setUser(user);            
            ctrl.setNewOrderController(this);
            
            ctrl.displayCustomers();
        }catch (IOException e){
            
        }
        });
        
        btn_addObject.setOnAction((event) -> {
            
            try {            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/select/SelectObject.fxml"));            
            Parent root1 = fxmlLoader.load();
            SelectObjectController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Select Objects");
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();            
            
            stage.show();
            //stage.setAlwaysOnTop(true);            
            ctrl.setUser(user);            
            ctrl.setNewOrderController(this);
            ctrl.getTv_objects().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);            
            
            ctrl.displayObjects();
        }catch (IOException e){
            
        }
            
        });
        
    }    

    public void setSelectedObjects() {
        
        //Create list of orders
        ObservableList<classes.Object> objectList = FXCollections.observableArrayList(classes.Object.getObjects(user));
        
        object_col_name.setCellValueFactory((param) -> {return param.getValue().getObject_name();});
        object_col_buildTime_formated.setCellValueFactory((param) -> {return param.getValue().getObject_buildTime_formated();});
        object_col_comment.setCellValueFactory((param) -> {return param.getValue().getObject_comment();});
        
        object_col_id.setCellValueFactory((param) -> {return param.getValue().getObject_id().asObject();});        
        object_col_soldCount.setCellValueFactory((param) -> {return param.getValue().getObject_SoldCount().asObject();});
        
        object_col_weight.setCellValueFactory((param) -> {return param.getValue().getObject_weight().asObject();});
        object_col_supportWeight.setCellValueFactory((param) -> {return param.getValue().getObject_supportWeight().asObject();});
        
        //Centering content
        object_col_name.setStyle("-fx-alignment: CENTER;");

        
        object_col_id.setStyle("-fx-alignment: CENTER;");
        object_col_buildTime_formated.setStyle("-fx-alignment: CENTER;");
        object_col_soldCount.setStyle("-fx-alignment: CENTER;");
        
        object_col_weight.setStyle("-fx-alignment: CENTER;");
        object_col_supportWeight.setStyle("-fx-alignment: CENTER;");
        
        tv_objects.setItems(objectList);
        
        
        tv_selectedObjects.setItems(selectedObjects);
    }

    public ObservableList<Object> getSelectedObjects() {
        return selectedObjects;
    }
    
    public void addSelectedObjects(ObservableList<Object> objects){
        objects.get(0).getObject_name().get();
        selectedObjects.addAll(objects);
    }
    
    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        txtField_customer.setText(selectedCustomer.getCustomer_id().get() + ";" + selectedCustomer.getCustomer_lastName().get() + ";" + selectedCustomer.getCustomer_firstName().get());
    }

    public DatePicker getDatePicker_dateCreated() {
        return datePicker_dateCreated;
    }

    public DatePicker getDatePicker_dueDate() {
        return datePicker_dueDate;
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

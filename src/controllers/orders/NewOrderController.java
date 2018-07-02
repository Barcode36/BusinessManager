/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.orders;

import classes.Customer;
import classes.OrderItem;
import classes.User;
import controllers.MainController;
import controllers.select.SelectCustomerController;
import controllers.select.SelectObjectController;
import controllers.select.SelectPrinterMaterialPriceController;
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
import javafx.scene.control.TableRow;
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
    
    private ObservableList<OrderItem> selectedObjects = FXCollections.observableArrayList();
    
    
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
    private TableView<OrderItem> tv_selectedObjects;
    
    @FXML
    private TableColumn<OrderItem, Integer> col_objectID, col_quantity,col_printerID, col_materialID;
    
    @FXML
    private TableColumn<OrderItem, String> col_objectName, col_printer, col_materialType, col_materialColor, col_buildTime_formatted;
    
    @FXML
    private TableColumn<OrderItem, Double> col_weight, col_supportWeight, col_objectPrice,col_objectCosts;
    
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
        
        tv_selectedObjects.setRowFactory( tv -> {
            TableRow<OrderItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    try {            
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/select/SelectPrinterMaterialPrice.fxml"));            
                        Parent root1 = fxmlLoader.load();
                        SelectPrinterMaterialPriceController ctrl = fxmlLoader.getController();
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Assign Additional Information");
           
                        stage.setScene(new Scene(root1));
                        stage.setResizable(false);
                        stage.centerOnScreen();            
            
                        stage.show();
                        //stage.setAlwaysOnTop(true);            
                        ctrl.setUser(user);            
                        ctrl.setNewOrderController(this);                                  
                        
                        ctrl.setSelectedItem(tv_selectedObjects.getSelectionModel().getSelectedItem());
                        ctrl.setElementValues();
                    }catch (IOException e){
            
                    }
                }
            });
            return row;
        });
        
    }    

    public void setSelectedObjects() {
        
        col_objectName.setCellValueFactory((param) -> {return param.getValue().getObject_name();});
        col_buildTime_formatted.setCellValueFactory((param) -> {return param.getValue().getObject_buildTime_formated();});
        col_materialColor.setCellValueFactory((param) -> {return param.getValue().getMaterial_color();});
        col_materialType.setCellValueFactory((param) -> {return param.getValue().getMaterial_type();});
        col_printer.setCellValueFactory((param) -> {return param.getValue().getPrinter_name();});
        
        col_materialID.setCellValueFactory((param) -> {return param.getValue().getMaterial_id().asObject();});        
        col_objectID.setCellValueFactory((param) -> {return param.getValue().getObject_id().asObject();});
        col_printerID.setCellValueFactory((param) -> {return param.getValue().getPrinter_id().asObject();});
        col_quantity.setCellValueFactory((param) -> {return param.getValue().getQunatity().asObject();});
        
        
        col_weight.setCellValueFactory((param) -> {return param.getValue().getObject_weight().asObject();});
        col_supportWeight.setCellValueFactory((param) -> {return param.getValue().getObject_supportWeight().asObject();});
        col_objectCosts.setCellValueFactory((param) -> {return param.getValue().getCosts().asObject();});
        col_objectPrice.setCellValueFactory((param) -> {return param.getValue().getPrice().asObject();});
        
        
        //Centering content
        col_objectName.setStyle("-fx-alignment: CENTER;");
        col_buildTime_formatted.setStyle("-fx-alignment: CENTER;");
        col_materialColor.setStyle("-fx-alignment: CENTER;");
        col_materialType.setStyle("-fx-alignment: CENTER;");
        col_printer.setStyle("-fx-alignment: CENTER;");
        
        col_materialID.setStyle("-fx-alignment: CENTER;");
        col_objectID.setStyle("-fx-alignment: CENTER;");
        col_printerID.setStyle("-fx-alignment: CENTER;");
        col_quantity.setStyle("-fx-alignment: CENTER;");
        
        
        col_weight.setStyle("-fx-alignment: CENTER;");
        col_supportWeight.setStyle("-fx-alignment: CENTER;");
        col_objectCosts.setStyle("-fx-alignment: CENTER;");
        col_objectPrice.setStyle("-fx-alignment: CENTER;");       
        
        tv_selectedObjects.setItems(selectedObjects);
    }

    public ObservableList<OrderItem> getSelectedObjects() {
        return selectedObjects;
    }
    
    public void addSelectedObjects(ObservableList<OrderItem> selectedObjects){
        
        this.selectedObjects.addAll(selectedObjects);
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

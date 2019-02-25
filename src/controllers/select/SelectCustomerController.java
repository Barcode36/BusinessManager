/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.select;

import classes.Customer;
import classes.MngApi;
import com.zaxxer.hikari.HikariDataSource;
import controllers.MainController;
import controllers.orders.NewOrderController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Erik PC
 */
public class SelectCustomerController implements Initializable {

    private HikariDataSource ds;
    
    
    private MainController main_controller;
    
    private NewOrderController newOrderController;
    
    @FXML
    private TableView<Customer> tv_customers;
    
    @FXML
    private TableColumn<Customer, String> customer_col_lastName, customer_col_firstName, customer_col_dateCreated, customer_col_mail, customer_col_phone, customer_col_company;
    
    @FXML 
    private TableColumn<Customer, Integer> customer_col_id;
    
    @FXML
    private TextField txtField_search;
    
    @FXML
    private Button btn_select, btn_close;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tv_customers.setRowFactory( tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    newOrderController.setSelectedCustomer(row.getItem());
                    newOrderController.getBtn_create().setDisable(false);
                    MngApi.closeWindow(btn_close);
                }
            });
            return row ;
        });
        
        btn_select.setOnAction((event) -> {
            
            Customer selectedCustomer = tv_customers.getSelectionModel().getSelectedItem();
            newOrderController.setSelectedCustomer(selectedCustomer);
            newOrderController.getBtn_create().setDisable(false);
            MngApi.closeWindow(btn_select);
            
        });
        
        btn_close.setOnAction((event) -> {
            
            MngApi.closeWindow(btn_close);
            
        });
        
    }    
    
    
    public void setDs(HikariDataSource ds) {
        this.ds = ds;
    }    
    
    public void displayCustomers() {        
        
        //Create list of orders
        ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getCustomers(main_controller.getCommonCustomerProperties(), ds));
        
        //set cell value factory for columns by type
        // customer_col_company, customer_col_comment;
        //Strings
        customer_col_lastName.setCellValueFactory((param) -> {return param.getValue().getCustomer_lastName();});
        customer_col_firstName.setCellValueFactory((param) -> {return param.getValue().getCustomer_firstName();});
        customer_col_dateCreated.setCellValueFactory((param) -> {return param.getValue().getCustomer_dateCreated();});
        customer_col_mail.setCellValueFactory((param) -> {return param.getValue().getCustomer_mail();});
        customer_col_phone.setCellValueFactory((param) -> {return param.getValue().getCustomer_phone();});
        customer_col_company.setCellValueFactory((param) -> {return param.getValue().getCustomer_company();});
        
        
        //Integers
        customer_col_id.setCellValueFactory((param) -> {return param.getValue().getCustomer_id().asObject();});
       
        
        //centering content of columns
        customer_col_lastName.setStyle("-fx-alignment: CENTER;");        
        customer_col_firstName.setStyle("-fx-alignment: CENTER;");
        customer_col_dateCreated.setStyle("-fx-alignment: CENTER;");
        customer_col_mail.setStyle("-fx-alignment: CENTER;");
        customer_col_phone.setStyle("-fx-alignment: CENTER;");
        customer_col_company.setStyle("-fx-alignment: CENTER;");
                
        customer_col_id.setStyle("-fx-alignment: CENTER;");        
        
        //set list to display in table
        tv_customers.setItems(customerList);
    }

    public void setNewOrderController(NewOrderController newOrderController) {
        this.newOrderController = newOrderController;
    }    

    public void setMain_controller(MainController main_controller) {
        this.main_controller = main_controller;
    }
    
}

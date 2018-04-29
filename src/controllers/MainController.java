/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Customer;
import classes.Order;
import classes.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Erik PC
 */
public class MainController implements Initializable {
    
    
    /*****************************          GENERAL          *****************************/      
        
    private User user = null;    
    
    @FXML
    private TabPane tp_main;       
    /*
    *
    *
    *
    *
    *    
    */    
    /*****************************          ORDERS TAB          *****************************/      
    
    @FXML
    private Tab tab_orders;
    
    @FXML
    private TableView<Order> tv_orders;
    
    @FXML
    private TableColumn<Order, String> order_col_customer, order_col_status, order_col_comment, order_col_dateCreated, order_col_dueDate;
    
    @FXML
    private TableColumn<Order, Integer> order_col_orderId, order_col_totalQuantity, order_col_totalBuildTime;
    
    @FXML
    private TableColumn<Order, Double> order_col_totalCosts, order_col_totalPrice, order_col_totalWeight;     
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          CUSTOMERS TAB          *****************************/
    
    @FXML
    private Tab tab_customers;
    
    @FXML
    private TableView<Customer> tv_customers;
    
    @FXML
    private TableColumn<Customer, String> customer_col_lastName, customer_col_firstName, customer_col_mail, customer_col_phone, customer_col_address, customer_col_city, customer_col_zipCode, customer_col_state, customer_col_company, customer_col_comment;
    
    @FXML 
    private TableColumn<Customer, Integer> customer_col_customerId;
    
    /*
    *
    *
    *
    *
    *    
    */    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    /*****************************          INITIALIZE ORDERS TAB          *****************************/    
    
        tab_orders.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab_orders.isSelected()) {
                    refreshOrdersTable(user);
                }
            }
        });    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          INITIALIZE CUSTOMERS TAB          *****************************/
    
    tab_customers.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab_customers.isSelected()) {
                    refreshCustomersTable(user);
                }
            }
        });
    
    
    }//end initialize    
    /*
    *
    *                                       METHODS
    *
    *
    *    
    */
    /*****************************          GENERAL          *****************************/          
    public void setUser(User user){
        this.user = user;        
    }

    
    /*****************************          ORDERS TAB
     * @param user *****************************/      
    
    public void refreshOrdersTable(User user) {
        //Create list of orders
        ObservableList<Order> orderList = FXCollections.observableArrayList(Order.getOrders(user));
        
        //set cell value factory for columns by type
        
        //Strings
        order_col_comment.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_comment());        
        order_col_customer.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_customer());
        order_col_dateCreated.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_dateCreated());
        order_col_dueDate.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_dueDate());
        order_col_status.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_status());
        
        
        //Integers
        order_col_orderId.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_id().asObject());
        order_col_totalBuildTime.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_buildTime().asObject());
        order_col_totalQuantity.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_quantity().asObject());
        
        //Doubles
        order_col_totalCosts.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_costs().asObject());
        order_col_totalPrice.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_price().asObject());
        order_col_totalWeight.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_weighht().asObject());
        
        //centering content of columns
        order_col_comment.setStyle("-fx-alignment: CENTER;");        
        order_col_customer.setStyle("-fx-alignment: CENTER;");
        order_col_dateCreated.setStyle("-fx-alignment: CENTER;");
        order_col_dueDate.setStyle("-fx-alignment: CENTER;");
        order_col_status.setStyle("-fx-alignment: CENTER;");
        
        order_col_orderId.setStyle("-fx-alignment: CENTER;");
        order_col_totalBuildTime.setStyle("-fx-alignment: CENTER;");
        order_col_totalQuantity.setStyle("-fx-alignment: CENTER;");
        
        order_col_totalCosts.setStyle("-fx-alignment: CENTER;");
        order_col_totalPrice.setStyle("-fx-alignment: CENTER;");
        order_col_totalWeight.setStyle("-fx-alignment: CENTER;");
        
        //set list to display in table
        tv_orders.setItems(orderList);
    }
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          CUSTOMERS TAB          *****************************/
    
    public void refreshCustomersTable(User user) {
        //Create list of orders
        ObservableList<Order> orderList = FXCollections.observableArrayList(Order.getOrders(user));
        
        //set cell value factory for columns by type
        //customer_col_lastName, customer_col_firstName, customer_col_mail, customer_col_phone, customer_col_address, customer_col_city, customer_col_zipCode, customer_col_state, customer_col_company, customer_col_comment;
        //Strings
        
        
        //centering content of columns
        order_col_comment.setStyle("-fx-alignment: CENTER;");        
        order_col_customer.setStyle("-fx-alignment: CENTER;");
        order_col_dateCreated.setStyle("-fx-alignment: CENTER;");
        order_col_dueDate.setStyle("-fx-alignment: CENTER;");
        order_col_status.setStyle("-fx-alignment: CENTER;");
        
        order_col_orderId.setStyle("-fx-alignment: CENTER;");
        order_col_totalBuildTime.setStyle("-fx-alignment: CENTER;");
        order_col_totalQuantity.setStyle("-fx-alignment: CENTER;");
        
        order_col_totalCosts.setStyle("-fx-alignment: CENTER;");
        order_col_totalPrice.setStyle("-fx-alignment: CENTER;");
        order_col_totalWeight.setStyle("-fx-alignment: CENTER;");
        
        //set list to display in table
        tv_orders.setItems(orderList);
    }
    /*
    *
    *
    *
    *
    *    
    */
}

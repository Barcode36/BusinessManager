/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Customer;
import classes.Object;
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
    private TableColumn<Order, String> order_col_customer, order_col_status, order_col_comment, order_col_dateCreated, order_col_dueDate, order_col_totalBuildTime_formated;
    
    @FXML
    private TableColumn<Order, Integer> order_col_orderId, order_col_totalQuantity;
    
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
    private TableColumn<Customer, String> customer_col_lastName, customer_col_firstName, customer_col_dateCreated, customer_col_mail, customer_col_phone, customer_col_address, customer_col_city, customer_col_zipCode, customer_col_country, customer_col_company, customer_col_comment;
    
    @FXML 
    private TableColumn<Customer, Integer> customer_col_id, customer_col_orderCount;
    
    @FXML
    private TableColumn<Customer, Double> customer_col_ordersPrice;
    
    /*
    *
    *
    *
    *
    *    
    */    
    /*****************************          OBJECTS TAB          *****************************/
    @FXML
    private Tab tab_objects;
    
    @FXML
    private TableView<Object> tv_objects;
    
    @FXML
    private TableColumn<Object, String> object_col_name, object_col_stlLink, object_col_buildTime_formated;
    
    @FXML
    private TableColumn<Object, Integer> object_col_id, object_col_soldCount;
    
    @FXML
    private TableColumn<Object, Double> object_col_weight, object_col_supportWeight;
    
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
    
    /*****************************          INITIALIZE OBJECTS TAB          *****************************/
    
    tab_objects.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab_objects.isSelected()) {
                    refreshObjectsTable(user);
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
        order_col_totalBuildTime_formated.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_buildTime_formated());
        
        //Integers
        order_col_orderId.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_id().asObject());        
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
        order_col_totalBuildTime_formated.setStyle("-fx-alignment: CENTER;");
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
        ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getCustomers(user));
        
        //set cell value factory for columns by type
        // customer_col_company, customer_col_comment;
        //Strings
        customer_col_lastName.setCellValueFactory((param) -> {return param.getValue().getCustomer_lastName();});
        customer_col_firstName.setCellValueFactory((param) -> {return param.getValue().getCustomer_firstName();});
        customer_col_dateCreated.setCellValueFactory((param) -> {return param.getValue().getCustomer_dateCreated();});
        customer_col_mail.setCellValueFactory((param) -> {return param.getValue().getCustomer_mail();});
        customer_col_phone.setCellValueFactory((param) -> {return param.getValue().getCustomer_phone();});
        customer_col_address.setCellValueFactory((param) -> {return param.getValue().getCustomer_address();});        
        customer_col_city.setCellValueFactory((param) -> {return param.getValue().getCustomer_city();});
        customer_col_zipCode.setCellValueFactory((param) -> {return param.getValue().getCustomer_zipCode();});
        customer_col_country.setCellValueFactory((param) -> {return param.getValue().getCustomer_country();});
        customer_col_company.setCellValueFactory((param) -> {return param.getValue().getCustomer_company();});
        customer_col_comment.setCellValueFactory((param) -> {return param.getValue().getCustomer_comment();});
        
        //Integers
        customer_col_id.setCellValueFactory((param) -> {return param.getValue().getCustomer_id().asObject();});
        customer_col_orderCount.setCellValueFactory((param) -> {return param.getValue().getCustomer_orderCount().asObject();});
        
        //Doubles
        customer_col_ordersPrice.setCellValueFactory((param) -> {return param.getValue().getCustomer_ordersPrice().asObject();});
        
        //centering content of columns
        customer_col_lastName.setStyle("-fx-alignment: CENTER;");        
        customer_col_firstName.setStyle("-fx-alignment: CENTER;");
        customer_col_dateCreated.setStyle("-fx-alignment: CENTER;");
        customer_col_mail.setStyle("-fx-alignment: CENTER;");
        customer_col_phone.setStyle("-fx-alignment: CENTER;");
        customer_col_address.setStyle("-fx-alignment: CENTER;");        
        customer_col_city.setStyle("-fx-alignment: CENTER;");        
        customer_col_zipCode.setStyle("-fx-alignment: CENTER;");
        customer_col_country.setStyle("-fx-alignment: CENTER;");
        customer_col_company.setStyle("-fx-alignment: CENTER;");
        customer_col_comment.setStyle("-fx-alignment: CENTER;");
        
        customer_col_id.setStyle("-fx-alignment: CENTER;");
        customer_col_orderCount.setStyle("-fx-alignment: CENTER;");
        
        customer_col_ordersPrice.setStyle("-fx-alignment: CENTER;");
        
        
        //set list to display in table
        tv_customers.setItems(customerList);
    }
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          OBJECTS TAB          *****************************/
    
    public void refreshObjectsTable(User user){
        
        //Create list of orders
        ObservableList<Object> objectList = FXCollections.observableArrayList(Object.getObjects(user));
        
        object_col_name.setCellValueFactory((param) -> {return param.getValue().getObject_name();});
        object_col_stlLink.setCellValueFactory((param) -> {return param.getValue().getObject_stlLink();});           
        object_col_buildTime_formated.setCellValueFactory((param) -> {return param.getValue().getObject_buildTime_formated();});
        
        object_col_id.setCellValueFactory((param) -> {return param.getValue().getObject_id().asObject();});        
        object_col_soldCount.setCellValueFactory((param) -> {return param.getValue().getObject_SoldCount().asObject();});
        
        object_col_weight.setCellValueFactory((param) -> {return param.getValue().getObject_weight().asObject();});
        object_col_supportWeight.setCellValueFactory((param) -> {return param.getValue().getObject_supportWeight().asObject();});
        
        //Centering content
        object_col_name.setStyle("-fx-alignment: CENTER;");
        object_col_stlLink.setStyle("-fx-alignment: CENTER;");
        
        object_col_id.setStyle("-fx-alignment: CENTER;");
        object_col_buildTime_formated.setStyle("-fx-alignment: CENTER;");
        object_col_soldCount.setStyle("-fx-alignment: CENTER;");
        
        object_col_weight.setStyle("-fx-alignment: CENTER;");
        object_col_supportWeight.setStyle("-fx-alignment: CENTER;");
        
        tv_objects.setItems(objectList);
        
    }    
    
    /*
    *
    *
    *
    *
    *    
    */
}//end MainController

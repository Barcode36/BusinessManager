/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.Order;
import classes.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Erik PC
 */
public class MainController implements Initializable {
    //login credentials which are set in loginController through setUser method
    private User user = null;    
    
    
    //order table columns
    @FXML
    private TableColumn<Order, String> col_customer, col_status, col_comment, col_dateCreated, col_dueDate;
    
    @FXML
    private TableColumn<Order, Integer> col_orderId, col_totalQuantity, col_totalBuildTime;
    
    @FXML
    private TableColumn<Order, Double> col_totalCosts, col_totalPrice, col_totalWeight; 
    
    @FXML
    private TableView<Order> tw_orders;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        
    public void setUser(User user){
        this.user = user;        
    }

    public void refreshOrdersTable(User user) {
        //Create list of orders
        ObservableList<Order> orderList = FXCollections.observableArrayList(Order.getOrders(user));
        
        //set cell value factory for columns by type
        
        //Strings
        col_comment.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_comment());        
        col_customer.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_customer());
        col_dateCreated.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_dateCreated());
        col_dueDate.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_dueDate());
        col_status.setCellValueFactory((TableColumn.CellDataFeatures<Order, String> param) -> param.getValue().getOrder_status());
        
        
        //Integers
        col_orderId.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_id().asObject());
        col_totalBuildTime.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_buildTime().asObject());
        col_totalQuantity.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_quantity().asObject());
        
        //Doubles
        col_totalCosts.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_costs().asObject());
        col_totalPrice.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_price().asObject());
        col_totalWeight.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_weighht().asObject());
        
        //centering content of columns
        col_comment.setStyle("-fx-alignment: CENTER;");        
        col_customer.setStyle("-fx-alignment: CENTER;");
        col_dateCreated.setStyle("-fx-alignment: CENTER;");
        col_dueDate.setStyle("-fx-alignment: CENTER;");
        col_status.setStyle("-fx-alignment: CENTER;");
        
        col_orderId.setStyle("-fx-alignment: CENTER;");
        col_totalBuildTime.setStyle("-fx-alignment: CENTER;");
        col_totalQuantity.setStyle("-fx-alignment: CENTER;");
        
        col_totalCosts.setStyle("-fx-alignment: CENTER;");
        col_totalPrice.setStyle("-fx-alignment: CENTER;");
        col_totalWeight.setStyle("-fx-alignment: CENTER;");
        
        //set list to display in table
        tw_orders.setItems(orderList);
    }
    
    
}

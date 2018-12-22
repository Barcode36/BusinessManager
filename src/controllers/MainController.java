/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.costs.NewCostController;
import classes.Cost;
import classes.Customer;
import classes.Material;
import classes.MngApi;
import classes.Object;
import classes.Order;
import classes.User;
import controllers.customers.NewCustomerController;
import controllers.materials.NewMaterialController;
import controllers.objects.NewObjectController;
import controllers.orders.NewOrderController;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Erik PC
 */
public class MainController implements Initializable {
    
    
    /*****************************          GENERAL - VARIABLES       *****************************/      
        
    private User user = null;    
    
    @FXML
    private TabPane tp_main;
    
    @FXML
    private ProgressBar progressBar;
    
    
    
    /*****************************          GENERAL - METHODS         *****************************/          
    public void setUser(User user){
        this.user = user;        
    }
    
    public void runService(Service service){
                        
            service.start();
            progressBar.progressProperty().bind(service.progressProperty());
            
            service.setOnSucceeded((event) -> {
                service.reset();
                progressBar.progressProperty().unbind();
                progressBar.setProgress(1);                
            });
            
            service.setOnFailed((event) -> {
                //service.reset();
                service.restart();
                progressBar.progressProperty().unbind();
                progressBar.setProgress(-1);
                service.start();                
                System.out.print(service.getState().toString());
            });
    }
    /*
    *
    *
    *
    *
    *    
    */    
    /*****************************          ORDERS TAB - VARIABLES          *****************************/      
    
    @FXML
    private Tab tab_orders;
    
    @FXML
    private TableView<Order> tv_orders;
    
    @FXML
    private TableColumn<Order, String> order_col_customer, order_col_status, order_col_comment, order_col_dateCreated, order_col_dueDate, order_col_totalBuildTime_formated;
    
    @FXML
    private TableColumn<Order, Integer> order_col_customerID, order_col_orderId, order_col_totalQuantity;
    
    @FXML
    private TableColumn<Order, Double> order_col_totalCosts, order_col_totalPrice, order_col_totalWeight, order_col_totalSupportWeight;     
    
    @FXML
    private Button order_btn_new, order_btn_edit, order_btn_refresh, order_btn_delete;
    
    @FXML
    private Label label_order_SoldOrders, label_order_SoldCostPrice, label_order_OrderProfit, label_order_NotSoldOrders, label_order_NotSoldCostPrice, label_order_NotSoldOrderProfit, label_order_info;
    
    @FXML
    private Label label_order_totalOrders, label_order_TotalCostPrice, label_order_TotalPricePerHour, label_order_TotalWeight, label_order_TotalBuildTime, label_order_TotalItemsPrinted;
            
    @FXML
    private Label label_order_SelectedCostsPrice, label_order_SelectedWeight, label_order_SelectedItemsPrinted, label_order_SelectedPricePerHour, label_order_SelectedBuildTime, label_order_SelectedOrders;
    
    /*****************************          ORDERS TAB - METHODS        *****************************/
    
    private void deleteOrder(Order order){        
        int order_id = order.getOrder_id().get();
        String query = "DELETE FROM OrderItems WHERE OrderID=" + order_id;
        MngApi.performUpdate(query, user);
        query = "DELETE FROM Orders WHERE OrderID=" + order_id;        
        MngApi.performUpdate(query, user);           
    }
    
    
    private void calculateOrderStatistics(){
        
        int sold_orders = 0, notSold_orders = 0, total_orders, total_itemsSold = 0, total_buildTime = 0;        
        double sold_price = 0, sold_costs = 0, notSold_price = 0, notSold_costs = 0, total_costs = 0, total_price = 0, total_weight = 0, total_supportWeight = 0;
        
        //setting  statistics for sold and not sold orders        
        //notSold_orders = MngApi.performIntegerQuery("SELECT COUNT(OrderID) FROM Orders WHERE OrderStatus='Not Sold'", user);
        
        
        for (int i = 0; i < tv_orders.getItems().size(); i++) {
            Order order = tv_orders.getItems().get(i);
            if (order.getOrder_status().get().equals("Sold")){
                sold_costs += order.getOrder_costs().get();
                sold_price += order.getOrder_price().get();
                sold_orders++;
            }  else {
                notSold_costs += order.getOrder_costs().get();
                notSold_price += order.getOrder_price().get();
                notSold_orders++;
            }
            
            total_weight += order.getOrder_weighht().get();
            total_supportWeight += order.getOrder_support_weight().get();
            
            total_buildTime += order.getOrder_buildTime().get();
            
            total_itemsSold += order.getOrder_quantity().get();
        }
        
        label_order_SoldOrders.setText(String.format(Locale.US, "Sold(%d)", sold_orders));
        label_order_SoldCostPrice.setText(String.format(Locale.US, "%.2f $/%.2f $", sold_price, sold_costs));
        label_order_OrderProfit.setText(String.format(Locale.US, "%.2f $", sold_price - sold_costs));
        
        label_order_NotSoldOrders.setText(String.format(Locale.US, "Not Sold(%d)", notSold_orders));
        label_order_NotSoldCostPrice.setText(String.format(Locale.US, "%.2f $/%.2f $", notSold_price, notSold_costs));
        label_order_NotSoldOrderProfit.setText(String.format(Locale.US, "%.2f $", notSold_price - notSold_costs));
        
        total_costs = sold_costs + notSold_costs;
        total_price = sold_price + notSold_price;
        total_orders = sold_orders + notSold_orders;
        
        label_order_totalOrders.setText(String.format(Locale.US, "Total(%d)", total_orders));
        label_order_TotalCostPrice.setText(String.format(Locale.US, "%.2f $/%.2f $", total_price, total_costs));
        label_order_TotalPricePerHour.setText(String.format(Locale.US, "%.2f$/h", total_price/total_buildTime*60));
        if (total_weight >= 1000){
            label_order_TotalWeight.setText(String.format(Locale.US, "%.2fkg/%.2fkg", total_weight/1000, total_supportWeight/1000));
        } else {
            label_order_TotalWeight.setText(String.format(Locale.US, "%.2fg/%.2fg", total_weight, total_supportWeight));
        }        
        label_order_TotalBuildTime.setText(String.format(Locale.US, "%s", MngApi.convertToFormattedTime(total_buildTime).get()));
        label_order_TotalItemsPrinted.setText(String.valueOf(total_itemsSold));        
    }
    
    private void calculateSelectedOrdersStatistics(ObservableList<Order> selectedOrders){
               
        int selected_orders, selected_itemsSold = 0, selected_buildTime = 0;        
        double  selected_weight = 0, selected_supportWeight = 0, selected_price = 0, selected_cost = 0;
        
        //setting  statistics for sold and not sold orders
        selected_orders = selectedOrders.size();
                
        for (int i = 0; i < selectedOrders.size(); i++) {
            Order seleectedOrder = selectedOrders.get(i);
            selected_price += seleectedOrder.getOrder_price().get();
            selected_cost += seleectedOrder.getOrder_costs().get();
            selected_buildTime += seleectedOrder.getOrder_buildTime().get();
            selected_weight += seleectedOrder.getOrder_weighht().get();
            selected_itemsSold += seleectedOrder.getOrder_quantity().get();
        }
        
        label_order_SelectedOrders.setText(String.format(Locale.US, "Selected(%d)", selected_orders));
        label_order_SelectedCostsPrice.setText(String.format(Locale.US, "%.2f $/%.2f $", selected_price, selected_cost));
        label_order_SelectedPricePerHour.setText(String.format(Locale.US, "%.2f$/h", selected_price/selected_buildTime*60));
        if (selected_weight >= 1000) {
            label_order_SelectedWeight.setText(String.format(Locale.US, "%.2fkg/%.2fkg", selected_weight/1000, selected_supportWeight/1000));
        } else {
            label_order_SelectedWeight.setText(String.format(Locale.US, "%.2fg/%.2fg", selected_weight, selected_supportWeight));
        }
        label_order_SelectedBuildTime.setText(String.format(Locale.US, "%s", MngApi.convertToFormattedTime(selected_buildTime).get()));
        label_order_SelectedItemsPrinted.setText(String.valueOf(selected_itemsSold));        
        
    }
    
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
        order_col_customerID.setCellValueFactory((TableColumn.CellDataFeatures<Order, Integer> param) -> param.getValue().getOrder_customerID().asObject());
        
        //Doubles
        order_col_totalCosts.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_costs().asObject());
        order_col_totalPrice.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_price().asObject());
        order_col_totalWeight.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_weighht().asObject());
        order_col_totalSupportWeight.setCellValueFactory((TableColumn.CellDataFeatures<Order, Double> param) -> param.getValue().getOrder_support_weight().asObject());
        
        //centering content of columns
        order_col_comment.setStyle("-fx-alignment: CENTER;");        
        order_col_customer.setStyle("-fx-alignment: CENTER;");
        order_col_dateCreated.setStyle("-fx-alignment: CENTER;");
        order_col_dueDate.setStyle("-fx-alignment: CENTER;");
        order_col_status.setStyle("-fx-alignment: CENTER;");
        
        order_col_orderId.setStyle("-fx-alignment: CENTER;");
        order_col_totalBuildTime_formated.setStyle("-fx-alignment: CENTER;");
        order_col_totalQuantity.setStyle("-fx-alignment: CENTER;");
        order_col_customerID.setStyle("-fx-alignment: CENTER;");
        
        order_col_totalCosts.setStyle("-fx-alignment: CENTER;");
        order_col_totalPrice.setStyle("-fx-alignment: CENTER;");
        order_col_totalWeight.setStyle("-fx-alignment: CENTER;");
        order_col_totalSupportWeight.setStyle("-fx-alignment: CENTER;");
        
        //set list to display in table
        tv_orders.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv_orders.setItems(orderList);
        
    }
    
    // Create the service
    private final Service<Void> service_refreshOrders = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshCostsTable = new Task<Void>() {
                @Override
                public Void call() throws Exception {                                        
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);
                        refreshOrdersTable(user);
                        calculateOrderStatistics();                        
                    });
                    return null;
                }        
            };
        return task_refreshCostsTable;
        }
    };

    public Service<Void> getService_refreshOrders() {
        return service_refreshOrders;
    }
    
    public Order getSelectedOrder(){
        return tv_orders.getSelectionModel().getSelectedItem();
    }    
    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          CUSTOMERS TAB - VARIABLES         *****************************/
    
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
    
    @FXML
    private Button customer_btn_new,customer_btn_refresh, customer_btn_showDetails, customer_btn_edit;
    
    @FXML
    private Label label_customer_selected, label_customers_custCount, label_customers_selectedOrders, label_customers_selectedItems, label_customers_selectedPrice, label_customers_selectedCosts, label_customers_selectedWeight, label_customers_selectedSupportWeight, label_customers_selectedBuildTime, label_customers_selectedPricePerHour;
    
    
    /*****************************          CUSTOMERS - METHODS         *****************************/
    
    private void calculateSelectedCustomersStatistics(ObservableList<Customer> selectedCustomers){
        
        int orders = 0, items = 0, time = 0;
        double price = 0.0, costs = 0.0, weight = 0.0, supports = 0.0, perHour;
        
        label_customer_selected.setText(String.format("Selected(%d)", selectedCustomers.size()));
        
        try {
            for (int i = 0; i < selectedCustomers.size(); i++) {
                
                int customer_id = selectedCustomers.get(i).getCustomer_id().get();
                Double[] statistics = Customer.getCustomerStats(customer_id, user);
                
                double d_orders = statistics[0];
                double d_items = statistics[1];
                double d_time = statistics[6];
                
                //orders = (int) d_orders;
                //items = (int) d_items;                
                //time = (int) d_time;
                
                orders += (int) d_orders;
                items += (int) d_items;                
                price += statistics[2];
                costs += statistics[3];
                weight += statistics[4];
                supports += statistics[5];
                time += (int) d_time;
                            
            }
        
            label_customers_selectedOrders.setText(String.format(Locale.US, "%d", orders));
            label_customers_selectedItems.setText(String.format(Locale.US, "%d", items));
            label_customers_selectedPrice.setText(String.format(Locale.US, "%.2f $", price));
            label_customers_selectedCosts.setText(String.format(Locale.US, "%.2f $", costs));
            label_customers_selectedWeight.setText(String.format(Locale.US, "%.2f g", weight));
            label_customers_selectedSupportWeight.setText(String.format(Locale.US, "%.2f g", supports));
            perHour = MngApi.round(price/time*60, 2);
            label_customers_selectedBuildTime.setText(MngApi.convertToFormattedTime(time).get());        
            label_customers_selectedPricePerHour.setText(String.format(Locale.US, "%.2f $/h", perHour));
        
        } catch (ArithmeticException | NumberFormatException e) {
            
            label_customers_selectedOrders.setText(String.format(Locale.US, "%d", orders));
            label_customers_selectedItems.setText(String.format(Locale.US, "%d", items));
            label_customers_selectedPrice.setText(String.format(Locale.US, "%.2f $", price));
            label_customers_selectedCosts.setText(String.format(Locale.US, "%.2f $", costs));
            label_customers_selectedWeight.setText(String.format(Locale.US, "%.2g $", weight));
            label_customers_selectedSupportWeight.setText(String.format(Locale.US, "%.2g $", supports));            
            label_customers_selectedBuildTime.setText("0");        
            label_customers_selectedPricePerHour.setText(String.format(Locale.US, "0 $/h"));
            
        }
        
    }
    
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
        
        tv_customers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //set list to display in table
        tv_customers.setItems(customerList);
        
        
        int total_customers = customerList.size();        
        label_customers_custCount.setText(String.valueOf(total_customers));
    }
    
    private void showCustomerDetails(ObservableList<Customer> selectedCustomer){
        Customer customer = selectedCustomer.get(0);
        
        
    }
    
    // Create the service
    private final Service<Void> service_refreshCustomers = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshCostsTable = new Task<Void>() {            
                @Override
                public Void call() throws Exception {
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);
                        refreshCustomersTable(user);                    
                    });                    
                    
                    return null;
                }        
            };
        return task_refreshCostsTable;
        }
    };

    public Service<Void> getService_refreshCustomers() {
        return service_refreshCustomers;
    }
    /*
    *
    *
    *
    *
    *    
    */    
    /*****************************          OBJECTS - VARIABLES        *****************************/
    @FXML
    private Tab tab_objects;
    
    @FXML
    private TableView<Object> tv_objects;
    
    @FXML
    private TableColumn<Object, String> object_col_name, object_col_stlLink, object_col_buildTime_formated, object_col_comment;
    
    @FXML
    private TableColumn<Object, Integer> object_col_id, object_col_soldCount;
    
    @FXML
    private TableColumn<classes.Object, Double> object_col_weight, object_col_supportWeight, object_col_soldPrice, object_col_objectCosts;
    
    @FXML
    private Button object_btn_new, object_btn_edit;
    
    @FXML
    private Label object_label_objCount,object_label_Selected,object_label_TimesPrinted,object_label_PriceCosts,object_label_PricePerHour,object_label_BuildTime, object_label_ItemSupportWeight;
    
    /*****************************          OBJECTS - METHODS         *****************************/
    
    public void refreshObjectsTable(User user){
        
        //Create list of orders
        ObservableList<Object> objectList = FXCollections.observableArrayList(Object.getObjects(user));
        
        object_col_name.setCellValueFactory((param) -> {return param.getValue().getObject_name();});
        object_col_stlLink.setCellValueFactory((param) -> {return param.getValue().getObject_stlLink();});           
        object_col_buildTime_formated.setCellValueFactory((param) -> {return param.getValue().getObject_buildTime_formated();});
        object_col_comment.setCellValueFactory((param) -> {return param.getValue().getObject_comment();});
        
        object_col_id.setCellValueFactory((param) -> {return param.getValue().getObject_id().asObject();});        
        object_col_soldCount.setCellValueFactory((param) -> {return param.getValue().getObject_SoldCount().asObject();});
        
        object_col_weight.setCellValueFactory((param) -> {return param.getValue().getObject_weight().asObject();});
        object_col_supportWeight.setCellValueFactory((param) -> {return param.getValue().getObject_supportWeight().asObject();});
        object_col_soldPrice.setCellValueFactory((param) -> {return param.getValue().getObject_soldPrice().asObject();});
        object_col_objectCosts.setCellValueFactory((param) -> {return param.getValue().getObject_costs().asObject();});
        
        //Centering content
        object_col_name.setStyle("-fx-alignment: CENTER;");
        object_col_stlLink.setStyle("-fx-alignment: CENTER;");
        
        object_col_id.setStyle("-fx-alignment: CENTER;");
        object_col_buildTime_formated.setStyle("-fx-alignment: CENTER;");
        object_col_soldCount.setStyle("-fx-alignment: CENTER;");
        
        object_col_weight.setStyle("-fx-alignment: CENTER;");
        object_col_supportWeight.setStyle("-fx-alignment: CENTER;");
        object_col_soldPrice.setStyle("-fx-alignment: CENTER;");
        object_col_objectCosts.setStyle("-fx-alignment: CENTER;");
        
        tv_objects.setItems(objectList);
        tv_objects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        object_label_objCount.setText(String.valueOf(objectList.size()));
    }    
    
    // Create the service
    private final Service<Void> service_refreshObjects = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshCostsTable = new Task<Void>() {            
                @Override
                public Void call() throws Exception {                     
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);
                        refreshObjectsTable(user);
                        calculateOrderStatistics();
                    });
                    
                    
                    return null;
                }        
            };
        return task_refreshCostsTable;
        }
    };

    public Service<Void> getService_refreshObjects() {
        return service_refreshObjects;
    }
    
    private void calculateSelectedObjectsStatistics(ObservableList<classes.Object> selectedObjects){
        
        int timesPrinted = 0, buildTime = 0;
        double price = 0, costs = 0, weight = 0, supportWeight = 0,pricePerHour = 0;
        
        for (int i = 0; i < selectedObjects.size(); i++) {
            
            classes.Object current = selectedObjects.get(i);
            
            timesPrinted += current.getObject_SoldCount().get();
            buildTime += current.getObject_buildTime().get()*current.getObject_SoldCount().get();
            price += current.getObject_soldPrice().get();
            costs += current.getObject_costs().get();
            weight += current.getObject_weight().get();
            supportWeight += current.getObject_supportWeight().get();
                        
        }
        
        object_label_Selected.setText(String.valueOf(selectedObjects.size()));
        object_label_TimesPrinted.setText(String.valueOf(timesPrinted));
        object_label_BuildTime.setText(String.valueOf(buildTime));
        object_label_PriceCosts.setText(String.format(Locale.US, "%.2f $/%.2f $", price, costs));        
        object_label_ItemSupportWeight.setText(String.format(Locale.US, "%.2f g/%.2f g", weight, supportWeight));
        object_label_PricePerHour.setText(String.format(Locale.US, "%.2f $/h" , price/buildTime*60));
        object_label_BuildTime.setText(MngApi.convertToFormattedTime(buildTime).get());        
    }
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          MATERIALS - VARIABLES          *****************************/
    
    @FXML
    private Tab tab_materials;
    
    @FXML
    private TableView<Material> tv_materials;
    
    @FXML
    private TableColumn<Material, String> material_col_color, material_col_manufacturer, material_col_type, material_col_finished, material_col_distributor, material_col_purchaseDate, material_col_comment;
    
    @FXML
    private TableColumn<Material, Integer> material_col_id;
    
    @FXML
    private TableColumn<Material, Double> material_col_remaining, material_col_weight, material_col_price, material_col_shipping, material_col_used, material_col_trash, material_col_soldFor, material_col_profit, material_col_diameter; 
    
    @FXML
    private Label materials_label_shippingPrice_sel, materials_label_paidSoldFor_sel, materials_label_remainingSoldRolls_sel, materials_label_remainingSoldWeight_sel, materials_label_trashWeight_sel, materials_label_avgRollPrice_sel, materials_label_totalWeight_sel, materials_label_shippingPrice, materials_label_paidSoldFor, materials_label_remainingSoldRolls, materials_label_remainingSoldWeight, materials_label_trashWeight, materials_label_avgRollPrice, materials_label_colorsTypes, materials_label_totalWeight, materials_label_Selected, materials_label_Total;
    
    @FXML
    private Button material_btn_new, material_btn_edit;
    
    /*****************************          MATERIALS - METHODS
     * @param materials *****************************/    
    private void calculateMaterialStatistics(ObservableList<Material> materials){
                     
        int total = materials.size(), remainingRolls = 0, soldRolls = 0, colors = 0, types = 0;        
        double shipping = 0, price = 0, paid = 0, profit = 0, remainingMaterial = 0, soldMaterial = 0, trash = 0, avgRollPrice = 0, total_weight = 0;
                
        for (int i = 0; i < materials.size(); i++) {
            
            Material material = materials.get(i);
            
            //remaining and sold rolls/weight callculation
            switch(material.getMaterial_finished().get()){
                case "Yes":
                    soldRolls += 1;                    
                    break;
                default:
                    remainingRolls += 1;                     
            }
            
            
            total_weight += material.getMaterial_weight().get();
            soldMaterial += material.getMaterial_weight().get() - material.getMaterial_remaining().get();
            trash += material.getMaterial_trash().get();                    
            shipping += material.getMaterial_shipping().get();
            price += material.getMaterial_price().get();
            profit += material.getMaterial_profit().get();
           
        }
        
        paid = shipping + price;
        avgRollPrice = paid/(soldRolls + remainingRolls);
        colors = MngApi.performIntegerQuery("SELECT COUNT(ColorID) FROM MaterialColors", user);
        types = MngApi.performIntegerQuery("SELECT COUNT(MaterialTypeID) FROM MaterialTypes", user);

        materials_label_Total.setText(String.format("Total(%d)", total));
        materials_label_shippingPrice.setText(String.format(Locale.US, "%.2f $/%.2f $", shipping, price));
        materials_label_paidSoldFor.setText(String.format(Locale.US, "%.2f $/%.2f $ (%.2f $)", paid, profit, profit - paid));
        materials_label_remainingSoldRolls.setText(String.format("%d/%d", soldRolls, remainingRolls));
        materials_label_totalWeight.setText(String.format("%.2f kg", total_weight/1000));
        materials_label_remainingSoldWeight.setText(String.format(Locale.US, "%.2f / %.2f kg",soldMaterial/1000 ,(total_weight - soldMaterial)/1000));
        materials_label_trashWeight.setText(String.format(Locale.US, "%.2f g", trash));
        materials_label_avgRollPrice.setText(String.format(Locale.US, "%.2f $", avgRollPrice));
        materials_label_colorsTypes.setText(String.format("%d/%d", colors, types));
    }
    
    private void calculateSelectedMaterialStatistics(ObservableList<Material> materials){
                     
        int total = materials.size(), remainingRolls = 0, soldRolls = 0, colors = 0, types = 0;        
        double shipping = 0, price = 0, paid = 0, profit = 0, remainingMaterial = 0, soldMaterial = 0, trash = 0, avgRollPrice = 0, total_weight = 0;
                
        for (int i = 0; i < materials.size(); i++) {
            
            Material material = materials.get(i);
            
            //remaining and sold rolls/weight callculation
            switch(material.getMaterial_finished().get()){
                case "Yes":
                    soldRolls += 1;                    
                    break;
                default:
                    remainingRolls += 1;                     
            }
            
            
            total_weight += material.getMaterial_weight().get();
            soldMaterial += material.getMaterial_weight().get() - material.getMaterial_remaining().get();
            trash += material.getMaterial_trash().get();                    
            shipping += material.getMaterial_shipping().get();
            price += material.getMaterial_price().get();
            profit += material.getMaterial_profit().get();
           
        }
        
        paid = shipping + price;
        avgRollPrice = paid/(soldRolls + remainingRolls);
                
        materials_label_Selected.setText(String.format("Selected(%d)", total));
        materials_label_shippingPrice_sel.setText(String.format(Locale.US, "%.2f $/%.2f $", shipping, price));
        materials_label_paidSoldFor_sel.setText(String.format(Locale.US, "%.2f $/%.2f $ (%.2f $)", paid, profit, profit - paid));
        materials_label_remainingSoldRolls_sel.setText(String.format("%d/%d", soldRolls, remainingRolls));
        materials_label_totalWeight_sel.setText(String.format("%.2f kg", total_weight/1000));
        materials_label_remainingSoldWeight_sel.setText(String.format(Locale.US, "%.2f / %.2f kg",soldMaterial/1000 ,(total_weight - soldMaterial)/1000));
        materials_label_trashWeight_sel.setText(String.format(Locale.US, "%.2f g", trash));
        materials_label_avgRollPrice_sel.setText(String.format(Locale.US, "%.2f $", avgRollPrice));
        
    }
    
    public void refreshMaterialsTable(User user){
        
        //Create list of orders
        ObservableList<Material> materialList = FXCollections.observableArrayList(Material.getMaterials(user));
        
        material_col_color.setCellValueFactory((param) -> {return param.getValue().getMaterial_color();});
        material_col_distributor.setCellValueFactory((param) -> {return param.getValue().getMaterial_distributor();});           
        material_col_finished.setCellValueFactory((param) -> {return param.getValue().getMaterial_finished();});
        material_col_manufacturer.setCellValueFactory((param) -> {return param.getValue().getMaterial_manufacturer();});
        material_col_purchaseDate.setCellValueFactory((param) -> {return param.getValue().getMaterial_purchaseDate();});
        material_col_type.setCellValueFactory((param) -> {return param.getValue().getMaterial_type();});
        material_col_comment.setCellValueFactory((param) -> {return param.getValue().getMaterial_comment();});
        
        material_col_id.setCellValueFactory((param) -> {return param.getValue().getMaterial_id().asObject();});        
        material_col_weight.setCellValueFactory((param) -> {return param.getValue().getMaterial_weight().asObject();});        
        
        material_col_remaining.setCellValueFactory((param) -> {return param.getValue().getMaterial_remaining().asObject();});
        material_col_diameter.setCellValueFactory((param) -> {return param.getValue().getMaterial_diameter().asObject();});
        material_col_price.setCellValueFactory((param) -> {return param.getValue().getMaterial_price().asObject();});
        material_col_shipping.setCellValueFactory((param) -> {return param.getValue().getMaterial_shipping().asObject();});
        material_col_profit.setCellValueFactory((param) -> {return param.getValue().getMaterial_profit().asObject();});        
        material_col_soldFor.setCellValueFactory((param) -> {return param.getValue().getMaterial_soldFor().asObject();});
        material_col_trash.setCellValueFactory((param) -> {return param.getValue().getMaterial_trash().asObject();});
        material_col_used.setCellValueFactory((param) -> {return param.getValue().getMaterial_used().asObject();});
        
        
        //Centering content
        material_col_color.setStyle("-fx-alignment: CENTER;");
        material_col_distributor.setStyle("-fx-alignment: CENTER;");
        material_col_finished.setStyle("-fx-alignment: CENTER;");
        material_col_manufacturer.setStyle("-fx-alignment: CENTER;");
        material_col_purchaseDate.setStyle("-fx-alignment: CENTER;");
        material_col_type.setStyle("-fx-alignment: CENTER;");
        
        material_col_id.setStyle("-fx-alignment: CENTER;");
        
        material_col_remaining.setStyle("-fx-alignment: CENTER;");
        material_col_price.setStyle("-fx-alignment: CENTER;");
        material_col_profit.setStyle("-fx-alignment: CENTER;");
        material_col_shipping.setStyle("-fx-alignment: CENTER;");        
        material_col_soldFor.setStyle("-fx-alignment: CENTER;");
        material_col_trash.setStyle("-fx-alignment: CENTER;");
        material_col_used.setStyle("-fx-alignment: CENTER;");        
        material_col_weight.setStyle("-fx-alignment: CENTER;");        
        material_col_diameter.setStyle("-fx-alignment: CENTER;");
        
        tv_materials.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv_materials.setItems(materialList);        
    }    
    
    // Create the service
    private final Service<Void> service_refreshMaterials = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshCostsTable = new Task<Void>() {            
                @Override
                public Void call() throws Exception {
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);
                        refreshMaterialsTable(user);
                        calculateMaterialStatistics(tv_materials.getItems());
                    });
                    
                    return null;
                }        
            };
        return task_refreshCostsTable;
        }
    };

    public Service<Void> getService_refreshMaterials() {
        return service_refreshMaterials;
    }    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          COSTS - VARIALES          *****************************/
    
    @FXML
    private Tab tab_costs;
    
    @FXML
    private TableView<Cost> tv_costs;
    
    @FXML
    private TableColumn<Cost, Integer> cost_col_id, cost_col_quantity, cost_col_printerID;
    
    @FXML
    private TableColumn<Cost, String> cost_col_name, cost_col_purchaseDate, cost_col_comment,cost_col_printer;
    
    @FXML
    private TableColumn<Cost, Double> cost_col_price, cost_col_shipping;
    
    @FXML
    private Button cost_btn_newCost, cost_btn_refresh;
    
    @FXML
    private Label costs_label_totalPaid, costs_label_total, costs_label_price, costs_label_shipping, costs_label_quantity, costs_label_selected, costs_label_price_sel, costs_label_shipping_sel, costs_label_quantity_sel, costs_label_totalPaid_sel;
    
    /*****************************          COSTS - METHODS         *****************************/
    
    private void calculateCostsStatistics(){
        
        double price = 0, shipping = 0;
        int total = 0, quantity = 0;

        for (int i = 0; i < tv_costs.getItems().size(); i++) {
                        
            Cost cost = tv_costs.getItems().get(i);
            
            price += cost.getCost_price().get();
            shipping += cost.getCost_shipping().get();
            quantity += cost.getCost_quantity().get();
            
        }
        
        costs_label_total.setText(String.format("Total(%d)", total));
        costs_label_price.setText(String.format(Locale.US, "%.2f $", price));
        costs_label_shipping.setText(String.format(Locale.US, "%.2f $", shipping));
        costs_label_totalPaid.setText(String.format(Locale.US, "%.2f $", price + shipping));
        costs_label_quantity.setText(quantity + "");
    }
    
    private void calculateCostsStatistics(ObservableList<Cost> costs){
        
        double price = 0, shipping = 0;
        int total = 0, quantity = 0;

        for (int i = 0; i < costs.size(); i++) {
                        
            Cost cost = costs.get(i);
            
            price += cost.getCost_price().get();
            shipping += cost.getCost_shipping().get();
            quantity += cost.getCost_quantity().get();
            
        }
        
        costs_label_selected.setText(String.format("Total(%d)", total));
        costs_label_price_sel.setText(String.format(Locale.US, "%.2f $", price));
        costs_label_shipping_sel.setText(String.format(Locale.US, "%.2f $", shipping));
        costs_label_totalPaid_sel.setText(String.format(Locale.US, "%.2f $", price + shipping));
        costs_label_quantity_sel.setText(quantity + "");
    }
    
    public void refreshCostsTable(User user){
        
        //Create list of orders
        ObservableList<Cost> costsList = FXCollections.observableArrayList(Cost.getCosts(user));
        
        cost_col_comment.setCellValueFactory((param) -> {return param.getValue().getCost_comment();});
        cost_col_name.setCellValueFactory((param) -> {return param.getValue().getCost_name();});           
        cost_col_purchaseDate.setCellValueFactory((param) -> {return param.getValue().getCost_purchaseDate();});
        cost_col_printer.setCellValueFactory((param) -> {return param.getValue().getCost_printer();});
        
        cost_col_id.setCellValueFactory((param) -> {return param.getValue().getCost_id().asObject();});        
        cost_col_quantity.setCellValueFactory((param) -> {return param.getValue().getCost_quantity().asObject();});
        cost_col_printerID.setCellValueFactory((param) -> {return param.getValue().getCost_printerID().asObject();});
        
        cost_col_price.setCellValueFactory((param) -> {return param.getValue().getCost_price().asObject();});
        cost_col_shipping.setCellValueFactory((param) -> {return param.getValue().getCost_shipping().asObject();});
        
        //Centering content
        cost_col_comment.setStyle("-fx-alignment: CENTER;");
        cost_col_name.setStyle("-fx-alignment: CENTER;");
        cost_col_purchaseDate.setStyle("-fx-alignment: CENTER;");
        cost_col_printer.setStyle("-fx-alignment: CENTER;");
        
        cost_col_id.setStyle("-fx-alignment: CENTER;");
        cost_col_quantity.setStyle("-fx-alignment: CENTER;");
        cost_col_printerID.setStyle("-fx-alignment: CENTER;");
        
        cost_col_price.setStyle("-fx-alignment: CENTER;");
        cost_col_shipping.setStyle("-fx-alignment: CENTER;");
        
        tv_costs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv_costs.setItems(costsList);
        
    }
    
    // Create the service
    private final Service<Void> service_refreshCosts = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshCostsTable = new Task<Void>() {            
                @Override
                public Void call() throws Exception {
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);                 
                        refreshCostsTable(user);
                        calculateCostsStatistics();
                    });
                                       
                    return null;
                }        
            };
        return task_refreshCostsTable;
        }
    };
    
    public Service<Void> getService_refreshCosts() {
        return service_refreshCosts;
    }
    
    
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
                        runService(service_refreshOrders);                  
                }
            }
        });
        
        tv_orders.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    order_btn_edit.fire();
                }
            });
            return row;
        });
            
        tv_orders.getSelectionModel().getSelectedItems().addListener((Change<? extends Order> c) -> {        
            calculateSelectedOrdersStatistics(tv_orders.getSelectionModel().getSelectedItems());
        });
        
        order_btn_refresh.setOnAction((event) -> {            
            runService(service_refreshOrders);            
        });
        
        order_btn_new.setOnAction((event) -> {
            
            try {            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Orders/NewOrder.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewOrderController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Order");
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();            
            
            stage.show();
            
            ctrl.setUser(user);            
            ctrl.setMainController(this);
            ctrl.setNewOrderFields();            
            
        }catch (IOException e){
            
        }
            
        });
        
        order_btn_edit.setOnAction((event) -> {
            
            try {            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Orders/NewOrder.fxml"));            
                Parent root1 = fxmlLoader.load();
                NewOrderController ctrl = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Edit Order");
           
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.centerOnScreen();            
                
                stage.show();
            
                ctrl.setUser(user);            
                ctrl.setMainController(this);
                ctrl.setUpdateOrderFields(tv_orders.getSelectionModel().getSelectedItems());            
            
            } catch (IOException e){
                e.printStackTrace();
            } catch (NullPointerException e){
                label_order_info.setText("Info: Select one order");
                label_order_info.setTextFill(Color.web("#ff0000"));
            }            
        });
        
        order_btn_delete.setOnAction((event) -> {            
            deleteOrder(tv_orders.getSelectionModel().getSelectedItem());            
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
                    runService(service_refreshCustomers);
                }
            }
        });
    
    customer_btn_refresh.setOnAction((event) -> {        
        runService(service_refreshCustomers);        
    });
    
    //in progress
    customer_btn_showDetails.setOnAction((event) -> {
        showCustomerDetails(tv_customers.getSelectionModel().getSelectedItems());
    });
        
    tv_customers.setRowFactory( tv -> {
        TableRow<Customer> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                customer_btn_edit.fire();
            }
        });
    return row;
    });

    tv_customers.getSelectionModel().getSelectedItems().addListener((Change<? extends Customer> c) -> {        
        calculateSelectedCustomersStatistics(tv_customers.getSelectionModel().getSelectedItems());        
    });
    
    customer_btn_new.setOnAction((event) -> {
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/customers/NewCustomer.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewCustomerController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Customer");

            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            //passing credentials to main controller
            ctrl.setUser(user);
            ctrl.setMainController(this);
            ctrl.setCustomer_label_id_value(MngApi.getCurrentAutoIncrementValue(user, "Customers"));
            ctrl.setComboBoxes();
            stage.show();
            MngApi.setActualDate(ctrl.getCustomer_datePicker_dateCreated());
            //stage.setAlwaysOnTop(true);
            
        }catch (IOException e){
            
        }
    });//end new cost button  setOnAction
    
    customer_btn_edit.setOnAction((event) -> {
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/customers/NewCustomer.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewCustomerController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Customer");

            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            //passing credentials to main controller
            ctrl.setUser(user);
            ctrl.setMainController(this);            
            ctrl.setUpdateCustomerFields(tv_customers.getSelectionModel().getSelectedItems());
            stage.show();
                        
        }catch (IOException e){
            
        }        
    });
    
    /*****************************          INITIALIZE OBJECTS TAB          *****************************/
    
    tv_objects.getSelectionModel().getSelectedItems().addListener((Change<? extends classes.Object> c) -> {        
            calculateSelectedObjectsStatistics(tv_objects.getSelectionModel().getSelectedItems());
        });
    
    tab_objects.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab_objects.isSelected()) {
                    runService(service_refreshObjects);
                }
            }
        });
    
    object_btn_new.setOnAction((event) -> {
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/objects/NewObject.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewObjectController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Object");
            stage.setMinHeight(400);
            stage.setMinWidth(440);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            
            //passing credentials to main controller
            ctrl.setUser(user);
            ctrl.setMainController(this);
            ctrl.setObject_label_id_value(MngApi.getCurrentAutoIncrementValue(user, "Objects"));
            stage.show();  
            //stage.setAlwaysOnTop(true);
            
        }catch (IOException e){
            
        }
    });//end new object button  setOnAction
    
    object_btn_edit.setOnAction((event) -> {
        
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/objects/NewObject.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewObjectController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Object");
            stage.setMinHeight(400);
            stage.setMinWidth(440);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            classes.Object obj = tv_objects.getSelectionModel().getSelectedItems().get(0);
            
            //passing credentials to main controller
            ctrl.setUser(user);
            ctrl.setMainController(this);
            ctrl.setObject_label_id_value(obj.getObject_id().get());
            ctrl.setEditFields(obj);
            stage.show();  
            //stage.setAlwaysOnTop(true);
            
        }catch (IOException e){
            
        }
        
    });
    
    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          INITIALIZE MATERIALS TAB          *****************************/
    
    tab_materials.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab_materials.isSelected()) {
                    runService(service_refreshMaterials);
                }
            }
        });
    
    tv_materials.setRowFactory(tv -> {
            TableRow<Material> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    material_btn_edit.fire();
                }
            });
            return row;
        });
    
    tv_materials.getSelectionModel().getSelectedItems().addListener((Change<? extends Material> c) -> {        
            calculateSelectedMaterialStatistics(tv_materials.getSelectionModel().getSelectedItems());
        });
    
    material_btn_new.setOnAction((event) -> {        
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/materials/NewMaterial.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewMaterialController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("New Material");
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.setMinHeight(440);
            //stage.setMinWidth(400);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            
            //passing credentials to main controller
            ctrl.setUser(user);
            ctrl.setMainController(this);
            ctrl.setComboBoxes();
            ctrl.setMaterial_label_id_value(MngApi.getCurrentAutoIncrementValue(user, "Materials"));
            MngApi.setActualDate(ctrl.getMaterial_datePicker_purchaseDate());
            
            stage.show();                        
            
        }catch (IOException e){

        }        
    });
    
    material_btn_edit.setOnAction((event) -> {
        
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/materials/NewMaterial.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewMaterialController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("Edit Material");
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.setMinHeight(440);
            //stage.setMinWidth(400);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            
            //passing credentials to main controller
            ctrl.setUser(user);
            ctrl.setMainController(this);
            ctrl.setUpdateMaterialFields(tv_materials.getSelectionModel().getSelectedItem());
            
            stage.show();                        
            
        }catch (IOException e){

        }  
        
    });
    
    /*
    *
    *
    *
    *
    *    
    */
     /*****************************          INITIALIZE COSTS TAB          *****************************/
    
    tab_costs.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab_costs.isSelected()) {
                    runService(service_refreshCosts);
                }
            }
        });
    
    tv_costs.getSelectionModel().getSelectedItems().addListener((Change<? extends Cost> c) -> {        
            calculateCostsStatistics(tv_costs.getSelectionModel().getSelectedItems());
        });
    
    cost_btn_newCost.setOnAction((event) -> {
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/costs/NewCost.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewCostController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Cost");
            stage.setMinHeight(440);
            stage.setMinWidth(400);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            
            //passing credentials to main controller
            ctrl.setUser(user);
            ctrl.setMainController(this);
            ctrl.setCost_label_id_value(MngApi.getCurrentAutoIncrementValue(user, "Costs"));
            stage.show();                        
            
        }catch (IOException e){
            
        }
    });//end new cost button  setOnAction
    
    cost_btn_refresh.setOnAction((event) -> {
        //refreshCostsTable(user);
        runService(service_refreshCosts);
    });
    
    /*
    *
    *
    *
    *
    *    
    */
    
    }//end initialize    
        
}//end MainController
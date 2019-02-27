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
import classes.Printer;
import classes.SimpleTableObject;
import com.zaxxer.hikari.HikariDataSource;
import controllers.customers.NewCustomerController;
import controllers.materials.NewMaterialController;
import controllers.objects.NewObjectController;
import controllers.orders.NewOrderController;
import controllers.printers.NewPrinterController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
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
        
    //private User ds = null;    
    private HikariDataSource ds;
    
    @FXML
    private TabPane tp_main;
    
    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private Label label_main_info;
    
    @FXML
    private List<SimpleTableObject> commonMaterialProperties, commonCustomerProperties;
           
    
     // Create the service
    private final Service<Void> service_refreshAll = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshPrintersTable = new Task<Void>() {            
                @Override
                public Void call() throws Exception {
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);                 
                        loadAll();                        
                    });
                                       
                    return null;
                }        
            };
        return task_refreshPrintersTable;
        }
    };
    
    /*****************************          GENERAL - METHODS *****************************/
    
    public void loadAll(){        
        runService(service_refreshCustomers);
        runService(service_refreshCosts);
        runService(service_refreshObjects);        
        runService(service_refreshMaterials);
        runService(service_refreshOrders);
        runService(service_refreshPrinters);
        runService(service_refreshStatistics);
    }
    
    //gets list of common material properties (loaded at the begining) based on types
    /*
    +----------------+------------------+
    | PropertyTypeID | PropertyTypeName |
    +----------------+------------------+
    |              1 | Type             |
    |              2 | Color            |
    |              3 | Manufacturer     |
    |              4 | Seller           |
    |              5 | Diameter         |
    |              6 | Weight           |
    |              7 | Printer Type     |
    +----------------+------------------+
    */
    public List<SimpleTableObject> getListOfMaterialProperties(int type){
        
        List<SimpleTableObject> properties = new ArrayList<>();
        
        for (int i = 0; i < commonMaterialProperties.size(); i++) {
            
            SimpleTableObject obj = commonMaterialProperties.get(i);
            
            if(obj.getProperty_type_id().get() == type)properties.add(obj);
            
        }
        
        return properties;
    }
    
    /*
    +----------------+------------------+
    | PropertyTypeID | PropertyTypeName |
    +----------------+------------------+
    |              1 | Company          |
    |              2 | Country          |
    +----------------+------------------+
    */
    
    public List<SimpleTableObject> getCommonCustomerPropertiesByType(int type){
        
        List<SimpleTableObject> properties = new ArrayList<>();
        
        for (int i = 0; i < commonCustomerProperties.size(); i++) {
            
            SimpleTableObject obj = commonCustomerProperties.get(i);
            
            if(obj.getProperty_type_id().get() == type)properties.add(obj);
            
            
        }
        
        return properties;
    }
    
    public void setDataSource(HikariDataSource ds){
        this.ds = ds;        
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

    public Label getMain_label_info() {
        return label_main_info;
    }

    public void setMain_label_info(Label main_label_info) {
        this.label_main_info = main_label_info;
    }

    public Service<Void> getService_refreshAll() {
        return service_refreshAll;
    }

    public List<SimpleTableObject> getCommonMaterialProperties() {
        return commonMaterialProperties;
    }

    public void setCommonMaterialProperties(List<SimpleTableObject> commonMaterialProperties) {
        this.commonMaterialProperties = commonMaterialProperties;
    }
    
    public List<SimpleTableObject> getCommonCustomerProperties() {
        return commonCustomerProperties;
    }

    public void setCommonCustomerProperties(List<SimpleTableObject> commonCustomerProperties) {
        this.commonCustomerProperties = commonCustomerProperties;
    }

    public TableView<Order> getTv_orders() {
        return tv_orders;
    }

    public TableView<Customer> getTv_customers() {
        return tv_customers;
    }

    public TableView<Object> getTv_objects() {
        return tv_objects;
    }

    public TableView<Material> getTv_materials() {
        return tv_materials;
    }

    public TableView<Cost> getTv_costs() {
        return tv_costs;
    }

    public TableView<Printer> getTv_printers() {
        return tv_printers;
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
    private Label label_order_SoldOrders, label_order_SoldCostPrice, label_order_OrderProfit, label_order_NotSoldOrders, label_order_NotSoldCostPrice, label_order_NotSoldOrderProfit;
    
    @FXML
    private Label label_order_totalOrders, label_order_TotalCostPrice, label_order_TotalPricePerHour, label_order_TotalWeight, label_order_TotalBuildTime, label_order_TotalItemsPrinted;
            
    @FXML
    private Label label_order_SelectedCostsPrice, label_order_SelectedWeight, label_order_SelectedItemsPrinted, label_order_SelectedPricePerHour, label_order_SelectedBuildTime, label_order_SelectedOrders;
    
    /*****************************          ORDERS TAB - METHODS        *****************************/    
    //calculates statics of all orders
    private void calculateOrderStatistics(){
        
        int sold_orders = 0, notSold_orders = 0, total_orders, total_itemsSold = 0, total_buildTime = 0;        
        double sold_price = 0, sold_costs = 0, notSold_price = 0, notSold_costs = 0, total_costs = 0, total_price = 0, total_weight = 0, total_supportWeight = 0;
        
                
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
        label_order_TotalPricePerHour.setText(String.format(Locale.US, "%.2f $/h", total_price/total_buildTime*60));
        if (total_weight >= 1000){
            label_order_TotalWeight.setText(String.format(Locale.US, "%.2f kg/%.2f kg", total_weight/1000, total_supportWeight/1000));
        } else {
            label_order_TotalWeight.setText(String.format(Locale.US, "%.2f g/%.2f g", total_weight, total_supportWeight));
        }        
        label_order_TotalBuildTime.setText(String.format(Locale.US, "%s", MngApi.convertToFormattedTime(total_buildTime).get()));
        label_order_TotalItemsPrinted.setText(String.valueOf(total_itemsSold));        
    }
    
    private void calculateOrderStatistics(ObservableList<Order> selectedOrders){
               
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
        label_order_SelectedPricePerHour.setText(String.format(Locale.US, "%.2f $/h", selected_price/selected_buildTime*60));
        if (selected_weight >= 1000) {
            label_order_SelectedWeight.setText(String.format(Locale.US, "%.2f kg/%.2f kg", selected_weight/1000, selected_supportWeight/1000));
        } else {
            label_order_SelectedWeight.setText(String.format(Locale.US, "%.2f g/%.2f g", selected_weight, selected_supportWeight));
        }
        label_order_SelectedBuildTime.setText(String.format(Locale.US, "%s", MngApi.convertToFormattedTime(selected_buildTime).get()));
        label_order_SelectedItemsPrinted.setText(String.valueOf(selected_itemsSold));        
        
    }
    
    public void refreshOrdersTable(HikariDataSource ds) {
        
        //Create list of orders
        ObservableList<Order> orderList = FXCollections.observableArrayList(Order.getOrders(ds));
        
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
                        refreshOrdersTable(ds);
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
    private Button customer_btn_new,customer_btn_refresh, customer_btn_showDetails, customer_btn_edit, customer_btn_delete;
    
    @FXML
    private Label label_customer_selected, label_customers_custCount, label_customers_selectedOrders, label_customers_selectedItems, label_customers_selectedPrice, label_customers_selectedCosts, label_customers_selectedWeight, label_customers_selectedSupportWeight, label_customers_selectedBuildTime, label_customers_selectedPricePerHour;
    
    
    /*****************************          CUSTOMERS - METHODS         *****************************/
    
    private void calculateCustomerStatistics(ObservableList<Customer> selectedCustomers){
        
        ObservableList<Order> allOrders = tv_orders.getItems();
        
        int orders = 0, items = 0, time = 0;
        double price = 0.0, costs = 0.0, weight = 0.0, supports = 0.0, perHour;
        
        label_customer_selected.setText(String.format("Selected(%d)", selectedCustomers.size()));
        
        try {
            for (int i = 0; i < selectedCustomers.size(); i++) {
                
                int customer_id = selectedCustomers.get(i).getCustomer_id().get();
                
                for (int j = 0; j < allOrders.size(); j++) {
                    Order order = allOrders.get(j);
                    if (order.getOrder_customerID().get() == customer_id){
                        
                        orders++;
                        items += order.getOrder_quantity().get();
                        price += order.getOrder_price().get();
                        costs += order.getOrder_costs().get();
                        weight += order.getOrder_weighht().get();
                        supports += order.getOrder_support_weight().get();
                        time += order.getOrder_buildTime().get();
                        
                    }  
                }
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
            label_customers_selectedSupportWeight.setText(String.format(Locale.US, "%.2f g", supports));            
            label_customers_selectedBuildTime.setText("0");        
            label_customers_selectedPricePerHour.setText(String.format(Locale.US, "0 $/h"));
            
        }
        
    }
    
    public void refreshCustomersTable(HikariDataSource ds) {
        //Create list of orders
        ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getCustomers(commonCustomerProperties,ds));
        
        
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
                        refreshCustomersTable(ds);
                        label_customers_custCount.setText(tv_customers.getItems().size() + "");
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
    private Button object_btn_new, object_btn_edit, object_btn_refresh, object_btn_delete;
    
    @FXML
    private Label object_label_objCount,object_label_Selected,object_label_TimesPrinted,object_label_PriceCosts,object_label_PricePerHour,object_label_BuildTime, object_label_ItemSupportWeight;
    
    /*****************************          OBJECTS - METHODS         *****************************/
    
    public void refreshObjectsTable(HikariDataSource ds){
        
        //Create list of orders
        ObservableList<Object> objectList = FXCollections.observableArrayList(Object.getObjects(ds));
        
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
                        refreshObjectsTable(ds);
                        object_label_objCount.setText(tv_objects.getItems().size() + "");
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
    
    private void calculatedObjectStatistics(ObservableList<classes.Object> selectedObjects){
        
        int timesPrinted = 0, buildTime = 0;
        double price = 0, costs = 0, weight = 0, supportWeight = 0;
        
        for (int i = 0; i < selectedObjects.size(); i++) {
            
            classes.Object current = selectedObjects.get(i);
            
            timesPrinted += current.getObject_SoldCount().get();
            buildTime += current.getObject_buildTime().get()*current.getObject_SoldCount().get();
            price += current.getObject_soldPrice().get();
            costs += current.getObject_costs().get();
            weight += current.getObject_weight().get();
            supportWeight += current.getObject_supportWeight().get();
                        
        }
        
        object_label_Selected.setText(String.format("Selected(%d)", selectedObjects.size()));
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
    private Label materials_label_shippingPrice_sel, materials_label_paidSoldFor_sel, materials_label_remainingSoldRolls_sel, materials_label_remainingSoldWeight_sel, materials_label_trashWeight_sel, materials_label_avgRollPrice_sel, materials_label_totalWeight_sel, materials_label_shippingPrice, materials_label_paidSoldFor, materials_label_paidSoldForDifference, materials_label_remainingSoldRolls, materials_label_remainingSoldWeight, materials_label_trashWeight, materials_label_avgRollPrice, materials_label_colorsTypes, materials_label_totalWeight, materials_label_Selected, materials_label_Total,materials_label_colorsTypes_sel;
    
    @FXML
    private Button material_btn_new, material_btn_edit, material_btn_refresh, material_btn_delete;
    
    /*****************************          MATERIALS - METHODS *****************************/    
    
    
    private void calculateMaterialStatistics(){
                     
        ObservableList<Material> materials = tv_materials.getItems();
        
        int total = materials.size(), remainingRolls = 0, soldRolls = 0, colors = 0, types = 0;        
        double shipping = 0, price = 0, paid = 0, profit = 0, soldMaterial = 0, trash = 0, avgRollPrice = 0, total_weight = 0;
                
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
        colors = SimpleTableObject.getNumberOfProperties(commonMaterialProperties, 2);
        types = SimpleTableObject.getNumberOfProperties(commonMaterialProperties, 1);

        materials_label_Total.setText(String.format("Total(%d)", total));
        materials_label_shippingPrice.setText(String.format(Locale.US, "%.2f $/%.2f $", shipping, price));
        materials_label_paidSoldFor.setText(String.format(Locale.US, "%.2f $/%.2f $", paid, profit));
        materials_label_paidSoldForDifference.setText(String.format(Locale.US, "%.2f $", profit - paid));
        materials_label_remainingSoldRolls.setText(String.format("%d/%d", soldRolls, remainingRolls));
        materials_label_totalWeight.setText(String.format("%.2f kg", total_weight/1000));
        materials_label_remainingSoldWeight.setText(String.format(Locale.US, "%.2f / %.2f kg",soldMaterial/1000 ,(total_weight - soldMaterial)/1000));
        materials_label_trashWeight.setText(String.format(Locale.US, "%.2f g", trash));
        materials_label_avgRollPrice.setText(String.format(Locale.US, "%.2f $", avgRollPrice));
        materials_label_colorsTypes.setText(String.format("%d/%d", colors, types));
    }
    
    private void calculateMaterialStatistics(ObservableList<Material> materials){
                     
        int total = materials.size(), remainingRolls = 0, soldRolls = 0, colors = 0, types = 0;        
        double shipping = 0, price = 0, paid, profit = 0, soldMaterial = 0, trash = 0, avgRollPrice, total_weight = 0;
                
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
        materials_label_colorsTypes_sel.setText(String.format("%d/%d", colors, types));
          
    }
    
    public void refreshMaterialsTable(HikariDataSource ds){
        
        //Create list of orders
        ObservableList<Material> materialList = FXCollections.observableArrayList(Material.getMaterials(commonMaterialProperties,ds));
        
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
                        refreshMaterialsTable(ds);
                        calculateMaterialStatistics();
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
    /*****************************          COSTS - VARIABLES          *****************************/
    
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
    private Button cost_btn_newCost, cost_btn_editCost, cost_btn_refresh, cost_btn_delete;
    
    @FXML
    private Label costs_label_totalPaid, costs_label_total, costs_label_price, costs_label_shipping, costs_label_quantity, costs_label_selected, costs_label_price_sel, costs_label_shipping_sel, costs_label_quantity_sel, costs_label_totalPaid_sel;
    
    /*****************************          COSTS - METHODS         *****************************/
    
    private void calculateCostsStatistics(){
        
        double price = 0, shipping = 0;
        int total = tv_costs.getItems().size(), quantity = 0;

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
        int total = costs.size(), quantity = 0;

        for (int i = 0; i < costs.size(); i++) {
                        
            Cost cost = costs.get(i);
            
            price += cost.getCost_price().get();
            shipping += cost.getCost_shipping().get();
            quantity += cost.getCost_quantity().get();
            
        }
        
        costs_label_selected.setText(String.format("Selected(%d)", total));
        costs_label_price_sel.setText(String.format(Locale.US, "%.2f $", price));
        costs_label_shipping_sel.setText(String.format(Locale.US, "%.2f $", shipping));
        costs_label_totalPaid_sel.setText(String.format(Locale.US, "%.2f $", price + shipping));
        costs_label_quantity_sel.setText(quantity + "");
    }
    
    public void refreshCostsTable(HikariDataSource ds){
        
        //Create list of orders
        ObservableList<Cost> costsList = FXCollections.observableArrayList(Cost.getCosts(ds));
        
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
                        refreshCostsTable(ds);
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
    /*****************************          PRINTERS - VARIABLES          *****************************/
    
    @FXML
    private Tab tab_printers;
    
    @FXML
    private TableView<Printer> tv_printers;
    
    @FXML
    private TableColumn<Printer, Integer> printer_col_id, pinter_col_itemsSold;
    
    @FXML
    private TableColumn<Printer, String> printer_col_name, printer_col_purchaseDate, printer_col_type, printer_col_comment;
    
    @FXML
    private TableColumn<Printer, Double> printer_col_price, printer_col_shipping, printer_col_incomes, printer_col_expenses, printer_col_overallIncome, printer_col_duty, printer_col_tax;
    
    @FXML
    private Button printer_btn_new, printer_btn_edit, printer_btn_delete, printer_btn_refresh;
    
    @FXML
    private Label printer_label_total, printer_label_priceShipping, printer_label_sum, printer_label_expenses, printer_label_totalPaid, printer_label_itemsSold, printer_label_incomes, printer_label_difference, printer_label_dutyTax;
    @FXML
    private Label printer_label_total_sel, printer_label_priceShipping_sel, printer_label_sum_sel, printer_label_expenses_sel, printer_label_totalPaid_sel, printer_label_itemsSold_sel, printer_label_incomes_sel, printer_label_difference_sel, printer_label_dutyTax_sel;
    
    
    /*****************************          PRINTERS - METHODS         *****************************/
    
    private void calculatePrinterStatistics(){
        
        ObservableList<Printer> printers = tv_printers.getItems();
        
        int items_sold = 0, total = printers.size();
        double price = 0, shipping = 0, duty = 0, tax = 0, expenses = 0, incomes = 0;
        double sum, totalPaid, difference;
        
        for (int i = 0; i < printers.size(); i++) {
            
           Printer printer = printers.get(i);
           
           items_sold += printer.getPrinter_itemsSold().get();
           price += printer.getPrinter_price().get();
           shipping += printer.getPrinter_shipping().get();
           duty += printer.getPrinter_duty().get();
           tax += printer.getPrinter_tax().get();
           expenses += printer.getPrinter_expenses().get();
           incomes += printer.getPrinter_incomes().get();
            
        }
        
        sum = price + shipping + duty + tax;
        totalPaid = sum + expenses;
        difference = totalPaid - incomes;
        
        printer_label_total.setText(String.format("Total(%d)", total));
        printer_label_priceShipping.setText(String.format(Locale.US, "%.2f $/%.2f $", price, shipping));
        printer_label_dutyTax.setText(String.format(Locale.US, "%.2f $/%.2f $", duty, tax));
        printer_label_sum.setText(String.format(Locale.US, "%.2f $", sum));
        printer_label_expenses.setText(String.format(Locale.US, "%.2f $", expenses));
        printer_label_totalPaid.setText(String.format(Locale.US, "%.2f $", totalPaid));
        printer_label_itemsSold.setText("" + items_sold);
        printer_label_incomes.setText(String.format(Locale.US, "%.2f $", incomes));
        printer_label_difference.setText(String.format(Locale.US, "%.2f $", difference));
        
    }
    
    private void calculatePrinterStatistics(ObservableList<Printer> printers){
                
        int items_sold = 0, total = printers.size();
        double price = 0, shipping = 0, duty = 0, tax = 0, expenses = 0, incomes = 0;
        double sum, totalPaid, difference;
        
        for (int i = 0; i < printers.size(); i++) {
            
           Printer printer = printers.get(i);
           
           items_sold += printer.getPrinter_itemsSold().get();
           price += printer.getPrinter_price().get();
           shipping += printer.getPrinter_shipping().get();
           duty += printer.getPrinter_duty().get();
           tax += printer.getPrinter_tax().get();
           expenses += printer.getPrinter_expenses().get();
           incomes += printer.getPrinter_incomes().get();
            
        }
        
        sum = price + shipping + duty + tax;
        totalPaid = sum + expenses;
        difference = totalPaid - incomes;
        
        printer_label_total_sel.setText(String.format("Selected(%d)", total));
        printer_label_priceShipping_sel.setText(String.format(Locale.US, "%.2f $/%.2f $", price, shipping));
        printer_label_dutyTax_sel.setText(String.format(Locale.US, "%.2f $/%.2f $", duty, tax));
        printer_label_sum_sel.setText(String.format(Locale.US, "%.2f $", sum));
        printer_label_expenses_sel.setText(String.format(Locale.US, "%.2f $", expenses));
        printer_label_totalPaid_sel.setText(String.format(Locale.US, "%.2f $", totalPaid));
        printer_label_itemsSold_sel.setText("" + items_sold);
        printer_label_incomes_sel.setText(String.format(Locale.US, "%.2f $", incomes));
        printer_label_difference_sel.setText(String.format(Locale.US, "%.2f $", difference));
        
    }
    
    public void refreshPrintersTable(HikariDataSource ds){
        
        //Create list of orders
        ObservableList<Printer> printersList = FXCollections.observableArrayList(Printer.getPrintersLong(ds));
        
        printer_col_name.setCellValueFactory((param) -> {return param.getValue().getPrinter_name();});
        printer_col_purchaseDate.setCellValueFactory((param) -> {return param.getValue().getPrinter_purchaseDate();});           
        printer_col_type.setCellValueFactory((param) -> {return param.getValue().getPrinter_type();});
        printer_col_comment.setCellValueFactory((param) -> {return param.getValue().getPrinter_comment();});
        
        printer_col_id.setCellValueFactory((param) -> {return param.getValue().getPrinter_id().asObject();});        
        pinter_col_itemsSold.setCellValueFactory((param) -> {return param.getValue().getPrinter_itemsSold().asObject();});
                
        printer_col_price.setCellValueFactory((param) -> {return param.getValue().getPrinter_price().asObject();});
        printer_col_shipping.setCellValueFactory((param) -> {return param.getValue().getPrinter_shipping().asObject();});
        printer_col_incomes.setCellValueFactory((param) -> {return param.getValue().getPrinter_incomes().asObject();});
        printer_col_expenses.setCellValueFactory((param) -> {return param.getValue().getPrinter_expenses().asObject();});
        printer_col_overallIncome.setCellValueFactory((param) -> {return param.getValue().getPrinter_overallIncome().asObject();});
        printer_col_duty.setCellValueFactory((param) -> {return param.getValue().getPrinter_duty().asObject();});
        printer_col_tax.setCellValueFactory((param) -> {return param.getValue().getPrinter_tax().asObject();});
        
        
        //Centering content
        printer_col_name.setStyle("-fx-alignment: CENTER;");
        printer_col_purchaseDate.setStyle("-fx-alignment: CENTER;");
        printer_col_type.setStyle("-fx-alignment: CENTER;");
        printer_col_comment.setStyle("-fx-alignment: CENTER;");
        
        printer_col_id.setStyle("-fx-alignment: CENTER;");
        pinter_col_itemsSold.setStyle("-fx-alignment: CENTER;");
        
        printer_col_price.setStyle("-fx-alignment: CENTER;");
        printer_col_shipping.setStyle("-fx-alignment: CENTER;");
        printer_col_incomes.setStyle("-fx-alignment: CENTER;");
        printer_col_expenses.setStyle("-fx-alignment: CENTER;");
        printer_col_overallIncome.setStyle("-fx-alignment: CENTER;");        
        printer_col_duty.setStyle("-fx-alignment: CENTER;");
        printer_col_tax.setStyle("-fx-alignment: CENTER;");
        
        tv_printers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv_printers.setItems(printersList);
        
    }
    
    // Create the service
    private final Service<Void> service_refreshPrinters = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshPrintersTable = new Task<Void>() {            
                @Override
                public Void call() throws Exception {
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);                 
                        refreshPrintersTable(ds);
                        calculatePrinterStatistics();
                    });
                                       
                    return null;
                }        
            };
        return task_refreshPrintersTable;
        }
    };
    
    public Service<Void> getService_refreshPrinters() {
        return service_refreshPrinters;
    }
       
    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          STATISTICS - VARIABLES          *****************************/
    
    @FXML
    private Tab tab_statistics;
    
    @FXML
    private Label statistics_label_order_totalOrders,statistics_label_order_TotalSoldNotSold,statistics_label_order_TotalBuildTime,statistics_label_order_TotalPricePerHour,statistics_label_order_TotalWeight,statistics_label_order_TotalItemsPrinted,statistics_label_order_TotalCostPrice;
    
    @FXML
    private Label statistics_materials_label_remainingSoldRolls,statistics_materials_label_avgRollPrice,statistics_materials_label_trashWeight,statistics_materials_label_Total,statistics_materials_label_totalWeight,statistics_materials_label_paidSoldFor,statistics_materials_label_colorsTypes,statistics_materials_label_remainingSoldWeight,statistics_materials_label_shippingPrice, statistics_materials_label_paidSoldForDifference;
    
    @FXML
    private Label statistics_costs_label_price,statistics_costs_label_total,statistics_costs_label_shipping,statistics_costs_label_quantity,statistics_costs_label_totalPaid;
    
    @FXML
    private Label statistics_printer_label_priceShipping,statistics_printer_label_total,statistics_printer_label_itemsSold,statistics_printer_label_incomes,statistics_printer_label_sum,statistics_printer_label_totalPaid,statistics_printer_label_expenses,statistics_printer_label_difference,statistics_printer_label_dutyTax;
    
    @FXML
    private Label statistics_summary_profits,statistics_summary_costs, statistics_summary_difference;
    
    /*****************************          PRINTERS - METHODS         *****************************/

    public void refreshStatisticsLabels(HikariDataSource ds){
        
        //Orders
        statistics_label_order_totalOrders.setText("Orders(" + tv_orders.getItems().size() + ")");
        statistics_label_order_TotalSoldNotSold.setText(String.format(Locale.US, label_order_SoldOrders.getText().replaceAll("\\D+","") + "/" + label_order_NotSoldOrders.getText().replaceAll("\\D+","")));
        statistics_label_order_TotalBuildTime.setText(label_order_TotalBuildTime.getText());
        statistics_label_order_TotalPricePerHour.setText(label_order_TotalPricePerHour.getText());
        statistics_label_order_TotalWeight.setText(label_order_TotalWeight.getText());
        statistics_label_order_TotalItemsPrinted.setText(label_order_TotalItemsPrinted.getText());
        statistics_label_order_TotalCostPrice.setText(label_order_TotalCostPrice.getText());
        
        //Materials
        statistics_materials_label_remainingSoldRolls.setText(materials_label_remainingSoldRolls.getText());
        statistics_materials_label_avgRollPrice.setText(materials_label_avgRollPrice.getText());
        statistics_materials_label_trashWeight.setText(materials_label_trashWeight.getText());
        statistics_materials_label_Total.setText("Materials(" + tv_materials.getItems().size() + ")");
        statistics_materials_label_totalWeight.setText(materials_label_totalWeight.getText());
        statistics_materials_label_paidSoldFor.setText(materials_label_paidSoldFor.getText());
        statistics_materials_label_paidSoldForDifference.setText(materials_label_paidSoldForDifference.getText());
        statistics_materials_label_colorsTypes.setText(materials_label_colorsTypes.getText());
        statistics_materials_label_remainingSoldWeight.setText(materials_label_remainingSoldWeight.getText());
        statistics_materials_label_shippingPrice.setText(materials_label_shippingPrice.getText());
        
        //Costs
        statistics_costs_label_price.setText(costs_label_price.getText());
        statistics_costs_label_total.setText("Costs(" + tv_costs.getItems().size() + ")");
        statistics_costs_label_shipping.setText(costs_label_shipping.getText());
        statistics_costs_label_quantity.setText(costs_label_quantity.getText());
        statistics_costs_label_totalPaid.setText(costs_label_totalPaid.getText());
        statistics_printer_label_priceShipping.setText(printer_label_priceShipping.getText());
                    
        //Printers
        statistics_printer_label_total.setText("Printers(" + tv_printers.getItems().size() + ")");
        statistics_printer_label_itemsSold.setText(printer_label_itemsSold.getText());
        statistics_printer_label_incomes.setText(printer_label_incomes.getText());
        statistics_printer_label_sum.setText(printer_label_sum.getText());
        statistics_printer_label_totalPaid.setText(printer_label_totalPaid.getText());
        statistics_printer_label_expenses.setText(printer_label_expenses.getText());
        statistics_printer_label_difference.setText(printer_label_difference.getText());
        statistics_printer_label_dutyTax.setText(printer_label_dutyTax.getText());
        
        //General
        
        //Summary
        double profits, costs, material_costs, costs_costs, printer_costs;
        
        material_costs = Double.parseDouble(statistics_materials_label_paidSoldFor.getText().split(" ")[0]);
        printer_costs = Double.parseDouble(statistics_printer_label_sum.getText().split(" ")[0]);
        costs_costs = Double.parseDouble(statistics_costs_label_totalPaid.getText().split(" ")[0]);
        
        //System.out.println("Material costs: " + material_costs + "\nCost costs: " + costs_costs + "\nPrinter costs: " + printer_costs);

        
        profits = Double.parseDouble(statistics_label_order_TotalCostPrice.getText().split(" ")[0]);
        costs = material_costs + printer_costs + costs_costs;
        
        
        statistics_summary_profits.setText(String.format(Locale.US, "%.2f $", profits));
        statistics_summary_costs.setText(String.format(Locale.US, "%.2f $", costs));
        statistics_summary_difference.setText(String.format(Locale.US, "%.2f $", profits - costs));
        
    }
    
    // Create the service
    private final Service<Void> service_refreshStatistics = new Service<Void>() {
        @Override
        protected Task<Void> createTask(){
            Task<Void> task_refreshPrintersTable = new Task<Void>() {            
                @Override
                public Void call() throws Exception {
                    Platform.runLater(() -> {
                        updateProgress(-1, 100);                 
                        refreshStatisticsLabels(ds);                        
                    });
                                       
                    return null;
                }        
            };
        return task_refreshPrintersTable;
        }
    };
    
    public Service<Void> getService_refreshStatistics() {
        return service_refreshStatistics;
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
            calculateOrderStatistics(tv_orders.getSelectionModel().getSelectedItems());
        });
        
        order_btn_refresh.setOnAction((event) -> {            
            loadAll();
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
            
            ctrl.setDs(ds);            
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
            
                ctrl.setDs(ds);            
                ctrl.setMainController(this);
                ctrl.setUpdateOrderFields(tv_orders.getSelectionModel().getSelectedItems());            
            
            } catch (IOException e){
                e.printStackTrace();
            } catch (NullPointerException e){
                label_main_info.setText("Info: Select one order");
                label_main_info.setTextFill(Color.web("#ff0000"));
            }            
        });
        
        order_btn_delete.setOnAction((event) -> {            
            Order.deleteOrders(tv_orders.getSelectionModel().getSelectedItems(), label_main_info, ds);
            runService(service_refreshOrders);
        });
        
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          INITIALIZE CUSTOMERS TAB          *****************************/
      
    customer_btn_refresh.setOnAction((event) -> {        
        //runService(service_refreshCustomers);        
        loadAll();
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
        calculateCustomerStatistics(tv_customers.getSelectionModel().getSelectedItems());        
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
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setCustomer_label_id_value(MngApi.getCurrentAutoIncrementValue(ds, "Customers"));
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
            ctrl.setDs(ds);
            ctrl.setMainController(this);            
            ctrl.setUpdateCustomerFields(tv_customers.getSelectionModel().getSelectedItems());
            stage.show();
                        
        }catch (IOException e){
            
        }        
    });
    
    customer_btn_delete.setOnAction((event) -> {        
        Customer.deleteCustomers(tv_customers.getSelectionModel().getSelectedItems(), label_main_info, ds);
        runService(service_refreshCustomers);
    });
    
    /*****************************          INITIALIZE OBJECTS TAB          *****************************/
    
    object_btn_refresh.setOnAction((event) -> {
        //runService(service_refreshCustomers);
        loadAll();
    });
            
    object_btn_delete.setOnAction((event) -> {
        classes.Object.deleteObjects(tv_objects.getSelectionModel().getSelectedItems(), label_main_info, ds);
        runService(service_refreshObjects);
    });
    
    tv_objects.getSelectionModel().getSelectedItems().addListener((Change<? extends classes.Object> c) -> {        
            calculatedObjectStatistics(tv_objects.getSelectionModel().getSelectedItems());
        });
    
    tv_objects.setRowFactory( tv -> {
        TableRow<classes.Object> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                object_btn_edit.fire();
            }
        });
    return row;
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
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setObject_label_id_value(MngApi.getCurrentAutoIncrementValue(ds, "Objects"));
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
            ctrl.setDs(ds);
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
            calculateMaterialStatistics(tv_materials.getSelectionModel().getSelectedItems());
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
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setComboBoxes();
            ctrl.setMaterial_label_id_value(MngApi.getCurrentAutoIncrementValue(ds, "Materials"));
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
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setUpdateMaterialFields(tv_materials.getSelectionModel().getSelectedItem());
            
            stage.show();                        
            
        }catch (IOException e){

        }  
        
    });
    
    material_btn_refresh.setOnAction((event) -> {
        //runService(service_refreshMaterials);
        loadAll();
    });
    
    material_btn_delete.setOnAction((event) -> {
        Material.deleteMaterials(tv_materials.getSelectionModel().getSelectedItems(), label_main_info, ds);
        runService(service_refreshMaterials);
    });
    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          INITIALIZE COSTS TAB          *****************************/
    
    tv_costs.setRowFactory( tv -> {
        TableRow<Cost> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                cost_btn_editCost.fire();
            }
        });
    return row;
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
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setFieldsValues();
            stage.show();                        
            
        }catch (IOException e){
            
        }
    });//end new cost button  setOnAction
    
    cost_btn_editCost.setOnAction((event) -> {
        
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/costs/NewCost.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewCostController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Cost");
            stage.setMinHeight(440);
            stage.setMinWidth(400);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            
            //passing credentials to main controller
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setUpdateCostFields(tv_costs.getSelectionModel().getSelectedItems().get(0));
            stage.show();                        
            
        }catch (IOException e){
            
        }
        
    });
    
    cost_btn_delete.setOnAction((event) -> {        
        Cost.deleteCosts(tv_costs.getSelectionModel().getSelectedItems(), label_main_info, ds);
        runService(service_refreshCosts);
    });
    
    cost_btn_refresh.setOnAction((event) -> {        
        //runService(service_refreshCosts);
        loadAll();
    });
    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          INITIALIZE PRINTERS TAB          *****************************/

    tv_printers.setRowFactory( tv -> {
        TableRow<Printer> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                printer_btn_edit.fire();
            }
        });
    return row;
    });
    
    tv_printers.getSelectionModel().getSelectedItems().addListener((Change<? extends Printer> c) -> {        
            calculatePrinterStatistics(tv_printers.getSelectionModel().getSelectedItems());
        });
    
    printer_btn_new.setOnAction((event) -> {        
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/printers/NewPrinter.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewPrinterController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("New Printer");
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.setMinHeight(440);
            //stage.setMinWidth(400);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            
            //passing credentials to main controller
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setComboBox();            
            
            stage.show();                        
            
        }catch (IOException e){

        }        
    });
    
    printer_btn_edit.setOnAction((event) -> {
        
        try{            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/printers/NewPrinter.fxml"));            
            Parent root1 = fxmlLoader.load();
            NewPrinterController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("New Printer");
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.setMinHeight(440);
            //stage.setMinWidth(400);
           
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.centerOnScreen();
            
            
            //passing credentials to main controller
            ctrl.setDs(ds);
            ctrl.setMainController(this);
            ctrl.setComboBox(tv_printers.getSelectionModel().getSelectedItems().get(0));            
            ctrl.setUpdatePrinterFields(tv_printers.getSelectionModel().getSelectedItems().get(0));
            stage.show();                        
            
        }catch (IOException e){

        }   
        
    });
    
    printer_btn_delete.setOnAction((event) -> {
        Printer.deletePrinters(tv_printers.getSelectionModel().getSelectedItems(), label_main_info, ds);
        runService(service_refreshPrinters);
    });
    
    printer_btn_refresh.setOnAction((event) -> {        
        //runService(service_refreshPrinters);
        loadAll();
    });
    
    /*
    *
    *
    *
    *
    *    
    */
    /*****************************          INITIALIZE STATISTICS TAB          *****************************/
    tab_statistics.setOnSelectionChanged((Event t) -> {
        if (tab_statistics.isSelected()) {
            runService(service_refreshStatistics);
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
        
}//end MainController
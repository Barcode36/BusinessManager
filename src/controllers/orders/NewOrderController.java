/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.orders;

import classes.Customer;
import classes.MngApi;
import classes.Order;
import classes.OrderItem;
import com.zaxxer.hikari.HikariDataSource;
import controllers.MainController;
import controllers.select.SelectCustomerController;
import controllers.select.SelectObjectController;
import controllers.select.SelectPrinterMaterialPriceController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class NewOrderController implements Initializable {
    
    private HikariDataSource ds;
    
    private MainController mainController;
    
    //private Customer selectedCustomer;
    
    private ObservableList<OrderItem> orderObjects = FXCollections.observableArrayList();
    
    
    private Order order;
    
    private boolean isBeingEdited;
    
    @FXML
    private ToggleGroup toggleGroup_status = new ToggleGroup();
    
    @FXML
    private Label label_title, label_info, label_orderID, label_weight, label_supportWeight, label_weightSum, label_quantity, label_buildTime, label_price, label_costs, label_profit;
    
    @FXML
    private TextField txtField_customer, txtField_pricePerHour, txtField_comment;
    
    @FXML
    private Button btn_selectCustomer, btn_addObject, btn_removeSelected, btn_calculatePrices, btn_create, btn_cancel;
    
    @FXML
    private RadioButton radioBtn_Sold, radioBtn_NotSold;
    
    @FXML
    private DatePicker datePicker_dateCreated, datePicker_dueDate;
    
    @FXML
    private TableView<OrderItem> tv_orderObjects;
    
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
            ctrl.setDs(ds);
            ctrl.setMain_controller(mainController);
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
            ctrl.setNewOrderController(this);
            ctrl.getTv_objects().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            ctrl.setMainController(mainController);
            
            ctrl.displayObjects();
        }catch (IOException e){
            
        }
            
        });
        
        tv_orderObjects.setRowFactory( tv -> {
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
                        ctrl.setDs(ds);
                        ctrl.setMainController(mainController);
                        ctrl.setNewOrderController(this);                        
                        ctrl.setFields(tv_orderObjects.getSelectionModel().getSelectedItem());
                                                
                    }catch (IOException e){
            
                    }
                      
                }
            });
            return row;
        });
        
        btn_removeSelected.setOnAction((event) -> {
            
            if(tv_orderObjects.getSelectionModel().getSelectedItems() == null){
                label_info.setText("Select an item.");
                label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            orderObjects.removeAll(tv_orderObjects.getSelectionModel().getSelectedItems());
            refreshSelectedObjects();
            
        });
        
        btn_calculatePrices.setOnAction((event) -> {
            
            if(txtField_pricePerHour.getText() == null)txtField_pricePerHour.setText("2.5");
            calculatePrices();
            
        });
        
        btn_create.setOnAction((event) -> {            
            createOrUpdateOrder(mainController.getMain_label_info());            
        });
        
        btn_cancel.setOnAction((event) -> {            
            MngApi.closeWindow(btn_cancel);            
        });
    }    
    
    public void calcualteSummary(){
        
        double summary_weight = 0, summary_supportWeight = 0, summary_weightSum = 0, summary_price = 0, summary_costs = 0, summary_profit = 0;
        int summary_quantity = 0, summary_buildTime = 0;
        
        double weight = 0, supportWeight = 0, weightSum = 0, price = 0, costs = 0, profit = 0;
        int quantity = 0, buildTime = 0;
        
        for (int i = 0; i < orderObjects.size(); i++) {
            if(orderObjects.get(i).getQuantity().get() != 0){
                
                quantity = orderObjects.get(i).getQuantity().get();
            
                weight = orderObjects.get(i).getOrderItem_weight().get();
                supportWeight = orderObjects.get(i).getOrderItem_supportWeight().get();
                //weightSum = weight + supportWeight;
                buildTime = orderObjects.get(i).getObject().getObject_buildTime().get();
            
                price = orderObjects.get(i).getPrice().get();
                costs = orderObjects.get(i).getObject().getObject_costs().get();
                //profit = price - costs;
            
                summary_quantity += quantity;
            
                summary_supportWeight += supportWeight;
                summary_weight += weight;
                summary_buildTime += buildTime;
                summary_price += price;
                summary_costs += costs;
            }            
        }
        
        summary_weightSum = summary_weight + summary_supportWeight;
        summary_profit = summary_price - summary_costs;
        
        label_weight.setText(String.format(Locale.UK, "%.2f g", summary_weight));
        label_supportWeight.setText(String.format(Locale.UK, "%.2f g", summary_supportWeight));
        label_weightSum.setText(String.format(Locale.UK, "%.2f g", summary_weightSum));        
        label_quantity.setText(String.format(Locale.UK, "%d", summary_quantity));        
            String summary_buildTime_formatted = MngApi.convertToFormattedTime(summary_buildTime).get();
        label_buildTime.setText(String.format("%s", summary_buildTime_formatted));
        label_price.setText(String.format(Locale.UK, "%.2f $", summary_price));
        label_costs.setText(String.format(Locale.UK, "%.2f $", summary_costs));
        label_profit.setText(String.format(Locale.UK, "%.2f $", summary_profit));        
        txtField_pricePerHour.setText(String.format(Locale.UK, "%.2f", summary_price/((double) summary_buildTime/60)));
        
    }
    
    //calculate prices based on user input in txtField_pricePerHour
    public void calculatePrices(){
        
        try{
            
            double pricePerHour = Double.parseDouble(txtField_pricePerHour.getText());
            double pricePerMinute = pricePerHour/60;
            
            for (int i = 0; i < orderObjects.size(); i++) {
                
                OrderItem item = orderObjects.get(i);
                
                int buildTime = item.getObject().getObject_buildTime().get()*item.getQuantity().get();                
                
                double finalPrice = buildTime*pricePerMinute;
                
                item.setPrice(new SimpleDoubleProperty(MngApi.round(finalPrice, 2)));
            }
            
            refreshSelectedObjects();
            
        } catch (NumberFormatException e){
            
            label_info.setText("Price per hour has bad format.");
            label_info.setTextFill(Color.web("#ff0000"));
            
        } catch (NullPointerException e){
            
            label_info.setText("Price per hour is empty.");
            label_info.setTextFill(Color.web("#ff0000"));
            
        }
        
        
    }
    
    public void setSelectedObjects() {
        
        col_objectName.setCellValueFactory((param) -> {return param.getValue().getObject().getObject_name();});
        col_buildTime_formatted.setCellValueFactory((param) -> {return param.getValue().getObject().getObject_buildTime_formated();});
        col_materialColor.setCellValueFactory((param) -> {return param.getValue().getMaterial().getMaterial_color();});
        col_materialType.setCellValueFactory((param) -> {return param.getValue().getMaterial().getMaterial_type();});
        col_printer.setCellValueFactory((param) -> {return param.getValue().getPrinter().getPrinter_name();});
        
        col_materialID.setCellValueFactory((param) -> {return param.getValue().getMaterial_id().asObject();});        
        col_objectID.setCellValueFactory((param) -> {return param.getValue().getObject_id().asObject();});
        col_printerID.setCellValueFactory((param) -> {return param.getValue().getPrinter_id().asObject();});
        col_quantity.setCellValueFactory((param) -> {return param.getValue().getQuantity().asObject();});
        
        
        col_weight.setCellValueFactory((param) -> {return param.getValue().getOrderItem_weight().asObject();});
        col_supportWeight.setCellValueFactory((param) -> {return param.getValue().getOrderItem_supportWeight().asObject();});
        col_objectCosts.setCellValueFactory((param) -> {return param.getValue().getObject().getObject_costs().asObject();});
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
        
        tv_orderObjects.setItems(orderObjects);
    }

    public ObservableList<OrderItem> getOrderObjects() {
        return orderObjects;
    }    
    
    public int getOrderID(){
        return Integer.parseInt(label_orderID.getText());        
    }
    
    public void refreshSelectedObjects(){        
        tv_orderObjects.refresh();
        calcualteSummary();
    }

    public Button getBtn_create() {
        return btn_create;
    }
    
    private void createOrUpdateOrder(Label info){
        try{           
            
            //check empty order or items without assigned material, printer, etc.
            if(tv_orderObjects.getItems().isEmpty())throw new NullPointerException();
            for (int i = 0; i < tv_orderObjects.getItems().size(); i++) {
                if(tv_orderObjects.getItems().get(i).getPrinter_id().get() == 0) throw new NullPointerException();            
            }
            
            //chceck for inappropriate signs in text fields and mepty text fields
            MngApi.checkApostrophe(txtField_comment);
            
            //preparing order
            Order newOrder;            
            SimpleIntegerProperty order_id = new SimpleIntegerProperty(Integer.parseInt(label_orderID.getText()));
            
            SimpleStringProperty status, comment, dateCreated, dueDate, totalBuildTimeFormatted;    
            SimpleIntegerProperty customer_id, totalQuantity, totalBuildTime;
            SimpleDoubleProperty totalPrice, totalCosts, totalWeight, totalSupportWeight;
            
            RadioButton soldStatus = (RadioButton)toggleGroup_status.getSelectedToggle();            
            status = new SimpleStringProperty(soldStatus.getText());
            
            comment = new SimpleStringProperty(txtField_comment.getText());
            dateCreated = new SimpleStringProperty(datePicker_dateCreated.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dueDate = new SimpleStringProperty(datePicker_dueDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            totalBuildTimeFormatted = new SimpleStringProperty(label_buildTime.getText());
                        
            customer_id = new SimpleIntegerProperty(Integer.parseInt(txtField_customer.getText().split(";")[0]));
            totalQuantity = new SimpleIntegerProperty(Integer.parseInt(label_quantity.getText()));
            totalBuildTime = new SimpleIntegerProperty(MngApi.convertToMinutes(label_buildTime.getText()));
            
            String[] totalPriceFormatted = label_price.getText().split(" ");
            totalPrice = new SimpleDoubleProperty(Double.parseDouble(totalPriceFormatted[0]));
            
            String[] totalCostsFormatted = label_costs.getText().split(" ");
            totalCosts = new SimpleDoubleProperty(Double.parseDouble(totalCostsFormatted[0]));
            
            String[] totalWeightFormatted = label_weight.getText().split(" ");
            totalWeight = new SimpleDoubleProperty(Double.parseDouble(totalWeightFormatted[0]));
            
            String[] totalSupportWeightFormatted = label_supportWeight.getText().split(" ");
            totalSupportWeight = new SimpleDoubleProperty(Double.parseDouble(totalSupportWeightFormatted[0]));
            
            newOrder = new Order(order_id, customer_id, status, comment, dateCreated, dueDate, totalQuantity, totalBuildTime, totalCosts, totalPrice, totalWeight, totalSupportWeight, dueDate, totalBuildTimeFormatted, orderObjects, Customer.getCustomerById(customer_id, mainController.getTv_customers().getItems()));
            Order.insertNewOrder(newOrder, info, ds);
            
            //We will now add assign order to objects, but first, we have to assign OrderID to items belonging to current order
            //we also have to assing correct orderItemID. We have to keep OrderItemID the same. if it is old order item or assign new OrderItemID
            //if it's brand new object (OrderItemID == 0).
            
            //For correct mechanism which will ensure the deleted/updated/added objects belonging to order we have to do 3 key things:
                //1. Determine IDs of removed original objects and remove them frob DB table
                //2. Determine updated original objects and update them in DB table
                //3. Determine new objects in order and add then in DB table
                    
            //1. Determine IDs of removed original objects and remove them from DB table
            /*
            We do this by comparing IDs of old OrderItems vs NewOrderItems. We remove matches from old OrderItems so we can keep only objects to remove
            because they are not in new OrderItems.
            */
            
            ObservableList<OrderItem> originalObjects = null;//OrderItem.getOrderItemsForOrders(order_id, mainController.getTv_orders().getItems());
            ObservableList<Integer> newObjects = FXCollections.observableArrayList();
                for (int j = 0; j < orderObjects.size(); j++) {
                    OrderItem item = orderObjects.get(j);
                    newObjects.add(item.getOrderItem_id().get());
                }
            
            originalObjects.removeAll(newObjects);            
            OrderItem.deleteOrderItem(originalObjects, info, ds);
            
            //2. Determine updated original objects and update them in DB table
            /*
            In orderObjects (new OrderItems) we have items with OrderItemID=<Integer> (old object - might be changed, need to update)
            or OrderItemID=0 (new objects)
            */
            
            //3. Determine new objects in order and add then in DB table
            /*
            New objects have OrderItemID=0. We will update existing objects and insert newone using OrderItem.insertNewOrderItems(orderObjects, user)
            method. Correct orderItemID will be assigned within this method
            */
            
            OrderItem.insertNewOrderItems(orderObjects, ds);
            
            mainController.runService(mainController.getService_refreshOrders());                
            MngApi.closeWindow(btn_create);
            
        } catch (NumberFormatException e) {                
            label_info.setText("Wrong number format, please check your fields.");
            label_info.setTextFill(Color.web("#ff0000"));
            //e.printStackTrace();            
        } catch (NullPointerException | IndexOutOfBoundsException e) {                
            label_info.setText("Insert some objects and/or assign some material first.");
            label_info.setTextFill(Color.web("#ff0000"));
            //e.printStackTrace();
        }
        //e.printStackTrace();
        
    }    
    
    public void setSelectedCustomer(Customer selectedCustomer) {
        //this.selectedCustomer = selectedCustomer;
        txtField_customer.setText(selectedCustomer.getCustomer_id().get() + ";" + selectedCustomer.getCustomer_lastName().get() + ";" + selectedCustomer.getCustomer_firstName().get());
    }

    public void setDs(HikariDataSource ds) {
        this.ds = ds;
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;        
    }
    
    public void setOrderFields(){
        tv_orderObjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        if (isBeingEdited){
            setFieldsForEditing();            
        } else {
            setFieldsForCreating();
        }                
    }
    
    private void setFieldsForCreating(){
        //get latest order ID
        label_orderID.setText(String.valueOf(MngApi.getCurrentAutoIncrementValue(ds, "Orders")));
        //set some default price per hour
        txtField_pricePerHour.setText("3");
        //set dates to actual date
        datePicker_dateCreated.setValue(LocalDate.now());
        datePicker_dueDate.setValue(LocalDate.now());
        //set button title
        btn_create.setText("Create");
    }
   
    private void setFieldsForEditing(){
        btn_create.setDisable(false);
        btn_create.setText("Update");
        label_title.setText("Edit Order");
        label_info.setText("Edit fields");
        label_info.setTextFill(Color.web("#ff0000"));
            
        label_orderID.setText(String.valueOf(order.getOrder_id().get()));
            
        LocalDate dateCerated = LocalDate.parse(order.getOrder_dateCreated().get());
        LocalDate dueDate = LocalDate.parse(order.getOrder_dueDate().get());
        datePicker_dateCreated.setValue(dateCerated);
        datePicker_dueDate.setValue(dueDate);
        txtField_customer.setText(order.getOrder_customerID().get() + ";" + order.getOrder_customer().get());        
            
        //list order items which belongs to particular order
        ObservableList<OrderItem> itemList = FXCollections.observableArrayList();//FXCollections.observableArrayList(OrderItem.getOrderItemsForOrders(mainController.getOrdersTable(), mainController.getOrderItemsTable(), mainController.getObjectsTable(), mainController.getMaterialsTable(), mainController.getCommonMaterialProperties(), mainController.getPrintersTable()));
        //add listed order items to global list
        orderObjects.addAll(itemList);
        //display this global list in table
        setSelectedObjects();
        //calculate summary for those objects
        calcualteSummary();
        txtField_comment.setText(order.getOrder_comment().get());
        //select whether order is sold or not
        String status = order.getOrder_status().get();        
            switch (status){
                case "Sold":
                    radioBtn_Sold.setSelected(true);
                    return;                
                default:
                    radioBtn_NotSold.setSelected(true);            
            }
    }
        
    public boolean isIsBeingEdited() {
        return isBeingEdited;
    }

    public void setIsBeingEdited(boolean isBeingEdited, ObservableList<Order> orders) {
        this.isBeingEdited = isBeingEdited;
        this.order = orders.get(0);
    }
    
    
    
}
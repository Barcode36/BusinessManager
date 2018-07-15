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
import classes.User;
import controllers.MainController;
import controllers.select.SelectCustomerController;
import controllers.select.SelectObjectController;
import controllers.select.SelectPrinterMaterialPriceController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    
    private User user;
    
    private MainController mainController;
    
    private Customer selectedCustomer;
    
    private ObservableList<OrderItem> selectedObjects = FXCollections.observableArrayList();
    
    @FXML
    private ToggleGroup toggleGroup_status = new ToggleGroup();
    
    @FXML
    private Label label_info, label_orderID, label_weight, label_supportWeight, label_weightSum, label_quantity, label_buildTime, label_price, label_costs, label_profit;
    
    @FXML
    private TextField txtField_customer, txtField_pricePerHour, txtField_comment;
    
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
                        
                        ctrl.setSelectedObject(tv_selectedObjects.getSelectionModel().getSelectedItem());
                        ctrl.setElementValues();
                    }catch (IOException e){
            
                    }
                      
                }
            });
            return row;
        });
        
        btn_removeSelected.setOnAction((event) -> {
            
            if(tv_selectedObjects.getSelectionModel().getSelectedItems() == null){
                label_info.setText("Select an item.");
                label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            selectedObjects.removeAll(tv_selectedObjects.getSelectionModel().getSelectedItems());
            refreshSelectedObjects();
            
        });
        
        btn_calculatePrices.setOnAction((event) -> {
            
            if(txtField_pricePerHour.getText() == null)txtField_pricePerHour.setText("2.5");
            calculatePrices();
            
        });
        
        btn_create.setOnAction((event) -> {            
            try{
                if(tv_selectedObjects.getItems().get(0) == null) throw new NullPointerException();
                //preparing order
                Order newOrder;            
                SimpleIntegerProperty order_id = new SimpleIntegerProperty(Integer.parseInt(label_orderID.getText()));
            
                SimpleStringProperty status, comment, dateCreated, dueDate;    
                SimpleIntegerProperty id, customer_id, totalQuantity;
                SimpleDoubleProperty totalPrice;
            
                RadioButton soldStatus = (RadioButton)toggleGroup_status.getSelectedToggle();            
                status = new SimpleStringProperty(soldStatus.getText());
            
                comment = new SimpleStringProperty(txtField_comment.getText());
                dateCreated = new SimpleStringProperty(datePicker_dateCreated.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                dueDate = new SimpleStringProperty(datePicker_dueDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            
                id = new SimpleIntegerProperty(Integer.parseInt(label_orderID.getText()));
                customer_id = selectedCustomer.getCustomer_id();
                totalQuantity = new SimpleIntegerProperty(Integer.parseInt(label_quantity.getText()));
            
                String[] totalPriceFormatted = label_price.getText().split(" ");
                totalPrice = new SimpleDoubleProperty(Double.parseDouble(totalPriceFormatted[0]));
            
                newOrder = new Order(customer_id, null, status, comment, dateCreated, dueDate, null, id, totalQuantity, null, null, totalPrice, null);
            
                //preparing orderItem - we have only one order but multiple items in it so we do this in loop. First of all we will generate update query for all items
                //They are basicaly mulitple insert querries in a row separated by ";"
                ObservableList<String> updateQueries = FXCollections.observableArrayList();
            
                for (int i = 0; i < selectedObjects.size(); i++) {
                
                    OrderItem obj = selectedObjects.get(i);
                    obj.setOrder_id(order_id);
                
                    SimpleIntegerProperty object_id, buildTime, quantity, printer_id, material_id;
                    SimpleDoubleProperty supportWeight, weight, price;            
                
                
                    object_id = obj.getObject_id();
                    buildTime = obj.getObject_buildTime();
                    quantity = obj.getQuantity();
                    printer_id = obj.getPrinter_id();
                    material_id = obj.getMaterial_id();
                
                    supportWeight = obj.getObject_supportWeight();
                    weight = obj.getObject_weight();
                    price = obj.getPrice();
                
                    updateQueries.add(OrderItem.generateUpdateQuery(obj));
                }
            
                Order.insertNewOrder(newOrder, user);
                OrderItem.insertMultipleOrderItems(updateQueries, user);
            
                MngApi.closeWindow(btn_create);
                
            } catch (NumberFormatException e) {                
                label_info.setText("Wrong number format, please check your fields.");
                label_info.setTextFill(Color.web("#ff0000"));
                //e.printStackTrace();
            } catch (NullPointerException e) {                
                label_info.setText("Insert some objects first.");
                label_info.setTextFill(Color.web("#ff0000"));
                //e.printStackTrace();
            }
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
        
        for (int i = 0; i < selectedObjects.size(); i++) {
            if(selectedObjects.get(i).getQuantity().get() != 0){
                
                quantity = selectedObjects.get(i).getQuantity().get();
            
                weight = quantity*selectedObjects.get(i).getObject_weight().get();
                supportWeight = quantity*selectedObjects.get(i).getObject_supportWeight().get();
                //weightSum = weight + supportWeight;
                buildTime = quantity*selectedObjects.get(i).getObject_buildTime().get();
            
                price = selectedObjects.get(i).getPrice().get();
                costs = selectedObjects.get(i).getCosts().get();
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
            String summary_buildTime_formatted = MngApi.convertToHours(summary_buildTime).get();
        label_buildTime.setText(String.format("%s", summary_buildTime_formatted));
        label_price.setText(String.format(Locale.UK, "%.2f $", summary_price));
        label_costs.setText(String.format(Locale.UK, "%.2f $", summary_costs));
        label_profit.setText(String.format(Locale.UK, "%.2f $", summary_profit));
    }
    
    public void calculatePrices(){
        
        try{
            
            double pricePerHour = Double.parseDouble(txtField_pricePerHour.getText());
            double pricePerMinute = pricePerHour/60;
            
            for (int i = 0; i < selectedObjects.size(); i++) {
                
                OrderItem item = selectedObjects.get(i);
                
                int buildTime = item.getObject_buildTime().get();
                int quantity = item.getQuantity().get();
                
                double finalPrice = buildTime*quantity*pricePerMinute;
                
                item.setPrice(new SimpleDoubleProperty(MngApi.round(finalPrice, 2)));
            }
            
            refreshSelectedObjects();
            
        } catch (NumberFormatException e){
            
            label_info.setText("Price per hour has bad format.");
            label_info.setTextFill(Color.web("#ff0000"));
            
        }
        
        
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
        col_quantity.setCellValueFactory((param) -> {return param.getValue().getQuantity().asObject();});
        
        
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

    public void refreshSelectedObjects(){        
        tv_selectedObjects.refresh();
        calcualteSummary();
    }

    public Button getBtn_create() {
        return btn_create;
    }
    
    
    
    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
        txtField_customer.setText(selectedCustomer.getCustomer_id().get() + ";" + selectedCustomer.getCustomer_lastName().get() + ";" + selectedCustomer.getCustomer_firstName().get());
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;        
    }
    
    public void setNewOrderFields(){
        
        label_orderID.setText(String.valueOf(MngApi.getCurrentAutoIncrementValue(user, "Orders")));
        tv_selectedObjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        txtField_pricePerHour.setText("2.5");
        
        datePicker_dateCreated.setValue(LocalDate.now());
        datePicker_dueDate.setValue(LocalDate.now());
        
    }
        
//    public void setNewOrder_label_id_value(int id) {
//        this.label_orderID.setText(String.valueOf(MngApi.getCurrentAutoIncrementValue(user, "Orders")));
//        tv_selectedObjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//    }
    
    public void setSelectedOrderField(Order order){
        
        label_orderID.setText(String.valueOf(order.getOrder_id().get()));
        
    }
    
    public void setSelectedOrder_label_id_value(Order order) {
        this.label_orderID.setText(String.valueOf(order.getOrder_id().get()));
        tv_selectedObjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
}

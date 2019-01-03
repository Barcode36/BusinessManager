/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.costs;

import classes.Cost;
import classes.MngApi;
import classes.Printer;
import classes.SimpleTableObject;
import classes.User;
import controllers.MainController;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class NewCostController implements Initializable {

    private User user;
    
    private MainController mainController;
    
    @FXML
    private Label cost_label_id, cost_label_info;
    
    @FXML
    private TextField cost_txtField_name, cost_txtField_quantity, cost_txtField_price, cost_txtField_shipping, cost_txtField_comment;
    
    @FXML
    private DatePicker cost_datePicker_purchaseDate;
    
    @FXML
    private Button cost_btn_create, cost_btn_cancel;
    
    @FXML
    private ComboBox<SimpleTableObject> comboBox_printer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cost_btn_create.setOnAction((event) -> {
            boolean isEmpty = MngApi.isTextFieldEmpty(cost_txtField_name, cost_txtField_quantity, cost_txtField_price, cost_txtField_shipping);
            
            if (isEmpty == true){
                cost_label_info.setText("Fields cannot be empty.");
                cost_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            MngApi.checkApostrophe(cost_txtField_comment,cost_txtField_name);
            
            SimpleIntegerProperty cost_id, cost_quantity, cost_printerID;
            SimpleStringProperty cost_name, cost_purchaseDate, cost_comment, cost_printer;
            SimpleDoubleProperty cost_shipping, cost_price;
            
            Cost newCost;
            
            try {
            cost_id = new SimpleIntegerProperty(getCost_label_id_value());
            cost_quantity = new SimpleIntegerProperty(Integer.parseInt(cost_txtField_quantity.getText()));
                        
            cost_printerID = new SimpleIntegerProperty(comboBox_printer.getValue().getId().get());
            cost_printer = new SimpleStringProperty(comboBox_printer.getValue().getName().get());
            
            cost_name = new SimpleStringProperty(cost_txtField_name.getText());            
            cost_purchaseDate = new SimpleStringProperty(cost_datePicker_purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cost_comment = new SimpleStringProperty(cost_txtField_comment.getText());
            
            cost_price = new SimpleDoubleProperty(Double.parseDouble(cost_txtField_price.getText()));
            cost_shipping = new SimpleDoubleProperty(Double.parseDouble(cost_txtField_shipping.getText()));
            
            newCost = new Cost(cost_id, cost_quantity, cost_printerID, cost_name, cost_purchaseDate, cost_comment, cost_printer, cost_shipping, cost_price);
            
            Cost.insertNewCost(newCost, user);
            
            MngApi.closeWindow(cost_btn_create);            
            mainController.runService(mainController.getService_refreshCosts());
            
            } catch (NumberFormatException e) {
                cost_label_info.setText("Wrong number format, please check your fields.");
                cost_label_info.setTextFill(Color.web("#ff0000"));
            }
            
            
            
        });
        
        cost_btn_cancel.setOnAction((event) -> {
            MngApi.closeWindow(cost_btn_cancel);
        });
    }    

    public DatePicker getCost_datePicker_purchaseDate() {
        return cost_datePicker_purchaseDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void setFieldsValues() {
        
        int id = MngApi.getCurrentAutoIncrementValue(user, "Costs");
        this.cost_label_id.setText(String.valueOf(id));

        ObservableList<SimpleTableObject> printers = FXCollections.observableArrayList(Printer.getPrinters(user));
        comboBox_printer.setItems(printers);
        comboBox_printer.setVisibleRowCount(7);
        comboBox_printer.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        comboBox_printer.setValue(printers.get(0));        
    }
    
    public void setUpdateCostFields(Cost cost) {
        
        int id = cost.getCost_id().get();
        this.cost_label_id.setText(String.valueOf(id));

        cost_btn_create.setText("Update");
        
        cost_txtField_name.setText(cost.getCost_name().get());
        cost_txtField_quantity.setText(String.valueOf(cost.getCost_quantity().get()));
        cost_txtField_price.setText(String.valueOf(cost.getCost_price().get()));
        cost_txtField_shipping.setText(String.valueOf(cost.getCost_shipping().get()));
        cost_txtField_comment.setText(cost.getCost_comment().get());
        
        LocalDate purchaseDate = LocalDate.parse(cost.getCost_purchaseDate().get());
        cost_datePicker_purchaseDate.setValue(purchaseDate);        
        
        ObservableList<SimpleTableObject> printers = FXCollections.observableArrayList(Printer.getPrinters(user));
        comboBox_printer.setItems(printers);
        comboBox_printer.setVisibleRowCount(7);        
        comboBox_printer.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        int printer_id = cost.getCost_printerID().get();
                
        for (int i = 0; i < printers.size(); i++) {
            
            if (printer_id == printers.get(i).getId().get())comboBox_printer.getSelectionModel().select(i);
                    
        }  
    }
    
    public int getCost_label_id_value(){
        return Integer.parseInt(this.cost_label_id.getText());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
}

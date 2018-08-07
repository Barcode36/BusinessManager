/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.customers;

import classes.Customer;
import classes.MngApi;
import classes.SimpleTableObject;
import classes.User;
import controllers.MainController;
import java.net.URL;
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
 * @author Erik PC
 */
public class NewCustomerController implements Initializable {

    private User user;
    
    private MainController mainController;   
    
    @FXML
    private Label customer_label_id, customer_label_info;
    
    @FXML
    private TextField  customer_txtField_firstName, customer_txtField_lastName, customer_txtField_phone, customer_txtField_mail, customer_txtField_address, customer_txtField_city, customer_txtField_zipCode, customer_txtField_comment; 
    
    @FXML
    private ComboBox<SimpleTableObject> customer_comboBox_country, customer_comboBox_company;
    
    @FXML
    private DatePicker customer_datePicker_dateCreated;
    
    @FXML
    private Button customer_btn_create, customer_btn_cancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        customer_btn_create.setOnAction((event) -> {
            boolean isEmpty = MngApi.isTextFieldEmpty(customer_txtField_firstName, customer_txtField_lastName);
            
            if (isEmpty == true || MngApi.isComboBoxEmpty(customer_comboBox_country, customer_comboBox_company)){
                customer_label_info.setText("Fields cannot be empty.");
                customer_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            SimpleStringProperty customer_lastName, customer_firstName, customer_dateCreated, customer_mail, customer_phone, customer_address, customer_city, customer_zipCode, customer_country, customer_company, customer_comment;
            SimpleIntegerProperty customer_id, customer_id_company, customer_id_country, customer_orderCount;
            SimpleDoubleProperty customer_ordersPrice;
            
            try {
                
                customer_lastName = new SimpleStringProperty(customer_txtField_lastName.getText());
                customer_firstName = new SimpleStringProperty(customer_txtField_firstName.getText());
                customer_dateCreated = new SimpleStringProperty(customer_datePicker_dateCreated.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                customer_comment = new SimpleStringProperty(customer_txtField_comment.getText());
                customer_mail = new SimpleStringProperty(customer_txtField_mail.getText());
                customer_phone = new SimpleStringProperty(customer_txtField_phone.getText());                
                customer_address = new SimpleStringProperty(customer_txtField_address.getText());
                customer_city = new SimpleStringProperty(customer_txtField_city.getText());
                customer_zipCode = new SimpleStringProperty(customer_txtField_zipCode.getText());
                customer_country = null;
                
                customer_company = new SimpleStringProperty(customer_comboBox_company.getSelectionModel().getSelectedItem().getName().get());
                
                customer_id = new SimpleIntegerProperty(getCustomer_label_id_value());
                customer_id_company = new SimpleIntegerProperty(customer_comboBox_company.getSelectionModel().getSelectedItem().getId().get());
                customer_id_country = new SimpleIntegerProperty(customer_comboBox_country.getSelectionModel().getSelectedItem().getId().get());
                
                customer_orderCount = new SimpleIntegerProperty(0);
                customer_ordersPrice = new SimpleDoubleProperty(0);                
                
                Customer newCustomer = new Customer(customer_lastName, customer_firstName, customer_dateCreated, customer_mail, customer_phone, customer_address, customer_city, customer_zipCode, customer_country, customer_company, customer_comment, customer_id, customer_id_company, customer_id_country, customer_orderCount, customer_ordersPrice);
                
                Customer.insertNewCustomer(newCustomer, user);
            
            MngApi.closeWindow(customer_btn_create);            
            mainController.runService(mainController.getService_refreshCustomers());
            
            } catch (NumberFormatException e) {
                customer_label_info.setText("Wrong number format, please check your fields.");
                customer_label_info.setTextFill(Color.web("#ff0000"));
            }
            
        });
        
        customer_btn_cancel.setOnAction((event) -> {            
            MngApi.closeWindow(customer_btn_cancel);
        });
    }    
    
    public void setComboBoxes(){
        
        ObservableList<SimpleTableObject> countries = FXCollections.observableArrayList(Customer.getCountries(user));
        ObservableList<SimpleTableObject> companies = FXCollections.observableArrayList(Customer.getCompanies(user));
        
        customer_comboBox_country.setItems(countries);
        customer_comboBox_country.getSelectionModel().select(229);
        customer_comboBox_country.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        customer_comboBox_company.setItems(companies);
        customer_comboBox_company.getSelectionModel().select(0);
        customer_comboBox_company.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getName().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
      
        customer_comboBox_country.setVisibleRowCount(5);
        customer_comboBox_company.setVisibleRowCount(5);
        
    }    
    
    public DatePicker getCustomer_datePicker_dateCreated() {
        return this.customer_datePicker_dateCreated;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setCustomer_label_id_value(int id) {
        this.customer_label_id.setText(String.valueOf(id));        
    }
    
    private int getCustomer_label_id_value(){
        return Integer.parseInt(this.customer_label_id.getText());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.printers;

import classes.MngApi;
import classes.Printer;
import classes.SimpleTableObject;
import com.zaxxer.hikari.HikariDataSource;
import controllers.MainController;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
 *
 * @author edemko
 */
public class NewPrinterController implements Initializable {
    
    private HikariDataSource ds;
    
    private MainController mainController;
    
    @FXML
    private Label printer_label_id, printer_label_info, printer_label_title;
    
    @FXML
    private DatePicker printer_datePicker_purchaseDate;
    
    @FXML
    private Button printer_btn_create, printer_btn_cancel;
    
    @FXML
    private TextField printer_txtField_price, printer_txtField_shipping, printer_txtField_comment, printer_txtField_duty, printer_txtField_tax, printer_txtField_name;
    
    @FXML
    private ComboBox<SimpleTableObject> printer_comboBox_type;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        printer_btn_create.setOnAction((event) -> {
           createPrinter();
        });
        
        printer_btn_cancel.setOnAction((event) -> {            
            MngApi.closeWindow(printer_btn_cancel);
        });
    }

    private void createPrinter(){        
        boolean isEmpty = MngApi.isTextFieldEmpty(printer_txtField_price, printer_txtField_shipping, printer_txtField_comment, printer_txtField_duty, printer_txtField_tax, printer_txtField_name);
            
            if (isEmpty == true || MngApi.isComboBoxEmpty(printer_comboBox_type)){
                printer_label_info.setText("Fields cannot be empty.");
                printer_label_info.setTextFill(Color.web("#ff0000"));
                return;
            }
            
            MngApi.checkApostrophe(printer_txtField_price, printer_txtField_shipping, printer_txtField_comment, printer_txtField_duty, printer_txtField_tax, printer_txtField_name);
            
            SimpleIntegerProperty printer_id, printer_itemsSold, printer_type_id;
            SimpleStringProperty printer_name, printer_purchaseDate, printer_comment, printer_type;
            SimpleDoubleProperty printer_shipping, printer_price, printer_duty, printer_tax, printer_incomes, printer_expenses, printer_overallIncome;
            
            try {
                
                printer_id = new SimpleIntegerProperty(Integer.parseInt(printer_label_id.getText()));
                printer_itemsSold = new SimpleIntegerProperty(0);
                printer_type_id = new SimpleIntegerProperty(printer_comboBox_type.getValue().getProperty_id().get());
                
                printer_name = new SimpleStringProperty(printer_txtField_name.getText());
                printer_purchaseDate = new SimpleStringProperty(printer_datePicker_purchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                printer_comment = new SimpleStringProperty(printer_txtField_comment.getText());
                printer_type = new SimpleStringProperty("");
                
                printer_shipping = new SimpleDoubleProperty(Double.parseDouble(printer_txtField_shipping.getText()));
                printer_price = new SimpleDoubleProperty(Double.parseDouble(printer_txtField_price.getText()));
                printer_duty = new SimpleDoubleProperty(Double.parseDouble(printer_txtField_duty.getText()));
                printer_tax = new SimpleDoubleProperty(Double.parseDouble(printer_txtField_tax.getText()));
                printer_incomes = new SimpleDoubleProperty(0);
                printer_expenses = new SimpleDoubleProperty(0);
                printer_overallIncome = new SimpleDoubleProperty(0);
                
                Printer newPrinter = new Printer(printer_id, printer_type_id, printer_name, printer_purchaseDate, printer_comment, printer_shipping, printer_price, printer_duty, printer_tax);
                
                Printer.insertNewPrinter(newPrinter, ds);
            
            MngApi.closeWindow(printer_btn_create);            
            mainController.runService(mainController.getService_refreshMaterials());
            
            } catch (NumberFormatException e) {
                printer_label_info.setText("Wrong number format, please check your fields.");
                printer_label_info.setTextFill(Color.web("#ff0000"));
            }        
    }
    
    public void setUpdatePrinterFields(Printer printer){
        
        printer_label_id.setText(String.valueOf(printer.getPrinter_id().get()));
        setComboBox(printer);
        printer_label_title.setText("Edit Printer");
        
        printer_txtField_price.setText(String.valueOf(printer.getPrinter_price().get()));
        printer_txtField_shipping.setText(String.valueOf(printer.getPrinter_shipping().get()));
        printer_txtField_comment.setText(printer.getPrinter_comment().get());
        printer_txtField_duty.setText(String.valueOf(printer.getPrinter_duty().get()));
        printer_txtField_tax.setText(String.valueOf(printer.getPrinter_tax().get()));
        printer_txtField_name.setText(printer.getPrinter_name().get());
        
        LocalDate purchaseDate = LocalDate.parse(printer.getPrinter_purchaseDate().get());
        printer_datePicker_purchaseDate.setValue(purchaseDate);
        
        printer_btn_create.setText("Update");
        
    }
    
    public void setComboBox(){
        
        setPrinter_label_id(MngApi.getCurrentAutoIncrementValue(ds, "Printers"));
        MngApi.setActualDate(printer_datePicker_purchaseDate);
        
        ObservableList<SimpleTableObject> printers = FXCollections.observableArrayList(getPrinterTypes());
        
        printer_comboBox_type.setItems(printers);
        printer_comboBox_type.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getProperty_name().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        printer_comboBox_type.setVisibleRowCount(5);
        
    }
    
    public void setComboBox(Printer printer){
        
        setPrinter_label_id(printer.getPrinter_id().get());
        LocalDate purchaseDate = LocalDate.parse(printer.getPrinter_purchaseDate().get());
        printer_datePicker_purchaseDate.setValue(purchaseDate);
        
        printer_btn_create.setText("Update");
        
        String printer_type = printer.getPrinter_type().get();
        
        ObservableList<SimpleTableObject> printers = FXCollections.observableArrayList(getPrinterTypes());
        
        printer_comboBox_type.setItems(printers);
        printer_comboBox_type.setConverter(new StringConverter<SimpleTableObject>() {
            @Override
            public String toString(SimpleTableObject object) {
                return object.getProperty_name().get();
            }

            @Override
            public SimpleTableObject fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        for (int i = 0; i < printers.size(); i++) {
            
            if (printer_type.equals(printers.get(i).getProperty_name().get()))printer_comboBox_type.getSelectionModel().select(i);
            
        }
        
        printer_comboBox_type.setVisibleRowCount(5);
    }

    public void setDs(HikariDataSource ds) {
        this.ds = ds;
    }

    public void setPrinter_label_id(int printer_id) {
        printer_label_id.setText(String.valueOf(printer_id));
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private List<SimpleTableObject> getPrinterTypes(){
        return mainController.getListOfMaterialProperties(7);
    }
}

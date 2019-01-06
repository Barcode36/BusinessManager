/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.select;


import classes.Material;
import classes.MngApi;
import classes.OrderItem;
import classes.Printer;
import classes.SimpleTableObject;

import classes.User;
import controllers.orders.NewOrderController;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;


/**
 * FXML Controller class
 *
 * @author edemko
 */
public class SelectPrinterMaterialPriceController implements Initializable {
    
    private User user;
    
    private NewOrderController newOrderController;
    
    private OrderItem selectedObject;
    
    private Material material;
    
    
    @FXML
    private ComboBox<SimpleTableObject> comboBox_printer;
    
    @FXML
    private TextField txtField_material, txtField_price, txtField_quantity, txtField_costs, txtField_weight, txtField_supportWeight, txtField_hours, txtField_minutes;
    
    @FXML
    private Button btn_selectMaterial, btn_assign, btn_cancel;
    
    @FXML
    private Label label_editedObject, label_info;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtField_quantity.textProperty().addListener((observable, oldValue, newValue) -> {             
            try{                
                if(txtField_quantity.isEditable())
                    setCostsTimeWeight(Integer.parseInt(newValue));                          
            } catch(NumberFormatException e){
                label_info.setText("Enter some positive, non-zero numeric value!");
                label_info.setTextFill(Color.web("#ff0000"));
            }
            
        });   
        
        txtField_weight.textProperty().addListener((observable, oldValue, newValue) -> {            
           //if(txtField_weight.isEditable())
               setCosts();
        });
        
        txtField_supportWeight.textProperty().addListener((observable, oldValue, newValue) -> {            
             //if(txtField_supportWeight.isEditable())
                 setCosts();
        });
        
        txtField_material.textProperty().addListener((observable, oldValue, newValue) -> {            
           setCosts();
        });
        
        btn_selectMaterial.setOnAction((event) -> {
            
            try {            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/select/SelectMaterial.fxml"));            
                Parent root1 = fxmlLoader.load();
                SelectMaterialController ctrl = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Select Material");
           
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.centerOnScreen();            
            
                stage.show();
                //stage.setAlwaysOnTop(true);            
                ctrl.setUser(user);
                ctrl.setSelectPrinterMaterialPriceController(this);
            
                ctrl.displayMaterials();
            }catch (IOException e){
            
            }
            
        });
        
        btn_assign.setOnAction((event) -> {
            try {
                int quantity, buildTime;
                double weight, supportWeight, material_remaining = MngApi.performDoubleQuery("SELECT MaterialWeights.WeightValue FROM MaterialWeights JOIN Materials ON MaterialWeights.WeightID = Materials.WeightID WHERE MaterialID=" + material.getMaterial_id().get(), user) - MngApi.performDoubleQuery("SELECT SUM(ItemWeight) FROM OrderItems WHERE ItemMaterialID=" + material.getMaterial_id().get(), user);
            
                double price, costs;
                
                boolean isEmpty = MngApi.isTextFieldEmpty(txtField_price, txtField_quantity, txtField_material, txtField_weight, txtField_supportWeight, txtField_hours, txtField_minutes);
                boolean isZero = false;
                
                if(Integer.parseInt(txtField_quantity.getText()) <= 0 || Double.parseDouble(txtField_weight.getText()) <= 0 || (Integer.parseInt(txtField_hours.getText()) <= 0 && Integer.parseInt(txtField_minutes.getText()) <= 0))isZero = true;            
                                
                if (isEmpty == true || isZero == true){
                    label_info.setText("Fields cannot be empty or negative/non-zero values.");
                    label_info.setTextFill(Color.web("#ff0000"));
                
                } else {
                    quantity = Integer.parseInt(txtField_quantity.getText());
                    
                    weight = Double.parseDouble(txtField_weight.getText());
                    supportWeight = Double.parseDouble(txtField_supportWeight.getText());
                
                    String buildTime_formatted = txtField_hours.getText() + "h " + txtField_minutes.getText() + "m";
                        buildTime = MngApi.convertToMinutes(buildTime_formatted);
                                     
                    int printerID = comboBox_printer.getValue().getId().get();
                    String printer_name = comboBox_printer.getValue().getName().get();
            
                    String[] material2 = txtField_material.getText().split(";");
                        String material_id = material2[0];
                            int materialID = Integer.parseInt(material_id);                
                        String material_type = material2[1] + " " + material2[2];
                        String material_color = material2[3];
                        
                    price = Double.parseDouble(txtField_price.getText());
                    costs = Double.parseDouble(txtField_costs.getText());       
                    
                    if((weight + supportWeight) > material_remaining) throw new IndexOutOfBoundsException();
                    
                    selectedObject.setQuantity(new SimpleIntegerProperty(quantity));
                    selectedObject.setObject_weight(new SimpleDoubleProperty(weight));
                    selectedObject.setObject_supportWeight(new SimpleDoubleProperty(supportWeight));
                    selectedObject.setObject_buildTime(new SimpleIntegerProperty(buildTime));
                    selectedObject.setObject_buildTime_formated(MngApi.convertToFormattedTime(buildTime));
                    selectedObject.setPrinter_id(new SimpleIntegerProperty(printerID));
                    selectedObject.setPrinter_name(new SimpleStringProperty(printer_name));
                    selectedObject.setMaterial_id(new SimpleIntegerProperty(materialID));
                    selectedObject.setMaterial_type(new SimpleStringProperty(material_type));
                    selectedObject.setMaterial_color(new SimpleStringProperty(material_color));
                    selectedObject.setPrice(new SimpleDoubleProperty(price));
                    selectedObject.setCosts(new SimpleDoubleProperty(costs));
                
                    newOrderController.setSelectedObjects();
                    newOrderController.refreshSelectedObjects();
                    MngApi.closeWindow(btn_assign);                    
                }
            }
            catch (NumberFormatException e) {                
                label_info.setText("Wrong number format, please check your fields.");
                label_info.setTextFill(Color.web("#ff0000"));
                e.printStackTrace();
            } catch (ArithmeticException e){
                label_info.setText("Enter some positive, non-zero value!");
                label_info.setTextFill(Color.web("#ff0000"));
            } catch (IndexOutOfBoundsException e){
                label_info.setText("Not enough material!");
                label_info.setTextFill(Color.web("#ff0000"));
            } catch (NullPointerException e){
                label_info.setText("Fill all the fields!");
                label_info.setTextFill(Color.web("#ff0000"));
            }
            
        });
        
        btn_cancel.setOnAction((event) -> {
            MngApi.closeWindow(btn_cancel);
        });
    }    
    
    public void setMaterialTxtField(Material material){
        this.material = material;
        
        int id = this.material.getMaterial_id().get();
        String type = this.material.getMaterial_manufacturer().get() + " " + this.material.getMaterial_type().get();
        String manufacturer = this.material.getMaterial_manufacturer().get();
        String color = this.material.getMaterial_color().get();
        
        txtField_material.setText(id + ";" + type + ";" + manufacturer + ";" + color);        
    }
    
    //set values at window opening
    public void setElementValues(OrderItem selectedObject){
        this.selectedObject = selectedObject;

        //set material values if there are some otherwise skip
        if(selectedObject.getMaterial_id().get() != 0) {
            this.material = Material.getMaterialByID(user, selectedObject.getMaterial_id());        
            int id = material.getMaterial_id().get();
            String type = material.getMaterial_manufacturer().get() + " " + this.material.getMaterial_type().get();
            String manufacturer = material.getMaterial_manufacturer().get();
            String color = material.getMaterial_color().get();        
            txtField_material.setText(id + ";" + type + ";" + manufacturer + ";" + color);
        }
        
        //if object id == 1 => it is 3D printing service so do not set weight, time etc
        if(selectedObject.getObject_id().get() == 1){
            txtField_quantity.setText("1");
            txtField_quantity.setEditable(false);
        //user doesnt need to interact with these fieldssince all values are loaded from database and just multiplied by quantity
        } else {            
            txtField_quantity.setText(String.valueOf(selectedObject.getQuantity().get()));
            txtField_quantity.setEditable(true);
            txtField_weight.setEditable(false);
            txtField_supportWeight.setEditable(false);
            txtField_minutes.setEditable(false);
            txtField_hours.setEditable(false);
            txtField_weight.setText(String.valueOf(selectedObject.getObject_weight().get()));
            txtField_supportWeight.setText(String.valueOf(selectedObject.getObject_supportWeight().get()));
            String[] buildTime_formatted = selectedObject.getObject_buildTime_formated().get().split(" ");
            txtField_hours.setText(String.format(Locale.UK, "%s", buildTime_formatted[0].replaceAll("[^\\d.]", "")));
            txtField_minutes.setText(String.format(Locale.UK, "%s", buildTime_formatted[1].replaceAll("[^\\d.]", "")));
        }
        
        ObservableList<SimpleTableObject> printers = FXCollections.observableArrayList(Printer.getPrintersShort(user));
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
        
        
        int printer_id = selectedObject.getPrinter_id().get();
                
        for (int i = 0; i < printers.size(); i++) {
            
            if (printer_id == printers.get(i).getId().get()){
                comboBox_printer.getSelectionModel().select(i);
            } else {
             comboBox_printer.setValue(printers.get(0));   
            }            
        } 
                
        label_editedObject.setText(selectedObject.getObject_id().get() + "; " + selectedObject.getObject_name().get());    
        txtField_price.setText(String.format(Locale.UK, "%.2f", selectedObject.getPrice().get()));
        txtField_costs.setText(String.format(Locale.UK, "%.2f", selectedObject.getCosts().get()));        
    }
    
       
    //sets costs, build time and wight when quantity is changed
    private void setCostsTimeWeight(int quantity){
        try{
            double material_price, material_shipping, material_weight, price_per_gram, weight, support_weight, total_weight, price, costs;
            int hours, minutes, build_time;
            
        
            quantity = Integer.parseInt(txtField_quantity.getText());
            if(quantity == 0){
                txtField_quantity.setText("1");
                quantity = 1;
            }
            
            //set up weights
            weight = quantity*selectedObject.getObject_weight().get();
            support_weight = quantity*selectedObject.getObject_supportWeight().get();
                total_weight = weight + support_weight;            
            txtField_weight.setText(String.valueOf(weight));
            txtField_supportWeight.setText(String.valueOf(support_weight));
            
            //set up build time            
            build_time = quantity*selectedObject.getObject_buildTime().get();
            minutes = build_time % 60;
            hours = (build_time - minutes)/60;
            txtField_hours.setText(String.valueOf(hours));
            txtField_minutes.setText(String.valueOf(minutes));
            
            //set up costs
            setCosts();
                        
        } catch (NumberFormatException e){
            label_info.setText("Wrong number format, please check your fields.");
            label_info.setTextFill(Color.web("#ff0000"));
            //e.printStackTrace();
        }

    }
    
    //calculating costs when wight is changed
    private void setCosts(){
        try{
            double material_price, material_shipping, material_weight, price_per_gram, weight, support_weight, total_weight, costs;            
            int quantity = Integer.parseInt(txtField_quantity.getText());
            
//            material_price = material.getMaterial_price().get();
//            material_shipping = material.getMaterial_shipping().get();
//            material_weight = material.getMaterial_weight().get();

            material_price = material.getMaterial_price().get();
            material_shipping = material.getMaterial_shipping().get();
            material_weight = material.getMaterial_weight().get();
                        
           
            weight = Double.parseDouble(txtField_weight.getText())*quantity;
            support_weight = Double.parseDouble(txtField_supportWeight.getText())*quantity;
                total_weight = weight + support_weight;                  
            price_per_gram = (material_price+material_shipping)/material_weight;                
            
            costs = price_per_gram*total_weight*quantity;        
            txtField_costs.setText(String.format(Locale.US, "%.2f", costs));
            
        } catch (NumberFormatException e){
            label_info.setText("Wrong number format, please check your fields.");
            label_info.setTextFill(Color.web("#ff0000"));
            //e.printStackTrace();
        } catch (NullPointerException e) {
            //It is okey if user is creating new order and material has not been set yet so there are missing data to calculate costs
            //e.printStackTrace();
        }

    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void setNewOrderController(NewOrderController newOrderController) {
        this.newOrderController = newOrderController;
    }
        
}

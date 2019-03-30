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
import com.zaxxer.hikari.HikariDataSource;
import controllers.MainController;
import controllers.orders.NewOrderController;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
    
    private HikariDataSource ds;
    
    private NewOrderController newOrderController;
    
    private OrderItem selectedObject, selectedObject_copy;
    
    private Material selectedMaterial;
    
    private MainController mainController;
        
    
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
            calculateCosts();
        });   
        
        txtField_weight.textProperty().addListener((observable, oldValue, newValue) -> {            
            try{     
                if(newValue.isEmpty())txtField_weight.setText("0");
                double quantity = Math.abs(Double.parseDouble(newValue));
                
                //Costs setting = just multiply price per gram by object total weight (support including)
                //Costs setting = just multiply price per one object - (objects + support)*pricePergram - by quantity (use absolute values)
                double obj_weight = Math.abs(Double.parseDouble(txtField_weight.getText()));
                double obj_sup_weight = Math.abs(Double.parseDouble(txtField_supportWeight.getText()));
                double price_per_gram = (selectedMaterial.getMaterial_price().get() + selectedMaterial.getMaterial_shipping().get())/selectedMaterial.getMaterial_weight().get();
                
                double costs = (obj_sup_weight + obj_weight)*price_per_gram;
                
                txtField_costs.setText("" + MngApi.round(costs, 2));
                //Time setting = user's input
            } catch(NumberFormatException e){
                label_info.setText("Enter some positive, non-zero numeric value!");
                label_info.setTextFill(Color.web("#ff0000"));
            }
        });
        
        txtField_supportWeight.textProperty().addListener((observable, oldValue, newValue) -> {            
            try{           
                if(newValue.isEmpty())txtField_supportWeight.setText("0");
                double quantity = Math.abs(Double.parseDouble(newValue));
                
                //Costs setting = just multiply price per gram by object total weight (support including)
                //Costs setting = just multiply price per one object - (objects + support)*pricePergram - by quantity (use absolute values)
                double obj_weight = Math.abs(Double.parseDouble(txtField_weight.getText()));
                double obj_sup_weight = Math.abs(Double.parseDouble(txtField_supportWeight.getText()));
                double price_per_gram = (selectedMaterial.getMaterial_price().get() + selectedMaterial.getMaterial_shipping().get())/selectedMaterial.getMaterial_weight().get();
                
                double costs = (obj_sup_weight + obj_weight)*price_per_gram;
                
                txtField_costs.setText("" + MngApi.round(costs, 2));
                //Time setting = user's input
            } catch(NumberFormatException e){
                label_info.setText("Enter some positive, non-zero numeric value!");
                label_info.setTextFill(Color.web("#ff0000"));
            }
        });
        
        txtField_material.textProperty().addListener((observable, oldValue, newValue) -> {            
            double obj_weight = Math.abs(Double.parseDouble(txtField_weight.getText()));
            double obj_sup_weight = Math.abs(Double.parseDouble(txtField_supportWeight.getText()));
            double price_per_gram = (selectedMaterial.getMaterial_price().get() + selectedMaterial.getMaterial_shipping().get())/selectedMaterial.getMaterial_weight().get();
                
            double costs = (obj_sup_weight + obj_weight)*price_per_gram;
            txtField_costs.setText("" + MngApi.round(costs, 2));            
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
                ctrl.setMainController(mainController);
                ctrl.setSelectPrinterMaterialPriceController(this);
            
                ctrl.displayMaterials();
            }catch (IOException e){
            
            }
            
        });
        
        btn_assign.setOnAction((event) -> {
            try {
                int quantity, buildTime;
                double weight, supportWeight, material_remaining = selectedMaterial.getMaterial_remaining().get();
            
                double price, costs;
                
                boolean isEmpty = MngApi.isTextFieldEmpty(txtField_price, txtField_quantity, txtField_material, txtField_weight, txtField_supportWeight, txtField_hours, txtField_minutes);
                boolean isZero = false;
                
                if(Integer.parseInt(txtField_quantity.getText()) <= 0 || Double.parseDouble(txtField_weight.getText()) <= 0 || (Integer.parseInt(txtField_hours.getText()) <= 0 && Integer.parseInt(txtField_minutes.getText()) <= 0))isZero = true;            
                                
                if (isEmpty == true || isZero == true){
                    label_info.setText("Fields cannot be empty/zero/negative/character values.");
                    label_info.setTextFill(Color.web("#ff0000"));
                
                } else {
                    quantity = Integer.parseInt(txtField_quantity.getText());
                    
                    weight = Double.parseDouble(txtField_weight.getText());
                    supportWeight = Double.parseDouble(txtField_supportWeight.getText());
                
                    String buildTime_formatted = txtField_hours.getText() + "h " + txtField_minutes.getText() + "m";
                        buildTime = MngApi.convertToMinutes(buildTime_formatted);
                                     
                    int printerID = comboBox_printer.getValue().getProperty_id().get();
                    String printer_name = comboBox_printer.getValue().getProperty_name().get();
            
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
            //reverse any changes to object and close window
            this.selectedObject = this.selectedObject_copy;
            MngApi.closeWindow(btn_cancel);
        });
    }    
    
    //in case we are editing previously defined object - there is stored only material ID, not other info so we need to get them 
    public void setMaterialTxtField(int material_id){
        this.selectedMaterial = getMaterialByID(mainController.getTv_materials().getItems(), selectedObject.getMaterial_id().get());
        
        int id = this.selectedMaterial.getMaterial_id().get();
        String type = this.selectedMaterial.getMaterial_type().get();
        String manufacturer = this.selectedMaterial.getMaterial_manufacturer().get();
        String color = this.selectedMaterial.getMaterial_color().get();
        
        txtField_material.setText(id + ";" + type + ";" + manufacturer + ";" + color);        
    }
    
    //in case we are redefining new material from "select Material" dialog window - there is stored complete information about material so we can use it as parameter
    public void setMaterialTxtField(Material material){
        this.selectedMaterial = material;
        
        int id = this.selectedMaterial.getMaterial_id().get();
        String type = this.selectedMaterial.getMaterial_type().get();
        String manufacturer = this.selectedMaterial.getMaterial_manufacturer().get();
        String color = this.selectedMaterial.getMaterial_color().get();
        
        txtField_material.setText(id + ";" + type + ";" + manufacturer + ";" + color);        
    }
        
    
    public void setFields(OrderItem selectedObject){        
        this.selectedObject = selectedObject;
        this.selectedObject_copy = this.selectedObject;
        
        this.selectedMaterial = Material.getMaterialByID(mainController.getTv_materials().getItems(), selectedObject.getMaterial_id().get());
        setComboBox();
        //by default, all fields are locked (in the fxml file), we unloack what we need via java code
        
        //first we determine if it's fresh object or already defined  object by comparingg orderItem_id values (0 = new, any different means already defined)
        //secondly, we determine if the same object is "3D printing" in general or some particlar object (for example Eiffel Tower) by comparint object_id value (1 = 3D printing, any other is particular object)
        int orderItem_id = selectedObject.getOrderItem_id().get();
        int object_id = selectedObject.getObject_id().get();
        
        switch (orderItem_id) {
            //it is new item - we need to update some fields only in case it is pre-defined object - itherwise just unlock some fields
            case 0:
                switch (object_id){
                    //it is 3D printing - in this case we dont want to edit quantity, cuz we can do only 1 3D printing. We want only edit weight, times, etc of that 3D printing
                    case 1:
                        unlockFields();
                        break;
                    //it is not 3D printing - in this case it is previously defined object with weights and times, we only want ot edit quantity - other fields must be locked
                    default:
                        txtField_weight.setText("" + selectedObject.getObject_weight().get());
                        txtField_supportWeight.setText("" + selectedObject.getObject_supportWeight().get());
                        txtField_hours.setText("" + ((selectedObject.getObject_buildTime().get() - selectedObject.getObject_buildTime().get()%60)/60));
                        txtField_minutes.setText("" + selectedObject.getObject_buildTime().get()%60);
                        txtField_quantity.setEditable(true);                        
                }                        
                break;
            //it is previously defined object - we are editing order item inside new/old order. We need to fill the fields with values of edited object + unlock some fields
            default:
                setTextFields();
                switch (object_id){
                    //it is 3D printing
                    case 1:
                        unlockFields();
                        break;
                    //it is not 3D printing
                    default:
                        txtField_quantity.setEditable(true);
                }                
        }        
    }
    
    
    private void setTextFields(){
        label_editedObject.setText(selectedObject.getObject_id().get() + "; "+ selectedObject.getObject_name().get());
        txtField_quantity.setText("" + selectedObject.getQuantity().get());
        txtField_weight.setText("" + selectedObject.getObject_weight().get());
        txtField_supportWeight.setText("" + selectedObject.getObject_supportWeight().get());
        txtField_hours.setText("" + ((selectedObject.getObject_buildTime().get() - selectedObject.getObject_buildTime().get()%60)/60));
        txtField_minutes.setText("" + selectedObject.getObject_buildTime().get()%60);
        txtField_material.setText("");
        
        //getting material
        this.selectedMaterial = getMaterialByID(mainController.getTv_materials().getItems(), selectedObject.getMaterial_id().get());
        //getting material properties
        int id = selectedMaterial.getMaterial_id().get();
        String type = selectedMaterial.getMaterial_manufacturer().get() + " " + this.selectedMaterial.getMaterial_type().get();
        String manufacturer = selectedMaterial.getMaterial_manufacturer().get();
        String color = selectedMaterial.getMaterial_color().get();        
        //setting material text field
        txtField_material.setText(id + ";" + type + ";" + manufacturer + ";" + color);
        
        txtField_price.setText("" + selectedObject.getPrice().get());
        //costs are calculated when loading order items 
        txtField_costs.setText("" + selectedObject.getCosts().get());
        
        //We need to set unit weight and print time so when user changes quantity, a unit value will be miltiplied
        double quantity = Double.parseDouble(txtField_quantity.getText());
        selectedObject.setObject_weight(new SimpleDoubleProperty(selectedObject.getObject_weight().get()/quantity));
        selectedObject.setObject_weight(new SimpleDoubleProperty(selectedObject.getObject_supportWeight().get()/quantity));
        selectedObject.setObject_buildTime(new SimpleIntegerProperty(selectedObject.getObject_buildTime().get()/(int) quantity));        
    }
    
    //this triggers only in case of pre-defined object. Because user can cahnge only quantity, it basically multiplies everything by quantity - this this method is included only in 
    //quantity change listener or material cahnge listener
    private void calculateCosts(){
        try{                
                double quantity = Math.abs(Double.parseDouble(txtField_quantity.getText()));
                selectedObject.setQuantity(new SimpleIntegerProperty((int) quantity));
                //Costs setting = just multiply price per one object - (objects + support)*pricePergram - by quantity (use absolute values)
                double obj_weight = Math.abs(Double.parseDouble(txtField_weight.getText()));
                double obj_sup_weight = Math.abs(Double.parseDouble(txtField_supportWeight.getText()));
                double price_per_gram = (selectedMaterial.getMaterial_price().get() + selectedMaterial.getMaterial_shipping().get())/selectedMaterial.getMaterial_weight().get();
                
                double costs = quantity*(obj_sup_weight + obj_weight)*price_per_gram;
                
                txtField_costs.setText("" + MngApi.round(costs, 2));
                
                //Time setting = just multiply time by quantity
                int build_time = Math.abs((int) quantity*selectedObject.getObject_buildTime().get());
                
                txtField_hours.setText("" + ((build_time - build_time%60)/60));
                txtField_minutes.setText("" + build_time%60);
                
                //Weight settings - just multiply fields by quantity
                txtField_weight.setText("" + selectedObject.getObject_weight().get()*quantity);
                txtField_supportWeight.setText("" + selectedObject.getObject_supportWeight().get()*quantity);
                
            } catch(NumberFormatException e){
                label_info.setText("Enter some positive, non-zero numeric value!");
                label_info.setTextFill(Color.web("#ff0000"));
            } 
    }
    
    private void unlockFields(){
        txtField_quantity.setText("1");
        txtField_weight.setEditable(true);
        txtField_supportWeight.setEditable(true);
        txtField_minutes.setEditable(true);
        txtField_hours.setEditable(true);
        txtField_price.setEditable(true);
    }
    
    
    public void setComboBox(){
        //set list of printers
            ObservableList<SimpleTableObject> printers = FXCollections.observableArrayList(getPrintersShort(mainController.getTv_printers()));
            
            comboBox_printer.setItems(printers);
            comboBox_printer.setVisibleRowCount(7);
            comboBox_printer.setConverter(new StringConverter<SimpleTableObject>() {
                @Override
                public String toString(SimpleTableObject object) {
                    return object.getProperty_name().get();
                }

                @Override
                public SimpleTableObject fromString(String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            //set previously defined printer, if there is any - otherwise set default first printer
            int printer_id = selectedObject.getPrinter_id().get();                
            for (int i = 0; i < printers.size(); i++) {
            
                if (printer_id == printers.get(i).getProperty_id().get()){
                    comboBox_printer.getSelectionModel().select(i);
                } else {
                    comboBox_printer.setValue(printers.get(0));   
                }            
            }
    }
        
    //sets costs, build time and weight when quantity is changed
    private void setCostsTimeWeight(){
        try{
            double  weight, support_weight, total_weight;
            double material_price, material_shipping, material_weight, price_per_gram, costs;
            int hours, minutes, build_time, quantity = Integer.parseInt(txtField_quantity.getText());
            
            //check for quantity to be 0
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
            material_price = selectedMaterial.getMaterial_price().get();
            material_shipping = selectedMaterial.getMaterial_shipping().get();
            material_weight = selectedMaterial.getMaterial_weight().get();
            
            price_per_gram = (material_price+material_shipping)/material_weight;                
            
            costs = price_per_gram*total_weight;        
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
    
    public Material getMaterialByID(ObservableList<Material> materials, int material_id){
        
        Material material = null;
        
        for (int i = 0; i < materials.size(); i++) {
            
            material = materials.get(i);
            if(material.getMaterial_id().get() == material_id)return material;
            
        }        
        
        return material;
    }
    
    private List<SimpleTableObject> getPrintersShort(TableView<Printer> tv_printers) {
        
        //Create list
        List<SimpleTableObject> allPrinters = new ArrayList<>();
               
        
        try {
            
            ObservableList<Printer> printers = tv_printers.getItems();
            
            SimpleIntegerProperty id;
            SimpleStringProperty name;
            
            for (int i = 0; i < printers.size(); i++) {
                
                id = new SimpleIntegerProperty(printers.get(i).getPrinter_id().get());
                name = new SimpleStringProperty(printers.get(i).getPrinter_name().get());
                
                allPrinters.add(new SimpleTableObject(id, id, name));
                
            }
            
         
            
        }catch (NullPointerException e){
            //signIn(event);
            e.printStackTrace();
        }
    return allPrinters;
    }
    
    public void setDs(HikariDataSource ds) {
        this.ds = ds;
    }
    
    public void setNewOrderController(NewOrderController newOrderController) {
        this.newOrderController = newOrderController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }      
}

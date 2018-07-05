/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.select;

import classes.Material;
import classes.MngApi;
import classes.OrderItem;
import classes.User;
import controllers.orders.NewOrderController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Erik PC
 */
public class SelectMaterialController implements Initializable {

    private User user;
    
    private SelectPrinterMaterialPriceController selectPrinterMaterialPriceController;
    
    
    @FXML
    private TableView<Material> tv_materials;
    
    @FXML
    private TableColumn<Material, String> material_col_color, material_col_manufacturer, material_col_type, material_col_distributor, material_col_purchaseDate;
    
    @FXML
    private TableColumn<Material, Integer> material_col_id, material_col_weight;
    
    @FXML
    private TableColumn<Material, Double> material_col_shipping,material_col_price, material_col_used, material_col_diameter; 
    
    @FXML
    private Button btn_select, btn_cancel;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_cancel.setOnAction((event) -> {            
            MngApi.closeWindow(btn_cancel);            
        });
        
        tv_materials.setRowFactory( tv -> {
            TableRow<Material> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    selectPrinterMaterialPriceController.setMaterial(tv_materials.getSelectionModel().getSelectedItem());
                    MngApi.closeWindow(btn_select);
                    TextField quantity = selectPrinterMaterialPriceController.getTxtField_quantity();
                    if(MngApi.isTextFieldEmpty(quantity) || !MngApi.isNumber(quantity.getText()))quantity.setText("1");
                    selectPrinterMaterialPriceController.setCosts(tv_materials.getSelectionModel().getSelectedItem());
                }
            });
            return row;
        });
        
        btn_select.setOnAction((event) -> {            
            selectPrinterMaterialPriceController.setMaterial(tv_materials.getSelectionModel().getSelectedItem());   
            selectPrinterMaterialPriceController.setCosts(tv_materials.getSelectionModel().getSelectedItem());
            MngApi.closeWindow(btn_select);            
        });
    }    

    
    public void displayMaterials(){
        //Create list of orders
        ObservableList<Material> materialList = FXCollections.observableArrayList(Material.getMaterials(user));
        
        material_col_color.setCellValueFactory((param) -> {return param.getValue().getMaterial_color();});
        material_col_distributor.setCellValueFactory((param) -> {return param.getValue().getMaterial_distributor();});
        material_col_manufacturer.setCellValueFactory((param) -> {return param.getValue().getMaterial_manufacturer();});
        material_col_purchaseDate.setCellValueFactory((param) -> {return param.getValue().getMaterial_purchaseDate();});
        material_col_type.setCellValueFactory((param) -> {return param.getValue().getMaterial_type();});
        
        
        material_col_id.setCellValueFactory((param) -> {return param.getValue().getMaterial_id().asObject();});        
        material_col_weight.setCellValueFactory((param) -> {return param.getValue().getMaterial_weight().asObject();});        
                
        material_col_diameter.setCellValueFactory((param) -> {return param.getValue().getMaterial_diameter().asObject();});
        material_col_price.setCellValueFactory((param) -> {return param.getValue().getMaterial_price().asObject();});        
        material_col_shipping.setCellValueFactory((param) -> {return param.getValue().getMaterial_shipping().asObject();});
        material_col_used.setCellValueFactory((param) -> {return param.getValue().getMaterial_used().asObject();});

        
        //Centering content
        material_col_color.setStyle("-fx-alignment: CENTER;");
        material_col_distributor.setStyle("-fx-alignment: CENTER;");        
        material_col_manufacturer.setStyle("-fx-alignment: CENTER;");
        material_col_purchaseDate.setStyle("-fx-alignment: CENTER;");
        material_col_type.setStyle("-fx-alignment: CENTER;");
        
        material_col_id.setStyle("-fx-alignment: CENTER;");
        
        material_col_price.setStyle("-fx-alignment: CENTER;");
        material_col_shipping.setStyle("-fx-alignment: CENTER;");
        material_col_used.setStyle("-fx-alignment: CENTER;");        
        material_col_weight.setStyle("-fx-alignment: CENTER;");        
        material_col_diameter.setStyle("-fx-alignment: CENTER;");
        
        tv_materials.setItems(materialList);        
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setSelectPrinterMaterialPriceController(SelectPrinterMaterialPriceController selectPrinterMaterialPriceController) {
        this.selectPrinterMaterialPriceController = selectPrinterMaterialPriceController;
    }
    
}

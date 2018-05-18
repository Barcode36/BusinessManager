/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.materials;

import classes.User;
import controllers.MainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class NewMaterialController implements Initializable {

    private User user;
    
    private MainController mainController;
    
    @FXML
    private Label material_label_id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setMaterial_label_id_value(int id) {
        this.material_label_id.setText(String.valueOf(id));        
    }
    
    public int getMaterial_label_id_value(){
        return Integer.parseInt(this.material_label_id.getText());
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

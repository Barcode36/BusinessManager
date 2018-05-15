/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.orders;

import classes.MngApi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author edemko
 */
public class ConnectionLostController implements Initializable {
    
    @FXML
    private Button btn_close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_close.setOnAction((event) -> {            
            Platform.exit();
        });
    }    
   
}

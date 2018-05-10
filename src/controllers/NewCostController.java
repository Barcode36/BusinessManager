/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.User;
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
public class NewCostController implements Initializable {

    private User user;
    
    @FXML
    private Label cost_label_id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Label getCost_label_id() {
        return cost_label_id;
    }

    public void setCost_label_id_value(int id) {
        this.cost_label_id.setText(String.valueOf(id));
    }
    
    
    
}

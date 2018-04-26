/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Erik PC
 */
public class MainController implements Initializable {
    //login credentials which are set in loginController through setCredentials method
    private String user = null;
    private String pass = null;
    private String ipAddress = null;
    private String dbName = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        //we use this method during logining process - it passes credetials to main controller for further use and also it sets login label (so user can see under which account he is logged in)
    public void setCredentials(String user, String pass, String ipAddress, String dbName){
        this.user = user;
        this.pass = pass;
        this.ipAddress = ipAddress;
        this.dbName = dbName;
    }//setCredentials() end

    public void displayOrdersTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

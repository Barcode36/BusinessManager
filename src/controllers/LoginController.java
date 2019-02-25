package controllers;

import classes.Customer;
import classes.Material;
import classes.MngApi;
import com.zaxxer.hikari.HikariDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {
    @FXML
    private TextField ipAddress, uName;
    @FXML
    private PasswordField uPasswd;
    @FXML
    private Button btn_login;    
    
    private HikariDataSource ds = new HikariDataSource();
    

    
    //signs in user into database and displays application's main window with loaded "Orders" table
    public void signIn(ActionEvent event) {       
        try{
            
            MngApi.closeWindow(btn_login); //closes parent window of specified node
                    
            ds.setJdbcUrl("jdbc:mariadb://" + ipAddress.getText() + ":3306/" + uName.getText());
            ds.setUsername(uName.getText());
            ds.setPassword(uPasswd.getText());
            ds.addDataSourceProperty("cachePrepStmts", "true");
            ds.addDataSourceProperty("prepStmtCacheSize", "250");
            ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            ds.setMaximumPoolSize(5);
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));            
            Parent root1 = fxmlLoader.load();
            MainController ctrl = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setTitle("PrintED");
            stage.setMinHeight(680);
            stage.setMinWidth(1139);
           
            stage.setScene(new Scene(root1));
            stage.setMaximized(true);
            
            //passing credentials to main controller
            ctrl.setDataSource(ds);            
            stage.show();            
            
            //when we first open up main windows, we need to load all orders - that's default view
            ctrl.setCommonCustomerProperties(Customer.getCommonCustomerProperties(ds));
            ctrl.setCommonMaterialProperties(Material.getCommonMaterialProperties(ds));
            ctrl.runService(ctrl.getService_refreshAll());
                
            
        } catch (IOException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            signIn(event);
            e.printStackTrace();
        }

    }//signIn end
    
}//loginController end
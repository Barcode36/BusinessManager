/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Erik PC
 */
public class BusinessManager extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load Login screen
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"));
        primaryStage.setTitle("Log In");
        primaryStage.setScene(new Scene(root, 465, 270));
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

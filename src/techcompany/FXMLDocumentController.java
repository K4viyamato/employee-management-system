/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techcompany;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author kavindu
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;



    @FXML
    private TextField username;
   
    //Database Tools
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result; 
    
    private double x = 0;
    private double y = 0;

    
    public void loginAdmin(){
        
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        
        connect = database.connectDb();
        
        try{
            
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            
            result = prepare.executeQuery();
            
            Alert alert;
            
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fields!");
                
                alert.showAndWait();
                
            }else{
                if(result.next()){
                    
                    getData.username = username.getText();
                    
                    getData.userRole = result.getString("role");
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully logged in");
                    alert.showAndWait();
                    
                    loginBtn.getScene().getWindow().hide();
                    
                    
                    
                    
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event)->{
                        
                        x = event.getSceneX();
                        y = event.getSceneY();
                    
                    });
                    
                    root.setOnMouseDragged((MouseEvent event)->{
                           
                        stage.setX(event.getScreenX() -x );
                        stage.setY(event.getScreenY() -y );
                        
                        
                    });
                    
                    
                    
                    stage.initStyle(StageStyle.TRANSPARENT); 
                    stage.setScene(scene);
                    stage.show();
                
                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Username or Password");
                    alert.showAndWait();
                    
                }
            
            }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void close(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Owner;
import model.Customer;
import app.Main;
import java.io.IOException;


public class LoginScreenController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;
    
    
    
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        
        if("admin".equals(username) && "admin".equals(password)) {
            
            loadScreen("OwnerStartScreen.fxml");
        } else {
            
            Customer customer = Main.bookstore.findCustomer(username, password);
if (customer != null) {
    CustomerStartScreenController.loggedInCustomer = customer;


    try {
    Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerStartScreen.fxml"));
    Stage stage = (Stage) loginButton.getScene().getWindow();
    stage.setScene(new Scene(root, 800, 600));
    } catch (IOException e) {
    e.printStackTrace();
    }

   
}else {
                errorLabel.setText("Invalid credentials. Please try again.");
            }
        }
    }
    
    private void loadScreen(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

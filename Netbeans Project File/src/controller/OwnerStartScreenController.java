package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OwnerStartScreenController {
    @FXML
    private Button booksButton;
    @FXML
    private Button customersButton;
    @FXML
    private Button logoutButton;
    
    @FXML
    private void handleBooks(ActionEvent event) {
        try {
            Parent booksScreen = FXMLLoader.load(getClass().getResource("/view/OwnerBooksScreen.fxml"));
            Stage stage = (Stage) booksButton.getScene().getWindow();
            stage.setScene(new Scene(booksScreen));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleCustomers(ActionEvent event) {
        try {
            Parent customersScreen = FXMLLoader.load(getClass().getResource("/view/OwnerCustomersScreen.fxml"));
            Stage stage = (Stage) customersButton.getScene().getWindow();
            stage.setScene(new Scene(customersScreen));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent loginScreen = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(loginScreen));
              
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

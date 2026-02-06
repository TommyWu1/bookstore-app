package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;
import app.Main;

public class OwnerCustomersScreenController {

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer, String> usernameColumn;

    @FXML
    private TableColumn<Customer, String> passwordColumn;

    @FXML
    private TableColumn<Customer, Integer> pointsColumn;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button backButton;

    private ObservableList<Customer> customersList;

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        customersList = FXCollections.observableArrayList(Main.bookstore.getCustomers());
        customersTable.setItems(customersList);
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            Customer newCustomer = new Customer(username, password);
            Main.bookstore.getCustomers().add(newCustomer); 
            customersList.add(newCustomer);                 

            usernameField.clear();
            passwordField.clear();
        } else {
            showAlert("Please fill in both username and password.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            Main.bookstore.getCustomers().remove(selectedCustomer);
            customersList.remove(selectedCustomer);
        } else {
            showAlert("Please select a customer to delete.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent ownerStart = FXMLLoader.load(getClass().getResource("/view/OwnerStartScreen.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(ownerStart, 800, 600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bookstore");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package controller;

import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Book;
import model.Customer;
import app.Main;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerStartScreenController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> nameColumn;

    @FXML
    private TableColumn<Book, Double> priceColumn;

    @FXML
    private TableColumn<Book, Boolean> selectColumn;

    @FXML
    private Button buyButton;
    @FXML
    private Button redeemButton;
    @FXML
    private Button logoutButton;

    // Logged-in user reference
    public static Customer loggedInCustomer;

    @FXML
    public void initialize() {
        
        booksTable.setEditable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        selectColumn.setCellFactory(col -> {
            CheckBoxTableCell<Book, Boolean> cell = new CheckBoxTableCell<>(index -> {
                Book b = booksTable.getItems().get(index);
                BooleanProperty bp = new SimpleBooleanProperty(b.isSelected());
                bp.addListener((obs, wasSelected, isNowSelected) -> {
                    b.setSelected(isNowSelected);
                });
                return bp;
            });
            cell.setEditable(true);
            return cell;
        });
        selectColumn.setEditable(true);

        
        booksTable.setItems(FXCollections.observableArrayList(Main.bookstore.getBooks()));
        booksTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        if (loggedInCustomer != null) {
            welcomeLabel.setText("Welcome " + loggedInCustomer.getUsername() +
                ". You have " + loggedInCustomer.getPoints() +
                " points. Status: " + loggedInCustomer.getStatus().getState());
        }
    }

    @FXML
    private void handleBuy(ActionEvent event) {
       
        List<Book> checkedBooks = booksTable.getItems()
            .stream()
            .filter(Book::isSelected)
            .collect(Collectors.toList());

        if (checkedBooks.isEmpty()) {
            showAlert("No books selected to buy!");
            return;
        }

        
        double totalCost = checkedBooks.stream().mapToDouble(Book::getPrice).sum();

       
        CustomerCostScreenController.selectedBooks = checkedBooks;
        CustomerCostScreenController.totalCost = totalCost;
        CustomerCostScreenController.redeemMode = false; 

       
        goToCostScreen();
    }

    @FXML
    private void handleRedeem(ActionEvent event) {
        
        List<Book> checkedBooks = booksTable.getItems()
            .stream()
            .filter(Book::isSelected)
            .collect(Collectors.toList());

        if (checkedBooks.isEmpty()) {
            showAlert("No books selected to redeem!");
            return;
        }

       
        double totalCost = checkedBooks.stream().mapToDouble(Book::getPrice).sum();

        
        CustomerCostScreenController.selectedBooks = checkedBooks;
        CustomerCostScreenController.totalCost = totalCost;
        CustomerCostScreenController.redeemMode = true; 

        
        goToCostScreen();
    }

    private void goToCostScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerCostScreen.fxml"));
            Stage stage = (Stage) buyButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
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

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

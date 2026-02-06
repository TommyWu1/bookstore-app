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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Book;
import app.Main;

public class OwnerBooksScreenController {
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> nameColumn;
    @FXML
    private TableColumn<Book, Double> priceColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;

    private ObservableList<Book> booksList;

    @FXML
    public void initialize() {
       
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        booksList = FXCollections.observableArrayList(Main.bookstore.getBooks());
        booksTable.setItems(booksList);
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        String name = nameField.getText();
        String priceText = priceField.getText();
        try {
            double price = Double.parseDouble(priceText);
            Book book = new Book(name, price);
            Main.bookstore.getBooks().add(book);
            booksList.add(book);
        } catch (NumberFormatException e) {
           
            System.out.println("Invalid price format.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Main.bookstore.getBooks().remove(selectedBook);
            booksList.remove(selectedBook);
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
}

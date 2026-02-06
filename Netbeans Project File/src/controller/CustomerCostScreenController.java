package controller;

import static controller.CustomerStartScreenController.loggedInCustomer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Book;
import model.Customer;

import java.io.IOException;
import java.util.List;

public class CustomerCostScreenController {

    
    public static List<Book> selectedBooks;
    public static double totalCost;
    public static boolean redeemMode;

    @FXML
    private Label titleLabel;

    @FXML
    private Label infoLabel;  

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    private Customer customer;

    
    private int usedPoints;       
    private double leftoverCost;  

    @FXML
    public void initialize() {
     
        this.customer = CustomerStartScreenController.loggedInCustomer;

        if (customer == null || selectedBooks == null || selectedBooks.isEmpty()) {
            infoLabel.setText("No data found...");
            return;
        }

        if (!redeemMode) {
           
            titleLabel.setText("Confirm Purchase");
            infoLabel.setText(
                "Total Cost: $" + totalCost + "\n" +
                "You will earn " + (int)(totalCost * 10) + " points."
            );
        } else {
            
            titleLabel.setText("Redeem Points");

            int neededPoints = (int)(totalCost * 100);      
            int userPoints = customer.getPoints();          
            usedPoints = Math.min(userPoints, neededPoints); 
            double coverageDollars = usedPoints / 100.0;     
            leftoverCost = totalCost - coverageDollars;      

            if (leftoverCost <= 0) {
                
                infoLabel.setText(
                    "Full Redemption\n" +
                    "Cost: $" + totalCost + " and " + neededPoints + " points are needed.\n" +
                    "You have " + userPoints + " points."
                );
            } else {
                
                infoLabel.setText(
                    "Partial Redemption:\n" +
                    "Book cost: $" + totalCost + "\n" +
                    "Points needed to purchase book(s): " + neededPoints + "\n" +
                    "You only have " + userPoints + " points.\n\n" +
                    "Points used: " + usedPoints + " (covers $" + coverageDollars + ")\n" +
                    "Leftover cost: $" + String.format("%.2f",leftoverCost) + "\n"
                );
            }
        }
    }

    @FXML
    private void handleConfirm(ActionEvent event) {
        if (customer == null || selectedBooks == null || selectedBooks.isEmpty()) {
            showAlert("No customer or no books selected.");
            return;
        }

        if (!redeemMode) {
            
            int earnedPoints = (int)(totalCost * 10);
            customer.setPoints(customer.getPoints() + earnedPoints);
            customer.updateStatus();
            showAlert(
                "Purchase successful!\n" +
                "Spent $" + totalCost + 
                "\nEarned " + earnedPoints + " points.\n" +
                "Now you have " + customer.getPoints() + " points." + "\nStatus: " + loggedInCustomer.getStatus().getState()
            );
        } else {
            
            int oldPoints = customer.getPoints();
            customer.setPoints(oldPoints - usedPoints); 

            if (leftoverCost > 0) {
                
                int extraPoints = (int)(leftoverCost * 10);
                customer.setPoints(customer.getPoints() + extraPoints);

                showAlert(
                    "Partial redemption:\n" +
                    "Used: " + usedPoints + " points and $" + (usedPoints / 100.0) + " was covered\n" +
                    "Paid: $" + leftoverCost + " and " + extraPoints + " points were earned.\n" +
                    "Total Points: " + customer.getPoints() + "\nStatus: " + loggedInCustomer.getStatus().getState()
                );
            } else {
                
                showAlert(
                    "Full redemption!\n" +
                    "Used: " + usedPoints + " points and $" + totalCost + " was covered\n" +
                    "Total Points: " + customer.getPoints() + "\nStatus: " + loggedInCustomer.getStatus().getState()
                );
            }

            customer.updateStatus();
        }

        
        goBackToCustomerScreen();
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        goBackToCustomerScreen();
    }

    private void goBackToCustomerScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerStartScreen.fxml"));
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bookstore");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

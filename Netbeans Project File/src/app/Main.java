
// LOGIN AS OWNER

// Username: admin
// Password: admin

package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bookstore;

public class Main extends Application {
    public static Bookstore bookstore = new Bookstore();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1) Load data on startup
        bookstore.loadData();

        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        primaryStage.setTitle("Book Store App");

        // 2) Custom window size if desired
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // 3) Save data on close
        primaryStage.setOnCloseRequest(event -> {
            bookstore.saveData();
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

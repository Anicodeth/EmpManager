import database.Database;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import views.DetailsWindow;
import views.RegisterWindow;

public class App extends Application {

    public static void main(String[] args) {
        Database.createDatabase();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        RegisterWindow registerWindow = new RegisterWindow();
        DetailsWindow detailsWindow = new DetailsWindow();
        primaryStage.setTitle("Employee Management System");
        Image image = new Image(getClass().getResourceAsStream("./assets/Emp.png")); // Replace with the actual image file

        ImageView imageView = new ImageView(image);
        Button registerButton = new Button("Register Employee");
        Button detailsButton = new Button("Employee Details");

        // Add a style class to your buttons
        registerButton.getStyleClass().add("button-styles");
        detailsButton.getStyleClass().add("button-styles");

        registerButton.setOnAction(e -> registerWindow.openRegisterWindow());
        detailsButton.setOnAction(e -> detailsWindow.openDetailsWindow());

        VBox vbox = new VBox(10); // 10 is the spacing between buttons
        vbox.setAlignment(Pos.CENTER); // Center the buttons
        vbox.getStyleClass().add("stack-pane"); // Add a style class to your root stack pane
        vbox.getChildren().addAll(imageView, registerButton, detailsButton);

        // Link the CSS file to the scene
        Scene scene = new Scene(new StackPane(vbox), 600, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

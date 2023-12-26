import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System");

        Button registerButton = new Button("Register Employee");
        Button detailsButton = new Button("Employee Details");

        // Add a style class to your buttons
        registerButton.getStyleClass().add("button-styles");
        detailsButton.getStyleClass().add("button-styles");

        registerButton.setOnAction(e -> openRegisterWindow());
        detailsButton.setOnAction(e -> openDetailsWindow());

        // Use a VBox for organizing buttons vertically
        VBox vbox = new VBox(10); // 10 is the spacing between buttons
        vbox.getStyleClass().add("stack-pane"); // Add a style class to your root stack pane
        vbox.getChildren().addAll(registerButton, detailsButton);

        // Link the CSS file to the scene
        Scene scene = new Scene(new StackPane(vbox), 300, 200);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openRegisterWindow() {
        Stage registerStage = new Stage();
        registerStage.setTitle("Register Employee");
        // Add the necessary components for the register window

        StackPane registerRoot = new StackPane();
        registerStage.setScene(new Scene(registerRoot, 300, 200));
        registerStage.show();
    }

    private void openDetailsWindow() {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Employee Details");
        // Add the necessary components for the details window

        StackPane detailsRoot = new StackPane();
        detailsStage.setScene(new Scene(detailsRoot, 300, 200));
        detailsStage.show();
    }
}

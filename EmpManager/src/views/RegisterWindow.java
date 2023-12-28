package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterWindow {

    public void openRegisterWindow() {
        Stage registerStage = new Stage();
        registerStage.setTitle("Register Employee");

        // Load your image
        //Image image = new Image(getClass().getResourceAsStream("your-image-file.jpg")); // Replace with the actual image file

        // Create an ImageView
        //ImageView imageView = new ImageView(image);

        // Use a VBox for organizing components vertically
        VBox registerVBox = new VBox(10);
        registerVBox.setAlignment(Pos.CENTER); // Center the components
        registerVBox.getChildren().addAll(new Button("Other Components"));

        // Set the VBox as the root of the scene
        registerStage.setScene(new Scene(registerVBox, 600, 500));
        registerStage.show();
    }
    
}

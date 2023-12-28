package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailsWindow {
    public void openDetailsWindow() {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Employee Details");

        // Load your image
        Image image = new Image(getClass().getResourceAsStream("../assets/Emp.png")); // Replace with the actual image file

        // Create an ImageView
        ImageView imageView = new ImageView(image);

        // Use a VBox for organizing components vertically
        VBox detailsVBox = new VBox(10);
        detailsVBox.setAlignment(Pos.CENTER); // Center the components
        //detailsVBox.getChildren().addAll(imageView, new Button("Other Components"));

        // Set the VBox as the root of the scene
        detailsStage.setScene(new Scene(detailsVBox, 600, 500));
        detailsStage.show();
    }
}

package views;

import database.Database;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;

public class RegisterWindow {

    public void openRegisterWindow() {
        Stage registerStage = new Stage();
        registerStage.setTitle("Register Employee");

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameTextField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameTextField = new TextField();

        Label positionLabel = new Label("Position:");
        TextField positionTextField = new TextField();

        Label addressLabel = new Label("Address:");
        TextField addressTextField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();

        Label phoneLabel = new Label("Phone:");
        TextField phoneTextField = new TextField();

        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobDatePicker = new DatePicker();

        Label salaryLabel = new Label("Salary:");
        TextField salaryTextField = new TextField();

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            // Call the method to add an employee to the database
            addEmployee(
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    positionTextField.getText(),
                    addressTextField.getText(),
                    emailTextField.getText(),
                    phoneTextField.getText(),
                    Date.valueOf(dobDatePicker.getValue()),
                    Double.parseDouble(salaryTextField.getText())
            );
        });

        VBox registerVBox = new VBox(10);
        registerVBox.setAlignment(Pos.CENTER);
        registerVBox.getChildren().addAll(
                firstNameLabel, firstNameTextField,
                lastNameLabel, lastNameTextField,
                positionLabel, positionTextField,
                addressLabel, addressTextField,
                emailLabel, emailTextField,
                phoneLabel, phoneTextField,
                dobLabel, dobDatePicker,
                salaryLabel, salaryTextField,
                submitButton
        );

        registerStage.setScene(new Scene(registerVBox, 600, 500));
        registerStage.show();
    }

    // Method to add an employee to the database
    private void addEmployee(String firstName, String lastName, String position,
                             String address, String email, String phone, Date dob, double salary) {
        Database.addEmployee(firstName, lastName, position, address, email, phone, dob, salary);
    }
}

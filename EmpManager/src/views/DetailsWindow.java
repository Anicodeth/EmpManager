package views;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Employee;

public class DetailsWindow {

    private TableView<Employee> tableView = new TableView<>();
    private Database database = new Database();


    public void openDetailsWindow() {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Employee Details");

        // Create TableView
        TableView<Employee> tableView = createTableView();

        // Search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });

        // Button for updating selected employee
        Button updateButton = new Button("Update");
        updateButton.getStyleClass().add("button-styles");
        updateButton.setOnAction(event -> {
            Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                openEditWindow(selectedEmployee);
            }
        });

        // Button for deleting selected employee
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("button-styles");
        deleteButton.setOnAction(event -> {
            Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                Database.deleteEmployee(selectedEmployee.getId());
                refreshTable(tableView);
            }
        });

        // Use an HBox for organizing search components horizontally
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getChildren().add(searchField);

        // Use a VBox for organizing components vertically
        VBox detailsVBox = new VBox(10);
        detailsVBox.setAlignment(Pos.CENTER); // Center the components
        detailsVBox.getChildren().addAll(searchBox, tableView, updateButton, deleteButton);

        Scene scene = new Scene(detailsVBox, 700, 550);
        scene.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
        // Set the VBox as the root of the scene
        detailsStage.setScene(scene);
        detailsStage.show();
    }
    private void filterTable(String searchTerm) {
        ObservableList<Employee> filteredList = FXCollections.observableArrayList();

        for (Employee employee : Database.getAllEmployees()) {
            if (employee.getFirstName().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredList.add(employee);
            }
        }

        tableView.setItems(filteredList);
    }
    private TableView<Employee> createTableView() {
        
        refreshTable(tableView);
        // Create TableColumn instances
        TableColumn<Employee, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Employee, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Employee, String> positionCol = new TableColumn<>("Position");
        TableColumn<Employee, String> addressCol = new TableColumn<>("Address");
        TableColumn<Employee, String> emailCol = new TableColumn<>("Email");
        TableColumn<Employee, String> phoneCol = new TableColumn<>("Phone");
        TableColumn<Employee, String> dobCol = new TableColumn<>("Date of Birth");
        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");

        // Define the property value factories for each column
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Add columns to the table
        tableView.getColumns().addAll(firstNameCol, lastNameCol, positionCol, addressCol,
                emailCol, phoneCol, dobCol, salaryCol);

        // Populate the table with data from the database
        refreshTable(tableView);

        return tableView;
    }

    private void openEditWindow(Employee employee) {
        Stage editStage = new Stage();
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.setTitle("Edit Employee");

        // Create labels and text fields for editing
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label positionLabel = new Label("Position:");
        Label addressLabel = new Label("Address:");
        Label emailLabel = new Label("Email:");
        Label phoneLabel = new Label("Phone:");
        Label dobLabel = new Label("Date of Birth:");
        Label salaryLabel = new Label("Salary:");

        TextField firstNameTextField = new TextField(employee.getFirstName());
        TextField lastNameTextField = new TextField(employee.getLastName());
        TextField positionTextField = new TextField(employee.getPosition());
        TextField addressTextField = new TextField(employee.getAddress());
        TextField emailTextField = new TextField(employee.getEmail());
        TextField phoneTextField = new TextField(employee.getPhone());
        TextField dobTextField = new TextField(employee.getDob().toString());
        TextField salaryTextField = new TextField(String.valueOf(employee.getSalary()));

        // Create a grid for organizing components
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Add labels and text fields to the grid
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameTextField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameTextField, 1, 1);
        grid.add(positionLabel, 0, 2);
        grid.add(positionTextField, 1, 2);
        grid.add(addressLabel, 0, 3);
        grid.add(addressTextField, 1, 3);
        grid.add(emailLabel, 0, 4);
        grid.add(emailTextField, 1, 4);
        grid.add(phoneLabel, 0, 5);
        grid.add(phoneTextField, 1, 5);
        grid.add(dobLabel, 0, 6);
        grid.add(dobTextField, 1, 6);
        grid.add(salaryLabel, 0, 7);
        grid.add(salaryTextField, 1, 7);

        // Button for updating employee details
        Button updateButton = new Button("Update");
        updateButton.setOnAction(event -> {
            // Update the employee details in the database
            updateEmployeeDetails(employee, firstNameTextField.getText(), lastNameTextField.getText(),
                    positionTextField.getText(), addressTextField.getText(), emailTextField.getText(),
                    phoneTextField.getText(), dobTextField.getText(), salaryTextField.getText());
            editStage.close();
        });

        // Add components to the scene
        grid.add(updateButton, 1, 8);

        // Set the grid as the root of the scene
        Scene editScene = new Scene(grid, 400, 400);
        editStage.setScene(editScene);

        // Show the edit window
        editStage.showAndWait();
    }

    private void updateEmployeeDetails(Employee employee, String firstName, String lastName, String position,
                                       String address, String email, String phone, String dob, String salary) {
        
        try {
            Database.updateEmployee(employee.getId(), firstName, lastName, position,
                    address, email, phone, java.sql.Date.valueOf(dob), Double.parseDouble(salary));
            refreshTable(tableView); // Refresh the table after updating
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    private void refreshTable(TableView<Employee> tableView) {
        ObservableList<Employee> employees = FXCollections.observableArrayList(Database.getAllEmployees());
        tableView.setItems(employees);
    }
}

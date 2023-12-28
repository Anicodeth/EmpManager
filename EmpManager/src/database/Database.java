package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class Database {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empmanager";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Create Employee table with more detailed fields
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employee (\n"
                    + "id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"
                    + "first_name TEXT,\n"
                    + "last_name TEXT,\n"
                    + "position TEXT,\n"
                    + "address TEXT,\n"
                    + "email TEXT,\n"
                    + "phone TEXT,\n"
                    + "dob DATE,\n"
                    + "salary REAL\n"
                    + ");";

            statement.execute(createTableSQL);

            System.out.println("Database and table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static void addEmployee(String firstName, String lastName, String position,
                                   String address, String email, String phone, Date dob, double salary) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO employee (first_name, last_name, position, address, email, phone, dob, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, position);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phone);
            preparedStatement.setDate(7, dob);
            preparedStatement.setDouble(8, salary);

            preparedStatement.executeUpdate();

            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employee")) {

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("position"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getDate("dob"),
                        resultSet.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Employee getEmployeeById(int employeeId) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM employee WHERE id = ?")) {

            preparedStatement.setInt(1, employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Employee(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("position"),
                            resultSet.getString("address"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getDate("dob"),
                            resultSet.getDouble("salary")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateEmployee(int employeeId, String firstName, String lastName, String position,
                                      String address, String email, String phone, Date dob, double salary) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE employee SET first_name = ?, last_name = ?, position = ?, address = ?, email = ?, phone = ?, dob = ?, salary = ? WHERE id = ?")) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, position);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phone);
            preparedStatement.setDate(7, dob);
            preparedStatement.setDouble(8, salary);
            preparedStatement.setInt(9, employeeId);

            preparedStatement.executeUpdate();

            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(int employeeId) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id = ?")) {

            preparedStatement.setInt(1, employeeId);

            preparedStatement.executeUpdate();

            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

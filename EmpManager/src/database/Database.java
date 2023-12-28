package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // Update the JDBC URL, username, and password for MySQL
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empmanager";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Create Employee table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employee (\n"
                    + "id INTEGER PRIMARY KEY AUTO_INCREMENT,\n"  // Use AUTO_INCREMENT for MySQL
                    + "name TEXT,\n"
                    + "position TEXT,\n"
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
}

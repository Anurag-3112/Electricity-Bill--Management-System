import java.sql.*;

public class conn {
    // Declare Connection and Statement objects
    Connection c;
    Statement s;

    // Constructor to establish the connection
    public conn() {
        try {
            // Load the MySQL JDBC Driver for version 8.x and later
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "9874");

            // Create a Statement object to execute queries
            s = c.createStatement();

            // Print a success message
            System.out.println("Database connected successfully!");

        } catch (ClassNotFoundException e) {
            // Handle exception for loading JDBC Driver
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            // Handle SQL exceptions (e.g., invalid URL, wrong username/password)
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Main method for testing (optional)
    public static void main(String[] args) {
        new conn();  // Create an instance of conn to test the connection
    }
}

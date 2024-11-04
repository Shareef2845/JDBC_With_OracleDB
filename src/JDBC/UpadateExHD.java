package JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpadateExHD {
    public static void main(String[] args) {
        // JDBC properties
        String driverClass = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Replace 'xe' with your Oracle DB service name if needed
        String username = "system"; // Replace with your DB username
        String password = "manager"; // Replace with your DB password

        // Load the JDBC driver
        try {
            Class.forName(driverClass);

            // Establish a connection
            Connection con = DriverManager.getConnection(url, username, password);

            // Create a Statement object
            Statement st = con.createStatement();

            // Execute an update SQL statement
            int count = st.executeUpdate("UPDATE student SET sname='NewName' WHERE sid=4");

            // Print the number of rows affected
            System.out.println("Number of rows updated: " + count);

            // Close resources
            st.close();
            con.close();

            System.out.println("Update operation completed successfully.");
        } catch (SQLException se) {
            // Handle SQL exceptions
            System.out.println("SQL Exception occurred: " + se.getMessage());
        } catch (ClassNotFoundException cnfe) {
            // Handle ClassNotFoundException
            System.out.println("JDBC Driver class not found: " + cnfe.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

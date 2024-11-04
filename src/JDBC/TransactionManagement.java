package JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionManagement {
    public static void main(String[] args) throws Exception {
        // JDBC properties
        String driverClass = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Replace 'xe' with your Oracle DB service name if needed
        String username = "system"; // Replace with your DB username
        String password = "manager"; // Replace with your DB password

        // Load the JDBC driver
        Class.forName(driverClass);
        
        // Establish a connection
        Connection con = DriverManager.getConnection(url, username, password);

        // Disable auto-commit mode
        con.setAutoCommit(false);
        
        // Create a Statement object
        Statement st = con.createStatement();
        
        try {
            // Execute SQL statements
            st.executeUpdate("INSERT INTO emptab2 VALUES (21, 'WER', 12.2)");
            // More SQL statements can be executed here if needed
            
            // Commit the transaction if all operations are successful
            con.commit();
            System.out.println("Transaction committed successfully.");
        } catch (SQLException se) {
            // Roll back the transaction if an error occurs
            con.rollback();
            System.out.println("Transaction rolled back due to an error: " + se.getMessage());
        } finally {
            // Close the resources
            st.close();
            con.close();
        }
    }
}

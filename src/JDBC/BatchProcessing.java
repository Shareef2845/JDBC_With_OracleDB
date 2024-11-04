package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessing {
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
            // Add SQL commands to the batch
            st.addBatch("INSERT INTO emptab2 VALUES (21, 'WER', 12.2)");
            st.addBatch("INSERT INTO emptab2 VALUES (22, 'BNML', 22.2)");
            st.addBatch("INSERT INTO emptab2 VALUES (23, 'AASD', 32.2)");

            // Execute the batch
            int[] count = st.executeBatch();
            
            // Print the number of rows affected by each command
            System.out.println("Batch execution results:");
            for (int i : count) {
                System.out.println("Rows affected: " + i);
            }

            // Commit the transaction
            con.commit();
            System.out.println("Batch processed successfully.");
        } catch (SQLException se) {
            // Roll back the transaction in case of an error
            con.rollback();
            System.out.println("Batch processing failed: " + se.getMessage());
        } finally {
            // Close the resources
            st.close();
            con.close();
        }
    }
}

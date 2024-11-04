package JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PreparedStatement2{
    public static void main(String[] args) {
        // JDBC driver and database URL
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";  // Replace 'xe' with your Oracle DB service name if needed
        String username = "system";  // Replace with your DB username
        String password = "manager"; // Replace with your DB password

        // SQL Update query
        String sql = "UPDATE student SET sname = ?, sfee = ? WHERE sid = ?";

        try {
            // Load Oracle JDBC Driver
            Class.forName(driver);

            // Establish Connection
            Connection con = DriverManager.getConnection(url, username, password);

            // Create a PreparedStatement for parameterized query
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Use Scanner to get user input
            Scanner sc = new Scanner(System.in);

            // Get student ID from the user
            System.out.println("Enter Student ID to update:");
            int sid = sc.nextInt();

            // Get new student name
            System.out.println("Enter new Student Name:");
            String sname = sc.next();

            // Get new student fee
            System.out.println("Enter new Student Fee:");
            double sfee = sc.nextDouble();

            // Set the parameters for the prepared statement
            pstmt.setString(1, sname);   // Set student name
            pstmt.setDouble(2, sfee);    // Set student fee
            pstmt.setInt(3, sid);        // Set student ID for WHERE clause

            // Execute the update query
            int count = pstmt.executeUpdate();

            // Check if the record was updated
            if (count == 0) {
                System.out.println("No rows updated. Student ID may not exist.");
            } else {
                System.out.println("Record updated successfully. Rows affected: " + count);
            }

            // Close resources
            pstmt.close();
            con.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

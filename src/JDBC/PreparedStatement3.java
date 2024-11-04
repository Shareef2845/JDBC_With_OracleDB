package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PreparedStatement3 {
    public static void main(String[] args) {
        // JDBC driver and database URL
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";  // Replace 'xe' with your Oracle DB service name if needed
        String username = "system";  // Replace with your DB username
        String password = "manager"; // Replace with your DB password
        String sql = "DELETE FROM student ";  // Base SQL query

        PreparedStatement pstmt = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName(driver);

            // Establish Connection
            Connection con = DriverManager.getConnection(url, username, password);

            // Scanner for user input
            Scanner sc = new Scanner(System.in);

            // Display menu options
            System.out.println("---------------------------");
            System.out.println("MENU");
            System.out.println("0: Delete All Students");
            System.out.println("1: Delete by Student ID");
            System.out.println("2: Delete by Student Name");
            System.out.println("3: Delete by Student Fee");
            System.out.println("---------------------------");

            // Get user choice
            System.out.println("Enter your option:");
            int option = sc.nextInt();

            // Use switch-case to select deletion criteria
            switch (option) {
                case 1:
                    sql += "WHERE sid = ?";
                    System.out.println("Enter Student ID:");
                    int stdId = sc.nextInt();
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, stdId);
                    break;
                case 2:
                    sql += "WHERE sname = ?";
                    System.out.println("Enter Student Name:");
                    String stdName = sc.next();
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, stdName);
                    break;
                case 3:
                    sql += "WHERE sfee = ?";
                    System.out.println("Enter Student Fee:");
                    double stdFee = sc.nextDouble();
                    pstmt = con.prepareStatement(sql);
                    pstmt.setDouble(1, stdFee);
                    break;
                default:
                    pstmt = con.prepareStatement(sql);  // Deleting all records
                    System.out.println("You have chosen to delete all rows.");
                    break;
            }
            
            // Execute the delete query
            int count = pstmt.executeUpdate();

            // Display the result of the deletion
            if (count == 0) {
                System.out.println("No records found to delete.");
            } else {
                System.out.println("Deleted " + count + " row(s) successfully.");
            }

            // Close the resources
            pstmt.close();
            con.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatement1 {
    public static void main(String[] args) {
        // JDBC driver and database URL
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";  // Replace 'xe' with your Oracle DB service name if needed
        String username = "system";  // Replace with your DB username
        String password = "manager"; // Replace with your DB password

        // SQL Insert query
        String sql = "INSERT INTO student (sid, sname, sfee) VALUES (?, ?, ?)";

        try {
            // 1. Load Oracle JDBC Driver
            Class.forName(driver);

            // 2. Establish Connection
            Connection con = DriverManager.getConnection(url, username, password);

            // 3. Create a PreparedStatement for parameterized query
            PreparedStatement pstmt = con.prepareStatement(sql);

            // 4. Set the parameters (replace with actual data)
            pstmt.setInt(1, 101);           // Setting student ID
            pstmt.setString(2, "John Doe"); // Setting student name
            pstmt.setDouble(3, 1000.50);    // Setting student fee

            // 5. Execute the insert query
            int count = pstmt.executeUpdate(); // Returns the number of affected rows

            // 6. Check if the record was inserted
            System.out.println("Record inserted successfully. Rows affected: " + count);

            // 7. Close the connection
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

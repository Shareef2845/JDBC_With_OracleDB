package JDBC;


import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StoreImage {
    public static void main(String[] args) throws Exception {
        // JDBC properties
        String driverclass = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";  // Replace 'xe' with your Oracle DB service name if needed
        String username = "system";  // Replace with your DB username
        String password = "manager"; // Replace with your DB password
        String sql = "INSERT INTO imagetab (id, filename, image) VALUES (?, ?, ?)"; // SQL query for inserting image

        // Load the Oracle JDBC Driver
        Class.forName(driverclass);
        
        // Establish the connection
        Connection con = DriverManager.getConnection(url, username, password);
        
        // The file path to the image you want to insert
        File imageFile = new File("F:\\VENKY\\1.jpg");
        
        // Reading the image from the file system
        FileInputStream fis = new FileInputStream(imageFile);

        // Create a PreparedStatement to insert the image
        PreparedStatement pstmt = con.prepareStatement(sql);

        // Set the parameters: student ID, image file name, and the image as a BLOB
        pstmt.setInt(1, 100); // ID
        pstmt.setString(2, "aa.jpg"); // Image file name
        pstmt.setBinaryStream(3, fis, (int) imageFile.length()); // Image file stream

        // Execute the update and insert the image
        int count = pstmt.executeUpdate();
        System.out.println("Image inserted successfully. Rows affected: " + count);

        // Close the connection
        pstmt.close();
        con.close();
    }
}

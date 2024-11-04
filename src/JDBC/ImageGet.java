package JDBC;


import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImageGet {
    public static void main(String[] args) throws Exception {
        // JDBC properties
        String driverclass = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Oracle DB URL
        String username = "system";  // Replace with your DB username
        String password = "manager"; // Replace with your DB password
        String sql = "SELECT * FROM imagetab WHERE id=?";  // SQL query to fetch image by ID
        
        // Load the Oracle JDBC Driver
        Class.forName(driverclass);
        
        // Establish the connection
        Connection con = DriverManager.getConnection(url, username, password);
        
        // Create a PreparedStatement for the SQL query
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        // Set the ID of the image you want to retrieve (replace 100 with the appropriate ID)
        pstmt.setInt(1, 100);
        
        // Execute the query
        ResultSet rs = pstmt.executeQuery();
        
        // Check if the image exists
        if (rs.next()) {
            // Get the image from the ResultSet as a BLOB
            Blob img = rs.getBlob(3);
            
            // Convert the BLOB to a byte array
            byte[] imgBytes = img.getBytes(1, (int) img.length());
            
            // Create a FileOutputStream to write the image to a file
            FileOutputStream fos = new FileOutputStream("E:\\aa.jpg");  // Specify the file path to save the image
            
            // Write the byte array to the file
            fos.write(imgBytes);
            fos.flush();
            fos.close();
            
            System.out.println("Image retrieved and saved successfully!");
        } else {
            System.out.println("No image found with the given ID.");
        }
        
        // Close the connection
        pstmt.close();
        con.close();
    }
}

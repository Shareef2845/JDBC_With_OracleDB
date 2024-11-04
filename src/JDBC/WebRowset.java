package JDBC;

import javax.sql.rowset.WebRowSet;
import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowset {
    public static void main(String[] args) throws Exception {
        // Create a WebRowSet object
        WebRowSet rs = new OracleWebRowSet();
        
        // Set the database connection properties
        rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe"); // Replace 'xe' with your Oracle DB service name if needed
        rs.setUsername("system"); // Replace with your DB username
        rs.setPassword("manager"); // Replace with your DB password
        
        // Set the SQL query
        rs.setCommand("SELECT * FROM student"); // Replace 'student' with your table name
        
        // Execute the query
        rs.execute();
        
        // Print the data in a human-readable format
        System.out.println("------DATA--------");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3));
        }
        
        // Output data in XML format
        System.out.println("------DATA-XML FORMAT--------");
        rs.writeXml(System.out);
        
        // Close the WebRowSet
        rs.close();
    }
}

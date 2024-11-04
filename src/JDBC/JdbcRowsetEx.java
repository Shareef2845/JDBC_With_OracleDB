package JDBC;


import javax.sql.rowset.JdbcRowSet;
import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowsetEx {
    public static void main(String[] args) throws Exception {
        // Create a JdbcRowSet object
        JdbcRowSet rs = new OracleJDBCRowSet();
        
        // Set the database connection properties
        rs.setUrl("jdbc:oracle:thin:@localhost:1521:xe"); // Replace 'xe' with your Oracle DB service name if needed
        rs.setUsername("system"); // Replace with your DB username
        rs.setPassword("manager"); // Replace with your DB password
        
        // Set the SQL query
        rs.setCommand("SELECT * FROM student"); // Replace 'student' with your table name
        
        // Execute the query
        rs.execute();
        
        // Print the cursor position
        System.out.println("CHECK CURSOR POSITION");
        System.out.println("BeforeFirst: " + rs.isBeforeFirst());
        System.out.println("AfterLast: " + rs.isAfterLast());
        
        // Move forward through the result set and print data
        System.out.println("MOVE FORWARD");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3));
        }
        
        // Move backward through the result set and print data
        System.out.println("MOVE BACKWARD");
        while (rs.previous()) {
            System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3));
        }
        
        // Move to the second row and print data
        System.out.println("MOVE TO 2ND ROW");
        rs.absolute(2);
        System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3));
        
        // Move one row back and print data
        System.out.println("MOVE ONE ROW BACK");
        rs.relative(-1);
        System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3));
        
        // Go to the first row and print data
        System.out.println("GO TO FIRST ROW");
        rs.first();
        System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3));
        
        // Go to the last row and print data
        System.out.println("GO TO LAST ROW");
        rs.last();
        System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3));
        
        // Close the RowSet
        rs.close();
    }
}

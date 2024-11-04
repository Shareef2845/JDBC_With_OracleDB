package JDBC;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CRUD {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 1. Register Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. Open a connection
            String dbURL = "jdbc:oracle:thin:@localhost:1522/xepdb1";
            String username = "jdbc";  // Updated based on your Oracle connection details
            String password = "admin";
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(dbURL, username, password);

            // 3. Create a Statement object
            stmt = conn.createStatement();

            // 4. Create a new table
            String createTableSQL = "CREATE TABLE Employees (" +
                                    "id NUMBER(5) PRIMARY KEY, " +
                                    "name VARCHAR2(50), " +
                                    "age NUMBER(3), " +
                                    "salary NUMBER(10, 2))";
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'Employees' created successfully.");

            // 5. Insert data into the table
            String insertSQL1 = "INSERT INTO Employees VALUES (1, 'shareef', 30, 60000)";
            String insertSQL2 = "INSERT INTO Employees VALUES (2, 'shyam', 25, 55000)";
            stmt.executeUpdate(insertSQL1);
            stmt.executeUpdate(insertSQL2);
            System.out.println("Records inserted successfully.");

          /*  // 6. Update a record
            String updateSQL = "UPDATE Employees SET salary = 65000 WHERE id = 1";
            stmt.executeUpdate(updateSQL);
            System.out.println("Record updated successfully.");

            // 7. Alter the table to add a new column
            String alterSQL = "ALTER TABLE Employees ADD department VARCHAR2(30)";
            stmt.executeUpdate(alterSQL);
            System.out.println("Table altered successfully.");

            // 8. Delete a record
            String deleteSQL = "DELETE FROM Employees WHERE id = 2";
            stmt.executeUpdate(deleteSQL);
            System.out.println("Record deleted successfully.");

            // 9. Truncate the table
            String truncateSQL = "TRUNCATE TABLE Employees";
            stmt.executeUpdate(truncateSQL);
            System.out.println("Table truncated successfully.");

            // 10. Drop the table
            String dropTableSQL = "DROP TABLE Employees";
            stmt.executeUpdate(dropTableSQL);
            System.out.println("Table 'Employees' dropped successfully."); */

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 11. Clean up the environment
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


/*

Go to system run below command:
==============================
CREATE USER username IDENTIFIED BY password;


Grant Privileges to the User:
=============================
grant all PRIVILEGES TO USER;

Drop User:
===========
drop USER testsbms CASCADE;

COMMIT;



For SID-based connection:
=========================
String dbURL = "jdbc:oracle:thin:@localhost:1522:orcl";

For Service name-based connection:
==================================
String dbURL = "jdbc:oracle:thin:@//localhost:1522/xepdb1";


*/
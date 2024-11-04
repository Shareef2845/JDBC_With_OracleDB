package JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Callabel_Statement  {
    public static void main(String[] args) throws Exception {
        // JDBC properties
        String driverClass = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";  // Replace 'XE' with your Oracle DB service name if needed
        String user = "system";  // Replace with your DB username
        String password = "manager"; // Replace with your DB password

        // SQL query to call the stored procedure
        String sql = "{? = call GETEMPNAME(?)}"; // Assuming GETEMPNAME is the stored procedure

        // Load the Oracle JDBC Driver
        Class.forName(driverClass);

        // Establish the connection
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a CallableStatement object to call the stored procedure
        CallableStatement cstmt = con.prepareCall(sql);

        // Register the output parameter (name of the employee)
        cstmt.registerOutParameter(1, Types.VARCHAR);
        
        // Set the input parameter (employee ID)
        cstmt.setInt(2, 10); // Example employee ID
        
        // Execute the stored procedure
        cstmt.execute();
        
        // Retrieve and print the output parameter (employee name)
        String empName = cstmt.getString(1);
        System.out.println("Employee Name: " + empName);

        // Close the resources
        cstmt.close();
        con.close();
    }
}

/*CREATE OR REPLACE FUNCTION GETEMPNAME (emp_id IN NUMBER) RETURN VARCHAR2 AS
    emp_name VARCHAR2(100);
BEGIN
    SELECT name INTO emp_name FROM employees WHERE id = emp_id;
    RETURN emp_name;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'No employee found';
END;
/
*/
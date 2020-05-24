package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class can be used to initialize the database connection
public class DBConnector {




    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String URL = "jdbc:postgresql://ec2-34-200-72-77.compute-1.amazonaws.com:5432/d6t2ihomrv0cr3";
        String UNAME = "nyataccyehwxrh";
        String PWD = "d7d0b02ba6051fee490b31b679376390f5121a6fbed7ed67d7ba50c34ed3cece";
        String dbDriver = "org.postgresql.Driver";

        // Database name to access

       // String dbName = "sql-lesson";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(URL ,
                UNAME,
                PWD);
        return con;
    }
}
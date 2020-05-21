package controller;

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
        String URL = "jdbc:postgresql://localhost:5432/sql-lesson";
        String UNAME = "postgres";
        String PWD = "sh7513244";
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
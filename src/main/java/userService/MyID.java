package userService;

import connection.DBConnector;
import web.LoginServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyID {

    static LoginServlet ls = new LoginServlet();
    static Connection con;

    static {
        try {
            con = DBConnector.initializeDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyID() throws SQLException, ClassNotFoundException {
    }

    public static String id(){
        String id = null;
        try {


            // Initialize the database

            PreparedStatement st = con
                    .prepareStatement("select id from users where username = ? and password=?");

            st.setString(1, ls.getUser());
            st.setString(2, ls.getPwd());
            ResultSet rset = st.executeQuery();
            while (rset.next()) {
                id = rset.getString("id");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    protected void finalize() throws Throwable
    {
        try { con.close(); }
        catch (SQLException e) {
            e.printStackTrace();
        }
        super.finalize();
    }
}

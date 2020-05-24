package userService;

import beans.User;
import connection.DBConnector;
import web.LoginServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String id() throws SQLException {
        String id = null;

        Users users = new Users();
        List<User> arr = users.people().stream().filter(e->e.getUsername().equals(ls.getUser()) && e.getPassword().equals(ls.getPwd())).collect(Collectors.toList());
        if (arr.size()>0){
            return arr.get(0).getId();
        }else
        return "not logged";



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

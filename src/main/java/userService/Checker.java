package userService;

import connection.DBConnector;
import web.LoginServlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Checker {


    static LoginServlet ls = new LoginServlet();
    static String username = "";
    static String password = "";
    static Users usrs = new Users();
    static HashMap<String, String> users = new HashMap<>();

    public static HashMap<String, String> all_users() throws SQLException {
//        try {
//            Connection con = DBConnector.initializeDatabase();
//            PreparedStatement st = con
//                    .prepareStatement("select username, password from users");
//
//            ResultSet rset = st.executeQuery();
//            while (rset.next()) {
//                username = rset.getString("username");
//                password = rset.getString("password");
//                users.put(username, password);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < usrs.people().size(); i++) {
            users.put(usrs.people().get(i).getUsername(), usrs.people().get(i).getPassword());

        }
        return users;
    }

    public boolean check(String user, String pwd) {
        try {
            return all_users().get(user).equals(pwd);

        } catch (Exception ex) {
            return false;
        }
    }

    public static void login_checker(HttpServletResponse resp, MyID myID) throws SQLException, ClassNotFoundException, IOException {
        if (myID.id() == null) {
            resp.sendRedirect("/register");
        }
    }
}

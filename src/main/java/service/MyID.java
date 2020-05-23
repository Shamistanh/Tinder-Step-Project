package service;

import controller.DBConnector;
import web.LoginServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyID {

    static LoginServlet ls = new LoginServlet();


    public static String id(){
        String id = null;
        try {


            // Initialize the database
            Connection con = DBConnector.initializeDatabase();
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
}

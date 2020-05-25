package userService;


import beans.User;
import connection.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Users {
    static String username="";
    static String password="";
    static String pic="";
    static String date="";
    static String id="";
    static int day = 0;
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

    public static List<User> people() throws SQLException {
        List<User> ar = new ArrayList<>();
        try {


            // Initialize the database

            PreparedStatement st = con
                    .prepareStatement("select id,username, password, pic, created_at from users");

            ResultSet rset = st.executeQuery();

            while (rset.next()) {

                username = rset.getString("username");
                password = rset.getString("password");
                pic = rset.getString("pic");
                id = rset.getString("id");
                date = rset.getString("created_at");
               // Date date2 = new Date(Integer.parseInt(date.split("-")[2]),Integer.parseInt(date.split("-")[1]),Integer.parseInt(date.split("-")[0])-1900);
                //long milliseconds  = date2.getTime();
               // day = (int)TimeUnit.MILLISECONDS.toDays(milliseconds);
                day = 0;

                ar.add(new User(username,password,pic,date,day,id));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ar;
    }

    public List<User> likeblePeople(String user_id) throws SQLException {
        List<User> likebles = people();
        return likebles.stream().filter(e-> {
            try {
                return !e.getId().equals(MyID.id());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return false;
        }).distinct().collect(Collectors.toList());
    }

}

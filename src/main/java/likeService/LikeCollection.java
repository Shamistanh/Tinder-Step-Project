package likeService;

import beans.User;
import connection.DBConnector;
import userService.MyID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LikeCollection {

    static Connection con;
    static String id;

    static {
        try {
            con = DBConnector.initializeDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<User> like_collection() throws SQLException, ClassNotFoundException {
        List<User> liked_coll = new ArrayList<>();
        MyID myID  = new MyID();
        try {

            PreparedStatement st = con
                    .prepareStatement("SELECT users.id, users.username,users.password, users.pic, users.created_at  FROM users, reactions WHERE users.id = reactions.whom");

            ResultSet rset = st.executeQuery();
            while (rset.next()) {
                id = rset.getString("id");
                String username = rset.getString("username");
                String password = rset.getString("password");
                String profile = rset.getString("pic");
                String date = rset.getString("created_at");

                liked_coll.add(new User(id, username,password,profile,date));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("my id" + myID.id());
        return liked_coll.stream().filter(e->!e.getId().equals(myID.id())).distinct().collect(Collectors.toList());
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

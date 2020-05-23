package service;

import beans.User;
import controller.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LikeCollection {


    public static List<User> like_collection(){
        List<User> liked_coll = new ArrayList<>();
        MyID myID  = new MyID();
        try {
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("SELECT users.id, users.username,users.password, users.pic, users.created_at  FROM users, reactions WHERE users.id = reactions.whom");
            ResultSet rset = st.executeQuery();
            while (rset.next()) {
                String id = rset.getString("id");
                String username = rset.getString("username");
                String password = rset.getString("password");
                String profile = rset.getString("pic");
                String date = rset.getString("created_at");
                liked_coll.stream().filter(e->!e.getUsername().equals(username) && !e.getPassword().equals(password) && !myID.id().equals(id)).distinct().map(u->liked_coll.add(u));
                liked_coll.add(new User(id, username,password,profile,date));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liked_coll;
    }
}

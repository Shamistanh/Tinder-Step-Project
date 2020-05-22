package service;


import beans.User;
import controller.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Users {
    static String username="";
    static String password="";
    static String pic="";
    static String date="";
    static String id="";
    static int day = 0;


    public static List<User> people(){
        List<User> ar = new ArrayList<>();
        try {


            // Initialize the database
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("select id,username, password, pic, created_at from users");

            ResultSet rset = st.executeQuery();

            while (rset.next()) {

                username = rset.getString("username");
                password = rset.getString("password");
                pic = rset.getString("pic");
                id = rset.getString("id");
                date = rset.getString("created_at");
                Date date2 = new Date(Integer.parseInt(date.split("-")[2]),Integer.parseInt(date.split("-")[1]),Integer.parseInt(date.split("-")[0])-1900);
                long milliseconds  = date2.getTime();
                day = (int)TimeUnit.MILLISECONDS.toDays(milliseconds);

                ar.add(new User(username,password,pic,date,day,id));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ar;
    }

    public List<User> likeblePeople(String user_id) {
        List<User> likebles = new ArrayList<>();
        for (int i = 0; i <people().size() ; i++) {
            if (!people().get(i).equals(user_id)){
                likebles.add(people().get(i));
            }
        }
        return likebles;
    }
}

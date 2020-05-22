package service;

import beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomUser {

    static Users usrs = new Users();
    static List<User> all_users = usrs.people();
    static List<User> likebles = new ArrayList<>();
    static String USER_ID = "u_id";
    static MyID myID  = new MyID();
    static String MY_ID = myID.id();
    static List<User> likeble_users = usrs.likeblePeople(MY_ID);
    static User delivered = new User();


    public static User generateRandom() {
        if (likeble_users.isEmpty()){
            likeble_users = usrs.likeblePeople(MY_ID);
            generateRandom();
        }else {
            int random_idx=0;
            random_idx = (int)(Math.random()*likeble_users.size());
            delivered = likeble_users.get(random_idx);
            likeble_users.remove(random_idx);
        }

         return delivered;
    }
}

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

public class RandomUser extends HttpServlet {

    static Users usrs = new Users();
    static List<User> all_users = usrs.people();
    static List<User> likebles = new ArrayList<>();
    static String USER_ID = "u_id";
    static String MY_ID;
    static List<User> likeble_users = usrs.likeblePeople(MY_ID);
    static User delivered = new User();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("kukinin adi"+cookie.getName());
            if (cookie.getName().equals(USER_ID)) {
                MY_ID = cookie.getValue();
            }

        }
    }

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

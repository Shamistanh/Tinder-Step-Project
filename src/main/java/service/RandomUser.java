package service;

import beans.User;
import controller.Users;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RandomUser extends HttpServlet {

    static Users usrs = new Users();

    static String USER_ID = "u_id";
    static String MY_ID;


    static User generateRandom() {
        return usrs.people().get((int) (Math.random() * usrs.people().size()));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(USER_ID)) {
                MY_ID = cookie.getValue();
            }

        }
    }

    public User getUser() {

        if (!generateRandom().getId().equals(MY_ID)) {
            return generateRandom();
        } else {
            generateRandom();
        }
        return generateRandom();
    }
}

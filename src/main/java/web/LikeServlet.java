package web;


import beans.User;
import lombok.SneakyThrows;
import userService.Checker;
import userService.MyID;
import userService.RandomUser;
import likeService.React;
import userService.Users;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LikeServlet extends HttpServlet {

    private final TemplateEngine engine;
    static String reaction = "0";
    private static final String USER_ID = "u_id";
    private static final String LIKE_ID = "liked_id";
    private static String WHO = "";
    private static String WHOM = "";
    RandomUser ru;
    User randomUser;
    HashMap<String, Object> data = new HashMap<>();


    static Users usrs = new Users();
    static List<User> all_users;

    static {
        try {
            all_users = usrs.people();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static List<User> likebles = new ArrayList<>();
    static MyID myID;
    Checker checker = new Checker();

    static {
        try {
            myID = new MyID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String MY_ID;

    static {
        try {
            MY_ID = myID.id();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static List<User> likeble_users;

    static {
        try {
            likeble_users = usrs.likeblePeople(MY_ID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static User delivered = new User();

    public LikeServlet(TemplateEngine engine) {
        this.engine = engine;
    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checker.login_checker(resp, myID);


    }


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        randomUser = generateRandom();
        WHO = myID.id();
        reaction = req.getParameter("reaction");
        Cookie c = new Cookie("liked_id", randomUser.getId());
        c.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(c);


        data.put("username", randomUser.getUsername());
        data.put("profile", randomUser.getProfile());
        engine.render("like-page.ftl", data, resp);

        React react = new React();
        if (reaction != null) {
            if (reaction.equals("like")) {
                react.ireact(WHO, WHOM, 1);

            } else if (reaction.equals("dislike")) {
                react.ireact(WHO, WHOM, 2);
            }
        }


        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LIKE_ID)) {
                    WHOM = cookie.getValue();
                }


            }
        }


    }


    public static User generateRandom() throws SQLException {
        if (likeble_users.isEmpty()) {
            likeble_users = usrs.likeblePeople(MY_ID);
            generateRandom();
        } else {
            int random_idx = 0;
            random_idx = (int) (Math.random() * likeble_users.size());
            delivered = likeble_users.get(random_idx);
            likeble_users.remove(random_idx);
        }

        return delivered;
    }
}

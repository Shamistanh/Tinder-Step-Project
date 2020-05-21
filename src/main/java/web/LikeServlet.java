package web;


import beans.User;
import controller.Users;
import service.RandomUser;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LikeServlet extends HttpServlet {

    private final TemplateEngine engine;

    public LikeServlet(TemplateEngine engine) {
        this.engine = engine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RandomUser randomUser = new RandomUser();
        HashMap<String, Object> data = new HashMap<>();

        data.put("username", randomUser.getUser().getUsername());
        data.put("profile", randomUser.getUser().getProfile());

        Cookie c = new Cookie("liked_id", randomUser.getUser().getId());
        c.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(c);

        engine.render("like-page.ftl", data, resp);
    }

}

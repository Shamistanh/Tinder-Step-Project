package web;


import beans.User;
import service.RandomUser;
import service.React;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LikeServlet extends HttpServlet {

    private final TemplateEngine engine;
    static String reaction = "0";

    public LikeServlet(TemplateEngine engine) {
        this.engine = engine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RandomUser ru = new RandomUser();
        User randomUser  = ru.generateRandom();
        HashMap<String, Object> data = new HashMap<>();

        data.put("username", randomUser.getUsername());
        data.put("profile", randomUser.getProfile());

        Cookie c = new Cookie("liked_id", randomUser.getId());
        c.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(c);

        engine.render("like-page.ftl", data, resp);
        React react = new React();
        reaction = req.getParameter("reaction");

        if (reaction.equals("like")) {
            react.ireact(1);

        } else if (reaction.equals("dislike")) {
            react.ireact(2);
        }
       // resp.sendRedirect("/users");
    }

}

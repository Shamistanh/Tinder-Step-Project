package web;

import beans.User;
import lombok.SneakyThrows;
import likeService.LikeCollection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final TemplateEngine engine;

    String liked_id;

    public UserServlet(TemplateEngine engine) {
        this.engine = engine;
    }



    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();
        LikeCollection likeCollection = new LikeCollection();
        // Users usr = new Users();
        List<User> students = likeCollection.like_collection();
        System.out.println(likeCollection.like_collection());
        data.put("users", students);

        liked_id = req.getParameter("user_idd");

        Cookie c = new Cookie("liked_id", liked_id);
        c.setMaxAge(60*60*24*7);
        resp.addCookie(c);
        if (liked_id !=null){
            resp.sendRedirect("/messages");
        }

        engine.render("people-list.ftl", data, resp);

    }

}
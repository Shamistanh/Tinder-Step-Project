package web;

import beans.User;
import controller.Users;
import service.LikeCollection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final TemplateEngine engine;

    public UserServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        LikeCollection likeCollection = new LikeCollection();

       // Users usr = new Users();
        List<User> students = likeCollection.like_collection();
        data.put("users", students);
        //data.put("day", day);

        engine.render("people-list.ftl", data, resp);
    }

}
package web;

import controller.AddUser;
import service.React;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LikeCollector extends HttpServlet {
    String reaction = "0";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (OutputStream os = resp.getOutputStream()) {
            Files.copy(Paths.get("content/templates", "like-page.html"), os);

        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        React react = new React();
        reaction = req.getParameter("reaction");

        if (reaction.equals("like")) {
            react.ireact(1);

        } else if (reaction.equals("dislike")) {
            react.ireact(2);
        }
        resp.sendRedirect("/users");

    }

}

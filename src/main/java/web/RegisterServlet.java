package web;

import controller.AddUser;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RegisterServlet extends HttpServlet {

    static String username;
    static String password;
    static  String profile;





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (OutputStream os = resp.getOutputStream()) {
            Files.copy(Paths.get("content/templates", "registration.html"), os);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         username = req.getParameter("email");
         password = req.getParameter("psw");
         profile = req.getParameter("profile");
        AddUser au = new AddUser();
        au.iadd(username,password,profile);
        resp.sendRedirect("/users");

    }


}

package web;

import service.Checker;
import service.MyID;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String user = "";
    private static String pwd = "";
    private static MyID myID = new MyID();

    public static String getUser() {
        return user;
    }

    public static String getPwd() {
        return pwd;
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try (OutputStream os = response.getOutputStream()) {
            Files.copy(Paths.get("content/templates", "login.html"), os);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Checker checker = new Checker();

        user = request.getParameter("logemail");
        pwd = request.getParameter("logpsw");
        if(checker.check(user, pwd)){
//            Cookie c = new Cookie("u_id", myID.id());
//            c.setMaxAge(60 * 60 * 24 * 7);
//            response.addCookie(c);
            response.sendRedirect("/users");
        }else {
            response.sendRedirect("/login");
        }


    }
}

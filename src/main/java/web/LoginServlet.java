package web;

import userService.Checker;
import userService.MyID;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
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
    private static MyID myID;

    static {
        try {
            myID = new MyID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
            response.sendRedirect("/users");
        }else {
            response.sendRedirect("/register");
        }


    }
}

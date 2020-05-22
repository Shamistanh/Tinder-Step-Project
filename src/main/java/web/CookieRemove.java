package web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CookieRemove extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();

        // this is just for text message
        String cookiess = Arrays.stream(cookies)
                .map(c -> String.format("name=%s, value=%s", c.getName(), c.getValue()))
                .collect(Collectors.joining());

        // this is actually deleting
        Arrays.stream(cookies)
                .forEach(c -> {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                });

        try (PrintWriter w = resp.getWriter()) {
            w.write("Cookies removed:\n");
            w.write(cookiess);
        }
    }

}

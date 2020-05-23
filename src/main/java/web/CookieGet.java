package web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CookieGet extends HttpServlet {

    static HashMap<String,String> hm = new HashMap<>();

    public static HashMap<String, String> getHm() {
        return hm;
    }



}

package service;

import controller.DBConnector;
import web.CookieGet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class React extends HttpServlet {

    static HashMap<String, String> rmap = new HashMap<>();
    static String USER_ID = "u_id";
    static String LIKE_ID = "liked_id";
    static String PLACEHOLDER1="helekiboshdur";
    static String PLACEHOLDER2="heleki bu da boshdur";
    static CookieGet cg = new CookieGet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();


        if (cookies != null) {
            for (Cookie cookie : cookies) {
                resp.getWriter().write(cookie.getName()+" "+cookie.getValue()+"\n");
                if (cookie.getName().equals(USER_ID)) {

                    PLACEHOLDER1 = cookie.getValue();
                }else if (cookie.getName().equals(LIKE_ID)) {
                    PLACEHOLDER2 = cookie.getValue();
                }
            }
        }

    }

    public static void ireact(int reaction){
        try {
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("insert into reactions (who,whom,reaction) values(?, ?, ?)");
            st.setString(1, cg.getHm().get(USER_ID));
            st.setString(2, cg.getHm().get(LIKE_ID));
            st.setInt(3, reaction);
          //  System.out.println(cg.getHm().get(USER_ID)+"  "+cg.getHm().get(LIKE_ID));
            st.executeUpdate();
            st.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

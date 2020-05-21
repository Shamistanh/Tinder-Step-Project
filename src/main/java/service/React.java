package service;

import controller.DBConnector;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();

        //Arrays.stream(cookies).map(e-> e.getName().equals(USER_ID) ? rmap.put(USER_ID,e.getValue()) : rmap.put(LIKE_ID,e.getValue()));

        for (Cookie cookie : cookies ){
            if(cookie.getName().equals(USER_ID)){
                rmap.put(USER_ID,cookie.getValue());
            }else if(cookie.getName().equals(LIKE_ID)){
                rmap.put(LIKE_ID,cookie.getValue());
            }

        }

    }

    public static void ireact(int reaction){
        try {
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("insert into reactions (who,whom,reaction) values(?, ?, ?)");
            st.setString(1, rmap.get(USER_ID));
            st.setString(2, rmap.get(LIKE_ID));
            st.setInt(3, reaction);
            System.out.println(rmap.get(USER_ID)+"  "+rmap.get(LIKE_ID));
            st.executeUpdate();
            st.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

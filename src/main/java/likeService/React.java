package likeService;

import connection.DBConnector;
import web.LikeServlet;
import web.TemplateEngine;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class React extends HttpServlet {

    static HashMap<String, String> rmap = new HashMap<>();
    static String USER_ID = "u_id";
    static String LIKE_ID = "liked_id";
    private static TemplateEngine render;
    static LikeServlet likeServlet = new LikeServlet(render);

    public static void ireact(String who, String whom, int reaction){
        try {
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("insert into reactions (who,whom,reaction) values(?, ?, ?)");
            st.setString(1, who);
            st.setString(2, whom);
            st.setInt(3, reaction);
            st.executeUpdate();
            st.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

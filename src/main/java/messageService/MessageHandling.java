package messageService;

import beans.Message;
import beans.User;
import connection.DBConnector;
import userService.MyID;
import userService.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageHandling {

    static Connection con;

    static {
        try {
            con = DBConnector.initializeDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MessageHandling() throws SQLException, ClassNotFoundException {
    }

    public static List<Message> allMessages(String who,String whom) throws SQLException, ClassNotFoundException {
        List<Message> message_coll = new ArrayList<>();
        MyID myID  = new MyID();
        try {
         //   Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("select * from messages where whom = ? and who= ? order by date desc");
            st.setString(1, who);
            st.setString(2, whom);
            ResultSet rset = st.executeQuery();
            while (rset.next()) {

                String who2 = rset.getString("who");
                String whom2 = rset.getString("whom");
                String message = rset.getString("message");
                String date = rset.getString("date");
                message_coll.add(new Message(who,whom,message,date));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return message_coll;
    }

    public String getProfile(String sender_id) {
       String pic="for now empty";
        String who="for now empty";
//        try {
//
//            PreparedStatement st = con
//                    .prepareStatement("select username, pic from users where id=?");
//            st.setString(1, sender_id);
//            ResultSet rset = st.executeQuery();
//            while (rset.next()) {
//                who= rset.getString("username");
//               pic = rset.getString("pic");
//            }
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        Users usrs = new Users();
        List<User> users = usrs.people().stream().filter(e->e.getId().equals(sender_id)).collect(Collectors.toList());

        return users.get(0).getUsername()+"-"+users.get(0).getProfile();
    }
    protected void finalize() throws Throwable
    {
        try { con.close(); }
        catch (SQLException e) {
            e.printStackTrace();
        }
        super.finalize();
    }


}

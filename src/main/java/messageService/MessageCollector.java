package messageService;

import beans.Message;
import connection.DBConnector;
import userService.MyID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageCollector {


    public static List<Message> message_collection() throws SQLException, ClassNotFoundException {
        List<Message> message_coll = new ArrayList<>();
        MyID myID  = new MyID();
        try {
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("select * from messages");
            ResultSet rset = st.executeQuery();
            while (rset.next()) {

                String who = rset.getString("who");
                String whom = rset.getString("whom");
                String message = rset.getString("message");
                String date = rset.getString("date");
              message_coll.add(new Message(who,whom,message,date));
            }

            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return message_coll;
    }
}


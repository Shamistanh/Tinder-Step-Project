package service;

import beans.Message;
import controller.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageHandling {


    public static List<Message> allMessages(String who,String whom){
        List<Message> message_coll = new ArrayList<>();
        MyID myID  = new MyID();
        try {
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("select * from messages where whom = ? and who= ?");
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
}

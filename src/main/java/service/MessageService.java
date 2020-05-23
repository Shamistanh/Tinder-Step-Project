package service;

import controller.DBConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MessageService {

    public void insertToDB(String id, String sender_id, String msg_text, LocalDate now) {
        try {
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("insert into messages (who,whom, message, date) values(?, ?, ?, ?)");
            st.setString(1, id);
            st.setString(2, sender_id);
            st.setString(3, msg_text);
            st.setDate(4, Date.valueOf(now));

            st.executeUpdate();
            st.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

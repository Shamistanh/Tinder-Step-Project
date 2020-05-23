package userService;

import connection.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ThreadLocalRandom;

public class AddUser {

    public static String idGenerator(String x){
        return (x.hashCode()+"").substring(x.length()/2)+ ThreadLocalRandom.current().ints(0, 1000).distinct();

    }


    public static void iadd(String username,String password, String pic){
        try {

            // Initialize the database
            Connection con = DBConnector.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("insert into users (id,username, password, pic) values(?, ?,?,?)");
            st.setString(1, idGenerator(password));
            st.setString(2, username);
            st.setString(3, password);
            st.setString(4, pic);
            st.executeUpdate();
            System.out.println(username);

            st.close();
            con.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}

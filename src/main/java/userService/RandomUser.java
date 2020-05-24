package userService;

import beans.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RandomUser {

    static Users usrs = new Users();
    static List<User> all_users;

    static {
        try {
            all_users = usrs.people();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static List<User> likebles = new ArrayList<>();
    static String USER_ID = "u_id";
    static MyID myID;

    static {
        try {
            myID = new MyID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String MY_ID;

    static {
        try {
            MY_ID = myID.id();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static List<User> likeble_users;

    static {
        try {
            likeble_users = usrs.likeblePeople(MY_ID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static User delivered = new User();


    public static User generateRandom() throws SQLException, ClassNotFoundException {

            likeble_users = usrs.likeblePeople(MY_ID);
     for (int i = 0; i < likeble_users.size(); i++) {
            delivered = likeble_users.get(i);
//            if (i==likeble_users.size()-1){
//                i=0;
//            }
        }


         return delivered;
    }
}

package userService;

import beans.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RandomUser {

    static Users usrs = new Users();
    static List<User> all_users = usrs.people();
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

    static String MY_ID = myID.id();
    static List<User> likeble_users;

    static {
        likeble_users = usrs.likeblePeople(MY_ID);
    }

    static User delivered = new User();


    public static User generateRandom() throws SQLException, ClassNotFoundException {
        if (likeble_users.isEmpty()){
            likeble_users = usrs.likeblePeople(MY_ID);
            generateRandom();
        }else {
            int random_idx=0;
            random_idx = (int)(Math.random()*likeble_users.size());
            delivered = likeble_users.get(random_idx);
            likeble_users.remove(random_idx);
        }

         return delivered;
    }
}

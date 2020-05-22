package beans;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {


    String username;

    String password;

    String profile;

    String date;

    int day;

    String id;

    public User(String id, String username, String password, String profile, String date) {
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.date = date;
        this.id = id;
    }

    public User() {

    }
}

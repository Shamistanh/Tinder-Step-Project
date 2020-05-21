package beans;

public class Message {

    int id=0;
    int who=0;
    int whom=0;
    String message="";
    String date="";

    public Message(int id, int who, int whom, String message, String date) {
        this.id = id;
        this.who = who;
        this.whom = whom;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public int getWhom() {
        return whom;
    }

    public void setWhom(int whom) {
        this.whom = whom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package service;

public class Whom {
    String getLiked_id="";
    public void add(String liked_id) {
        getLiked_id = liked_id;
    }
    public String get(){
        return getLiked_id;
    }
}

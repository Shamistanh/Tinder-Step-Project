package service;

import DAO.DAOUserSQL;
import beans.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Checker {
    private static Connection conn = null;
    static String username = "";
    static String password = "";
    static HashMap<String, String> users = new HashMap<>();


    public Checker(Connection conn) {
        this.conn = conn;
    }
    public static DAOUserSQL daoUserSQL = new DAOUserSQL(conn);

    public static HashMap<String, String> all_users() {
        HashMap<String, String> hm = (HashMap<String, String>) daoUserSQL.getAll().stream().collect(Collectors.toMap(User::getPassword,User::getUsername));
        return hm;
    }

    public static boolean check(String user, String pwd) {
        if (user == null || pwd ==null){
            user= "";
            pwd="";
        }
        if (all_users().get(pwd)== null){
            return false;
        }else
        return all_users().get(pwd).equals(user);

    }

}

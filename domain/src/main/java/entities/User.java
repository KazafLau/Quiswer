package entities;

import java.sql.Timestamp;

/**
 * Created by kazaf on 16-10-1.
 */
public class User {

    public int userid;
    public String username;
    public String userpassword;
    public String useremail;
    public Timestamp user_time;
    public boolean user_active;

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Timestamp getUser_time() {
        return user_time;
    }

    public void setUser_time(Timestamp user_time) {
        this.user_time = user_time;
    }

    public boolean isUser_active() {
        return user_active;
    }

    public void setUser_active(boolean user_active) {
        this.user_active = user_active;
    }

    public User() {
    }

    public User(String username, String userpassword, String useremail) {
        this.username = username;
        this.userpassword = userpassword;
        this.useremail = useremail;
    }

    public User(int userid, String username, String userpassword, String useremail) {

        this.userid = userid;
        this.username = username;
        this.userpassword = userpassword;
        this.useremail = useremail;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsermail() {
        return useremail;
    }

    public void setUsermail(String usermail) {
        this.useremail = usermail;
    }


    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}

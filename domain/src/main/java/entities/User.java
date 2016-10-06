package entities;

/**
 * Created by kazaf on 16-10-1.
 */
public class User {

    int userid;
    String username;
    String password;
    String useremail;

    public User() {
    }

    public User(int userid, String username, String password, String useremail) {

        this.userid = userid;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsermail() {
        return useremail;
    }

    public void setUsermail(String usermail) {
        this.useremail = usermail;
    }
}

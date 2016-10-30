package dao;

import entities.User;

import java.util.List;

/**
 * Created by kazaf on 16-10-1.
 */
public interface UserDao {

    public User getUser(int userid) ;

    public User getUserbyEmail(String useremail);

    public void insertUser(User user);

    public void modifyUser(User user);

    public List<User> showallusers();

    public User getActiveUser(int userid);


}

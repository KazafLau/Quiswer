package dao;

import entities.User;

/**
 * Created by kazaf on 16-10-1.
 */
public interface UserDao {

    public User getUser(int userid) ;

    public User getUserbyEmail(String useremail);

    public void insertUser(User user);

    public void modifyUser(User user);


}

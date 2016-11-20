package dao;

import entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kazaf on 16-10-1.
 */
@Repository
public interface UserDao {

    public User getUser(int userid) ;

    public User getUserbyEmail(String useremail);

    public void insertUser(User user);

    public void modifyUser(User user);

    public List<User> showallusers();

    public List<User> showUserswithName(String username);

    public User getActiveUser(int userid);


    public User getUserfromID(int userid);

}

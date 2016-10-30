package dao;

import entities.Friends;

import java.util.List;

/**
 * Created by kazaf on 16-10-5.
 */
public interface FriendsDao {


    public void makefriends(Friends friends);

    public Friends getfriends(Friends friends);

    public List<Friends> showfriends(int userid);

    public Friends showfriendship(Friends friends);
}

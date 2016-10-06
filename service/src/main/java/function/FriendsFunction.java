package function;

import conf.Conf;
import entities.Friends;
import entities.User;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazaf on 16-10-5.
 */
public class FriendsFunction {

    @Resource
    private Conf conf;

    public String MakeFriends(int userid1,int userid2){
        Friends friends=new Friends();
        if(userid1>userid2) {
            friends.setFriend1(userid2);
            friends.setFriend2(userid1);
        }else{
            friends.setFriend1(userid1);
            friends.setFriend2(userid2);
        }

        if(conf.getFriendsDao().getfriends(friends)!=null)
            return userid1+" and "+userid2+" already made friends";
        else {
            conf.getFriendsDao().makefriends(friends);
            conf.getSession().commit();
            return userid1+" and "+userid2+" make friends successfully";
        }

    }

    public List<User> ShowFriends(int userid){
        List<User> friendList=new ArrayList<User>();
        for( Friends friend:conf.getFriendsDao().showfriends(userid))
        {
            if(friend.getFriend1()==userid){
                friendList.add(conf.getUserDao().getUser(friend.getFriend2()));
            }else {
                friendList.add(conf.getUserDao().getUser(friend.getFriend1()));
            }
        }
        return friendList;
    }
}

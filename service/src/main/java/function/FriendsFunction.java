package function;

import conf.Conf;
import entities.Friends;
import entities.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazaf on 16-10-5.
 */
@Component
public class FriendsFunction {

    @Resource
    private Conf conf;

    public String MakeFriends(int userid1,int userid2){
        Friends friends=compareID(userid1,userid2);
        if(conf.getFriendsDao().getfriends(friends)!=null)
            return userid1+" and "+userid2+" already made friends";
        else {
            conf.getFriendsDao().makefriends(friends);
            conf.getSession().commit();
            return userid1+" and "+userid2+" make friends successfully";
        }

    }

    public List<Friends> ShowFriendsShip(int userid){
        //返回的是List<Friedns>类型,即好友关系的列表
        //返回了两个List 一个是在friends_t表中的所有记录List,另一个是在user_t中的好友记录
        return conf.getFriendsDao().showfriends(userid);
    }

    public List<User> ShowFriends(int userid){
        List<User> userList=new ArrayList<User>();
        for( Friends friend:ShowFriendsShip(userid))
        {
            if(friend.getFriend1()==userid){
                userList.add(conf.getUserDao().getUser(friend.getFriend2()));
            }else {
                userList.add(conf.getUserDao().getUser(friend.getFriend1()));
            }
        }
        return userList;
    }

    public int ShowFriendShip(int userid1,int userid2){
        Friends friends=compareID(userid1,userid2);
        if(conf.getFriendsDao().showfriendship(friends)==null){
            System.out.println(userid1+" and "+userid2+" are not friends!");
            return 0;
        }else{
            System.out.println(userid1+" and "+userid2+" are already friends!");
            return 1;
        }
    }



    private Friends compareID(int userid1,int userid2){
        Friends friends=new Friends();
        if(userid1>userid2) {
            friends.setFriend1(userid2);
            friends.setFriend2(userid1);
        }else{
            friends.setFriend1(userid1);
            friends.setFriend2(userid2);
        }
        return friends;
    }
}

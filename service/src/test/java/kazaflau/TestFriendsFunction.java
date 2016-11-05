package kazaflau;

import entities.Friends;
import entities.User;
import function.FriendsFunction;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Kazaf on 16/10/26.
 */
public class TestFriendsFunction {

    FriendsFunction friendsFunction=new FriendsFunction();



    @Test
    public void testShowFriendShip(){
        System.out.println("========testShowFriendShip========");
        for(Friends friends:friendsFunction.ShowFriendsShip(1)){
            System.out.println("1:"+friends.getFriend1()+"   2:"+friends.getFriend2()+" :"+friends.getFriends_time()+"  active:"+friends.isFriends_active());
        }
    }

    @Test
    public void testShowFriends(){
        System.out.println("========testShowFriends========");
        for(User user:friendsFunction.ShowFriends(28)){
        System.out.println(user.getUserid());}
    }

}

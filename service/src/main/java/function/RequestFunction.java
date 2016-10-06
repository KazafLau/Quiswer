package function;

import conf.Conf;
import entities.Friends;
import entities.Request;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by kazaf on 16-10-5.
 */

public class RequestFunction {

    @Resource
    private Conf conf;

    public String addRequest(int userid1,int userid2,String message){

        if(conf.getUserDao().getUser(userid2)!=null)
        {
        Friends friends=new Friends(userid1,userid2);
        if(conf.getFriendsDao().getfriends(friends)!=null)
            return "already friends,please dont send request!";
        else{
            Request request=new Request();
            request.setRequest_from(userid1);
            request.setRequest_to(userid2);
            request.setRequest_time(new Timestamp(System.currentTimeMillis()));
            request.setRequest_message(message);
            conf.getRequestDao().addrequest(request);
            conf.getSession().commit();
            return "request made successfully";}
            }
        else
            return "the user is not existed!";
    }

    public String responseRequest(int request_id,int request_state){
        FriendsFunction friendsFunction=new FriendsFunction();
        Request request=conf.getRequestDao().findrequest(request_id);
        request.setRequest_state(request_state);
        request.setRequest_modifytime(new Timestamp(System.currentTimeMillis()));
        conf.getRequestDao().modifyrequest(request);
        if (request_state==1)
            return friendsFunction.MakeFriends(request.getRequest_from(),request.getRequest_to());
        else
        return "request response deny ,please make another request";
    }
}

package function;

import conf.Conf;
import entities.Friends;
import entities.Request;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kazaf on 16-10-5.
 */
@Component
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
            request.setRequest_message(message);
            conf.getRequestDao().addrequest(request);
            conf.getSession().commit();
            return "ok";}
            }
        else
            return "the user is not existed!";
    }

    public String responseRequest(Request request){
        conf.getRequestDao().modifyrequest(request);
        conf.getSession().commit();
            return "response successfully!";
    }

    public List<Request> listRequest(int userid){
        //显示的是state=0的userid为request_to的所有request
        return conf.getRequestDao().showrequests(userid);
    }


    public Map<Request,String> MapRequestwithName(int userid){
        Map<Request,String> requestmap=new HashMap<Request, String>();
        for (Request request:listRequest(userid)){
            requestmap.put(request,conf.getUserDao().getUserfromID(request.getRequest_from()).getUsername());
        }
        return requestmap;
    }

    public Request findRequest(int request_id){
        return conf.getRequestDao().findrequest(request_id);
    }
}

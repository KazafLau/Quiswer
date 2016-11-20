package function;

import conf.Conf;
import dao.FriendsDao;
import dao.RequestDao;
import dao.UserDao;
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

    //@Resource
    //private Conf conf;
    @Resource
    private RequestDao requestDao;
    @Resource
    private UserDao userDao;
    @Resource
    private FriendsDao friendsDao;

    public String addRequest(int userid1,int userid2,String message){
        if(userDao.getUser(userid2)!=null)
        {
        Friends friends=new Friends(userid1,userid2);
        if(friendsDao.getfriends(friends)!=null)
            return "already friends,please dont send request!";
        else{
            Request request=new Request();
            request.setRequest_from(userid1);
            request.setRequest_to(userid2);
            request.setRequest_message(message);
            requestDao.addrequest(request);
            //conf.getSession().commit();
            return "ok";}
            }
        else
            return "the user is not existed!";
    }

    public String responseRequest(Request request){
        requestDao.modifyrequest(request);
        //conf.getSession().commit();
        if(request.getRequest_state()==1)
            return "acceptfriend";
        else
            return "denyfriend";
    }

    public List<Request> listRequest(int userid){
        //显示的是state=0的userid为request_to的所有request
        return requestDao.showrequests(userid);
    }


    public Map<Request,String> MapRequestwithName(int userid){
        Map<Request,String> requestmap=new HashMap<Request, String>();
        for (Request request:listRequest(userid)){
            requestmap.put(request,userDao.getUserfromID(request.getRequest_from()).getUsername());
        }
        return requestmap;
    }

    public Request findRequest(int request_id){
        return requestDao.findrequest(request_id);
    }
}

package kazaflau;

import entities.Request;
import function.RequestFunction;
import org.junit.Test;

/**
 * Created by Kazaf on 16/10/26.
 */
public class TestRequestFunction {

    RequestFunction requestFunction=new RequestFunction();

    @Test
    public void testaddRequest(){
        int userid1=3;
        int userid2=36;
        String message="this is a test request!";
        System.out.println();
        System.out.println();
        System.out.println("=============testaddRequest==================");
        System.out.println(requestFunction.addRequest(userid1,userid2,message));
    }

    @Test
    public void testresponseRequest(){
        Request request=requestFunction.findRequest(20);
        request.setRequest_state(1);
        System.out.println();
        System.out.println();
        System.out.println("=============testresponseRequest==================");
        System.out.println(requestFunction.responseRequest(request));

    }

    @Test
    public void testlistRequest(){
        System.out.println();
        System.out.println();
        System.out.println("=============testlistRequest==================");
        for(Request request:requestFunction.listRequest(2)){
            System.out.println(request.getRequest_id()+"    "+request.getRequest_from()+"    "+request.getRequest_message()+"     "+request.getRequest_state()+"    "+request.getRequest_time());
        }
    }

    @Test
    public void testfindRequest(){
        Request request=requestFunction.findRequest(11);
        System.out.println();
        System.out.println();
        System.out.println("=============testfindRequest==================");
        System.out.println(request.getRequest_id()+"  from  "+request.getRequest_from()+"  to  "+request.getRequest_to()+"    "+request.getRequest_message()+"     "+request.getRequest_state()+"    "+request.getRequest_time());
    }
}

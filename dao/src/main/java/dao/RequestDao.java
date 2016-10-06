package dao;

import entities.Request;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by kazaf on 16-10-5.
 */
public interface RequestDao {

    public void addrequest(Request request);

    public void modifyrequest(Request request);

    public List<Request> showrequests(int request_to);

    public Request findrequest(int request_id);

    public void modiyrequeststate(int request_id, int request_state, Timestamp request_modifytime);

}

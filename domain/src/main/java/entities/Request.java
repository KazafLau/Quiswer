package entities;

import java.sql.Timestamp;

/**
 * Created by kazaf on 16-10-5.
 */
public class Request {
    public int request_id;
    public Timestamp request_time;
    public int request_from;
    public int request_to;
    public String request_message;
    public int request_state;
    public Timestamp request_modifytime;

    public Timestamp getRequest_modifytime() {
        return request_modifytime;
    }

    public void setRequest_modifytime(Timestamp request_modifytime) {
        this.request_modifytime = request_modifytime;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public Timestamp getRequest_time() {
        return request_time;
    }

    public void setRequest_time(Timestamp request_time) {
        this.request_time = request_time;
    }

    public int getRequest_from() {
        return request_from;
    }

    public void setRequest_from(int request_from) {
        this.request_from = request_from;
    }

    public int getRequest_to() {
        return request_to;
    }

    public void setRequest_to(int request_to) {
        this.request_to = request_to;
    }

    public String getRequest_message() {
        return request_message;
    }

    public void setRequest_message(String request_message) {
        this.request_message = request_message;
    }

    public int getRequest_state() {
        return request_state;
    }

    public void setRequest_state(int request_state) {
        this.request_state = request_state;
    }
}

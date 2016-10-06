package entities;

/**
 * Created by kazaf on 16-10-5.
 */
public class Friends {

    public int frirends_id;
    public int friend1;
    public int friend2;

    public Friends() {
    }

    public Friends(int friend1, int friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    public int getFrirends_id() {
        return frirends_id;
    }

    public void setFrirends_id(int frirends_id) {
        this.frirends_id = frirends_id;
    }

    public int getFriend1() {
        return friend1;
    }

    public void setFriend1(int friend1) {
        this.friend1 = friend1;
    }

    public int getFriend2() {
        return friend2;
    }

    public void setFriend2(int friend2) {
        this.friend2 = friend2;
    }
}

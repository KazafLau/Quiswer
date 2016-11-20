package conf;

import dao.FriendsDao;
import dao.QuestionDao;
import dao.RequestDao;
import dao.UserDao;
import entities.Friends;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by kazaf on 16-10-1.
 */
//@Repository
public class Conf {


    private static Reader reader;
    private static SqlSession session;
    private static SqlSessionFactory factory;
    private static Resources resources;

    private static UserDao userDao;
    private static FriendsDao friendsDao;
    private static RequestDao requestDao;
    private static QuestionDao questionDao;



    static
    {
        try {
            reader = resources.getResourceAsReader("conf.xml");
            factory=new SqlSessionFactoryBuilder().build(reader);
                    }
    catch(IOException e)
    { e.printStackTrace();}
    }

    public static Reader getReader(){
        if(reader==null)
            try{
            reader=resources.getResourceAsReader("conf.Conf.xml");
        }catch (IOException e){e.printStackTrace();}
        return reader;
    }

    public static SqlSessionFactory getFactory(){
        if(factory==null){
            factory= new SqlSessionFactoryBuilder().build(reader);
        }
        return factory;
    }

    public static SqlSession getSession(){
        if (session==null){
            session=getFactory().openSession();
        }
        return session;
    }

    public static UserDao getUserDao(){
        userDao=getSession().getMapper(UserDao.class);
        return userDao;
    }

    public static FriendsDao getFriendsDao() {
        friendsDao=getSession().getMapper(FriendsDao.class);
        return friendsDao;
    }

    public static RequestDao getRequestDao() {
        requestDao=getSession().getMapper(RequestDao.class);
        return requestDao;
    }

    public static QuestionDao getQuestionDao() {
        questionDao=getSession().getMapper(QuestionDao.class);
        return questionDao;
    }
}

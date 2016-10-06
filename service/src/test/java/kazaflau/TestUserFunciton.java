package kazaflau;

import entities.Question;
import entities.Request;
import entities.User;
import function.FriendsFunction;
import function.QuestionFunction;
import function.RequestFunction;
import function.UserFunction;

/**
 * Created by kazaf on 16-10-3.
 */
public class TestUserFunciton {

    public static void main(String args[]){
       User user=new User();
        user.setUsername("Lucy");
        user.setPassword("201499");
        user.setUsermail("lucy2015@icloud.com");

        UserFunction userFunction=new UserFunction();
        FriendsFunction friendsFunction=new FriendsFunction();
        RequestFunction requestFunction=new RequestFunction();
        QuestionFunction questionFunction=new QuestionFunction();

        /*
        System.out.println(userFunction.Register(user));

        System.out.println(requestFunction.addRequest(1,2,"hello i m kazaf wanna make friends with u!"));

        user.setUsermail("liu.lucy@icloud.com");
        user.setPassword("201511");
        System.out.println(userFunction.Login(user));

        System.out.println(requestFunction.responseRequest(8,1));
        */

        for(User user1:friendsFunction.ShowFriends(2)){
            System.out.println(user1.getUsername()+" "+user1.getUserid()+" "+user1.getUsermail());
        }

        Question question=new Question();
        String question_text="hey i just want to test this question function, and i wannn ask you a little question i want , the question is that whether you wanna be different and successful from the others?";
        //System.out.println(questionFunction.AddQuestion(1,2,question_text,1));
        String answer_text="Yes i am and i d like to be the best one of me even the world, id like sacrifice myself to my dream";
        System.out.println(questionFunction.AnswerQuestion(1,answer_text));



    }
}

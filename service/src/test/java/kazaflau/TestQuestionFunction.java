package kazaflau;

import entities.Question;
import function.QuestionFunction;
import function.UserFunction;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kazaf on 16/10/26.
 */
public class TestQuestionFunction {

    QuestionFunction questionFunction=new QuestionFunction();
    UserFunction userFunction=new UserFunction();


    //@Test
    public void testAddQuestion(){
        Question question=new Question("hello it's me !",27,30,1);
        System.out.println();
        System.out.println();
        System.out.println("=============testAddQuestion==================");
        System.out.println(questionFunction.AddQuestion(question));
    }

    //@Test
    public void testAnswerQuestion(){
        System.out.println();
        System.out.println();
        System.out.println("=============testAnswerQuestion==================");
        System.out.println(questionFunction.AnswerQuestion(10,"im very happy to meetyou!"));

    }

   // @Test
    public void testShowALlQuestions(){
        System.out.println();
        System.out.println();
        System.out.println("=============testShowALlQuestions==================");
        for(Question question:questionFunction.ShowAllQuestions(2)){
            System.out.println(question.getQuestion_id()+"  "+question.getQuestion_from()+"  "+question.getQuestion_to()+"  "+question.getQuestion_text());
        }
    }

    //@Test
    public void testShowALlQuestionswithName(){
        System.out.println();
        System.out.println();
        System.out.println("=============testShowALlQuestionswithName==================");
        Map<Question,String> questionMap=questionFunction.ShowALLQuestionswithName(30);
        for(Question question:questionMap.keySet()) {
            System.out.println("Question :" +question.getQuestion_text()+ " from " + questionMap.get(question));
        }
    }

}

package function;

import conf.Conf;
import entities.Question;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by kazaf on 16-10-6.
 */
public class QuestionFunction {

    @Resource
    private Conf conf;

    public String AddQuestion(int userid,int friendid,String questiontext,int questionprivacy){
        Question question=new Question();
        question.setQuestion_from(userid);
        question.setQuestion_to(friendid);
        question.setQuestion_time(new Timestamp(System.currentTimeMillis()));
        question.setQuestion_privacy(questionprivacy);
        question.setQuestion_text(questiontext);

        conf.getQuestionDao().addquestion(question);
        conf.getSession().commit();
        return "question added successfully";
    }

    public String AnswerQuestion(int questionid,String answer){

        Question question=conf.getQuestionDao().findquestion(questionid);
        if(question==null){
            return  "the question_id is wrong ";
        }else{
            question.setQuestion_answer(answer);
            question.setAnswer_time(new Timestamp(System.currentTimeMillis()));
            conf.getQuestionDao().answerquestion(question);
            conf.getSession().commit();
        return "the question is answered successfully!";
        }
    }
}

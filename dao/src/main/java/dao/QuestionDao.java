package dao;

import entities.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kazaf on 16-10-5.
 */
@Repository
public interface QuestionDao {

    public Question findquestion(int questionid);

    public void addquestion(Question question);

    public void deletequestion(int questionid);

    public void modifyquestionprivarcy(int questionid,int privacy);

    public void answerquestion(Question question);

    public List<Question> showallquestions(int userid);
}

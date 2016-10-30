package dao;

import entities.Question;

import java.util.List;

/**
 * Created by kazaf on 16-10-5.
 */
public interface QuestionDao {

    public Question findquestion(int questionid);

    public void addquestion(Question question);

    public void deletequestion(int questionid);

    public void modifyquestionprivarcy(int questionid,int privacy);

    public void answerquestion(Question question);

    public List<Question> showallquestions(int userid);
}

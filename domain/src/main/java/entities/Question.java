package entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by kazaf on 16-10-5.
 */
public class Question implements Serializable {

    public int question_id;
    public String question_text;
    public int question_to;
    public int question_from;
    public int question_privacy;
    public Timestamp question_time;
    public int questions_state;
    public String question_answer;
    public Timestamp answer_time;

    public Question() {
    }

    public Question(String question_text, int question_to, int question_from, int question_privacy) {
        this.question_text = question_text;
        this.question_to = question_to;
        this.question_from = question_from;
        this.question_privacy = question_privacy;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public int getQuestion_to() {
        return question_to;
    }

    public void setQuestion_to(int question_to) {
        this.question_to = question_to;
    }

    public int getQuestion_from() {
        return question_from;
    }

    public void setQuestion_from(int question_from) {
        this.question_from = question_from;
    }

    public int getQuestion_privacy() {
        return question_privacy;
    }

    public void setQuestion_privacy(int question_privacy) {
        this.question_privacy = question_privacy;
    }

    public Timestamp getQuestion_time() {
        return question_time;
    }

    public void setQuestion_time(Timestamp question_time) {
        this.question_time = question_time;
    }

    public int getQuestions_state() {
        return questions_state;
    }

    public void setQuestions_state(int questions_state) {
        this.questions_state = questions_state;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    public Timestamp getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(Timestamp answer_time) {
        this.answer_time = answer_time;
    }
}

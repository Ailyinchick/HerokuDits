package incubator.service;

import java.util.Comparator;

public class SortByQuestionId implements Comparator<QuestionStatModel> {
    public int compare(QuestionStatModel q1, QuestionStatModel q2) {
        return q1.getQuestionId() - q2.getQuestionId();
    }
}




package incubator.service;


import incubator.dao.QuestionRepository;
import incubator.model.Answer;
import incubator.model.Question;
import incubator.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    /**
     * Method returns List of answers for current question by it's id
     * @param questionId
     * @return
     */

    public List<String> getAnswersByQuestion(int questionId) {
        List<Question> questions = questionRepository.findAll(Question.class, questionRepository.getBeanToBeAutowired());
        List<String> str = new ArrayList<>();

        if (questionId >= 0 && questionId <= questions.size()) {
            for (Question question : questions) {
                if (question.getQuestionId() == questionId) {
                    for (Answer answer : question.getAnswers()
                    ) {
                        str.add(answer.getDescription());
                    }
                }
            }
        } else {
            System.out.println("Такой вопрос не найден в БД");
        }
        return str;
    }

    /**
     * Method checks if question with current id is exist and returns it
     * @param id
     * @return
     */

    public Question getQuestionById(int id) {
        Question question = new Question();
        List<Question> questions = questionRepository.findAll(Question.class, questionRepository.getBeanToBeAutowired());
        if (id >= 0 && id <= questions.size()) {
            for (Question quest : questions
            ) {
                if (quest.getQuestionId() == id) {
                    question = quest;
                }
            }
        } else {
            System.out.println("Вопроса в таким описанием нет в БД");
        }
        return question;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll(Question.class, questionRepository.getBeanToBeAutowired());
    }

    /**
     * Method returns List of String with all questions description
     * @return
     */

    public List<String> getNamesQuestions() {
        List<String> namesTopics = new ArrayList<>();
        for (Question q : questionRepository.findAll(Question.class, questionRepository.getBeanToBeAutowired())
        ) {
            namesTopics.add(q.getDescription());
        }
        return namesTopics;
    }

    /**
     * Method creates question with current description and test and returns it
     * @param nameQuestion
     * @param test
     * @return
     */

    public Question getQuestionByDescription(String nameQuestion, Test test) {
        Question question = new Question();
        question.setDescription(nameQuestion);
        question.setTest(test);
        questionRepository.create(question);
        return question;
    }


}

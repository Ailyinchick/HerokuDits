package incubator.service;

import incubator.dao.QuestionRepository;
import incubator.dao.TestRepository;
import incubator.dao.TopicRepository;
import incubator.model.Question;
import incubator.model.Test;
import incubator.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTopicTestQuestionService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TopicService topicService;

    @Autowired
    TestService testService;

    @Autowired
    QuestionService questionService;

    /**
     * Method creates new question with current topic and for current test
     * @param nameTopic
     * @param nameTest
     * @param nameQuestion
     * @return
     */

    @Transactional
    public Question createNewQuestion(String nameTopic, String nameTest, String nameQuestion) {
        Topic topic = topicService.createTopicByName(nameTopic);
        Test test = testService.createTestByName(nameTest, topic);
        Question question = questionService.getQuestionByDescription(nameQuestion, test);
        return question;
    }

}









package incubator.dao;

import incubator.config.HibernateConfig;
import incubator.config.WebConfig;
import incubator.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;


@ComponentScan(basePackages = "incubator")
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:hibernate.properties")
public class QuestionRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void showAll() {
        for (Question q : questionRepository.findAll(Question.class, questionRepository.getBeanToBeAutowired())
        ) {
            System.out.println(q);
        }
    }

/*    @Test
    public void testsDifficulty() {
        for (Question q : questionRepository.findAll(Question.class, questionRepository.getBeanToBeAutowired())
        ) {
            System.out.println(q.getDescription() + " - " + questionRepository.testsDifficulty(q.getQuestionId()));
        }
    }*/

}



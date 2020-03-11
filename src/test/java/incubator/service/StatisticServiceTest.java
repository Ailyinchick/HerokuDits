package incubator.service;

import incubator.config.HibernateConfig;
import incubator.config.WebConfig;
import incubator.dao.StatisticRepository;
import incubator.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ComponentScan(basePackages = "incubator")
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:hibernate.properties")
public class StatisticServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    QuestionService questionService;

    @Autowired
    StatisticRepository statisticRepository;

    @Autowired
    UserService userService;

/*    @Test
    public void showAll() {
        for (int i = 1; i < 6; i++) {
            statisticService.userStatistic(i);
        }
    }*/

    @Test
    public void fastTest() {
        System.out.println(getClass().getResourceAsStream("db.prorperties"));
    }


    @Test
    public void save() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Statistic statistic = new Statistic();
        statistic.setCorrect(1);
        statistic.setDate(formatter.format(new Date()));
        statistic.setQuestion(questionService.getQuestionById(1));
        statistic.setUser(userService.getUserByUsername("GavinBelson"));
        statisticService.testingCreateMethod(statistic);
    }

    @Test
    public void statisticTest() {
        for (Statistic st : statisticRepository.personalUserTestStatistic("1", "2019-12-14 11:44:00", "2019-12-14 11:44:08")
        ) {
            System.out.println(st);
        }
    }

    @Test
    public void userStatisticTest() {
        List<QuestionStatModel> list = statisticRepository.personalUserStatistic(1);
        for (QuestionStatModel qsm: list
        ) {
            System.out.println(qsm);
        }
    }


    public void getAllStatisticByQuestionId(){
        List<Statistic> statistics = new ArrayList<>(statisticService.getFilteredStatisticByQuestionId(1));
        for (Statistic statistic: statistics
             ) {
            System.out.println(statistic);
        }
    }

    @Test
    public void testFindAll(){
        for (Statistic s: statisticService.findAll()
             ) {
            System.out.println(s);
        }
    }
}

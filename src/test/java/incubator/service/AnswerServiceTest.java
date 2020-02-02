package incubator.service;

import incubator.config.HibernateConfig;
import incubator.config.WebConfig;
import incubator.dao.AnswerRepository;
import incubator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@ComponentScan(basePackages = "incubator")
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:hibernate.properties")
public class AnswerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AnswerService answerService;

/*    @Test
    public void showAll() {
        System.out.println(answerService.getAnswerByDescription(""));
    }*/


    @Test
    public void test1() {

/*    A a = new A();
    a = new B();

    System.out.println(a.getClass());
    C.method12(a);*/

/*    B b = new B();
    A a = new B();
    b = (B) new A();

    a.meth();
    System.out.println(a.getClass());
    C.method12(a);*/
    }

}

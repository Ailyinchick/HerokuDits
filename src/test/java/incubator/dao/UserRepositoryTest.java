package incubator.dao;


import incubator.config.HibernateConfig;
import incubator.config.WebConfig;
import incubator.model.User;
import incubator.service.UserService;
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
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void showAll() {
        for (User u : userRepository.findAll(User.class, userRepository.getBeanToBeAutowired())
        ) {
            System.out.println(u);
        }
    }

    @Test
    public void getStatistics() {
        System.out.println(userService.getUserByUsername("GavinBelson").getStatistics());
    }

}

package incubator.service;

import incubator.dao.TestRepository;
import incubator.model.Question;
import incubator.model.Test;
import incubator.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;


    /**
     * Method returns object of class Test by input name of test
     *
     * @param testName
     * @return Test
     */

    public Test getTestByName(String testName) {
        List<Test> testList = testRepository.findAll(Test.class, testRepository.getBeanToBeAutowired());
        Test outTest = new Test();
        if (testList.toString().contains(testName)) {
            for (Test test : testList
            ) {
                if (test.getName().equals(testName)) {
                    outTest = test;
                }
            }
        } else {
            System.out.println("Тест с названием " + testName + " не найден в списке");
        }
        return outTest;
    }


    /**
     * Method returns List of  questions by test's name
     *
     * @param testName
     * @return List<Question>
     */

    public List<Question> getQuestionsByTest(String testName) {
        List<Test> tests = testRepository.findAll(Test.class, testRepository.getBeanToBeAutowired());
        List<Question> str = new ArrayList<>();

        if (tests.toString().contains(testName)) {
            for (Test test : tests) {
                if (test.getName().equals(testName)) {
                    str.addAll(test.getQuestions());
                }
            }
        } else {
            System.out.println("Тест с названием " + testName + " не найден в списке");
        }
        return str;
    }


    /**
     * Method returns List of test's names
     *
     * @return List<String>
     */

    public List<String> getNamesTests() {
        List<String> namesTopics = new ArrayList<>();
        for (Test t : testRepository.findAll(Test.class, testRepository.getBeanToBeAutowired())
        ) {
            namesTopics.add(t.getName());
        }
        return namesTopics;
    }


    /**
     * Method returns List of all tests
     *
     * @return List<Test>
     */

    public List<Test> getAllTests() {
        return testRepository.findAll(Test.class, testRepository.getBeanToBeAutowired());
    }


    /**
     * Method checks if test with this name is exist and edit it in that case, or creates new test in another and returns this test
     *
     * @param nameTest
     * @param topic
     */

    public Test createTestByName(String nameTest, Topic topic) {
        Test newTest = new Test();
        for (Test t : getAllTests()
        ) {
            if (nameTest.equals(t.getName())) {
                newTest.setTestId(t.getTestId());
            }
        }
        newTest.setName(nameTest);
        newTest.setDescription(nameTest);
        newTest.setTopic(topic);
        testRepository.create(newTest);
        return newTest;
    }

}

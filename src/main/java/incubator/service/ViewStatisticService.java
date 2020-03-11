package incubator.service;

import incubator.dao.QuestionRepository;
import incubator.model.Question;
import incubator.model.Statistic;
import incubator.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ViewStatisticService {


    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TestService testService;

    @Autowired
    StatisticService statisticService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;


    private ViewStatistic getQuestionInfo(Question question) {
        ViewStatistic viewStatistic;

        List<Statistic> statisticList = statisticService.getFilteredStatisticByQuestionId(question.getQuestionId());
        if (!statisticList.isEmpty()) {
            int numberOfTimes = statisticList.size();
            int countOfCorrectAnswers = 0;
            for (Statistic statistic : statisticList) {
                if (statistic.isCorrect(statistic.getCorrect())) {
                    countOfCorrectAnswers++;
                }
            }
            viewStatistic = new ViewStatistic(question.getDescription(), numberOfTimes, (int) Math.round(((double) countOfCorrectAnswers) / numberOfTimes * 100));
        } else {
            viewStatistic = null;
        }
        return viewStatistic;
    }

    public List<ViewStatistic> getQuestionStatisticList() {
        List<ViewStatistic> questionInfoList = new ArrayList<>();
        ViewStatistic viewStatistic;
        List<Question> questionList = questionService.getAllQuestions();
        for (Question question : questionList) {
            viewStatistic = getQuestionInfo(question);
            if (viewStatistic != null) {
                questionInfoList.add(viewStatistic);
            }
        }
        questionInfoList.sort(Comparator.comparing(ViewStatistic::getName));
        return questionInfoList;
    }

    private ViewStatistic getTestInfo(Test test) {
        ViewStatistic viewStatistic;
        List<Statistic> statisticList = statisticService.getFilteredStatisticByTestId(test.getTestId());
        if (!statisticList.isEmpty()) {
            int numberOfQuestionInTest = test.getQuestions().size();
            int numberOfTimes = statisticList.size();
            viewStatistic = new ViewStatistic(
                    test.getName(),
                    numberOfTimes / numberOfQuestionInTest,
                    calculatePercentage(statisticList));
        } else {
            viewStatistic = null;
        }
        return viewStatistic;
    }

    public List<ViewStatistic> getTestStatisticList() {
        List<ViewStatistic> testInfoList = new ArrayList<>();
        ViewStatistic viewStatistic;
        List<Test> testList = testService.getAllTests();
        for (Test test : testList) {
            viewStatistic = getTestInfo(test);
            if (viewStatistic != null) {
                testInfoList.add(viewStatistic);
            }
        }
        testInfoList.sort(Comparator.comparing(ViewStatistic::getName));
        return testInfoList;
    }

    private ViewStatistic getUserTestInfo(Statistic statistic) {
        ViewStatistic viewStatistic;
        List<Statistic> statisticList = statisticService.getFilteredStatisticByTestUser(statistic);
        if (!statisticList.isEmpty()) {
            int countTimesCompletedTest = countUserCompletedTest(statistic);
            viewStatistic = new ViewStatistic(
                    statistic.getUser().getFIO(),
                    statistic.getQuestion().getTest().getName(),
                    countTimesCompletedTest,
                    calculatePercentage(statisticList));
        } else {
            viewStatistic = null;
        }
        return viewStatistic;
    }

    private int countUserCompletedTest(Statistic statistic) {
        int count = 0;
        List<Statistic> statisticList = statisticService.getFilteredStatisticByTestId(statistic.getQuestion().getTest().getTestId());
        for (Statistic s : statisticList) {
            if (statistic.getUser().getUserId() == s.getUser().getUserId()) {
                count++;
            }
        }
        return count;
    }


    private int countCorrectAnswer(Statistic statistic) {
        int count = 0;
        List<Statistic> statisticList = statisticService.getFilteredStatisticByTestUser(statistic);

        for (Statistic s : statisticList) {
            if (s.getCorrect() == 1) {
                count++;
            }
        }
        return count;
    }

    public int calculatePercentage(List<Statistic> statisticList) {
        int countOfTrueAnswers = 0;
        double countQuestions = statisticList.size();

        for (Statistic statistic : statisticList) {
            if (statistic.getCorrect() == 1) countOfTrueAnswers++;
        }
        return (int) Math.round(countOfTrueAnswers / countQuestions * 100);
    }


    public List<ViewStatistic> getUserTestStatisticList() {
        List<ViewStatistic> userTestInfoList = new ArrayList<>();
        ViewStatistic viewStatistic;
        List<Statistic> statisticList = statisticService.findAll();
        for (Statistic statistic : statisticList) {
            viewStatistic = getUserTestInfo(statistic);
            if (viewStatistic != null) {
                userTestInfoList.add(viewStatistic);
            }
        }
        Set<ViewStatistic> set = new HashSet<>(userTestInfoList);
        userTestInfoList.clear();
        userTestInfoList.addAll(set);
        userTestInfoList.sort(Comparator.comparing(ViewStatistic::getFIO));
        return userTestInfoList;
    }

}
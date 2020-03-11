package incubator.service;

import incubator.dao.StatisticRepository;
import incubator.model.Question;
import incubator.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    public Map<String, Statistic> statList;


    /**
     * Method saves each statistic with correct date and time
     * @param map
     * @param endTest
     */
    @Transactional
    public void saveMapOfStat(Map<String, Statistic> map, String endTest) {
        for (Statistic st : map.values()
        ) {
            st.setDate(endTest);
            testingCreateMethod(st);
        }
    }


    public List<Statistic> findAll() {
        return statisticRepository.findAll(Statistic.class, statisticRepository.getBeanToBeAutowired());
    }

    /**
     * Method saves statistic in DB
     * @param statistic
     */
    @Transactional
    public void testingCreateMethod(Statistic statistic) {
        statisticRepository.testingCreateMethod(statistic, statisticRepository.getBeanToBeAutowired());
    }

    /**
     * Method returns List of 'Statistic' objects for current user by ints login and with current date and time from begin to end
     * @param userId
     * @param start
     * @param end
     * @return
     */
    public List<Statistic> selectUserTestStatistic(String userId, String start, String end) {
        return statisticRepository.personalUserTestStatistic(userId, start, end);
    }

    /**
     * Method returns List of 'Statistic' for current question by it's id
     * @param questionId
     * @return
     */
    public List<Statistic> getFilteredStatisticByQuestionId(int questionId) {
        List<Statistic> statistics = new ArrayList<>(findAll());
        statistics.removeIf(q -> questionId != q.getQuestion().getQuestionId());
        return statistics;
    }

    /**
     * Method returns List of 'Statistic' for current test by it's id
     * @param testId
     * @return
     */
    public List<Statistic> getFilteredStatisticByTestId(int testId) {
        List<Statistic> statistics = new ArrayList<>(findAll());
        statistics.removeIf(t -> testId != t.getQuestion().getTest().getTestId());
        return statistics;
    }

    /**
     * Method returns List of 'Statistic' for current user and current test
     * @param statistic
     * @return
     */
    public List<Statistic> getFilteredStatisticByTestUser(Statistic statistic) {
        List<Statistic> statistics = new ArrayList<>(findAll());
        statistics.removeIf(u -> statistic.getUser().getUserId() != u.getUser().getUserId());
        statistics.removeIf(t -> statistic.getQuestion().getTest().getTestId() != t.getQuestion().getTest().getTestId());
        return statistics;
    }

    public List<Statistic> getFilteredStatisticByUserId(int userId, List<Statistic> statistics) {
        statistics.removeIf(u -> userId != u.getUser().getUserId());
        return statistics;
    }

    public Map<String, Statistic> getStatList() {
        return statList;
    }

    public void setStatList(Map<String, Statistic> statList) {
        this.statList = statList;
    }

    public List<QuestionStatModel> getStatList(int userId) {
        return statisticRepository.personalUserStatistic(userId);
    }
}



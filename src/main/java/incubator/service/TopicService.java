package incubator.service;

import incubator.dao.TopicRepository;
import incubator.model.Test;
import incubator.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public List<Topic> getTopics() {
        return topicRepository.findAll(Topic.class, topicRepository.getBeanToBeAutowired());
    }

    public List<String> getTestsByTopic(String topicName) {
        List<String> str = new ArrayList<>();
        if (topicRepository.findAll(Topic.class, topicRepository.getBeanToBeAutowired()).toString().contains(topicName)) {
            for (Topic topic : topicRepository.findAll(Topic.class, topicRepository.getBeanToBeAutowired())
            ) {
                if (topic.getName().equals(topicName)) {
                    for (Test test : topic.getTests()
                    ) {
                        str.add(test.getName());
                    }
                }
            }
        } else {
            System.out.println("Такой топик не найден в БД");
        }
        return str;
    }

    public List<String> getNamesTopics() {
        List<String> namesTopicsL = new ArrayList<>();

        for (Topic t : topicRepository.findAll(Topic.class, topicRepository.getBeanToBeAutowired())
        ) {
            namesTopicsL.add(t.getName());
        }

        return namesTopicsL;
    }

    public Topic getTopicByName(String nameTopic) {

        for (Topic t : getTopics()
        ) {
            if (nameTopic.equals(t.getName())) {
                return t;
            }
        }
        Topic topic = new Topic();
        topic.setName(nameTopic);
        topicRepository.create(topic);
        return topic;
    }

    public Topic createTopicByName(String nameTopic) {
        Topic newTopic = new Topic();
        for (Topic t : getTopics()
        ) {
            if (nameTopic.equals(t.getName())) {
                newTopic.setTopicId(t.getTopicId());
                newTopic.setName(t.getName());
                newTopic.setDescription(t.getDescription());
                return newTopic;
            }
        }
        newTopic.setName(nameTopic);
        newTopic.setDescription(nameTopic);
        topicRepository.create(newTopic);
        return newTopic;


    }

}

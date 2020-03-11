package incubator.service;

import incubator.dao.UserRepository;
import incubator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    /**
     * Method returns List with usernames
     * @return
     */
    public List<String> getUsernames() {
        List<User> list = userRepository.findAll(User.class, userRepository.getBeanToBeAutowired());
        List<String> usernames = new ArrayList<>();
        for (User u : list
        ) {
            usernames.add(u.getLogin());
        }
        return usernames;
    }

    /**
     * Method returns user by its id
     * @param inUserId
     * @return
     */
    public User getUserByUserId(int inUserId) {
        User outUser = new User();
        if (inUserId >= 1 && inUserId <= getUsernames().size()) {
            for (User user : userRepository.findAll(User.class, userRepository.getBeanToBeAutowired())
            ) {
                if (user.getUserId() == inUserId) outUser = user;
            }
        } else {
            System.out.println("Пользователь с таким ID не найден в базе");
        }
        return outUser;
    }

    /**
     * Method returns object of class 'User' with current username
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        User outUser = new User();
        if (getUsernames().contains(username)) {
            List<User> users = getAllUsers();
            for (User u : users
            ) {
                if (u.getLogin().equals(username))
                    outUser = u;
            }
        } else {
            System.out.println("Неверное имя пользователя!");
            throw new NullPointerException("Такой логин не найден в методе getUserByUsername");
        }
        return outUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(User.class, userRepository.getBeanToBeAutowired());
    }


}

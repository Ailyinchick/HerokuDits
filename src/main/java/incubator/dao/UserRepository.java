package incubator.dao;

import incubator.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository implements DaoRepos<User> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    /**
     * Method checks if user with this name 'login' is exist
     * @param login
     * @return
     */

    public boolean ifExistsUserWithLogin(String login){
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from user where login = :login");
        query.setParameter("login", login);
        return query.list().isEmpty();
    }

}

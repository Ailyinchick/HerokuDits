package incubator.dao;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoRepos<T> {

    SessionFactory getBeanToBeAutowired();

    default void create(T t) {
        getBeanToBeAutowired().getCurrentSession().saveOrUpdate(t);
    }

    default void update(T t, SessionFactory sessionFactory) {
        sessionFactory.getCurrentSession().update(t);
    }

    default void delete(T t, SessionFactory sessionFactory) {
        sessionFactory.getCurrentSession().delete(t);
    }

    default void save(T t, SessionFactory sessionFactory) {
        sessionFactory.getCurrentSession().save(t);
    }

    default List<T> findAll(Class T, SessionFactory sessionFactory) {
        List<T> tlist = (List<T>) sessionFactory.openSession().createQuery("From " + T.getSimpleName()).list();
        return tlist;
    }

    default void testingCreateMethod(T t, SessionFactory sessionFactory) {
        sessionFactory.getCurrentSession().merge(t);
    }


}


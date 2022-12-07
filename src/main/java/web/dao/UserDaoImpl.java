package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("select us from User us").getResultList();
    }

    @Override
    public User getUserById(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        manager.persist(user);
    }

    @Override
    public void updateUser(User updatedUser) {
        manager.merge(updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        Query<User> query = (Query<User>) manager.createQuery("delete from User user where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}

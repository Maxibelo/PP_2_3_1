package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {
    private final EntityManager entityManager;

    @Autowired
    public UserDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<User> index() {
        return entityManager.createQuery("select p from User p", User.class)
                .getResultList();
    }


    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(int id, User updatedUser) {
        entityManager.getTransaction().begin();
        User userUpd = entityManager.find(User.class, id);
        userUpd.setFirstName(updatedUser.getFirstName());
        userUpd.setLastName(updatedUser.getLastName());
        userUpd.setEmail(updatedUser.getEmail());
        entityManager.persist(userUpd);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}



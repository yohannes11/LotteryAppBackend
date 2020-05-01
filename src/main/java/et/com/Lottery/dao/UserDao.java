package et.com.Lottery.dao;

import com.cassiomolin.user.domain.User;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO for User
 */
@Stateless
public class UserDao {
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public void create(User entity) {
        em.persist(entity);
    }

    public void deleteById(Long id) {
        User entity = em.find(User.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public User update(User entity) {
        return em.merge(entity);
    }

    public List<User> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u ORDER BY u.id", User.class);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }


    public User userFindByUserDataId(Long id) {
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u WHERE u.userData = :id", User.class);
        findAllQuery.setParameter("id", id);
        List<User> users = findAllQuery.getResultList();
        return (users.isEmpty()) ? null : users.get(0);
    }

    public User findUserByUserName(String userName) {
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u WHERE u.username = :userName", User.class);
        findAllQuery.setParameter("userName", userName);
        List<User> users = findAllQuery.getResultList();
        return (users.isEmpty()) ? null : users.get(0);
    }

    public List<User> listAllByGroup(Long userGroup, Integer startPosition, Integer maxResult) {
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u where u.userGroup=:userGroup ORDER BY u.id", User.class);
        findAllQuery.setParameter("userGroup", userGroup);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }

    public User userFindByUserDataIdByGroup(Long userData) {
        TypedQuery<User> findAllQuery = em.createQuery(
                "SELECT DISTINCT u FROM User u where  u.userData=:userData ORDER BY u.id", User.class);
        findAllQuery.setParameter("userData", userData);
        if (findAllQuery.getResultList().isEmpty()) {
            return null;
        } else {
            return findAllQuery.getResultList().get(0);
        }

    }

}

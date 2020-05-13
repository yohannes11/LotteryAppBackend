package et.com.Lottery.dao;


import et.com.Lottery.model.UsersData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UsersDataDao {
    @PersistenceContext(unitName = "LotteryApp-persistence-unit")
    private EntityManager em;

    public void create(UsersData entity) {
        em.persist(entity);
    }

    public UsersData createAndReturn(UsersData entity) {
        em.persist(entity);
        return entity;
    }

    public void deleteById(Long id) {
        UsersData entity = em.find(UsersData.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public UsersData findById(Long id) {
        return em.find(UsersData.class, id);
    }

    public UsersData update(UsersData entity) {
        return em.merge(entity);
    }

    public List<UsersData> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<UsersData> findAllQuery = em
                .createQuery("SELECT DISTINCT r FROM UsersData r ORDER BY r.id",
                        UsersData.class);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }

    public List<UsersData> searchUsersData(String parameter, Integer startPosition,
                                           Integer maxResult) {
        TypedQuery<UsersData> query = em.
                createQuery("SELECT DISTINCT c FROM UsersData c " +
                        "left join User u on u.userData=c.id " +
                        "where c.phoneNumber like:tel or " +
                        " SIMILARITY(u.username,:username) > 0.5  or " +
                        " SIMILARITY(c.firstName,:fname) > 0.5 or " +
                        " SIMILARITY(c.lastName,:lname) > 0.5 ORDER BY c.id DESC", UsersData.class);
        query.setParameter("fname", parameter);
        query.setParameter("username", parameter);
        query.setParameter("lname", parameter);
        query.setParameter("tel", "%" + parameter + "%");

        if (startPosition != null) {
            query.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

}
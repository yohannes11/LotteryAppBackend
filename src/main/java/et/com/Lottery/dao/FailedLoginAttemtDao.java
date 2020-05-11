package et.com.Lottery.dao;



import et.com.Lottery.model.FailedLoginAtempt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO for FailedLoginAtempt
 */
@Stateless
public class FailedLoginAttemtDao {
    @PersistenceContext(unitName = "LotteryApp-persistence-unit")
    private EntityManager em;

    public void create(FailedLoginAtempt entity) {
        em.persist(entity);
    }

    public void deleteById(Long id) {
        FailedLoginAtempt entity = em.find(FailedLoginAtempt.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public FailedLoginAtempt findById(Long id) {
        return em.find(FailedLoginAtempt.class, id);
    }

    public FailedLoginAtempt update(FailedLoginAtempt entity) {
        return em.merge(entity);
    }

    public List<FailedLoginAtempt> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<FailedLoginAtempt> findAllQuery = em.createQuery(
                "SELECT DISTINCT b FROM FailedLoginAtempt b ORDER BY b.id", FailedLoginAtempt.class);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }

    public int listAllCount() {
        TypedQuery<FailedLoginAtempt> findAllQuery = em.createQuery(
                "SELECT DISTINCT b FROM FailedLoginAtempt b ORDER BY b.id", FailedLoginAtempt.class);
        return findAllQuery.getResultList().size();
    }

    public void deleteAll() {
        Query deleteAll = em.createQuery(
                "DELETE from FailedLoginAtempt");
        deleteAll.executeUpdate();
    }

    public List<FailedLoginAtempt> findByUserId(Long userid) {
        TypedQuery<FailedLoginAtempt> findAllQuery = em.createQuery(
                "SELECT DISTINCT b FROM FailedLoginAtempt b WHERE b.userId = :userId", FailedLoginAtempt.class);
        findAllQuery.setParameter("userId", userid);
        return findAllQuery.getResultList();
    }
    public void clearLoginAttempts() {
        TypedQuery<FailedLoginAtempt> findAllQuery = em.createQuery(
                "UPDATE  FailedLoginAtempt set hourlyAtempt=0,dailyAtempt=0,status=1 ", FailedLoginAtempt.class);
        findAllQuery.executeUpdate();
    }

}

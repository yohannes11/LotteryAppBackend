package et.com.Lottery.dao;


import et.com.Lottery.model.CompanySetting;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO for CompanySetting
 */
@Stateless
public class CompanySettingDao {
    @PersistenceContext(unitName = "LotteryApp-persistence-unit")
    private EntityManager em;

    public void create(CompanySetting entity) {
        em.persist(entity);
    }

    public void deleteById(Long id) {
        CompanySetting entity = em.find(CompanySetting.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    public CompanySetting findById(Long id) {
        return em.find(CompanySetting.class, id);
    }

    public CompanySetting update(CompanySetting entity) {
        return em.merge(entity);
    }

    public List<CompanySetting> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<CompanySetting> findAllQuery = em.createQuery(
                "SELECT DISTINCT c FROM CompanySetting c ORDER BY c.id",
                CompanySetting.class);
        if (startPosition != null) {
            findAllQuery.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            findAllQuery.setMaxResults(maxResult);
        }
        return findAllQuery.getResultList();
    }

    public CompanySetting findByName(String name) {
        TypedQuery<CompanySetting> findAllQuery = em.createQuery(
                "SELECT DISTINCT c FROM CompanySetting c WHERE c.name = :settingName ORDER BY c.id",
                CompanySetting.class);
        findAllQuery.setParameter("settingName", name);

        if (findAllQuery.getResultList().isEmpty()) {
            return null;

        } else {
            return findAllQuery.getResultList().get(0);

        }
    }

}



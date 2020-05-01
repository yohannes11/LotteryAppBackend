package et.com.Lottery.dao;



import et.com.Lottery.model.PasswordHistory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO for PasswordHistory
 */
@Stateless
public class PasswordHistoryDao {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public void create(PasswordHistory entity) {
		em.persist(entity);
	}

	public void deleteById(Long id) {
		PasswordHistory entity = em.find(PasswordHistory.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public PasswordHistory findById(Long id) {
		return em.find(PasswordHistory.class, id);
	}

	public PasswordHistory update(PasswordHistory entity) {
		return em.merge(entity);
	}

	public List<PasswordHistory> listAll(Integer startPosition,
			Integer maxResult) {
		TypedQuery<PasswordHistory> findAllQuery = em.createQuery(
				"SELECT DISTINCT p FROM PasswordHistory p ORDER BY p.id",
				PasswordHistory.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
	public List<PasswordHistory> listByUserId(Integer startPosition,
										 Integer maxResult, Long userId) {
		TypedQuery<PasswordHistory> findAllQuery = em.createQuery(
				"SELECT DISTINCT p FROM PasswordHistory p WHERE p.userId = :userId",
				PasswordHistory.class);
		findAllQuery.setParameter("userId", userId);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}

}

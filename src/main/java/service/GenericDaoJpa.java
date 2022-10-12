package service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import domain.Stadium;

@Transactional
public class GenericDaoJpa<T> implements GenericDao<T> {

    private Class<T> type;
    protected EntityManager em;

    public GenericDaoJpa(Class<T> type) {
        super();
        this.type = type;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    @Override
    public T get(int id) {
        T entity = this.em.find(this.type, id);
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return this.em.createQuery("select entity from " + this.type.getName() + " entity").getResultList();
    }

    @Override
    public void insert(T object) {
        em.persist(object);
    }

    @Override
    public void delete(T object) {
        em.remove(em.merge(object));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(int id) {
        T entity = this.em.find(this.type, id);
        return entity != null;
    }

    @Override
    public T update(T object) {
        return em.merge(object);
    }

	@Override
	public Stadium geefStadium(String naam) {
		return em.createNamedQuery("Stadium.geefStadiumViaNaam", Stadium.class).setParameter("x", naam).getSingleResult();
	}
    
    
}

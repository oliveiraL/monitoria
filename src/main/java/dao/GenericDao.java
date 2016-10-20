package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDao {
	@PersistenceContext
	private EntityManager em;
	
	public Query getQuey(String hql){
		return  em.createQuery(hql);
	}
}

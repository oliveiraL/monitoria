package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
@Stateless
public class CrudDao<T> {
	@PersistenceContext
	private EntityManager em;
	
	private Class<T> typeClass;
	
	public Query getQuey(String hql){
		return  em.createQuery(hql);
	}
	
	public void salvar(T obj) {
		em.persist(obj);
	}
	public void atualizar(T obj) {
		em.merge(obj);
	}
	public void remover(T obj) {
		em.remove(obj);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		String qs = "select * from "+typeClass.getName();
		Query q = getQuey(qs);
		return (List<T>) q.getResultList();
	}
}

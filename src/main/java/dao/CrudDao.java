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
		obj = em.merge(obj);
		em.remove(obj);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar(Class<T> typeClass) {
		String qs = "select d from "+typeClass.getName()+" d";
		Query q = getQuey(qs);
		return (List<T>) q.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public T finByID(int id, Class<T> typeClass) {
		return em.find(typeClass, id);
	}
}
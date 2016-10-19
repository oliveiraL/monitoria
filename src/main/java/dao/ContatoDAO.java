package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dominio.Contato;

@Stateless
public class ContatoDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Contato c) {
		em.persist(c);
	}
	
	public void atualizar(Contato c) {
		em.merge(c);
	}
	
	public void remover(Contato c) {
		c = em.find(Contato.class, c.getId());
		em.remove(c);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> listar() {
		String qs = "select c from Contato c";
		Query q = em.createQuery(qs);
		return (List<Contato>) q.getResultList();
	}
	
	public Contato buscarContatoNome(String nome) {
		String qs = "select c from Contato c where c.nome = :nome";
		Query q = em.createQuery(qs);
		q.setParameter("nome", nome);
		try {
			return (Contato) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}

package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import dominio.Usuario;

@Stateless
public class UsuarioDAO extends GenericDao {
	
	public Usuario buscarLogin(String idSigaa) {
		String hql = "Select u from Usuario u where u.idSigaa = "+idSigaa;
		try {
			return (Usuario) getQuey(hql).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	
	}
}

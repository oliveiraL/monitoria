package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dominio.Comentario;
import dominio.Resposta;

@Stateless
public class ComentarioDao extends GenericDao {
	
	public Long quantComentariosByDuvida(int idDuvida){
		String hql = "Select COUNT(c) from Comentario c where c.duvida.id = :idDuvida ";
		Query query = getQuey(hql);
		query.setParameter("idDuvida", idDuvida);
		return (Long) query.getSingleResult();
	}
	
	public Long quantComentariosByResposta(int idResposta){
		String hql = "Select COUNT(c) from Comentario c where c.resposta.id = :idResposta ";
		Query query = getQuey(hql);
		query.setParameter("idResposta", idResposta);
		return (Long) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> getListaComentarioByDuvida(int idDuvida){
		String hql = "Select c from Comentario c where c.duvida.id = :idDuvida";
		Query query = getQuey(hql);
		query.setParameter("idDuvida", idDuvida);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comentario> getListaComentarioByResposta(int idResposta){
		String hql = "Select c from Comentario c where c.resposta.id = :idResposta";
		Query query = getQuey(hql);
		query.setParameter("idResposta", idResposta);
		return query.getResultList();
	}
}

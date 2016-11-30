package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dominio.Resposta;

@Stateless
public class ComentarioDao extends GenericDao {
	
	public Long quantComentariosByDuvida(int idDuvida){
		String hql = "Select COUNT(c) from Comentario r where c.duvida.id = :idDuvida ";
		Query query = getQuey(hql);
		query.setParameter("idDuvida", idDuvida);
		return (Long) query.getSingleResult();
	}
	
	public Long quantComentariosByResposta(int idResposta){
		String hql = "Select COUNT(c) from Comentario r where c.resposta.id = :idResposta ";
		Query query = getQuey(hql);
		query.setParameter("idResposta", idResposta);
		return (Long) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Resposta> getListaComentarioByDuvida(int idDuvida){
		String hql = "Select c from Comentario c where c.duvida.id = :idDuvida";
		Query query = getQuey(hql);
		query.setParameter("idDuvida", idDuvida);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Resposta> getListaComentarioByResposta(int idResposta){
		String hql = "Select c from Comentario c where c.resposta.id = :idResposta";
		Query query = getQuey(hql);
		query.setParameter("idResposta", idResposta);
		return query.getResultList();
	}
}

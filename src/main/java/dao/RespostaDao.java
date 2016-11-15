package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dominio.Resposta;

@Stateless
public class RespostaDao extends GenericDao {
	public Long quantRespostas(int idDuvida){
		String hql = "Select COUNT(r) from Resposta r where r.duvida.id = :idDuvida ";
		Query query = getQuey(hql);
		query.setParameter("idDuvida", idDuvida);
		return (Long) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Resposta> listaRespostas(int idDuvida){
		String hql = "Select r from Resposta r where r.duvida.id = :idDuvida";
		Query query = getQuey(hql);
		query.setParameter("idDuvida", idDuvida);
		return query.getResultList();
	}
}

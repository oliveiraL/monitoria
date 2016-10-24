package dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class RespostaDao extends GenericDao {
	public Long quantRespostas(int idDuvida){
		String hql = "Select COUNT(r) from Resposta r where r.duvida.id = :idDuvida ";
		Query query = getQuey(hql);
		query.setParameter("idDuvida", idDuvida);
		return (Long) query.getSingleResult();
	}
}

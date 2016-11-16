package dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dominio.Disciplina;
import dominio.Perfil;

@Stateless
public class DisciplinaDao extends GenericDao {
	public Disciplina getCodigo(String codigo){
		String hql = "Select d from Disciplina d where d.codigo = :codigo";
		Query query = getQuey(hql);
		return (Disciplina) query.setParameter("codigo",codigo).getSingleResult();
		
	}
}

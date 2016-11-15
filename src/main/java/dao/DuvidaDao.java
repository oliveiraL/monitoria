package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dominio.Duvida;
import dominio.Resposta;

@Stateless
public class DuvidaDao extends GenericDao{
	@SuppressWarnings("unchecked")
	public List<Duvida> listaPorData(){
		return getQuey("Select d from Duvida d ORDER BY  d.dataCadastro DESC").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Duvida> listDuvidasPorPessoa(int idPessoa){
		String hql = "Select d from Duvida d where d.pessoa.id = :idPessoa";
		Query query = getQuey(hql);
		query.setParameter("idPessoa", idPessoa);
		return query.getResultList();	
	}
}

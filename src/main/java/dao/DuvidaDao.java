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
	
	@SuppressWarnings("unchecked")
	public List<Duvida> listDuvidasPorDisciplina(int idDisciplina){
		String hql = "Select d from Duvida d where d.disciplina.id = :idDisciplina";
		Query query = getQuey(hql);
		query.setParameter("idDisciplina", idDisciplina);
		return query.getResultList();	
	}
}

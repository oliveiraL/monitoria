package dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dominio.Pessoa;

@Stateless
public class PessoaDao extends GenericDao{
	public Pessoa emailCadastrado(String nome){
		String hql = "select p from Pessoa p where p.nome like :nome ";
		Query query = getQuey(hql);
		query.setParameter("nome", nome);
		try{
			return (Pessoa) query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Long proximaPessoa(){
		String hql = "select COUNT(p) from Pessoa p";
		Query query = getQuey(hql);
		return (Long) query.getSingleResult();
	}
	
	public Integer insert(Pessoa p){
		String hql = "into pessoas (id_pessoa, cpf, email, nome, sexo) values ("+(proximaPessoa()+1)+", "+p.getCpf()+
				", "+p.getEmail()+","+p.getNome()+", "+p.getSexo()+")";
		return getQuey(hql).executeUpdate();
	}
}

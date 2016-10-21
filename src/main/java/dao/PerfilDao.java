package dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import dominio.Perfil;

@Stateless
public class PerfilDao extends GenericDao{
	
	public Perfil findByPessoa(int id_pessoa){
		String hql = "Select p from Perfil p where p.pessoa.id = :id_pessoa";
		Query query = getQuey(hql);
		return (Perfil) query.setParameter("id_pessoa",id_pessoa).getSingleResult();
	}
}

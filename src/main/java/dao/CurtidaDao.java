package dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import dominio.Pessoa;
import dominio.Curtida;
import dominio.Duvida;
@Stateless
public class CurtidaDao extends GenericDao {
	public Boolean existeCurtida(Pessoa pessoa, Duvida duvida){
		
		try{
			return getQuey("Select c from Curtida c where c.pessoa.id = :id_pessoa and c.duvida.id = :id_duvida")
				.setParameter("id_pessoa", pessoa.getId())
				.setParameter("id_duvida", duvida.getId())
				.getSingleResult() != null;
	
		}catch (NoResultException e) {
			return false;
		}
	}
	
	public Curtida getCurtir(Duvida duvida, Pessoa pessoa){
		try{
			return (Curtida) getQuey("Select c from Curtida c where c.pessoa.id = :id_pessoa and c.duvida.id = :id_duvida")
				.setParameter("id_pessoa", pessoa.getId())
				.setParameter("id_duvida", duvida.getId())
				.getSingleResult();
	
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Long qtdCurtidas(Duvida duvida){
		return (Long) getQuey("Select COUNT(c) from Curtida c where c.duvida.id = :id_duvida")
				.setParameter("id_duvida", duvida.getId()).getSingleResult();
	}
}

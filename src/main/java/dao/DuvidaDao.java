package dao;

import java.util.List;

import javax.ejb.Stateless;

import dominio.Duvida;

@Stateless
public class DuvidaDao extends GenericDao{
	@SuppressWarnings("unchecked")
	public List<Duvida> listaPorData(){
		return getQuey("Select d from Duvida d ORDER BY  d.dataCadastro DESC").getResultList();
	}
}

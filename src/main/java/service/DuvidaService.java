package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;


import dao.DuvidaDao;
import dominio.Duvida;

@Stateless
public class DuvidaService extends CrudService<Duvida> {
	
	@Inject
	private DuvidaDao duvidaDao;
	
	public List<Duvida> listaPorData(){
		return duvidaDao.listaPorData();
	}
}

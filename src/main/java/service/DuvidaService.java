package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;


import dao.DuvidaDao;
import dominio.Disciplina;
import dominio.Duvida;
import dominio.Pessoa;

@Stateless
public class DuvidaService extends CrudService<Duvida> {
	
	@Inject
	private DuvidaDao duvidaDao;
	
	public List<Duvida> listaPorData(){
		return duvidaDao.listaPorData();
	}
	
	public List<Duvida> listDuvidasPorPessoa(Pessoa pessoa){
		return duvidaDao.listDuvidasPorPessoa(pessoa.getId());
	}
	
	public List<Duvida> listDuvidasPorDisciplina(Disciplina disciplina){
		return duvidaDao.listDuvidasPorDisciplina(disciplina.getId());
	}
	
}

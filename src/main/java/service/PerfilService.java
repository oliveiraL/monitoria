package service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.PerfilDao;
import dominio.Perfil;
import dominio.Pessoa;

@Stateless
public class PerfilService extends GenericService<Perfil>{
	
	@Inject
	private PerfilDao perfilDao;
	
	public Perfil findByPessoa(Pessoa pessoa){
		return perfilDao.findByPessoa(pessoa.getId());
	}
	
}

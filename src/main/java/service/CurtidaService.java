package service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import dao.CurtidaDao;
import dominio.Curtida;
import dominio.Duvida;
import dominio.Pessoa;

@Stateless
public class CurtidaService extends CrudService<Curtida>{
	@Inject
	private CurtidaDao curtidaDao;
	
	public Boolean existeCurtida(Pessoa pessoa, Duvida duvida){
		return curtidaDao.existeCurtida(pessoa, duvida);
	}
	
	public Curtida getCurtir(Duvida duvida, Pessoa pessoa){
		return curtidaDao.getCurtir(duvida, pessoa);
	}
	
	public Long qtdCurtidas(Duvida duvida){
		return curtidaDao.qtdCurtidas(duvida);
	}
	
}

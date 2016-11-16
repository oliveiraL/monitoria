package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.RespostaDao;
import dominio.Duvida;
import dominio.Resposta;

@Stateless
public class RespostaService extends GenericService<Resposta>{
	
	@Inject
	private RespostaDao respostaDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long quantRespostas(Duvida duvida){
		return respostaDao.quantRespostas(duvida.getId());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Resposta> listaRespostas(Duvida duvida){
		return respostaDao.listaRespostas(duvida.getId());
	}
}

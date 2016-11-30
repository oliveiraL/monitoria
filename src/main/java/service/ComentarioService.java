package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.ComentarioDao;
import dao.RespostaDao;
import dominio.Comentario;
import dominio.Duvida;
import dominio.Resposta;

@Stateless
public class ComentarioService extends GenericService<Comentario>{
	
	@Inject
	private ComentarioDao comentarioDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long quantComentariosByDuvida(Duvida duvida){
		return comentarioDao.quantComentariosByDuvida(duvida.getId());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long quantComentariosByResposta(Resposta resposta){
		return comentarioDao.quantComentariosByResposta(resposta.getId());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Resposta> getListaComentarioByDuvida(Duvida duvida){
		return comentarioDao.getListaComentarioByDuvida(duvida.getId());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Resposta> getListaComentarioByResposta(Resposta resposta){
		return comentarioDao.getListaComentarioByResposta(resposta.getId());
	}
}
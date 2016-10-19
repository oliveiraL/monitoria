package negocio;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.ContatoDAO;
import dominio.Contato;

@Stateful
public class ContatosService {
	
	@Inject
	private ContatoDAO contatoDAO;
	
	public List<Contato> listarContatos() {
		return contatoDAO.listar();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastrarContato(Contato contato) {
		Contato c = contatoDAO.buscarContatoNome(contato.getNome());
		if (c == null) {
			contatoDAO.salvar(contato);
		} else {
			contatoDAO.atualizar(contato);
		}
	}
}

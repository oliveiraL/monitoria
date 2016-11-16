package service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.json.JSONObject;

import dao.PessoaDao;
import dominio.Pessoa;
import exception.NegocioException;

@Stateless
public class PessoaService extends CrudService<Pessoa> {
	
	@Inject
	private PessoaDao pessoaDao;

	public Pessoa cadastrarPessoa(String json) throws NegocioException{		
		JSONObject jsonObject = new JSONObject(json);
		Pessoa pessoa = pessoaDao.emailCadastrado(jsonObject.getString("email"));
		if(pessoa != null)
			return pessoa; 
		pessoa = new Pessoa();
		pessoa.setNome(jsonObject.getString("nome"));
		pessoa.setEmail(jsonObject.getString("email"));
		salvar(pessoa);
		return pessoa;
	}
	
}

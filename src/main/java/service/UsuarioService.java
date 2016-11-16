package service;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.json.JSONObject;

import dominio.Pessoa;
import dominio.Usuario;
import exception.APIException;
import exception.NegocioException;

@Stateless
public class UsuarioService extends CrudService<Usuario>{
	
	@Inject
	private ApiService apiService;
	
	@Inject
	private PessoaService pessoaService;
	
	@Inject
	private PerfilService perfilService;
	
	
	@Override
	public void salvar(Usuario obj) throws NegocioException {
		try {
			apiService.autentificacaoAplicacao();
			String res = apiService.dadosUsuario(obj.getLogin());
			JSONObject jsonObject = new JSONObject(res);
			Integer id = jsonObject.getInt("id");
			String json = apiService.perfilUsuario(id.toString());
			String j = apiService.turmasDiscente("");
			Pessoa pessoa = pessoaService.cadastrarPessoa(json);
			perfilService.cadastrarPerfil(json, pessoa);
		} catch (APIException e) {
			throw new NegocioException("Erro ao cadastrar usuario.");
		}catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
		//super.salvar(obj);
	}
	
}

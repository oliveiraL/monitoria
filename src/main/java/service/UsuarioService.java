package service;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import org.json.JSONObject;

import dao.UsuarioDAO;
import dominio.Pessoa;
import dominio.Usuario;
import exception.NegocioException;

@Stateless
public class UsuarioService extends CrudService<Usuario>{
	
	@Inject
	private ApiService apiService;
	
	@Inject
	private PessoaService pessoaService;
	
	@EJB
	private PerfilService perfilService;
	
	@Inject
	private UsuarioDAO usuarioDao;
	
	
	public Usuario buscarLogin(String idSigaa) {
		return usuarioDao.buscarLogin(idSigaa);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario cadastrar(String accessToken) throws NegocioException {
		try {
			Usuario obj = new Usuario();
			obj.setAccessToken(accessToken);
			String dadosUsuario = apiService.dadosUsuario(obj);
			JSONObject jsonObject = new JSONObject(dadosUsuario);
			Integer id = jsonObject.getInt("id");
			String perfilUsuario = apiService.perfilUsuario(id.toString(),obj);
			Pessoa pessoa = pessoaService.cadastrarPessoa(perfilUsuario);
			obj.setPessoa(pessoa);
			perfilService.cadastrarPerfil(perfilUsuario, obj);
			obj.setLogin(jsonObject.getString("username"));
			obj.setSenha(jsonObject.getString("password"));
			obj.setIdSigaa(id);
			obj.setAtivo(true);
			super.salvar(obj);
			return obj;
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	
	
}

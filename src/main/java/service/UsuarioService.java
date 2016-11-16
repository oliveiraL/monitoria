package service;


import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.UsuarioDAO;
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
	
	@Inject
	private UsuarioDAO usuarioDao;
	
	
	public Usuario buscarLogin(String login) {
		return usuarioDao.buscarLogin(login);
	}
	
	@Override
	public void salvar(Usuario obj) throws NegocioException {
		try {
			String res = apiService.dadosUsuario();
			JSONObject jsonObject = new JSONObject(res);
			Integer id = jsonObject.getInt("id");
			String json = apiService.perfilUsuario(id.toString());
			String j = apiService.turmasDiscente("");
			Pessoa pessoa = pessoaService.cadastrarPessoa(json);
			perfilService.cadastrarPerfil(json, pessoa);
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
		//super.salvar(obj);
	}
	
	public Usuario cadastrar() throws NegocioException {
		try {
			
			Pessoa p = new Pessoa();
			p.setCpf("123123123123");
			pessoaService.salvar(p);
			
			
			Usuario obj = new Usuario();			
			String dadosUsuario = apiService.dadosUsuario();
			JSONObject jsonObject = new JSONObject(dadosUsuario);
			Integer id = jsonObject.getInt("id");
			String perfilUsuario = apiService.perfilUsuario(id.toString());
			Pessoa pessoa = pessoaService.cadastrarPessoa(perfilUsuario);
			perfilService.cadastrarPerfil(perfilUsuario, pessoa);
			obj.setPessoa(pessoa);
			obj.setLogin(jsonObject.getString("username"));
			obj.setSenha(jsonObject.getString("password"));
			obj.setAtivo(true);
			super.salvar(obj);
			return obj;
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	
	
}

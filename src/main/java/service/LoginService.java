package service;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.UsuarioDAO;
import dominio.Usuario;
import exception.LoginException;
import exception.NegocioException;
import helpers.Faces;

@Stateless
public class LoginService {
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private ApiService apiService;
	
	public Usuario login() throws IOException, NegocioException{
		String dadosUsuario = apiService.dadosUsuario();
		JSONObject jsonObject = new JSONObject(dadosUsuario);
		String username = jsonObject.getString("username");
		Usuario u = usuarioService.buscarLogin(username);
		if (u != null) {
			return u;
			
		} else{
			Usuario usuario = usuarioService.cadastrar();
			return usuario;
		}
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario login(String l, String s) throws LoginException{
		Usuario u = usuarioService.buscarLogin(l);
		if (u != null) {
			if(!u.isAtivo()){
				throw new LoginException("usuario inativo.");
			}
			if (!u.getSenha().equals(s)){
				throw new LoginException("usuario e/ou senha incorreto.");
			}
		} else{
			throw new LoginException("usuario não cadastrado");
		}
		
		return u;
	}
}

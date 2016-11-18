package service;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import dominio.Usuario;
import exception.NegocioException;

@Stateless
public class LoginService {
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private ApiService apiService;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario login(String accessToken) throws IOException, NegocioException{
		Usuario usuario = new Usuario();
		usuario.setAccessToken(accessToken);
		String dadosUsuario = apiService.dadosUsuario(usuario);
		JSONObject jsonObject = new JSONObject(dadosUsuario);
		Integer idSigaa = jsonObject.getInt("id");
		usuario = usuarioService.buscarLogin(idSigaa.toString());
		if (usuario == null) {
			usuario = usuarioService.cadastrar(accessToken);
		}
		return usuario;
		
	}
}

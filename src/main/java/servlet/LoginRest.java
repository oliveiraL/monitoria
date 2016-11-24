package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dominio.Perfil;
import dominio.Usuario;
import exception.NegocioException;
import service.LoginService;
import service.PerfilService;

@Stateless
@Path("/entrar")
public class LoginRest {
	
	@EJB
	private LoginService loginService;
	
	@EJB
	private PerfilService perfilService;
	
	@GET
	@Path("/{accessToken}")
	@Produces("application/json; charset=UTF-8")
	public Perfil getPerfil(@PathParam("accessToken") String accessToken) throws IOException, NegocioException{
		Usuario usuario = loginService.login(accessToken);
		Perfil perfil = perfilService.findByPessoa(usuario.getPessoa());
		return perfil;
	}
}

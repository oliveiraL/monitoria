package servlet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dominio.Usuario;
import service.LoginService;
import service.UsuarioService;

@Stateless
@Path("/usuario")
public class UsuarioRest {
	
	@EJB
	private UsuarioService usuarioService;

	@EJB
	private LoginService loginService;
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json; charset=UTF-8")
	public Usuario getUsuario(@PathParam("id") String id){
		Usuario usuario = usuarioService.buscarLogin(id);
		if(usuario != null)
			return usuario;
		return new Usuario();
	}
}

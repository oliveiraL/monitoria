package servlet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dominio.Perfil;
import dominio.Usuario;
import service.PerfilService;

@Stateless
@Path("/perfil")
public class PerfilRest {
	
	@EJB
	private PerfilService perfilService;
	
	@GET
	@Path("/pessoa/{id}")
	@Produces("application/json; charset=UTF-8")
	public Perfil getPerfil(@PathParam("id") String id){
		return perfilService.findByPessoa(id);
	}
}

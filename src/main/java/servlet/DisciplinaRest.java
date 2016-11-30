package servlet;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dominio.Disciplina;
import dominio.Perfil;
import service.PerfilService;

@Stateless
@Path("/disciplina")
public class DisciplinaRest {
	
	@EJB
	private PerfilService perfilService;
	
	@GET
	@Path("/minhas/{idPerfil}")
	@Produces("application/json; charset=UTF-8")
	public List<Disciplina> minhasDisciplina(@PathParam("idPerfil") String id){
		Perfil p = perfilService.finByID(Integer.parseInt(id), Perfil.class);
		return p.getDisciplinas();
	}
}

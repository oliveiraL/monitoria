package servlet;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dominio.Duvida;
import service.DuvidaService;

@Stateless
@Path("/duvida")
public class DuvidaRest {
	//Android Chat Application using GCM
	@EJB
	private DuvidaService duvidaService;
	
	@GET
	@Path("/todas")
	@Produces("application/json; charset=UTF-8")
	public List<Duvida> todasDuvidas(){
		return duvidaService.listaPorData();
	}
}

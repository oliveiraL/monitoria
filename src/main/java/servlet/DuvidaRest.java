package servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dominio.Disciplina;
import dominio.Duvida;
import dominio.Pessoa;
import dominio.Resposta;
import dominio.Usuario;
import dto.DuvidaDTO;
import dto.RespostaDTO;
import service.CurtidaService;
import service.DisciplinaService;
import service.DuvidaService;
import service.PessoaService;
import service.RespostaService;
import service.UsuarioService;

@Stateless
@Path("/duvida")
public class DuvidaRest {
	//Android Chat Application using GCM
	@EJB
	private DuvidaService duvidaService;
	
	@EJB
	private UsuarioService usuarioService;
	
	@EJB
	private CurtidaService curtidaService;
	
	@EJB
	private RespostaService respostaService;
	
	@EJB
	private DisciplinaService disciplinaService;
	
	@EJB
	private PessoaService pessoaService;
	
	@GET
	@Path("/todas/{id}")
	@Produces("application/json; charset=UTF-8")
	public List<DuvidaDTO> todasDuvidas(@PathParam("id") String id){
		Pessoa p = pessoaService.finByID(Integer.parseInt(id), Pessoa.class);
		return duvidaDTO(duvidaService.listaPorData(), p);
	}
	@GET
	@Path("/minhas/{id}")
	@Produces("application/json; charset=UTF-8")
	public List<DuvidaDTO> minhasDuvidas(@PathParam("id") String id){
		Pessoa p = pessoaService.finByID(Integer.parseInt(id), Pessoa.class);
		return duvidaDTO(duvidaService.listDuvidasPorPessoa(p), p);
	}
	@GET
	@Path("/disciplina/{idTurma}/{id}")
	@Produces("application/json; charset=UTF-8")
	public List<DuvidaDTO> turmaDuvidas(@PathParam("id") String idPessoa, @PathParam("id") String idTurma){
		Pessoa p = pessoaService.finByID(Integer.parseInt(idPessoa), Pessoa.class);
		Disciplina disciplina = disciplinaService.finByID(Integer.parseInt(idTurma), Disciplina.class);
		return duvidaDTO(duvidaService.listDuvidasPorDisciplina(disciplina), p);
	}
	
	@SuppressWarnings("unused")
	private List<DuvidaDTO> duvidaDTO(List<Duvida> duvidas, Pessoa pessoa){
		ArrayList<DuvidaDTO> duvidasDTO = new ArrayList<DuvidaDTO>();
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		for(Duvida d : duvidas){
			DuvidaDTO dto = new DuvidaDTO();
			dto.setTitulo(d.getAssunto());
			dto.setDescricao(d.getDescricao());
			dto.setCurtida(curtidaService.existeCurtida(pessoa, d));
			dto.setDataCriacao(dt.format(d.getDataCadastro()));
			dto.setNomeUsuario(d.getPessoa().getNome());
			dto.setTotalCurtida(curtidaService.qtdCurtidas(d).intValue());
			dto.setDisciplina(d.getDisciplina().getDescricao());
			dto.setImagemUsuario(0x7f020070);
			dto.setRespostas(respostaDTO(d));
			duvidasDTO.add(dto);
		}
		
		return duvidasDTO;
	}
	
	private List<RespostaDTO> respostaDTO(Duvida duvida){
		List<Resposta> respostas = respostaService.listaRespostas(duvida);
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		ArrayList<RespostaDTO> respostaDTOs = new ArrayList<>();
		for(Resposta r: respostas){
			RespostaDTO dto = new RespostaDTO();
			dto.setData(dt.format(duvida.getDataCadastro()));
			dto.setDescricao(r.getDescricao());
			dto.setMelhorResposta(false);
			dto.setNomeUsuario(r.getPessoa().getNome());
			dto.setFotoUsuario(0x7f020070);
			respostaDTOs.add(dto);
		}
		return respostaDTOs;
	}
}

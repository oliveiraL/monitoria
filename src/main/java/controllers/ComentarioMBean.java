package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Comentario;
import dominio.Duvida;
import dominio.Resposta;
import service.ComentarioService;

@ManagedBean
@SessionScoped
public class ComentarioMBean extends GenericMBean<Comentario> {
	
	private Duvida duvidaSelecionada;
	
	private Resposta respostaSelecionada;
	
	@EJB
	private ComentarioService comentarioService;
	
	public ComentarioMBean() {
		// TODO Auto-generated constructor stub
		obj = new Comentario();
	}
	
	public String comentarDuvida(Duvida duvida){
		obj = new Comentario();
		duvidaSelecionada = duvida;
		return "/pages/comentario/form.jsf";
	}
	
	public String comentarResposta(Resposta resposta){
		obj = new Comentario();
		respostaSelecionada = resposta;
		return "/pages/comentario/formByResposta.jsf";
	}
	
	public Duvida getDuvidaSelecionada() {
		return duvidaSelecionada;
	}
	
	public Resposta getRespostaSelecionada() {
		return respostaSelecionada;
	}

	public String salvarComentarioByDuvida() {
		// TODO Auto-generated method stub
		obj.setPessoa(getUsuarioLogado().getPessoa());
		obj.setDuvida(duvidaSelecionada);
		super.salvar();
		return "/pages/index.jsf";
	}
	
	public String salvarComentarioByResposta() {
		// TODO Auto-generated method stub
		obj.setPessoa(getUsuarioLogado().getPessoa());
		obj.setResposta(respostaSelecionada);
		super.salvar();
		return "/pages/index.jsf";
	}
	
	public void setDuvidaSelecionada(Duvida duvidaSelecionada) {
		this.duvidaSelecionada = duvidaSelecionada;
	}
	
	/**
	 * @param respostaSelecionada the respostaSelecionada to set
	 */
	public void setRespostaSelecionada(Resposta respostaSelecionada) {
		this.respostaSelecionada = respostaSelecionada;
	}

	public Long getQuantidadeComentarioByDuvida(Duvida duvida){
		return comentarioService.quantComentariosByDuvida(duvida);
	}
	
	public Long getQuantidadeComentarioByResposta(Resposta resposta){
		return comentarioService.quantComentariosByResposta(resposta);
	}
	
	public List<Comentario> getListaComentarioByDuvida(Duvida duvida){
		return comentarioService.getListaComentarioByDuvida(duvida);
	}
	
	public List<Comentario> getListaComentarioByResposta(Resposta resposta){
		return comentarioService.getListaComentarioByResposta(resposta);
	}
	
}


package controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Duvida;
import dominio.Resposta;
import service.RespostaService;

@ManagedBean
@SessionScoped
public class RespostaMBean extends GenericMBean<Resposta> {
	
	private Duvida duvidaSelecionada;
	
	@EJB
	private RespostaService respostaService;
	
	public RespostaMBean() {
		// TODO Auto-generated constructor stub
		obj = new Resposta();
	}
	
	public String respoder(Duvida duvida){
		obj = new Resposta();
		duvidaSelecionada = duvida;
		return "/pages/resposta/form.jsf";
	}

	public Duvida getDuvidaSelecionada() {
		return duvidaSelecionada;
	}

	@Override
	public String salvar() {
		// TODO Auto-generated method stub
		obj.setPessoa(getUsuarioLogado().getPessoa());
		obj.setDuvida(duvidaSelecionada);
		super.salvar();
		return "/pages/index.jsf";
	}
	
	public void setDuvidaSelecionada(Duvida duvidaSelecionada) {
		this.duvidaSelecionada = duvidaSelecionada;
	}
	
	public Long getQuantidadeResposta(Duvida duvida){
		return respostaService.quantRespostas(duvida);
	}
	
	public List<Resposta> getListaRespostas(Duvida duvida){
		return respostaService.listaRespostas(duvida);
	}
	
}

package controllers;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Curtida;
import dominio.Duvida;
import dominio.Pessoa;
import service.CurtidaService;


@ManagedBean
@SessionScoped
public class CurtidaMBean extends GenericMBean<Curtida>{
	
	@EJB
	private CurtidaService curtidaService;
	
	public CurtidaMBean() {
		// TODO Auto-generated constructor stub
		obj = new Curtida();
	}
	
	public String curtir(Duvida duvida){
		obj = new Curtida();
		obj.setData(new Date());
		obj.setPessoa(getUsuarioLogado().getPessoa());
		obj.setDuvida(duvida);
		salvar();
		return null;
	}
	
	public boolean existeCurtida(Duvida duvida){
		Pessoa p = getUsuarioLogado().getPessoa();
		return curtidaService.existeCurtida(p, duvida);
	}
	
	public String descurtir(Duvida duvida){
		Pessoa p = getUsuarioLogado().getPessoa();
		obj = curtidaService.getCurtir(duvida, p);
		remover();
		return null;
	}
	
	public Long getQtdCurtidas(Duvida duvida){
		return curtidaService.qtdCurtidas(duvida);
	}
	
}

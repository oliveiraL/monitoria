package controllers;
	

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Perfil;
import dominio.Pessoa;
import service.PerfilService;

@ManagedBean
@SessionScoped
public class PerfilMBean extends GenericMBean<Perfil> {
	
	@EJB
	private PerfilService perfilService;
	
	public PerfilMBean() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Perfil getObj() {
		// TODO Auto-generated method stub
		if(obj == null){
			Pessoa p = getUsuarioLogado().getPessoa();
			obj = perfilService.findByPessoa(p);
		}
		return obj;
	}
}

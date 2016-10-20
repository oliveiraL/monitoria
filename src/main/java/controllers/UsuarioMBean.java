package controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dominio.Usuario;
import exception.LoginException;
import service.LoginService;

@ManagedBean
@RequestScoped
public class UsuarioMBean {
	private Usuario usuario;
	
	@EJB
	private LoginService loginService;
	
	public UsuarioMBean() {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String login() {
		try {
			loginService.login(usuario.getLogin(), usuario.getSenha());
			return "/pages/index.jsf";
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("", msg);
			return null;
		}	
		
	}
}

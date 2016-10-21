package controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import exception.LoginException;
import service.LoginService;

@ManagedBean
@javax.faces.bean.RequestScoped
public class LoginMBean {
	private Usuario usuario;
	
	@EJB
	private LoginService loginService;
	
	public LoginMBean() {
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
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
			sessaoHttp.setAttribute("usuarioLogado", usuario);
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

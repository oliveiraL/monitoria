package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dominio.Pessoa;
import dominio.Usuario;
import exception.LoginException;
import exception.NegocioException;
import helpers.Faces;
import helpers.OauthHelper;
import service.LoginService;
import service.PessoaService;

@ManagedBean
@RequestScoped
@SessionScoped
public class LoginMBean {
	private Usuario usuario;
	
	@EJB
	private LoginService loginService;
	
	@EJB
	private PessoaService pessoaService;
	
	public LoginMBean() {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//	public String login() {
//		try {
//			usuario = loginService.login(usuario.getLogin(), usuario.getSenha());
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//			HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
//			sessaoHttp.setAttribute("usuarioLogado", usuario);
//			return "/monitoria/pages/index.jsf";
//		} catch (LoginException e) {
//			// TODO Auto-generated catch block
//			Faces.addMessageErro(e.getMessage());
//			return null;
//		}	
//		
//	}
	
	public String logOut(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
		sessaoHttp.setAttribute("usuarioLogado", null);
		return "/login.jsf";
	}
	
//	public String login(String teste) throws IOException, NegocioException{
//		
//		Usuario u = loginService.login();
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//		HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
//		sessaoHttp.setAttribute("usuarioLogado", u);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("/monitoria/pages/index.jsf");
//		return "";
//	}
	
}

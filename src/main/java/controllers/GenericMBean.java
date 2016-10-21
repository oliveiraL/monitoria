package controllers;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import service.GenericService;


public class GenericMBean<T> {
	
	@EJB
	private GenericService<T> service;
	
	protected T obj;
	
	private Usuario usuarioLogado;
	 
	public void salvar(T obj) {
		service.salvar(obj);
	}
	 
	public void atualizar(T obj) {
		service.atualizar(obj);
	}
	 
	public void remover(T obj) {
		service.remover(obj);
	}
	 
	public List<T> listar() {
		return service.listar();
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public Usuario getUsuarioLogado() {
		if(this.usuarioLogado == null){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
			usuarioLogado = (Usuario) sessaoHttp.getAttribute("usuarioLogado");
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	
}

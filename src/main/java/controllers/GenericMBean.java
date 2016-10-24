package controllers;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import service.GenericService;


public class GenericMBean<T> {
	
	@EJB
	private GenericService<T> service;
	
	protected T obj;
		
	protected ListDataModel<T> listagem;
	
	private Usuario usuarioLogado;
	
	public GenericMBean() {
		// TODO Auto-generated constructor stub
	}
	 
	public String salvar() {
		service.salvar(obj);
		return "";
	}
	 
	public void atualizar() {
		service.atualizar(obj);
	}
	 
	public void remover() {
		service.remover(obj);
	}
	 
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		return service.listar((Class<T>) obj.getClass());
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

	@SuppressWarnings("unchecked")
	public ListDataModel<T> getListagem() {
		return listagem = new ListDataModel<>(service.listar((Class<T>) obj.getClass()));
	}

	public void setListagem(ListDataModel<T> listagem) {
		this.listagem = listagem;
	}
	
	public String getDir(){
		return "";
	}
	
	
	
	
	
}

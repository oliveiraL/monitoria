package controllers;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import service.GenericService;


public class GenericMBean<T> {
	
	@EJB
	private GenericService<T> service;
	
	private T obj;
	
	 
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
	
	
}

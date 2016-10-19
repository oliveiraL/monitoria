package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Contato;
import negocio.ContatosService;

@ManagedBean
@SessionScoped
public class ContatoMB {
	private Contato contato;
	
	@EJB
	private ContatosService contatosService;
	
	private List<Contato> listaContatos;
	
	
	public ContatoMB() {
		contato = new Contato();
		listaContatos = new ArrayList<Contato>(); 
	}
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public List<Contato> getListaContatos() {
		setListaContatos(contatosService.listarContatos());
		return listaContatos;
	}

	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}

	public String cadastrar() {
		contatosService.cadastrarContato(contato);
		contato = new Contato();
		return "/interna/lista.jsf";
	}
	
	public String novo() {
		return "/interna/cadastra.jsf";
	}
}

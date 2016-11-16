package controllers;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Usuario;
import exception.NegocioException;
import helpers.Faces;
import service.UsuarioService;

@ManagedBean
@SessionScoped
public class UsuarioMBean extends GenericMBean<Usuario> {
	
	private String confirmarSenha;
	
	@EJB
	private UsuarioService usuarioService;
	
	public UsuarioMBean() {
		// TODO Auto-generated constructor stub
		obj = new Usuario();
	}
	
	public String novo(){
		obj = new Usuario();
		return "cadastro.jsf";
	}
	
	@Override
	public String salvar() {
		
		boolean erro = false;
		if(obj.getLogin().trim().isEmpty()){
			Faces.addMessageErro("Por favor, preencha o campo usuario.");
			erro = true;
		}
		if(obj.getSenha().trim().isEmpty()){
			Faces.addMessageErro("Por favor, preencha o campo senha");
			erro = true;
		}
		if(confirmarSenha.trim().isEmpty()){
			Faces.addMessageErro("Por favor, preencha o campo confirmar senha.");
			erro = true;
		}
		if(!confirmarSenha.equals(obj.getSenha())){
			Faces.addMessageErro("Senhas invalidas.");
			erro = true;
		}
		if(erro){
			return null;
		}
		try {
			usuarioService.salvar(obj);
		} catch (NegocioException e) {
			Faces.addMessageErro(e.getMessage());
			return null;
		}
		return "login.jsf";
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	
}

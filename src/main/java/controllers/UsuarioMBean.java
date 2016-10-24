package controllers;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dominio.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioMBean extends GenericMBean<Usuario> {
	public UsuarioMBean() {
		// TODO Auto-generated constructor stub
		obj = new Usuario();
	}
	
	
}

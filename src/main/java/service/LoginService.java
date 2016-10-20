package service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import dao.UsuarioDAO;
import dominio.Usuario;
import exception.LoginException;

@Stateless
public class LoginService {
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void login(String l, String s) throws LoginException{
		Usuario u = usuarioDAO.buscarLogin(l);
		if (u != null) {
			if(!u.isAtivo()){
				throw new LoginException("usuario inativo.");
			}
			if (!u.getSenha().equals(s)){
				throw new LoginException("usuario e/ou senha incorreto.");
			}
		} else{
			throw new LoginException("usuario não cadastrado");
		}
	}
}

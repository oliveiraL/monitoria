package service;

import javax.ejb.Stateless;

import dominio.DadosAPI;
import exception.APIException;
import helpers.OauthHelper;

@Stateless
public class ApiService {
	
	
	public void autentificacaoAplicacao() throws APIException{
		String resposta = OauthHelper.getInstance().requestTokenApp(DadosAPI.URL_API+"/authz-server/oauth/token", 
				DadosAPI.CLIENT_ID, DadosAPI.CLIENT_SECRET);
		
		if(resposta == null){
			throw new APIException("Erro na autentificação na api");
		}
	}
	
	
	public String dadosUsuario(){
		return OauthHelper.getInstance().requestResourceGet(DadosAPI.URL_API+"/usuario-services/services/usuario/info");
	}
	
	public String perfilUsuario(String idUsuario){
		return OauthHelper.getInstance().requestResourceGet(DadosAPI.URL_API+"/ensino-services/services/consulta/perfilusuario/"+idUsuario);
	}
	
	public String turmasDiscente(String idDiscente){
		return OauthHelper.getInstance().requestResourceGet(DadosAPI.URL_API+"/ensino-services/services/consulta/turmas/usuario/discente/"+idDiscente);
	}
}

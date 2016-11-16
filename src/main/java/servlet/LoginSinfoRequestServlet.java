package servlet;


import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;


import exception.APIException;
import service.ApiService;


@SuppressWarnings("serial")
@WebServlet("/loginsfrequest")
public class LoginSinfoRequestServlet extends HttpServlet {

	public LoginSinfoRequestServlet() {
	
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		

		HttpServletResponse response = (HttpServletResponse) resp;
		OAuthClientRequest request = null;		
		try {
			request = OAuthClientRequest
					   .authorizationLocation("http://apitestes.info.ufrn.br/authz-server/oauth/authorize")
					   .setClientId("imd-monitores-disciplina-app-id")
					   .setRedirectURI("http://localhost:8080/monitoria/loginsfresponse")
					   .setResponseType("code")
					   .buildQueryMessage();
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getLocationUri());		
	}

}

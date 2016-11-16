package servlet;
import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import exception.LoginException;
import exception.NegocioException;
import helpers.Faces;
import helpers.OauthHelper;
import service.ApiService;
import service.LoginService;
import service.UsuarioService;

@SuppressWarnings("serial")
@WebServlet("/loginsfresponse")
public class LoginSinfoServletResponse extends HttpServlet{
		
	public LoginSinfoServletResponse() {
		
	}
	@Inject
	private LoginService loginService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String code = (String) req.getParameter("code");
		if(code != null){
			
			OAuthClientRequest request = null;
			try {
				request = OAuthClientRequest
				        .tokenLocation("http://apitestes.info.ufrn.br/authz-server/oauth/token")
				        .setGrantType(GrantType.AUTHORIZATION_CODE)
				        .setClientId("imd-monitores-disciplina-app-id")
				        .setClientSecret("ga5eCh7DraTR")
				        .setRedirectURI("http://localhost:8080/monitoria/loginsfresponse")
				        .setCode(code)
				        .buildQueryMessage();
				OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
				String accessToken = oAuthClient.accessToken(request, OAuth.HttpMethod.POST).getAccessToken();
				OauthHelper.getInstance();
				OauthHelper.setAccessToken(accessToken);
				loginService.login();
				resp.sendRedirect("/monitoria/pages/index.jsf");
				
			} catch (OAuthSystemException | OAuthProblemException e) {
				Faces.addMessageErro("Erro de conexão com servidor.");
				resp.sendRedirect("/monitoria/login.jsf");
			}catch (Exception e) {
				Faces.addMessageErro(e.getMessage());
				resp.sendRedirect("/monitoria/login.jsf");
			}
		}else{
			resp.sendRedirect("/monitoria/login.jsf");
		}
		
	}
	
}

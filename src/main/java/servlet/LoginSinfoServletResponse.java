package servlet;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import controllers.UsuarioMBean;
import dominio.Usuario;
import helpers.OauthHelper;
import service.LoginService;



@SuppressWarnings("serial")
@WebServlet("/loginsfresponse")
public class LoginSinfoServletResponse extends HttpServlet{
		
	@EJB
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
				Usuario usuario = loginService.login(accessToken);
				if(usuario == null){
					resp.sendRedirect("/monitoria/login.jsf");
				}else{
					HttpSession session= req.getSession();  
			        session.setAttribute("usuarioLogado",usuario);
					resp.sendRedirect("/monitoria/pages/index.jsf");
				}
			} catch (OAuthSystemException | OAuthProblemException e) {
				resp.sendRedirect("/monitoria/login.jsf");
			}catch (Exception e) {
				resp.sendRedirect("/monitoria/login.jsf");
			}
		}else{
			resp.sendRedirect("/monitoria/login.jsf");
		}
		
	}
	
}

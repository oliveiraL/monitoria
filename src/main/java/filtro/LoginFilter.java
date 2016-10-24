package filtro;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.UsuarioMBean;
import dominio.Usuario;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Usuario usuario = (Usuario) 
                ((HttpServletRequest) request)
                  .getSession().getAttribute("usuarioLogado");

                //Verifica se nosso ManagedBean ainda não 
                //foi instanciado ou caso a
                //variável loggedIn seja false, assim saberemos que  
                // o usuário não está logado
                if (usuario == null) {
                  String contextPath = ((HttpServletRequest) request)
                   .getContextPath();
                    //Redirecionamos o usuário imediatamente 
                    //para a página de login.xhtml
                  ((HttpServletResponse) response).sendRedirect
                   (contextPath + "/login.jsf");
                } else {
                       //Caso ele esteja logado, apenas deixamos 
                       //que o fluxo continue
                     chain.doFilter(request, response);
                }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

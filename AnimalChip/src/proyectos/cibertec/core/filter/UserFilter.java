package proyectos.cibertec.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proyectos.cibertec.web.action.UsuarioLoginAction;

@WebFilter("/userFilter/*")
public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

		UsuarioLoginAction ua2 = (UsuarioLoginAction) httpRequest.getSession().getAttribute("usuarioLoginAction");
		System.out.println(ua2==null?"null": (ua2.getoUsuario()==null?"usuario Null":ua2.getoUsuario().getUsuario()) );
		
		
		if(ua2==null || ua2.getoUsuario()==null){
			
			System.out.println(httpRequest.getContextPath() + "/usuarios.xhtml");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/usuarios.xhtml");
		}
		else{
			System.out.println(httpRequest.getRequestURI()); 
            
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

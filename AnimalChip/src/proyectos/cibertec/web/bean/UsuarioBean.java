package proyectos.cibertec.web.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component("usuarioBean")
public class UsuarioBean {

	private String login;
	private String clave;
	private String empresaRuc;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void limpiar() {
		this.setClave("");
		this.setLogin("");
	}

	public String getEmpresaRuc() {
		return empresaRuc;
	}

	public void setEmpresaRuc(String empresaRuc) {
		this.empresaRuc = empresaRuc;
	}

}

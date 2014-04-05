package proyectos.cibertec.web.action;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.Foto;
import proyectos.cibertec.core.entity.Rol;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.LoginService;
import proyectos.cibertec.core.service.RolService;
import proyectos.cibertec.core.service.UsuarioService;
import proyectos.cibertec.core.util.Constantes;
import proyectos.cibertec.web.bean.UsuarioBean;

@Scope("session")
@Controller("usuarioLoginAction")
public class UsuarioLoginAction {

	@SuppressWarnings("unused")
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private LoginService loginService;

	/** ATRIBUTOS **/
	private UsuarioBean usuarioBean;
	private Usuario oUsuario;
	private String passwordValidate;
	private Foto oFoto;
	

	/** CONSTRUCTOR **/
	public UsuarioLoginAction() {
		this.oFoto = new Foto();
		this.usuarioBean = new UsuarioBean();
	}

	/** GETTER Y SETTER **/


	public Usuario getoUsuario() {
		return oUsuario;
	}

	public void setoUsuario(Usuario oUsuario) {
		this.oUsuario = oUsuario;
	}

	public String getPasswordValidate() {
		return passwordValidate;
	}

	public void setPasswordValidate(String passwordValidate) {
		this.passwordValidate = passwordValidate;
	}

	public Foto getoFoto() {
		return oFoto;
	}

	public void setoFoto(Foto oFoto) {
		this.oFoto = oFoto;
	}
	
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	/** METODOS PROPIOS **/
	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}
	
	private boolean validarIngreso(String empresa, Rol...rols){
		try {
			// Primero obtenemos el usuario
			Usuario usuarioEncontrado;
			boolean checkEmpresa;
			if(empresa==null){
				usuarioEncontrado = loginService.loginSocio(usuarioBean.getLogin());
				checkEmpresa = false;
			}
			else{
				usuarioEncontrado = loginService.loginSocioEmpresa(usuarioBean.getLogin(), empresa);
				checkEmpresa = true;
			}
			
			// Comprobamos si se encontro el usuario
			if (usuarioEncontrado == null || usuarioEncontrado.getFlagActivo()==0x00) {
				// El usuario no existe
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "El usuario ingresado no se encuentra registrado.");
				return false;
			}
			// Comrpobamos que su rol sea uno de la lista
			else if(!this.comprobarUsuarioRol(usuarioEncontrado, rols)){
				// El usuario existe pero el rol es incorrecto 
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "El usuario ingresado no se encuentra registrado.");
				return false;
			}
			// Comprobamos que su empresa sea la ingresada (o en caso de no ingresada lo dejamos pasar
			else if(checkEmpresa && (empresa==null || usuarioEncontrado.getEmpresa().getFlagActivo()==0x00) ){			
				// El usuario existe pero la empresa es incorrecta
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "El usuario ingresado no se encuentra registrado.");
				return false;				
			}
			else if(usuarioBean.getClave()==null || !usuarioBean.getClave().equals(usuarioEncontrado.getPassword())){
				// Password incorrecto
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "El usuario y/o password incorrecto.");
				return false;
			}
			else{
				addMessage(FacesMessage.SEVERITY_INFO, "Success", "Welcome");
				this.oUsuario = usuarioEncontrado;
				return true;
			}			
		}
		catch(Exception ex){
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "ERROR FATAL!");
			System.out.println(ex.getMessage());
			return false;
		}		
	}	
		//---
	public String validarIngresoUsuarioEmpresa(){
		try {
			Rol rolUsuario1 = rolService.findById(1); // 1 = UsuarioVet
			Rol rolUsuario2 = rolService.findById(2); // 2 = AdminEmpresa
			if (this.validarIngreso(this.usuarioBean.getEmpresaRuc(), rolUsuario1, rolUsuario2)){				
				return Constantes.NAVEGACION_EXITOSOCIO;
			}
			else{
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constantes.NAVEGACION_ERRORLOGIN; 
		}
	}
	
	public String validarIngresoProveedor(){
		try {
			Rol rolUsuario1 = rolService.findById(3); // 3 = AdministradorGeneral
			Rol rolUsuario2 = rolService.findById(7); // 7 = Asistente de Administracion
			if (this.validarIngreso(null, rolUsuario1, rolUsuario2)){		
				return Constantes.NAVEGACION_EXITOSOCIO;
			}
			else{
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constantes.NAVEGACION_ERRORLOGIN; 
		}
	}
	
	public String validarIngresoSupervisor(){
		try {
			Rol rolUsuarioVeterinario = rolService.findById(4); // 4 = Supervisor
			if (this.validarIngreso(this.usuarioBean.getEmpresaRuc(), rolUsuarioVeterinario)){				
				return Constantes.NAVEGACION_EXITOSOCIO;
			}
			else{
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constantes.NAVEGACION_ERRORLOGIN; 
		}
	}
	
	public String logout(){
		this.oUsuario = null;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "logout";
	}
	
	private boolean comprobarUsuarioRol(Usuario usuario, Rol...rols){
		for(Rol rol : rols){
			if(usuario.getRol().getId()==rol.getId()){
				return true;
			}
		}		
		return false;
	}

	
	

}

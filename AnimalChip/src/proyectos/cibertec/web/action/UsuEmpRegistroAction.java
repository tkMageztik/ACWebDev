package proyectos.cibertec.web.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Rol;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.EmpresaService;
import proyectos.cibertec.core.service.RolService;
import proyectos.cibertec.core.service.UsuarioService;

@Scope("session")
@Controller("usuEmpRegistroAction")
public class UsuEmpRegistroAction {
	@Autowired
	private UsuarioService usuarioService;
		
	@Autowired
	private RolService rolService;
	
	@Autowired
	private EmpresaService empresaService;

	private Usuario usuarioNuevo;
	private Usuario usuarioSeleccionado;
	private Usuario usuarioLogeado;
	private String rucEmpresa;
	private Empresa empresaSeleccionada;

	private List<Usuario> listaUsuariosEmpresa ;
	
	@PostConstruct
	public void postConstruct(){
		this.usuarioNuevo = new Usuario();
		this.usuarioLogeado = this.obtenerUsuarioLogeado();
		this.cargarListaUsuarios();	
	}
	
	private Usuario obtenerUsuarioLogeado(){
		Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLoginAction");
		if(o!=null){
			UsuarioLoginAction ia = (UsuarioLoginAction) o; 
			return ia.getoUsuario();
		}
		else{
			return null;
		}
	}	
	
	/**
	 * Carga la lista de usuarios en el atributo listaUsuariosEmpresa.
	 * Los usuarios obtenidos serán los pertenecientes a la empresa del
	 * usuario logeado. En caso que el usuario sea del tipo administrador
	 * o supervisor, se mostrará los datos de la empresa que se busco anteriormente
	 */
	private void cargarListaUsuarios(){
		if(usuarioLogeado!=null && usuarioLogeado.getEmpresa()!=null){		
			try {
				if((usuarioLogeado.getRol().getId()==3 || usuarioLogeado.getRol().getId()==4)){
					if(empresaSeleccionada!=null){
						listaUsuariosEmpresa = usuarioService.listAccountsByEmpresa(empresaSeleccionada);
					}
					else{
						this.addMessage(FacesMessage.SEVERITY_INFO, "Error", "Se requiere buscar una empresa.");
					}
				}
				else{		
					empresaSeleccionada = usuarioLogeado.getEmpresa();
					listaUsuariosEmpresa = usuarioService.listAccountsByEmpresa(usuarioLogeado.getEmpresa());
				}
			} catch (Exception e) {				
				this.listaUsuariosEmpresa=null;
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Busca una empresa por el ruc ingresado en el atributo "rucEmpresa".
	 * Si existe la empresa, llama al metodo cargarListaUsuarios().
	 */
	public void buscarEmpresa(){
		try{			
			if(rucEmpresa==null || rucEmpresa.length()<=0){
				this.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Ingrese el RUC.");
			}
			else{
				List<Empresa> resultList = empresaService.buscarEmpresaPorRuc(rucEmpresa);
				if(resultList.size()>0){
					empresaSeleccionada = resultList.get(0);
					listaUsuariosEmpresa = usuarioService.listAccountsByEmpresa(empresaSeleccionada);
					this.cargarListaUsuarios();
				}
				else{
					this.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Empresa no encontrada.");
				}
			}
			
		} catch (Exception e) {				
			empresaSeleccionada = null;
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserta usuarioNuevo en la base de datos como un usuario de la misma empresa
	 * que el usuario logeado actualmente. 
	 * Antes de insertar se verifica si la cantidad de usuarios permitidos para
	 * la empresa sea mayor que la cantidad de usuarios actualmente inscritos, 
	 * de no ser así no se permite la inscripción del usuario.
	 */	
	public void insertarUsuario(){		
		/* TODO: Deberia obtenerse la cantidad de usuarios de nuevo de la BD en ves de la lista actual.
		 		 De obtenerse una cantidad distinta se deberia cargar de nuevo la lista de usuarios*/
		if(usuarioLogeado.getEmpresa().getMaximoUsuarios()<= listaUsuariosEmpresa.size()){
			this.addMessage(FacesMessage.SEVERITY_WARN, "Info", "Ha llegado al limite de usados permitidos para su empresa.");
		}
		else{			
			try {
				// Dependiendo del tipo de usuario logeado debemos asignar la empresa
				if((usuarioLogeado.getRol().getId()==3 || usuarioLogeado.getRol().getId()==4)){
					if(empresaSeleccionada!=null){
						usuarioNuevo.setEmpresa(empresaSeleccionada);
					}
					else{
						this.addMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "No existe ninguna empresa seleccionada, no se ingresará el usuario.");
						return;
					}
				}
				else{
					this.usuarioNuevo.setEmpresa(usuarioLogeado.getEmpresa());
				}
				
				// Asignamos los demas datos del usuario
				Rol rolAdministradorEmpresa = this.rolService.findById(1);
				this.usuarioNuevo.setRol(rolAdministradorEmpresa);
								
				// Registramos el usuario y volvemos a cargar la lista
				this.usuarioService.registrarUsuario(usuarioNuevo);
				this.cargarListaUsuarios();				
				
				// Limpiamos los datos y mandamos el mensaje
				this.usuarioNuevo = new Usuario();				
				this.addMessage(FacesMessage.SEVERITY_INFO, "Info", "El usuario ha sido ingresado.");
				
			} catch (Exception e) {
				e.printStackTrace();
				setListaUsuariosEmpresa(null);
			}
		}
	}
	
	/**
	 * Actualiza los datos de usuarioSeleccionado.
	 */
	public void actualizarUsuario(){
		try {
			this.usuarioService.actualizarUsuario(usuarioSeleccionado);
			this.cargarListaUsuarios();
			
			this.addMessage(FacesMessage.SEVERITY_INFO, "Info", "El usuario ha sido actualizado.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Cambia el estado del usuario entre Activo e Inactivo.
	 * El método no permite desactivar al usuario actualmente logeado.
	 */
	public void cambiarEstadoActivacionUsuario(){
		if(usuarioSeleccionado.getId()==usuarioLogeado.getId() ){
			this.addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede desactivar su propio usuario.");
		}
		else{
			try {
				if(usuarioSeleccionado.getFlagActivo()==0x00){
					usuarioSeleccionado.setFlagActivo((byte) 0x01);
				}
				else{
					usuarioSeleccionado.setFlagActivo((byte) 0x00);
				}				
				this.usuarioService.actualizarUsuario(usuarioSeleccionado);
				this.cargarListaUsuarios();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}
	
	//=========GET & SET=========
		
	public List<Usuario> getListaUsuariosEmpresa() {
		return listaUsuariosEmpresa;
	}

	public void setListaUsuariosEmpresa(List<Usuario> listaUsuariosEmpresa) {
		this.listaUsuariosEmpresa = listaUsuariosEmpresa;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public Usuario getUsuarioNuevo() {
		return usuarioNuevo;
	}

	public void setUsuarioNuevo(Usuario usuarioNuevo) {
		this.usuarioNuevo = usuarioNuevo;
	}
	
	public Usuario getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public void setUsuarioLogeado(Usuario usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}

	public String getRucEmpresa() {
		return rucEmpresa;
	}

	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

}

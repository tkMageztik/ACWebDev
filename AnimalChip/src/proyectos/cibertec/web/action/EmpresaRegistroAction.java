package proyectos.cibertec.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Foto;
import proyectos.cibertec.core.entity.Rol;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.EmpresaService;
import proyectos.cibertec.core.service.FotoService;
import proyectos.cibertec.core.service.LoginService;
import proyectos.cibertec.core.service.RolService;
import proyectos.cibertec.core.service.UsuarioService;
import proyectos.cibertec.core.util.Constantes;
import proyectos.cibertec.web.bean.EmpresaBean;
import proyectos.cibertec.web.bean.UsuarioBean;

@Scope("session")
@Controller("empresaRegistroAction")
public class EmpresaRegistroAction {

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private FotoService fotoService;

	@Autowired
	private LoginService loginService;

	/** ATRIBUTOS **/
	private UsuarioBean usuarioBean;
	private EmpresaBean empresaBean;
	private Usuario oUsuario;
	private Empresa oEmpresa;
	private String passwordValidate;
	private Foto oFotoEmpresa;
	private boolean validatePhoto;
	private UploadedFile uploadedPhoto;

	/** CONSTRUCTOR **/
	public EmpresaRegistroAction() {
		this.oUsuario = new Usuario();
		this.oEmpresa = new Empresa();
		this.oFotoEmpresa = new Foto();
		this.usuarioBean = new UsuarioBean();
		this.validatePhoto = false;
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

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public UploadedFile getUploadedPhoto() {
		return uploadedPhoto;
	}

	public void setUploadedPhoto(UploadedFile uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}

	public EmpresaBean getEmpresaBean() {
		return empresaBean;
	}

	public void setEmpresaBean(EmpresaBean empresaBean) {
		this.empresaBean = empresaBean;
	}

	public Empresa getoEmpresa() {
		return oEmpresa;
	}

	public void setoEmpresa(Empresa oEmpresa) {
		this.oEmpresa = oEmpresa;
	}
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Foto getoFotoEmpresa() {
		return oFotoEmpresa;
	}

	public void setoFotoEmpresa(Foto oFotoEmpresa) {
		this.oFotoEmpresa = oFotoEmpresa;
	}

	/** METODOS PROPIOS **/
	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}
	
	public String validarIngreso() {

		try {
			
			Usuario oUsuario = loginService.loginSocio(usuarioBean.getLogin());
			
			System.out.println("Usuario: " + usuarioBean.getLogin());
			
			if (oUsuario == null) {
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "El usuario ingresado no se encuentra registrado.");
				return null;
			}
			else
			{
				
				if (oUsuario.getPassword().equals(usuarioBean.getClave())){
					setoUsuario(oUsuario);
					addMessage(FacesMessage.SEVERITY_INFO, "Success", "Welcome");
					return Constantes.NAVEGACION_EXITOSOCIO;
				}
				else {
					addMessage(FacesMessage.SEVERITY_ERROR,"Error", "Contraseña Incorrecta. Por favor, reingrese sus datos de usuario.");
					return null;
				}
			}
		} catch (Exception ex) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario ingresado no se encuentra registrado.");
			return Constantes.NAVEGACION_ERRORLOGIN;
		}
		
		
	}

	public void handleFileUploadEmpresa(FileUploadEvent event) {
		if(event!=null){
			this.uploadedPhoto = event.getFile();			
			this.validatePhoto = this.uploadedPhoto !=null;
			addMessage(FacesMessage.SEVERITY_INFO, "Success", event.getFile().getFileName() + " was uploaded.");
		}
		else{
			this.validatePhoto = false;
		}
	}
	
	private String copyPhotoToServer(UploadedFile uploadedFile, String filename) throws IOException{
		ExternalContext external = 	FacesContext.getCurrentInstance().getExternalContext();   		
		
		// Obtenemos la extension y el nombre temporal a usar.
		String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
    	if(filename==null){ 
    		filename = UUID.randomUUID().toString();
    	}
    	
		String path = external.getRealPath("/photocam") ;
		// Obtenemos el stream
		InputStream input = uploadedFile.getInputstream();
		
    	File directory = new File(path );    
    	if(!directory.exists()){
    		directory.mkdir();
    	}
    	
    	File file = new File(path, filename +"."+ extension);
    	System.out.println(directory.getPath());
    	System.out.println(file.getPath());
    	  	
    	FileOutputStream output = new FileOutputStream(file);
    	
    	
    	try {
    	    IOUtils.copy(input, output);
    	    return "/photocam/" +  filename + "." + extension;
    	} finally {
    	    IOUtils.closeQuietly(output);
    	    IOUtils.closeQuietly(input);
    	}
    	
	}

	public String validarGrabar() throws Exception {

		if (!oUsuario.getPassword().equals(passwordValidate)) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas ingresadas no coinciden.");
			return Constantes.NAVEGACION_VETERINARIOREGISTRO;
		}
		else
		{	
			try {
				// Guardamos la empresa
				
				fotoService.insertarFoto(oFotoEmpresa);
				
				oEmpresa.setFoto(oFotoEmpresa);
				empresaService.registrarEmpresa(oEmpresa);
				
				if(this.uploadedPhoto!=null){				
					oEmpresa.getFoto().setRuta(this.copyPhotoToServer(this.uploadedPhoto, "Empresa-" + oEmpresa.getId()));
					fotoService.actualizarFoto(oEmpresa.getFoto());
				}							
				
				// Creamos y guardamos el usuario				
				Rol rolAdministradorEmpresa = this.rolService.findById(2);
				oUsuario.setRol(rolAdministradorEmpresa);				
				oUsuario.setEmpresa(oEmpresa);
				
				usuarioService.registrarUsuario(oUsuario);			
				
				
				//fotoService.actualizarFoto(oUsuario.getFoto());
				
				addMessage(FacesMessage.SEVERITY_INFO, "Success", "Veterinario Registrado.");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("veterinarioRegistroAction", null);
				
				
				
				return "registrovet";
			} catch (Exception ex) {
				throw ex;
				//addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ya existe un veterinario registrado con dicho usuario.");
				//System.out.println(ex);
				//return Constantes.NAVEGACION_VETERINARIOREGISTRO;
			}	
			finally{

				this.oUsuario = new Usuario();
				this.oEmpresa = new Empresa();
				this.oFotoEmpresa = new Foto();
				this.usuarioBean = new UsuarioBean();
				this.validatePhoto = false;
			}
		}
	
			
	}

}

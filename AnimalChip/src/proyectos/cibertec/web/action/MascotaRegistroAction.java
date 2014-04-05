package proyectos.cibertec.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.print.attribute.standard.Severity;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Foto;
import proyectos.cibertec.core.entity.Mascota;
import proyectos.cibertec.core.entity.Propietario;
import proyectos.cibertec.core.entity.Rfid;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.FotoService;
import proyectos.cibertec.core.service.MascotaService;
import proyectos.cibertec.core.service.PropietarioService;
import proyectos.cibertec.core.service.RfidService;
import proyectos.cibertec.core.util.report.AbstractReportBean;
import proyectos.cibertec.web.reportImpl.ReportCertificado;

@Scope("session")
@Controller("mascotaRegistroAction")
public class MascotaRegistroAction{	
	
	@Autowired
	private MascotaService mascotaService;
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private PropietarioService propietarioService;
	
	@Autowired
	private RfidService rfidService;
	
	private Mascota mascota;	
	private int idMascotaCertificado;
	private Propietario propietario;	
	private Rfid rfid;	
	private String codigoRfid;
	private boolean propietarioFlag;
	private boolean validatePhoto;
	private UploadedFile uploadedPhoto;
	private Usuario usuarioLogeado;
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void postConstruct(){
		this.clear();
	}
	
	private void clear(){
		rfid = null;
		codigoRfid = null;
		mascota = new Mascota();
		propietario = new Propietario();
		propietarioFlag = false;
		usuarioLogeado = obtenerUsuarioLogeado();
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
	
	public void buscarRfid(){		
		try {			
			//Primero hay que buscar el rfid
			List<Rfid> listaRfid = rfidService.buscarRfidporCodigo(codigoRfid);
			Rfid rfidEncontrado =null;
			if(listaRfid!=null && listaRfid.size()>0){
				Rfid rfidActual = listaRfid.get(0);
				if(rfidActual.getFlagEnUso()== 0x00){
					rfidEncontrado =  rfidActual;
				}
				else{
					addMessage(FacesMessage.SEVERITY_WARN,"Error","RFID en uso");
					return;
				}
				
				
			}
			else{
				addMessage(FacesMessage.SEVERITY_WARN,"Error","RFID no encontrado");
				return;
			}
			
			// Comprobamos la empresa
			Empresa empresaActual = null;		
	
			
			empresaActual = usuarioLogeado.getEmpresa();
			
			if(empresaActual==null || rfidEncontrado.getEmpresa()==null || 
					empresaActual.getId()!=rfidEncontrado.getEmpresa().getId()){
				addMessage(FacesMessage.SEVERITY_WARN,"Error","RFID no pertenece a esta empresa.");
			}
			else{
				rfid = rfidEncontrado;
				addMessage(FacesMessage.SEVERITY_INFO,"Info","RFID encontrado.");
			}
		}
		catch(Exception ex){
			// TODO: Agregar exception
			addMessage(FacesMessage.SEVERITY_ERROR,"Error","FATAL ERROR.");
			System.out.println(ex.getMessage());
		}
	}
	
	public void buscarPropietario(){
		try{
			List<Propietario> listaProp = propietarioService.buscarPropietarioPorDocIden(propietario.getDocumentoIdentidad());			
			if(listaProp!=null && listaProp.size()>0){
				propietario= listaProp.get(0);
				propietarioFlag = true;
				addMessage(FacesMessage.SEVERITY_INFO,"Información","Propietario encontrado.");
			}
			else{
				addMessage(FacesMessage.SEVERITY_INFO,"Información","Propietario no encontrado.");
				propietarioFlag = true;
			}
		}
		catch(Exception ex){
			addMessage(FacesMessage.SEVERITY_ERROR,"Error","FATAL ERROR.");
			ex.printStackTrace();
		}
	}
	
	public void limpiarPropietario(){
		this.propietario = new Propietario();
		propietarioFlag = false;
	}
	

	private boolean comprobarDatosPropietario(){
		// Comprobamos los datos del propietario
		if(propietario==null){
			propietario = new Propietario();
			return false;
		}
		else 
		if( propietario.getNombres()== null || propietario.getNombres().trim().length()<=0){
			return false;
		}
		else if( propietario.getApellidos()== null || propietario.getApellidos().trim().length()<=0){
			return false;
		}
		else if( propietario.getCorreoElectronico()== null || propietario.getCorreoElectronico().trim().length()<=0){
			return false;
		}
		
		// Si todo paso, true
		else{
			return true;
		}		
	}
	
	private boolean comprobarDatosMascota(){
		if(mascota==null){
			mascota = new Mascota();
			return false;
		}
		else 
		if( mascota.getRazaMascota()== null ||mascota.getRazaMascota().trim().length()<=0){
			return false;
		}
		else if( mascota.getColorMascota()== null ||mascota.getColorMascota().trim().length()<=0){
			return false;
		}
		else if( mascota.getGeneroMascota()== null ||mascota.getGeneroMascota().trim().length()<=0){
			return false;
		}
		else if( mascota.getNombreMascota()== null ||mascota.getNombreMascota().trim().length()<=0){
			return false;
		}
		else if( mascota.getFchNacMascota()== null ){
			return false;
		}
		
		// Si todo paso, true
		else{
			return true;
		}		
	}
	
	public void registrarMascota(){	
		try {					
			
			if(rfid== null){
				this.addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Microchip no ingresado.");
				return;
			}
			
			if(!comprobarDatosPropietario()){
				this.addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos de propietario incompleto.");
				return;
			}
			

			if(!comprobarDatosMascota()){
				this.addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos de mascota incompleto.");
				return;
			}
			propietarioService.insertarPropietario(propietario);
		
				
			
			this.rfid.setFlagEnUso((byte) 1);			
			rfidService.actualizarRFID(rfid);
			
			
			mascota.setPropietario(propietario);
			mascota.setRfid(rfid);
			mascota.setUsuarioInsc(usuarioLogeado);	
			
			mascotaService.registrarMascota(mascota);
			
			if(uploadedPhoto!=null){
				Foto f= new Foto();				
				f.setRuta(copyPhotoToServer(uploadedPhoto,"mascota" + mascota.getId()));
				mascota.setFoto(f);
				fotoService.insertarFoto(f);
				mascotaService.actualizarMascota(mascota);				
			}			
			
			addMessage(FacesMessage.SEVERITY_INFO,"Información","Registrado con éxito.");
			int idMascota = mascota.getId();				
			
			clear();
			
			this.mostrarCertificado(idMascota);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mostrarCertificado(int idMascota){
		ReportCertificado rc = new ReportCertificado();
		rc.setIdMascotaCertificado(idMascota);
		rc.execute();
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		if(event!=null){
			this.uploadedPhoto = event.getFile();			
			this.setValidatePhoto(this.uploadedPhoto !=null);
			addMessage(FacesMessage.SEVERITY_INFO, "Success", event.getFile().getFileName() + " was uploaded.");
		}
		else{
			this.setValidatePhoto(false);
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
		
    	File directory = new File(path);
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

	
	/** METODOS PROPIOS **/
	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}	
	

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public String getCodigoRfid() {
		return codigoRfid;
	}

	public void setCodigoRfid(String codigoRfid) {
		this.codigoRfid = codigoRfid;
	}

	public Rfid getRfid() {
		return rfid;
	}

	public void setRfid(Rfid rfid) {
		this.rfid = rfid;
	}

	public boolean isPropietarioFlag() {
		return propietarioFlag;
	}

	public void setPropietarioFlag(boolean propietarioFlag) {
		this.propietarioFlag = propietarioFlag;
	}

	public boolean isValidatePhoto() {
		return validatePhoto;
	}

	public void setValidatePhoto(boolean validatePhoto) {
		this.validatePhoto = validatePhoto;
	}

	public int getIdMascotaCertificado() {
		return idMascotaCertificado;
	}

	public void setIdMascotaCertificado(int idMascotaCertificado) {
		this.idMascotaCertificado = idMascotaCertificado;
	}

}

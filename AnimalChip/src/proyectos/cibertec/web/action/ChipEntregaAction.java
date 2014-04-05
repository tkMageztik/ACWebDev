package proyectos.cibertec.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import proyectos.cibertec.core.entity.Rfid;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.RfidService;
import proyectos.cibertec.core.service.UsuarioService;
import proyectos.cibertec.core.util.LeerExcelRfid;

@Scope("session")
@Controller("chipEntregaAction")
public class ChipEntregaAction {

	@Autowired
	private RfidService rfidService;
	
	@Autowired
	private UsuarioService usuarioService;

	private Usuario usuarioSeleccionado;
	
	/** ATRIBUTOS **/
	private int cantidad;
	private List<Rfid> lstChips;
	private String loginUsuario;
	
	/** CONSTRUCTOR **/
	public ChipEntregaAction() {

		lstChips = new ArrayList<Rfid>();
	}
	
	public void obtenerChips(){
		try {
			lstChips = new ArrayList<Rfid>();
			if (cantidad > 0)
			{
				for(int i=0; i < cantidad; i++)
				{
					Rfid oRfid = new Rfid();
					oRfid.setFechaIngreso(new Date());					
					lstChips.add(oRfid);
				}				
			}
			else
				addMessage(FacesMessage.SEVERITY_INFO, "Error", "Ingrese una cantidad mayor a 0.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void buscarUsuarioPorLogin(){
		try {
			if(loginUsuario== null || loginUsuario.length()<=0){
				addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ingrese un login.");
			}
			else{
				this.usuarioSeleccionado =  usuarioService.buscarUsuarioPorLogin(this.loginUsuario);
				if(usuarioSeleccionado==null){
					addMessage(FacesMessage.SEVERITY_WARN, "Error", "No se encontraron datos.");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadExcelRfid(FileUploadEvent event) {
	    	UploadedFile file = event.getFile();    			
	        if(file != null) {         	
	        	//Comrpobar la extension
	        	String str = file.getFileName();
	        	String suffix = FilenameUtils.getExtension(str);
	        	if(!suffix.equals("xls")){             		
	        		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	        				"Error en la lectura.",
	    					"El archivo no es del tipo Excel");
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, msg);

	        	}
	        	else{
	        		try{ 
	        			LeerExcelRfid ler = new LeerExcelRfid();        			
	        			
		        		ler.copyExcelToServer(file);       				        		
		        		
		        		List<Rfid> resultado =  ler.readRfid();		        		
		        		if(resultado.size()!=this.cantidad){
		        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
		            				"Error en la lectura.", "El excel contiene una cantidad distinta de RFIDs a la solicitada.");
		        			FacesContext.getCurrentInstance().addMessage(null, msg);
		        		}
		        		else{
		        			this.lstChips = resultado;
		        		}
	        		}
	        		catch (Exception e) {
	        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	            				"Error en la lectura.",
	        					e.getMessage());
	            		FacesContext.getCurrentInstance().addMessage(null, msg);
					}
	        			
	        	} 	
	        }  
	        else{
	        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	    				"Error en la lectura.",
						"No se selecciono ningun archivo");
	    		
	    		FacesContext.getCurrentInstance().addMessage(null, msg);


	        }   
	        
    }	  
	 
	  
	public void registrarTrasladoChips() {
		

		try {
			
			if (cantidad > 0)
			{
				for(Rfid rfid : lstChips){
					if(rfid.getCodInterno()==null || rfid.getCodInterno().length()==0){
						addMessage(FacesMessage.SEVERITY_INFO, "Error", "No se ha ingresado el código rfid");
						return;
					}
				}				
				List<Rfid> nuevaLista = new ArrayList<Rfid>();
				boolean go = true;
				for(Rfid rfid : lstChips){
					List<Rfid> listaRes = rfidService.buscarRfidporCodigo(rfid.getCodInterno());
					if(listaRes!=null && listaRes.size()>0 && listaRes.get(0).getUsuario()==null){
						listaRes.get(0).setEncontrado(true);
						nuevaLista.add(listaRes.get(0));
					}
					else{
						rfid.setEncontrado(false);
						nuevaLista.add(rfid);
						go = false;
					}
					
				}
				
				lstChips= nuevaLista;
				
				if(go){
					for(Rfid rfid : lstChips){						
						rfid.setUsuario(usuarioSeleccionado);
						
						rfid.setFlagActivo( (byte) 1 );
						
						rfidService.actualizarRFID(rfid);
						addMessage(FacesMessage.SEVERITY_INFO, "Success", "Registro Relizado con Éxito.");
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("chipRegistroAction", null);
						obtenerChips();
					}
				}
				else{
					// Error
					addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Códigos no encontrados.");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("chipRegistroAction", null);
				}
				
				
			}
			else
				addMessage(FacesMessage.SEVERITY_INFO, "Error", "Ingrese una cantidad mayor a 0.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	/** GETTER Y SETTER **/
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public List<Rfid> getLstChips() {
		return lstChips;
	}

	public void setLstChips(List<Rfid> lstChips) {
		this.lstChips = lstChips;
	}

	/** METODOS PROPIOS **/
	public void addMessage(FacesMessage.Severity severity, String title, String message) {
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	
}

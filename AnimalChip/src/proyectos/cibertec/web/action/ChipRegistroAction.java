package proyectos.cibertec.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
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
import proyectos.cibertec.core.service.RfidService;
import proyectos.cibertec.core.util.LeerExcelRfid;

@Scope("session")
@Controller("chipRegistroAction")
public class ChipRegistroAction {

	@Autowired
	private RfidService rfidService;

	/** ATRIBUTOS **/
	private int cantidad;
	private List<Rfid> lstChips;
	
	/** CONSTRUCTOR **/
	public ChipRegistroAction() {

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
		        		
		        		// TODO: Comprobaciones de cantidad, etc
		        		if(resultado.size()!=this.cantidad){
		        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
		            				"Error en la lectura.", "El excel contiene una cantidad distinta de RFIDs a la solicitada.");
		        			FacesContext.getCurrentInstance().addMessage(null, msg);
		        		}
		        		else{
		        			this.lstChips = resultado;
		        		}
		        		//---
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

	  
	  private String copyExcelToServer(UploadedFile uploadedFile) throws IOException{
			ExternalContext external = 	FacesContext.getCurrentInstance().getExternalContext();   		
			
			// Obtenemos la extension y el nombre temporal a usar.
			String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
			String filename = UUID.randomUUID().toString();
	    	
	    	
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
	    	    return file.getAbsolutePath();
	    	} finally {
	    	    IOUtils.closeQuietly(output);
	    	    IOUtils.closeQuietly(input);
	    	}
	    	
		}
	  
	public void registrarChips() {
		

		try {
			
			if (cantidad > 0)
			{
				for(Rfid rfid : lstChips){
					if(rfid.getCodInterno()==null || rfid.getCodInterno().length()==0){
						addMessage(FacesMessage.SEVERITY_INFO, "Error", "No se ha ingresado el código rfid");
						return;
					}
				}
				
				
				for(Rfid rfid : lstChips){
					rfidService.registrarRFID(rfid);	
				}
				lstChips=new ArrayList<Rfid>();
				addMessage(FacesMessage.SEVERITY_INFO, "Success", "Registro Relizado con Éxito.");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("chipRegistroAction", null);
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

	
}

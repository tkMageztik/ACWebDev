package proyectos.cibertec.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Rfid;
import proyectos.cibertec.core.entity.Rol;
import proyectos.cibertec.core.entity.TipoEntidad;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.EmpresaService;
import proyectos.cibertec.core.service.RfidService;
import proyectos.cibertec.core.service.RolService;
import proyectos.cibertec.core.service.TipoEntidadService;
import proyectos.cibertec.core.service.UsuarioService;
import proyectos.cibertec.core.util.LeerExcelRfid;
import proyectos.cibertec.web.bean.EmpresaBean;

@Scope("session")
@Controller("chipRegEntAction")
public class ChipRegEntAction {
	@Autowired
	private RfidService rfidService;

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private TipoEntidadService tipoEntidadService;
	
	private int cantidad;
	private List<Rfid> lstChips;	
	private List<TipoEntidad> lstTipoEntidad;
	private Empresa empresaSeleccionada;	
	private EmpresaBean empresaBean;
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void postConstruct(){
		lstChips = new ArrayList<Rfid>();
		empresaBean = new EmpresaBean();
		try {
			setLstTipoEntidad(tipoEntidadService.listaTipoEntidad());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Busca la empresa usando el string "rucEmpresa" como base de busqueda.
	 */
	public void obtenerEmpresa(){		
		if(empresaBean.getRuc()!=null && empresaBean.getRuc().length()>0){
			try {
				List<Empresa> listaEmpresa = empresaService.buscarEmpresaPorRuc(empresaBean.getRuc());
				if(listaEmpresa!=null && listaEmpresa.size()>0){
					this.empresaSeleccionada = listaEmpresa.get(0);
					this.empresaBean.setRazonSocial(empresaSeleccionada.getRazonSocial());
					this.empresaBean.setRuc(empresaSeleccionada.getRuc());
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			addMessage(FacesMessage.SEVERITY_INFO, "Error", "Ingrese un RUC para buscar.");
		}
	}

	/**
	 * Crea una lista de chips que serán usados para llenar la data.
	 */
	public void obtenerChips(){
		try {
			lstChips = new ArrayList<Rfid>();
			if (cantidad > 0)
			{
				for(int i=1; i <= cantidad; i++)
				{
					Rfid oRfid = new Rfid();
					oRfid.setFechaIngreso(new Date());
					oRfid.setCorrelativo(i);
					lstChips.add(oRfid);
				}				
			}
			else{
				addMessage(FacesMessage.SEVERITY_INFO, "Error", "Ingrese una cantidad mayor a 0.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR, "Error", "FATAL ERROR.");
		}
	}
	
	/**
	 * Carga un archivo Excel con la lista de rfids para su analisis.
	 * @param event Evento del FileUpload
	 */
	public void uploadExcelRfid(FileUploadEvent event) {
    	UploadedFile file = event.getFile();    			
        if(file != null) {
    		try{ 
    			LeerExcelRfid ler = new LeerExcelRfid();
        		File archivoExcel = ler.copyExcelToServer(file);
        		List<Rfid> resultado =  ler.readRfid();
        		
        		if(resultado.size()!=this.cantidad){
        			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            				"Error en la lectura.", "El excel contiene una cantidad distinta de RFIDs a la solicitada.");
        			FacesContext.getCurrentInstance().addMessage(null, msg);
        		}
        		else{
        			this.lstChips = resultado;
        		}
        		        		
        		if(archivoExcel.exists()){
        			archivoExcel.delete();
        		}
    		}
    		catch(SecurityException se){
    			// Si no se puede eliminar el archivo, no hay problemas, pero deveria hacerse un log.
    			// TODO: log de archivo no eliminado
    		}
    		catch (Exception e) {
    			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
        				"Error en la lectura.",
    					e.getMessage());
        		FacesContext.getCurrentInstance().addMessage(null, msg);
			}        			
        	 	
        }  
        else{
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
    				"Error en la lectura.",
					"No se selecciono ningun archivo");    		
    		FacesContext.getCurrentInstance().addMessage(null, msg);
        }   
        
	}
	
	/**
	 * Limpia la empresa seleccinada
	 */
	public void limpiarEmpresaSeleccionada(){
		this.empresaSeleccionada = null;
		this.empresaBean.setRazonSocial(null);
		this.empresaBean.setRuc(null);
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
				
				if(empresaSeleccionada==null){
					empresaSeleccionada = new Empresa();
					empresaSeleccionada.setRazonSocial(empresaBean.getRazonSocial());
					empresaSeleccionada.setRuc(empresaBean.getRuc());		
					empresaSeleccionada.setTipoEntidad(empresaBean.getTipoEntidad());
					empresaService.registrarEmpresa(empresaSeleccionada);
					
					Usuario usuario = new Usuario();
					usuario.setPassword(new StringBuilder(empresaBean.getRuc()).reverse().toString());
					usuario.setUsuario("admin");
					usuario.setEmpresa(empresaSeleccionada);
					
					Rol rolAdministradorEmpresa = this.rolService.findById(2);
					usuario.setRol(rolAdministradorEmpresa);		
					
					usuarioService.registrarUsuario(usuario);
					
				}
				
				
				for(Rfid rfid : lstChips){
					rfid.setEmpresa(this.empresaSeleccionada);
					rfid.setUsuario(this.obtenerUsuarioLogeado());
					rfidService.registrarRFID(rfid);					
				}				
				
				lstChips=new ArrayList<Rfid>();
				addMessage(FacesMessage.SEVERITY_INFO, "Success", "Registro Relizado con Éxito.");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("chipRegistroAction", null);
				
				// Limpiamos
				this.clear();
			}
			else
				addMessage(FacesMessage.SEVERITY_INFO, "Error", "Ingrese una cantidad mayor a 0.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
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
	
	private void clear(){
		cantidad = 1;
		this.obtenerChips();
	}
	
	/** GETTERS & SETTERS **/ 
	
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

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	/** METODOS PROPIOS **/
	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}

	public EmpresaBean getEmpresaBean() {
		return empresaBean;
	}

	public void setEmpresaBean(EmpresaBean empresaBean) {
		this.empresaBean = empresaBean;
	}

	public List<TipoEntidad> getLstTipoEntidad() {
		return lstTipoEntidad;
	}

	public void setLstTipoEntidad(List<TipoEntidad> lstTipoEntidad) {
		this.lstTipoEntidad = lstTipoEntidad;
	}
	
}

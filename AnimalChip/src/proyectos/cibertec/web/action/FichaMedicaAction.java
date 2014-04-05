package proyectos.cibertec.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.FichaMedica;
import proyectos.cibertec.core.entity.Mascota;
import proyectos.cibertec.core.service.FichaMedicaService;
import proyectos.cibertec.core.service.MascotaService;
import proyectos.cibertec.core.util.report.AbstractReportBean;
import proyectos.cibertec.web.reportImpl.ReportCertificado;
import proyectos.cibertec.web.reportImpl.ReportFichaMedica;


@Scope("session")
@Controller("fichaMedicaAction")
public class FichaMedicaAction {
	
	@Autowired
	private MascotaService mascotaService;
		
	@Autowired
	private FichaMedicaService fichaMedicaService;
	
	private FichaMedica fichaMedica;
	private List<FichaMedica> listaFicha;
	private Mascota mascota;
	private String codigoRfid;	
	
	@PostConstruct
	public void postConstruct(){
		fichaMedica = new FichaMedica();
		fichaMedica.setFechaVisita(new Date());

	}
	
	/**
	 * Busca la mascota 
	 */
	public void buscarMascota(){
		try {
			List<Mascota> listaMascotas = mascotaService.buscarPorCodigoRFID(codigoRfid);
			if(listaMascotas!=null && listaMascotas.size()>0){
				mascota = listaMascotas.get(0);
				addMessage(FacesMessage.SEVERITY_INFO,"Info", "Mascota Encontrada.");
			}			
			else{
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "Mascota no encontrada");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "FATAL ERROR");
		}		
	}
	
	/**
	 * Ingresa la ficha médica al sistema
	 */
	public void ingresarFichaMedica(){
		try{
			if(mascota==null){
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "Ingresado una mascota.");
			}
			else{
				fichaMedica.setMascota(mascota);
				fichaMedica.setFechaVisita(new Date());
				fichaMedicaService.insertarFichaMedica(fichaMedica);
				addMessage(FacesMessage.SEVERITY_INFO,"Info", "Ingresado con éxito");
				clear();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "FATAL ERROR");
		}	
	}
	
	/**
	 * Limpia para un nuevo uso, no limpiara la mascota.
	 */
	private void clear(){
		fichaMedica = new FichaMedica();
	}
	
	public void mostrarCertificado(){
		ReportCertificado rc = new ReportCertificado();
		rc.setIdMascotaCertificado(this.mascota.getId());
		rc.execute();
	}
	
    public void mostrarFichaMedica(){
    	ReportFichaMedica rfm = new ReportFichaMedica();
    	rfm.setIdMascota(this.mascota.getId());
    	rfm.execute();
    }
		
	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public String getCodigoRfid() {
		return codigoRfid;
	}

	public void setCodigoRfid(String codigoRfid) {
		this.codigoRfid = codigoRfid;
	}

	public FichaMedica getFichaMedica() {
		return fichaMedica;
	}

	public void setFichaMedica(FichaMedica fichaMedica) {
		this.fichaMedica = fichaMedica;
	}

	public List<FichaMedica> getListaFicha() {
		return listaFicha;
	}

	public void setListaFicha(List<FichaMedica> listaFicha) {
		this.listaFicha = listaFicha;
	}
	
	
}

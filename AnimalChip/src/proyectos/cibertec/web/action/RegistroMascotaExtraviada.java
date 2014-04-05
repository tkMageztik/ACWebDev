package proyectos.cibertec.web.action;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.Mascota;
import proyectos.cibertec.core.entity.MascotaExtraviada;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.MascotaExtraviadaService;
import proyectos.cibertec.core.service.MascotaService;

@Scope("session")
@Controller("registroMascotaExtraviada")
public class RegistroMascotaExtraviada {
	
	
	@Autowired
	private MascotaService mascotaService;
	
	@Autowired
	private MascotaExtraviadaService mascotaExtraviadaService;
	
	private Mascota mascota;	
	private String codigoRfid;	

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
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "FATAL ERROR");
		}		
	}
	

	public void registrarMascotaExtraviada(){
		if(mascota==null){
			//Error
			addMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese una mascota.");			
		}
		else{
		
			MascotaExtraviada me = new MascotaExtraviada();
			me.setFecha(new Date());
			me.setIdMascota(mascota.getId());
			me.setUsuario(obtenerUsuarioLogeado());
			
			
			try {
				List<MascotaExtraviada> listaRes = mascotaExtraviadaService.buscarMascotaExtraviada(mascota.getId());
				if(listaRes.size()>0){
					addMessage(FacesMessage.SEVERITY_INFO, "Info", "La mascota ya ha sido reportada.");
				}
				else{
					mascotaExtraviadaService.insertarMascotaExtraviada(me);
					addMessage(FacesMessage.SEVERITY_INFO, "Info", "Registrada como extraviada.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addMessage(FacesMessage.SEVERITY_ERROR, "Error", "FATAL ERROR");
			}
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

	
	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}
		
	public String getCodigoRfid() {
		return codigoRfid;
	}

	public void setCodigoRfid(String codigoRfid) {
		this.codigoRfid = codigoRfid;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}


}

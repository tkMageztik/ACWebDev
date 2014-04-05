package proyectos.cibertec.web.action;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.entity.Mascota;
import proyectos.cibertec.core.service.MascotaService;


@Scope("session")
@Controller("buscarMascotaAction")
public class BuscarMascotaAction {

	@Autowired
	private MascotaService mascotaService;
	
	private Mascota mascotaEncontrada;
	
	private String codigoRfid;
	
	public void buscarMascota(){
		try {
			List<Mascota> listaMascotas = mascotaService.buscarPorCodigoRFID(codigoRfid);
			if(listaMascotas!=null && listaMascotas.size()>0){
				setMascotaEncontrada(listaMascotas.get(0));
				System.out.println(mascotaEncontrada.getPropietario().getApellidos());
			}			
			else{
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "Mascota no encontrada.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "FATAL ERROR.");
		}		
	}
	
	public void addMessage(FacesMessage.Severity severity, String title, String message) {		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
	}

	public Mascota getMascotaEncontrada() {
		return mascotaEncontrada;
	}

	public void setMascotaEncontrada(Mascota mascotaEncontrada) {
		this.mascotaEncontrada = mascotaEncontrada;
	}

	public String getCodigoRfid() {
		return codigoRfid;
	}

	public void setCodigoRfid(String codigoRfid) {
		this.codigoRfid = codigoRfid;
	}

	
}

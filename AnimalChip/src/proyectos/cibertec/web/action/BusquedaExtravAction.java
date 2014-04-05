package proyectos.cibertec.web.action;

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
@Controller("busquedaExtravAction")
public class BusquedaExtravAction {
	
	@Autowired
	private MascotaService mascotaService;
	
	@Autowired
	private MascotaExtraviadaService mascotaExtraviadaService;
	
	private String codigoRfid;	
	
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

	private Mascota mascota;
	
	private MascotaExtraviada mascotaExtraviada;
	
	private boolean permisoQuitar = false;

	public void buscarMascota(){
		try {
			List<Mascota> listaMascotas = mascotaService.buscarPorCodigoRFID(codigoRfid);
			if(listaMascotas!=null && listaMascotas.size()>0){
				Mascota mascotaEncontrada = listaMascotas.get(0);
				List<MascotaExtraviada> listaME = mascotaExtraviadaService.buscarMascotaExtraviada(mascotaEncontrada.getId());
				if(listaME!=null && listaME.size()>0){
					this.mascota = mascotaEncontrada;
					this.mascotaExtraviada = listaME.get(0);
					
					Usuario usuarioLogeado = obtenerUsuarioLogeado();
					
					if(usuarioLogeado.getRol().equals(3) ||
					   usuarioLogeado.getEmpresa().getId()==mascotaExtraviada.getUsuario().getEmpresa().getId()
							 ){
						permisoQuitar = true;
					}
					else{
						permisoQuitar = false;
					}
					
				}
				else{
					addMessage(FacesMessage.SEVERITY_ERROR,"Error", "Mascota no encontrada como extraviada.");
				}
			}			
			else{
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "Mascota no encontrada como extraviada.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "FATAL ERROR.");
		}		
	}
	
	public void liberarMascotaExtraviada(){
		if(mascotaExtraviada==null){
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe encontrar un registro de mascota extraviada.");
			return;
		}
	
		if(permisoQuitar)
		{				
			mascotaExtraviada.setFlagActivo((byte) 0x00);
			try {
				mascotaExtraviadaService.actualizarMascotaExtraviada(mascotaExtraviada);
				this.clear();
				addMessage(FacesMessage.SEVERITY_INFO,"Info", "Ejecutado con éxito.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				addMessage(FacesMessage.SEVERITY_ERROR,"Error", "FATAL ERROR.");
			}
			
		}
		else{
			addMessage(FacesMessage.SEVERITY_ERROR,"Error", "No puede quitar esta mascota de la lista de perdidos.");
		}			
		

	}

	private void clear(){
		mascotaExtraviada = null;
		mascota = null;
		permisoQuitar = false;
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

	public MascotaExtraviada getMascotaExtraviada() {
		return mascotaExtraviada;
	}

	public void setMascotaExtraviada(MascotaExtraviada mascotaExtraviada) {
		this.mascotaExtraviada = mascotaExtraviada;
	}

	public boolean isPermisoQuitar() {
		return permisoQuitar;
	}

	public void setPermisoQuitar(boolean permisoQuitar) {
		this.permisoQuitar = permisoQuitar;
	}

}

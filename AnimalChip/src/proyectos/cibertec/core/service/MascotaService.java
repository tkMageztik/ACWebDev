package proyectos.cibertec.core.service;

import java.util.List;

import proyectos.cibertec.core.entity.Mascota;

public interface MascotaService {
	
	public abstract void registrarMascota(Mascota oMascota) throws Exception;
	public abstract void actualizarMascota(Mascota oMascota) throws Exception;
	public abstract List<Mascota> buscarPorCodigoRFID(String codigoRfid) throws Exception;

}

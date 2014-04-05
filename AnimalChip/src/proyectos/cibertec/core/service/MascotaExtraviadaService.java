package proyectos.cibertec.core.service;

import java.util.List;

import proyectos.cibertec.core.entity.MascotaExtraviada;

public interface MascotaExtraviadaService {
	public abstract void insertarMascotaExtraviada(MascotaExtraviada mascotaExtraviada) throws Exception;
	public abstract void actualizarMascotaExtraviada(MascotaExtraviada mascotaExtraviada) throws Exception;
	public abstract List<MascotaExtraviada> buscarMascotaExtraviada(int idMascota) throws Exception;
}

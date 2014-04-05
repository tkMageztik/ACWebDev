package proyectos.cibertec.core.service;

import proyectos.cibertec.core.entity.Foto;

public interface FotoService {
	public abstract void actualizarFoto(Foto foto) throws Exception;
	public abstract void insertarFoto(Foto foto) throws Exception;
}

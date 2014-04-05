package proyectos.cibertec.core.service;

import java.util.List;

import proyectos.cibertec.core.entity.TipoEntidad;

public interface TipoEntidadService  {

	public abstract List<TipoEntidad> listaTipoEntidad() throws Exception;
	public abstract TipoEntidad obtenerPorId(int id) throws Exception;
}

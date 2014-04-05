package proyectos.cibertec.core.service;

import java.util.List;

import proyectos.cibertec.core.entity.Propietario;

public interface PropietarioService {
	public void insertarPropietario(Propietario propietario) throws Exception;
	public List<Propietario> buscarPropietarioPorDocIden(String doc) throws Exception;
}

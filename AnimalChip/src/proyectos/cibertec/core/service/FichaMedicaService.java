package proyectos.cibertec.core.service;

import java.util.List;

import proyectos.cibertec.core.entity.FichaMedica;

public interface FichaMedicaService {
	public void insertarFichaMedica(FichaMedica fichaMedica) throws Exception;
	public List<FichaMedica> listarFichaMedica(int idMascota) throws Exception;
	
}

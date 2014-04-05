package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.entity.FichaMedica;
import proyectos.cibertec.core.util.base.dao.EntityDao;

public interface FichaMedicaDao extends EntityDao<FichaMedica>{
	public List<FichaMedica> listarFichaMedica(int idMascota) throws Exception;
}

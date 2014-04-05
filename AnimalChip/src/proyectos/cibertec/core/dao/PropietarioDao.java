package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.entity.Propietario;
import proyectos.cibertec.core.util.base.dao.EntityDao;

public interface PropietarioDao extends EntityDao<Propietario>{
	public List<Propietario> buscarPropietarioPorDocIden(String doc) throws Exception;
}

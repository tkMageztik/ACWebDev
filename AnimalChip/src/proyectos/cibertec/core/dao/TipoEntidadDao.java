package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.entity.TipoEntidad;
import proyectos.cibertec.core.util.base.dao.EntityDao;

public interface TipoEntidadDao extends EntityDao<TipoEntidad>{
	public List<TipoEntidad> listarTipoEntidad() throws Exception;
	public TipoEntidad obtenerPorId(int id) throws Exception;
}

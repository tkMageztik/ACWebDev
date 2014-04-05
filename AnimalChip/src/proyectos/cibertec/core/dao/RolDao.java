package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.entity.Rol;
import proyectos.cibertec.core.util.base.dao.EntityDao;

public interface RolDao extends EntityDao<Rol>{
	public List<Rol> findRolById(int id) throws Exception;
}

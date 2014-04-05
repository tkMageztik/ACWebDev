package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.util.base.dao.EntityDao;
import proyectos.cibertec.core.entity.Mascota;

public interface MascotaDao extends EntityDao<Mascota>{
	
	public List<Mascota> buscarMascotaPorRFID(String codigoRFID) throws Exception;
	
}

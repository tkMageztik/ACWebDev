package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.entity.MascotaExtraviada;
import proyectos.cibertec.core.util.base.dao.EntityDao;

public interface MascotaExtraviadaDao extends EntityDao<MascotaExtraviada>{
	public List<MascotaExtraviada> buscarMascotaExtraviada(int idMascota) throws Exception;
}

package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.entity.Rfid;
import proyectos.cibertec.core.util.base.dao.EntityDao;

public interface RfidDao extends EntityDao<Rfid>{
	public void insertarListaRfid(List<Rfid> lista) throws Exception;
	public List<Rfid> buscarPorCodigo(String codigo) throws Exception;
}

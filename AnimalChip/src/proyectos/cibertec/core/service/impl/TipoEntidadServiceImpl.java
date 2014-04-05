package proyectos.cibertec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.TipoEntidadDao;
import proyectos.cibertec.core.entity.TipoEntidad;
import proyectos.cibertec.core.service.TipoEntidadService;

@Service("TipoEntidadService")
public class TipoEntidadServiceImpl implements TipoEntidadService{

	@Autowired
	private TipoEntidadDao primaryMemberDao;
	
	@Override
	public List<TipoEntidad> listaTipoEntidad() throws Exception {
		return primaryMemberDao.listarTipoEntidad();
	}

	@Override
	public TipoEntidad obtenerPorId(int id) throws Exception {
		return primaryMemberDao.obtenerPorId(id);
	}

}

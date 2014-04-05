package proyectos.cibertec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.MascotaExtraviadaDao;
import proyectos.cibertec.core.entity.MascotaExtraviada;
import proyectos.cibertec.core.service.MascotaExtraviadaService;

@Service("mascotaExtraviadaServiceImpl")
public class MascotaExtraviadaServiceImpl implements MascotaExtraviadaService{


	@Autowired
	private MascotaExtraviadaDao primaryMemberDao;
	
	@Override
	public void insertarMascotaExtraviada(MascotaExtraviada mascotaExtraviada)  throws Exception {
		primaryMemberDao.persist(mascotaExtraviada);
	}

	@Override
	public List<MascotaExtraviada> buscarMascotaExtraviada(int idMascota)
			throws Exception {
		return primaryMemberDao.buscarMascotaExtraviada(idMascota);
	}

	@Override
	public void actualizarMascotaExtraviada(MascotaExtraviada mascotaExtraviada)
			throws Exception {
		primaryMemberDao.merge(mascotaExtraviada);
	}
		
}

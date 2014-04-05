package proyectos.cibertec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.MascotaDao;
import proyectos.cibertec.core.entity.Mascota;
import proyectos.cibertec.core.service.MascotaService;

@Service("mascotaService")
public class MascotaServiceImpl implements MascotaService {

	@Autowired
	private MascotaDao mascotaDao;
	
	@Override
	public void registrarMascota(Mascota oMascota) throws Exception {
		mascotaDao.persist(oMascota);
	}

	@Override
	public void actualizarMascota(Mascota oMascota) throws Exception {
		mascotaDao.merge(oMascota);		
	}

	@Override
	public List<Mascota> buscarPorCodigoRFID(String codigoRfid) throws Exception {
		List<Mascota> listaRes = mascotaDao.buscarMascotaPorRFID(codigoRfid);
		for(Mascota m : listaRes){
			m.getPropietario();
		}
		return listaRes;
	}

}

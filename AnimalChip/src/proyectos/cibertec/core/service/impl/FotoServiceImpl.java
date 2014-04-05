package proyectos.cibertec.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.FotoDao;
import proyectos.cibertec.core.entity.Foto;
import proyectos.cibertec.core.service.FotoService;

@Service("fotoService")
public class FotoServiceImpl implements FotoService{

	@Autowired
	private FotoDao fotoDao;
	
	public void actualizarFoto(Foto foto) throws Exception {
		fotoDao.merge(foto);		
	}

	public void insertarFoto(Foto foto) throws Exception {
		fotoDao.persist(foto);		
	}

}

package proyectos.cibertec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.FichaMedicaDao;
import proyectos.cibertec.core.entity.FichaMedica;
import proyectos.cibertec.core.service.FichaMedicaService;

@Service("fichaMedicaService")
public class FichaMedicaServiceImpl implements FichaMedicaService {

	@Autowired
	private FichaMedicaDao primaryMemberDao;
	
	@Override
	public void insertarFichaMedica(FichaMedica fichaMedica) throws Exception {
		primaryMemberDao.persist(fichaMedica);		
	}

	@Override
	public List<FichaMedica> listarFichaMedica(int idMascota) throws Exception {
		return primaryMemberDao.listarFichaMedica(idMascota);
	}
	
	
}

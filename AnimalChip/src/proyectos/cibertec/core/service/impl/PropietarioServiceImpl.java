package proyectos.cibertec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.PropietarioDao;
import proyectos.cibertec.core.entity.Propietario;
import proyectos.cibertec.core.service.PropietarioService;

@Service("propietarioService")
public class PropietarioServiceImpl implements PropietarioService {

	@Autowired
	private PropietarioDao propietarioDao;
	
	@Override
	public void insertarPropietario(Propietario propietario) throws Exception{
		propietarioDao.persist(propietario);	
	}

	@Override
	public List<Propietario> buscarPropietarioPorDocIden(String docIden) throws Exception{
		return propietarioDao.buscarPropietarioPorDocIden(docIden);
	}

}

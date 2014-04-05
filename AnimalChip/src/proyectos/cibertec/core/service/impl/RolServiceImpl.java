package proyectos.cibertec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.RolDao;
import proyectos.cibertec.core.entity.Rol;
import proyectos.cibertec.core.service.RolService;

@Service("rolService")
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolDao primaryMemberDao;

	public Rol findById(int id) throws Exception {
		List<Rol> lista = primaryMemberDao.findRolById(id);
		if(lista!=null && lista.size()>0){
			return lista.get(0);
		}
		else{
			return null;
		}
	}
	


}

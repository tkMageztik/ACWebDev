package proyectos.cibertec.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import proyectos.cibertec.core.dao.UsuarioDao;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UsuarioDao primaryMemberDao;
	
	@Transactional
	public Usuario loginSocio(String usuario) throws Exception {
		
		@SuppressWarnings("rawtypes")
		List lst = primaryMemberDao.findUserByLogin(usuario);
		if ( lst != null && lst.size()>0)
			return (Usuario) (lst.get(0));
			
		return null;
	}

	@Override
	public Usuario loginSocioEmpresa(String usuario, String rucEmpresa)
			throws Exception {
		
		@SuppressWarnings("rawtypes")
		List lst = primaryMemberDao.findUserByLoginEmpresa(usuario, rucEmpresa);
		if ( lst != null && lst.size()>0)
			return (Usuario) (lst.get(0));
			
		return null;
	}

}

package proyectos.cibertec.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyectos.cibertec.core.dao.UsuarioDao;
import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.service.UsuarioService;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Transactional
	public void registrarUsuario(Usuario oUsuario) throws Exception {
		usuarioDao.persist(oUsuario);

	}	
	
	@Transactional
	public void actualizarUsuario(Usuario oUsuario) throws Exception {
		//fotoDao.merge(oUsuario.getFoto());
		usuarioDao.merge(oUsuario);
	}
	
	public ArrayList<Usuario> listAccounts() throws Exception {
		
		ArrayList<Usuario> lstAccounts = new ArrayList<Usuario>();
		
		try{
			if (usuarioDao.listAccounts() != null)
				lstAccounts = (ArrayList<Usuario>) usuarioDao.listAccounts();
		}
		catch (Exception ex)
		{
			System.out.println("catch");
			System.out.println(ex);
		}
		
		return lstAccounts;
	}

	public Usuario buscarUsuarioPorLogin(String login) throws Exception {
		List<Usuario> res = usuarioDao.findUserByLogin(login);
		
		if(res!=null && res.size()>0){
			return res.get(0);
		}
		else{
			return null;
		}
	}

	public List<Usuario> listAccountsByEmpresa(Empresa empresa)
			throws Exception {
		return usuarioDao.listAccountsByEmpresa(empresa);
	}

	public List<Usuario> listAccountsByEmpresa(String rucEmpresa)
			throws Exception {
		return usuarioDao.listAccountsByEmpresa(rucEmpresa);
	}
}

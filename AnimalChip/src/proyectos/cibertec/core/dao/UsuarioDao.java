package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.util.base.dao.EntityDao;
import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Usuario;

public interface UsuarioDao extends EntityDao<Usuario>{
	public List<Usuario> findUserByLogin(String usuario) throws Exception;
	public List<Usuario> findUserByLoginEmpresa(String usuario, String rucEmpresa) throws Exception;
	public List<Usuario> listAccounts() throws Exception;
	public List<Usuario> listAccountsByEmpresa(Empresa empresa) throws Exception;
	public List<Usuario> listAccountsByEmpresa(String rucEmpresa) throws Exception;
}

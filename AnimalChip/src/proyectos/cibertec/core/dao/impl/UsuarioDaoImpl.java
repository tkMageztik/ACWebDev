package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.UsuarioDao;
import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Usuario;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("usuarioDao")
public class UsuarioDaoImpl extends EntityDaoImpl<Usuario> implements UsuarioDao{

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> findUserByLogin(String usuario) throws Exception {
		Query query = getEntityManager().createNamedQuery(
				"Usuario.findByLogin");
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> findUserByLoginEmpresa(String usuario, String rucEmpresa){
			Query query = getEntityManager().createQuery(
					"SELECT u FROM Usuario u where u.usuario like :usuario and u.empresa.ruc like :rucEmpresa");
			query.setParameter("usuario", usuario);
			query.setParameter("rucEmpresa", rucEmpresa);
			return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listAccounts() throws Exception {
		Query query = getEntityManager().createQuery("SELECT v FROM Veterinario v WHERE perfilUsu != 'admin'");
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listAccountsByEmpresa(Empresa empresa) throws Exception {
		
		Query query = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.empresa = :empresa");
		query.setParameter("empresa", empresa);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listAccountsByEmpresa(String rucEmpresa) throws Exception {
		
		Query query = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.empresa.ruc = :rucEmpresa");
		query.setParameter("rucEmpresa", rucEmpresa);
		
		return query.getResultList();
	}


	
}

package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.RolDao;
import proyectos.cibertec.core.entity.Rol;

import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("rolDaoImpl")
public class RolDaoImpl  extends EntityDaoImpl<Rol> implements RolDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Rol> findRolById(int id) throws Exception {		 
		Query query = getEntityManager().createQuery("Select r from Rol r where r.id=:id");
		query.setParameter("id", id);		
		return  query.getResultList();	
	}

}

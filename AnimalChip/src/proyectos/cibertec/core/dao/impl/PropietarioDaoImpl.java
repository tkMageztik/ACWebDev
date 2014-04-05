package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.PropietarioDao;

import proyectos.cibertec.core.entity.Propietario;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("propietarioDao")
public class PropietarioDaoImpl extends EntityDaoImpl<Propietario>  implements PropietarioDao {

	
	@SuppressWarnings("unchecked")
	public List<Propietario> buscarPropietarioPorDocIden(String doc) throws Exception {		 
		Query query = getEntityManager().createQuery("Select p from Propietario p where p.documentoIdentidad like :doc");
		query.setParameter("doc", doc);
		return query.getResultList();
	}

	
}

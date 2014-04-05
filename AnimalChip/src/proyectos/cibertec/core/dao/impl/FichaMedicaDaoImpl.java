package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.FichaMedicaDao;
import proyectos.cibertec.core.entity.FichaMedica;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("fichaMedicaDaoImpl")
public class FichaMedicaDaoImpl extends EntityDaoImpl<FichaMedica> implements FichaMedicaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<FichaMedica> listarFichaMedica(int idMascota)
			throws Exception {
		Query query = getEntityManager()
				.createQuery("Select f from FichaMedica f where f.mascota.id = :idMascota");
		query.setParameter("id",  idMascota);
		return query.getResultList();	
	}
	
}

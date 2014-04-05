package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.MascotaExtraviadaDao;
import proyectos.cibertec.core.entity.MascotaExtraviada;

import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("mascotaExtraviadaDaoImpl")
public class MascotaExtraviadaDaoImpl extends EntityDaoImpl<MascotaExtraviada> implements MascotaExtraviadaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MascotaExtraviada> buscarMascotaExtraviada(int idMascota) throws Exception {
		 
		Query query = getEntityManager()
				.createQuery("Select m from MascotaExtraviada m where m.idMascota = :idMascota and flagActivo = 1");
		query.setParameter("idMascota", idMascota);
		
		return query.getResultList();
	}
	
	

}

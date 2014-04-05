package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.MascotaDao;
import proyectos.cibertec.core.entity.Mascota;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("mascotaDao")
public class MascotaDaoImpl extends EntityDaoImpl<Mascota> implements
		MascotaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Mascota> buscarMascotaPorRFID(String codigoRFID) throws Exception{
		System.out.println(getEntityClass().getSimpleName());
		 
		Query query = getEntityManager().createQuery("Select m from Mascota m where m.rfid.codInterno like :codigoRFID");
		query.setParameter("codigoRFID", codigoRFID);
		
		return query.getResultList();
	}
	
}

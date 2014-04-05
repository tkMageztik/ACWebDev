package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.TipoEntidadDao;
import proyectos.cibertec.core.entity.TipoEntidad;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("tipoEntidadDao")
public class TipoEntidadDaoImpl  extends EntityDaoImpl<TipoEntidad>  implements TipoEntidadDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoEntidad> listarTipoEntidad() throws Exception {
		Query query = getEntityManager().createQuery("Select te from TipoEntidad te");
		return  query.getResultList();	
	}

	@Override
	@SuppressWarnings("unchecked")
	public TipoEntidad obtenerPorId(int id) throws Exception {
		Query query = getEntityManager().createQuery("Select te from TipoEntidad te where te.id = @id");
		query.setParameter("id", id);
		query.setMaxResults(1);
		List<TipoEntidad> resultado = query.getResultList();
		if(resultado.size()>0){
			return resultado.get(0);
		}
		else{
			return null;
		}
	}

	
	
}

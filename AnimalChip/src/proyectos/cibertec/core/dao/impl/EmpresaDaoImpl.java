package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.EmpresaDao;
import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("empresaDao")
public class EmpresaDaoImpl extends EntityDaoImpl<Empresa> implements EmpresaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> buscarEmpresaPorRuc(String ruc)  throws Exception{		 
		Query query = getEntityManager()
				.createQuery("Select e from Empresa e where e.ruc like :ruc");
		query.setParameter("ruc",  ruc);			
		return query.getResultList();	
	}

}

package proyectos.cibertec.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import proyectos.cibertec.core.dao.RfidDao;
import proyectos.cibertec.core.entity.Rfid;
import proyectos.cibertec.core.util.base.dao.impl.EntityDaoImpl;

@Repository("rfidDao")
public class RfidDaoImpl extends EntityDaoImpl<Rfid> implements RfidDao{

	@Override
	public void insertarListaRfid(List<Rfid> lista) {
		getEntityManager().getTransaction().begin();
		try{
			for(Rfid rfid : lista){
				getEntityManager().persist(rfid);				
			}
			getEntityManager().getTransaction().commit();
		}
		catch(Exception ex){
			getEntityManager().getTransaction().rollback();
			System.out.println(ex.getMessage());
			//return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rfid> buscarPorCodigo(String codigo) {
		
		try {
			System.out.println(getEntityClass().getSimpleName());
			 
			Query query = getEntityManager().createQuery("Select r from Rfid r where r.codInterno like :codInterno");
			query.setParameter("codInterno",  codigo);
			
			return query.getResultList();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	
	}


}

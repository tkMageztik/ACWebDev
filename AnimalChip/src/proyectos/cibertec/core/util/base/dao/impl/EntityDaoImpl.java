package proyectos.cibertec.core.util.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import proyectos.cibertec.core.util.base.dao.EntityDao;

public class EntityDaoImpl<E> implements EntityDao<E> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected E instance;
	private Class<E> entityClass;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void persist(E e) throws Exception {
		getEntityManager().persist(e);
	}

	@Transactional
	public void merge(E e) throws Exception {
		getEntityManager().merge(e);
	}

	@Transactional
	public void remove(Object id) throws Exception {
		getEntityManager().remove((E) getEntityManager().find(getEntityClass(), id));
	}

//	@SuppressWarnings("unchecked")
//	public E findById(Object id) throws Exception {
//		return (E)getEntityManager().find(getClass(), id);
//	}

	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() throws Exception{
		if(entityClass==null){
			Type type = getClass().getGenericSuperclass();
			if(type instanceof ParameterizedType){
			ParameterizedType paramType = (ParameterizedType)type;
			if(paramType.getActualTypeArguments().length==2){
				if(paramType.getActualTypeArguments()[1] instanceof TypeVariable){
					throw new IllegalArgumentException("Could not guess entity class by reflection");
				}else{
					entityClass = (Class<E>) paramType.getActualTypeArguments()[1];
				}
			}else{
				entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
			}
			}
		}
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	public E findByName(Object name) throws Exception {
	    try {
	        return (E)getEntityManager().createNamedQuery(getEntityClass().getSimpleName() + ".findByName")
	                               .setParameter("name", name)
	                               .getSingleResult();
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	        return null;
	    }
	}

	
	//-------------
	@SuppressWarnings("rawtypes")
	public List findByIdMembresia(Object id) throws Exception {
		try {
			System.out.println(getEntityClass().getSimpleName());
			Query query = getEntityManager().createNamedQuery(
					getEntityClass().getSimpleName() + ".findByIdMembresia");
			query.setParameter("id", id);
			List result = query.getResultList();
			return result;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List findByRFID(Object id) throws Exception {
		try {
			System.out.println(getEntityClass().getSimpleName());
			Query query = getEntityManager().createNamedQuery(
					getEntityClass().getSimpleName() + ".findByRFID");
			query.setParameter("id", "" + id);
			List result = query.getResultList();
			return result;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List findByIdVeterinario(Object id) throws Exception {
		try {
			System.out.println(getEntityClass().getSimpleName());
			Query query = getEntityManager().createNamedQuery(
					getEntityClass().getSimpleName() + ".findByIdVeterinario");
			query.setParameter("id", id);
			List result = query.getResultList();
			
			System.out.println("Size " + result.size());
			if (result.size() == 0)
				result = null;
			
			return result;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List findByVet(Object usuarioVet) throws Exception {
		try {
			System.out.println(getEntityClass().getSimpleName());
			Query query = getEntityManager().createNamedQuery(
					getEntityClass().getSimpleName() + ".findByVet");
			query.setParameter("usuarioVet", usuarioVet);
			List result = query.getResultList();
			return result;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	
	@SuppressWarnings("rawtypes")
	public List listRFID() throws Exception {
		try {

			System.out.println("Before");
			Query query = getEntityManager().createQuery("SELECT r FROM RFID r");
			System.out.println("After Create Query");
			
			List result = query.getResultList();
			
			System.out.println("After Getting Result");
			return result;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List listAviableRFID() throws Exception {
		try {

			System.out.println("Before");
			Query query = getEntityManager().createQuery("SELECT r FROM RFID r where flagComprado != 1");
			System.out.println("After Create Query");
			
			List result = query.getResultList();
			
			System.out.println("After Getting Result");
			return result;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
}

package proyectos.cibertec.core.util.base.dao;

public interface EntityDao<E> {

	public abstract void persist(E e) throws Exception;

	public abstract void merge(E e) throws Exception;

	public abstract void remove(Object id) throws Exception;

	//public abstract E findById(Object id) throws Exception;

	public abstract E findByName(Object name) throws Exception;

}

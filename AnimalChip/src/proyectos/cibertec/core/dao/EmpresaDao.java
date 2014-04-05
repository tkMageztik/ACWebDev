package proyectos.cibertec.core.dao;

import java.util.List;

import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.util.base.dao.EntityDao;

public interface EmpresaDao extends EntityDao<Empresa>{
	public List<Empresa> buscarEmpresaPorRuc(String ruc) throws Exception;
}

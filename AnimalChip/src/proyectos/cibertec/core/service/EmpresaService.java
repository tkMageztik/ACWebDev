package proyectos.cibertec.core.service;

import java.util.List;

import proyectos.cibertec.core.entity.Empresa;

public interface EmpresaService {

	
	public abstract void registrarEmpresa(Empresa oEmpresa) throws Exception;
	public abstract List<Empresa> buscarEmpresaPorRuc(String ruc) throws Exception;
	public abstract void actualizarEmpresa(Empresa empresa) throws Exception;
}

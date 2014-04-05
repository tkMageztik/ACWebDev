package proyectos.cibertec.core.service;

import proyectos.cibertec.core.entity.Usuario;

public interface LoginService {
	
	public abstract Usuario loginSocio(String usuario)throws Exception;
	public abstract Usuario loginSocioEmpresa(String usuario, String rucEmpresa)throws Exception;

}

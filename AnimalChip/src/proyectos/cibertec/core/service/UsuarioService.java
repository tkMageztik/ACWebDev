package proyectos.cibertec.core.service;

import java.util.ArrayList;
import java.util.List;

import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.entity.Usuario;

public interface UsuarioService {

	
	public abstract void registrarUsuario(Usuario oUsuario) throws Exception;	
	public abstract void actualizarUsuario(Usuario oUsuario) throws Exception;	
	public abstract ArrayList<Usuario> listAccounts() throws Exception;	
	public abstract Usuario buscarUsuarioPorLogin(String login) throws Exception;
	public List<Usuario> listAccountsByEmpresa(Empresa empresa) throws Exception;
	public List<Usuario> listAccountsByEmpresa(String rucEmpresa) throws Exception;
	
}

package proyectos.cibertec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyectos.cibertec.core.dao.EmpresaDao;
import proyectos.cibertec.core.entity.Empresa;
import proyectos.cibertec.core.service.EmpresaService;


@Service("empresaService")
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDao primaryMemberDao;
	
	@Transactional
	public void registrarEmpresa(Empresa oEmpresa) throws Exception {		
		primaryMemberDao.persist(oEmpresa);	
	}

	@Override
	public List<Empresa> buscarEmpresaPorRuc(String ruc) throws Exception {
		return primaryMemberDao.buscarEmpresaPorRuc(ruc);
	}

	@Override
	public void actualizarEmpresa(Empresa empresa) throws Exception {
		primaryMemberDao.merge(empresa);
	}
	
	
}

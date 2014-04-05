package proyectos.cibertec.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectos.cibertec.core.dao.RfidDao;
import proyectos.cibertec.core.entity.Rfid;
import proyectos.cibertec.core.service.RfidService;

@Service("rfidService")
public class RfidServiceImpl implements RfidService{

	@Autowired
	private RfidDao primaryMemberDao;
	
	
	public void registrarRFID(Rfid oRfid) throws Exception {
		primaryMemberDao.persist(oRfid);		
	}

	public void registrarListaRFID(List<Rfid> lista) throws Exception {
		primaryMemberDao.insertarListaRfid(lista);		
	}

	public void actualizarRFID(Rfid oRfid) throws Exception {
		primaryMemberDao.merge(oRfid);
	}

	public ArrayList<Rfid> listRfid() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Rfid> listAviableRFID() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Rfid> buscarRfidporCodigo(String codigo) throws Exception {
		return primaryMemberDao.buscarPorCodigo(codigo);
	}

}

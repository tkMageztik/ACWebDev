package proyectos.cibertec.core.service;

import java.util.ArrayList;
import java.util.List;

import proyectos.cibertec.core.entity.Rfid;

public interface RfidService {
	
	public abstract void registrarRFID(Rfid oRfid) throws Exception;
	public abstract void registrarListaRFID(List<Rfid> lista) throws Exception;	
	public abstract void actualizarRFID(Rfid oRfid) throws Exception;
	public abstract ArrayList<Rfid> listRfid() throws Exception;
	public abstract List<Rfid> buscarRfidporCodigo(String codigo) throws Exception;
	public abstract ArrayList<Rfid> listAviableRFID() throws Exception;
}

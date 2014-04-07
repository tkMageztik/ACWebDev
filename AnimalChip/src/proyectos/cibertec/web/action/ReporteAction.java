package proyectos.cibertec.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.web.reportImpl.ReportRFID;

@Scope("session")
@Controller("reporteAction")
public class ReporteAction {
	
	private String rucVeterinariaRepRFID;
	
	public void executeReportRFID(){
		 ReportRFID reporteRFID = new ReportRFID();
		 reporteRFID.setRucVeterinaria(rucVeterinariaRepRFID);
		 reporteRFID.execute();
	}

	public String getRucVeterinariaRepRFID() {
		return rucVeterinariaRepRFID;
	}

	public void setRucVeterinariaRepRFID(String rucVeterinariaRepRFID) {
		this.rucVeterinariaRepRFID = rucVeterinariaRepRFID;
	}
	
}

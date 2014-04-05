package proyectos.cibertec.web.action.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.util.report.AbstractReportBean;

@Scope("session")
@Controller("mascotaRazaReport")
public class MascotaRazaReport   extends AbstractReportBean{
	
	private final String COMPILE_FILE_NAME = "MascotaRazaEspecie";
	private String razaMascota;
	

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}
 
	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();
		reportParameters.put("razaMascota", razaMascota);
 
		return reportParameters;
	}
    
 
	public String execute() {
    	try {
        	super.setExportOption(ExportOption.PDF);
            super.prepareReport();
        } catch (Exception e) {
            // make your own exception handling
            e.printStackTrace();
        }
 
        return null;
    }

	public String getRazaMascota() {
		return razaMascota;
	}

	public void setRazaMascota(String razaMascota) {
		this.razaMascota = razaMascota;
	}
	
}

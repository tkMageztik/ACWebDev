package proyectos.cibertec.web.reportImpl;

import java.util.HashMap;
import java.util.Map;

import proyectos.cibertec.core.util.report.AbstractReportBean;

public class ReportRFID  extends AbstractReportBean{

	private final String COMPILE_FILE_NAME = "ReporteRFID";
	
	private String rucVeterinaria;

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}
	
	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();
		reportParameters.put("rucVeterinaria", rucVeterinaria);
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

	public String getRucVeterinaria() {
		return rucVeterinaria;
	}

	public void setRucVeterinaria(String rucVeterinaria) {
		this.rucVeterinaria = rucVeterinaria;
	}

}

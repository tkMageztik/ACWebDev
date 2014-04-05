package proyectos.cibertec.web.reportImpl;

import java.util.HashMap;
import java.util.Map;

import proyectos.cibertec.core.util.report.AbstractReportBean;
public class ReportFichaMedica extends AbstractReportBean {
	
	private final String COMPILE_FILE_NAME = "FichaMedica";

	private int idMascota;
	
	@Override
    protected String getCompileFileName() {
        return COMPILE_FILE_NAME;
    }
 
    @Override
    protected Map<String, Object> getReportParameters() {
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put("idMascota", idMascota);
        return reportParameters;
    }
    
    /**
     * Exporta el reporte pdf de fichas médicas
     * @return null
     */
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
	
	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}
	
}

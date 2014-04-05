package proyectos.cibertec.web.reportImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import proyectos.cibertec.core.util.report.AbstractReportBean;

public class ReportMascotaExtraviada extends AbstractReportBean{

	private final String COMPILE_FILE_NAME = "RepMascotaExtraviadas";
	private Date fechaInicio;
	private Date fechaFin;
	
	 
    @Override
    protected String getCompileFileName() {
        return COMPILE_FILE_NAME;
    }
 
    @Override
    protected Map<String, Object> getReportParameters() {
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        reportParameters.put("dateStart", sdf.format(fechaInicio));
        reportParameters.put("dateEnd", sdf.format(fechaFin));
 
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

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
}

package proyectos.cibertec.web.reportImpl;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import proyectos.cibertec.core.util.report.AbstractReportBean;


public class ReportCertificado extends AbstractReportBean{


	private final String COMPILE_FILE_NAME = "CertificadoMascota";
	
	private int idMascotaCertificado;
	
	@Override
    protected String getCompileFileName() {
        return COMPILE_FILE_NAME;
    }
 
    public int getIdMascotaCertificado() {
		return idMascotaCertificado;
	}

	public void setIdMascotaCertificado(int idMascotaCertificado) {
		this.idMascotaCertificado = idMascotaCertificado;
	}

	@Override
    protected Map<String, Object> getReportParameters() {
        Map<String, Object> reportParameters = new HashMap<String, Object>();       
        reportParameters.put("idMascota", idMascotaCertificado );
        System.out.println("MascotaIdReporte :" + idMascotaCertificado );

    	ExternalContext external = 	FacesContext.getCurrentInstance().getExternalContext(); 
    	String path = external.getRealPath("/report") + "/AnimalChipImg.png" ;
    	
        reportParameters.put("imgLogo", path );
        
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
	
	

}

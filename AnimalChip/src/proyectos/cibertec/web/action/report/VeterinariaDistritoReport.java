package proyectos.cibertec.web.action.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import proyectos.cibertec.core.util.report.AbstractReportBean;

@Scope("session")
@Controller("veterinariaDistritoReport")
public class VeterinariaDistritoReport  extends AbstractReportBean{

	private final String COMPILE_FILE_NAME = "VeterinariaPorDistrito";
	private String departamento;
	private String distrito;
	private String provincia;
	

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}
 
	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();

		reportParameters.put("departamento", (departamento.length()>0? departamento:null));
		reportParameters.put("distrito", (distrito.length()>0? distrito:null));
		reportParameters.put("provincia", (provincia.length()>0? provincia:null));
 
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

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}

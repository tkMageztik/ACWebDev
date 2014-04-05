package proyectos.cibertec.web.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import proyectos.cibertec.core.entity.TipoEntidad;


@Scope("session")
@Component("empresaBean")
public class EmpresaBean {
	
	private String ruc;	
	private String razonSocial;
	private TipoEntidad tipoEntidad;
	private String distrito;
	private String provincia;
	private String departamento;
	
	public String getRuc() {
		return ruc;
	}
	
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public TipoEntidad getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(TipoEntidad tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
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

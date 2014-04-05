package proyectos.cibertec.web.bean;

import org.springframework.stereotype.Component;

@Component("socioBean")
public class SocioBean {

	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String dni;
	private String email;
	private String direccion;
	private String telefono;
	private String celular;
	private Integer distrito;
	private Integer provincia;
	private Integer departamento;
	
	private Integer tipoDocumento;
	private String nroDocumento;
	private Integer tipoMembresia;

	private Double latitud;
	private Double longitud;
	
	private Boolean admitido;
		
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public Integer getTipoMembresia() {
		return tipoMembresia;
	}

	public void setTipoMembresia(Integer tipoMembresia) {
		this.tipoMembresia = tipoMembresia;
	}

	public Boolean getAdmitido() {
		return admitido;
	}

	public void setAdmitido(Boolean admitido) {
		this.admitido = admitido;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Integer getDistrito() {
		return distrito;
	}

	public void setDistrito(Integer distrito) {
		this.distrito = distrito;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}
	
}

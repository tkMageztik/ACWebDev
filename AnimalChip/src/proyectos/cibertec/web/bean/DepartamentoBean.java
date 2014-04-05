package proyectos.cibertec.web.bean;

import org.springframework.stereotype.Component;

@Component("departamentoBean")
public class DepartamentoBean {
	
	private String id;
	private String nombre;
	private String latlng;
	
	public DepartamentoBean(){}
	
	public DepartamentoBean(String id, String nombre, String latlng)
	{
		this.id = id;
		this.nombre = nombre;
		this.latlng = latlng;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	
	

}

package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the foto database table.
 * 
 */
@Entity
@Table(name="foto")
public class Foto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String ruta;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="foto")
	private List<Empresa> empresas;

	//bi-directional many-to-one association to Mascota
	@OneToMany(mappedBy="foto")
	private List<Mascota> mascotas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="foto")
	private List<Usuario> usuarios;

    public Foto() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public List<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public List<Mascota> getMascotas() {
		return this.mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
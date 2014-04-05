package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@Table(name="rol")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idRol")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descRol;

	private byte flagActivo;

	//bi-directional many-to-one association to Detallerol
	@OneToMany(mappedBy="rol")
	private List<DetalleRol> detalleRols;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="rol")
	private List<Usuario> usuarios;


    public Rol() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescRol() {
		return descRol;
	}

	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}

	public byte getFlagActivo() {
		return flagActivo;
	}

	public void setFlagActivo(byte flagActivo) {
		this.flagActivo = flagActivo;
	}

	public List<DetalleRol> getDetalleRols() {
		return detalleRols;
	}

	public void setDetalleRols(List<DetalleRol> detalleRols) {
		this.detalleRols = detalleRols;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
			
}
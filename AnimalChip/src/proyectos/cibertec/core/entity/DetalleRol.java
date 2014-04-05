package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detallerol database table.
 * 
 */
@Entity
@Table(name="detallerol")
public class DetalleRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idDetalleRol")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descDetalleRol;

	private String descLargaDetalleRol;

	private int secuenciador;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idRol")
	private Rol rol;

    public DetalleRol() {
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSecuenciador() {
		return this.secuenciador;
	}

	public void setSecuenciador(int secuenciador) {
		this.secuenciador = secuenciador;
	}

	public String getDescDetalleRol() {
		return descDetalleRol;
	}

	public void setDescDetalleRol(String descDetalleRol) {
		this.descDetalleRol = descDetalleRol;
	}

	public String getDescLargaDetalleRol() {
		return descLargaDetalleRol;
	}

	public void setDescLargaDetalleRol(String descLargaDetalleRol) {
		this.descLargaDetalleRol = descLargaDetalleRol;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
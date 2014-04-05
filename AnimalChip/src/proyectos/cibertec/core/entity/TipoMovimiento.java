package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipomovimiento database table.
 * 
 */
@Entity
@Table(name="tipoMovimiento")
public class TipoMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idTipoMovimiento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String claseMovimiento;

	private String descripcion;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="tipoMovimiento")
	private List<Movimiento> movimientos;

    public TipoMovimiento() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClaseMovimiento() {
		return claseMovimiento;
	}

	public void setClaseMovimiento(String claseMovimiento) {
		this.claseMovimiento = claseMovimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}    
    
}
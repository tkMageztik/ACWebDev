package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the movimiento database table.
 * 
 */
@Entity
@Table(name="movimiento")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idmovimiento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	private Date fechaMovimiento;

	//bi-directional many-to-one association to Detallemovimiento
	@OneToMany(mappedBy="movimiento")
	private List<DetalleMovimiento> detalleMovimientos;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idEmpresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Tipomovimiento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTipoMovimiento")
	private TipoMovimiento tipoMovimiento;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

    public Movimiento() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public List<DetalleMovimiento> getDetalleMovimientos() {
		return detalleMovimientos;
	}

	public void setDetalleMovimientos(List<DetalleMovimiento> detalleMovimientos) {
		this.detalleMovimientos = detalleMovimientos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		
}
package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detallemovimiento database table.
 * 
 */
@Entity
@Table(name="detallemovimiento")
public class DetalleMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idDetalleMovimiento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Movimiento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idMovimiento")
	private Movimiento movimiento;

	//bi-directional many-to-one association to Rfid
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idRfid")
	private Rfid rfid;

    public DetalleMovimiento() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movimiento getMovimiento() {
		return this.movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	
	public Rfid getRfid() {
		return this.rfid;
	}

	public void setRfid(Rfid rfid) {
		this.rfid = rfid;
	}
	
}
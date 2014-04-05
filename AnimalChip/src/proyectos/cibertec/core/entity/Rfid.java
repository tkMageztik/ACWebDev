package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rfid database table.
 * 
 */
@Entity
@Table(name="rfid")
public class Rfid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idRfid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String codInterno;

    @Temporal( TemporalType.DATE)
	private Date fechaIngreso;

    private int anioIngreso;
    private int mesIngreso;
    
	private byte flagActivo;
	private byte flagEnUso;

	//bi-directional many-to-one association to Detallemovimiento
	@OneToMany(mappedBy="rfid")
	private List<DetalleMovimiento> detalleMovimientos;

	//bi-directional many-to-one association to Mascota
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idMascota")
	private Mascota mascota;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUsuarioInscrib")
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idEmpresa")
	private Empresa empresa;
		
	private int correlativo;
	
	private String razonSocialEmpresa;
	
	private String rucEmpresa;

	@Transient
	private boolean encontrado;
	
    public Rfid() {
    }
    
    @SuppressWarnings("unused")
	@PrePersist
    private void prePersist(){
    	this.flagActivo = 0x01;
    	this.flagEnUso= 0x00;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodInterno() {
		return codInterno;
	}

	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaIngreso);
		this.setAnioIngreso(cal.get(Calendar.YEAR));
		this.setMesIngreso(cal.get(Calendar.MONTH));
	}

	public byte getFlagActivo() {
		return flagActivo;
	}

	public void setFlagActivo(byte flagActivo) {
		this.flagActivo = flagActivo;
	}

	public List<DetalleMovimiento> getDetalleMovimientos() {
		return detalleMovimientos;
	}

	public void setDetalleMovimientos(List<DetalleMovimiento> detalleMovimientos) {
		this.detalleMovimientos = detalleMovimientos;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEncontrado() {
		return encontrado;
	}

	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}

	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}

	public String getRucEmpresa() {
		return rucEmpresa;
	}

	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}

	public int getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}

	public int getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(int anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public int getMesIngreso() {
		return mesIngreso;
	}

	public void setMesIngreso(int mesIngreso) {
		this.mesIngreso = mesIngreso;
	}

	public byte getFlagEnUso() {
		return flagEnUso;
	}

	public void setFlagEnUso(byte flagEnUso) {
		this.flagEnUso = flagEnUso;
	}	
    
}
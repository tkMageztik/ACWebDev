package proyectos.cibertec.core.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="mascotaextraviada")
public class MascotaExtraviada implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idMascotaExtraviada")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int idMascota;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
    @Temporal( TemporalType.TIMESTAMP)
	private Date fecha;

    private int anio;
    
    private int mes;
    
    private byte flagActivo;
    
    @SuppressWarnings("unused")
   	@PrePersist
    private void prePersist(){
    	this.flagActivo = 0x01;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;		
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		this.setAnio(cal.get(Calendar.YEAR));
		this.setMes(cal.get(Calendar.MONTH));
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public byte getFlagActivo() {
		return flagActivo;
	}

	public void setFlagActivo(byte flagActivo) {
		this.flagActivo = flagActivo;
	}
    
}

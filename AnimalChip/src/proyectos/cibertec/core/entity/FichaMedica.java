package proyectos.cibertec.core.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="fichamedica")
public class FichaMedica {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    @Temporal( TemporalType.DATE)
	private Date fechaVisita;
	
	private int anioVisita;
	
	private int mesVisita;
	
	private Usuario vetAtencion;
	
	@Column(length=500)
	private String observacion;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idMascota")
	private Mascota mascota;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaVisita);
		this.setAnioVisita(cal.get(Calendar.YEAR));
		this.setMesVisita(cal.get(Calendar.MONTH));
	}

	public int getAnioVisita() {
		return anioVisita;
	}

	public void setAnioVisita(int anioVisita) {
		this.anioVisita = anioVisita;
	}

	public int getMesVisita() {
		return mesVisita;
	}

	public void setMesVisita(int mesVisita) {
		this.mesVisita = mesVisita;
	}

	public Usuario getVetAtencion() {
		return vetAtencion;
	}

	public void setVetAtencion(Usuario vetAtencion) {
		this.vetAtencion = vetAtencion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	
}

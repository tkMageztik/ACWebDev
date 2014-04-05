package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;

/**
 * The persistent class for the mascota database table.
 * 
 */
@Entity
@Table(name="mascota")
public class Mascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idMascota")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String colorMascota;

	private String especieMascota;

    @Temporal( TemporalType.DATE)
	private Date fchNacMascota;
    
    private int anioNacMascota;
    
    private int mesNacMascota;

	private String generoMascota;

	private String nombreMascota;

	private String razaMascota;

	//bi-directional many-to-one association to Foto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFotoMascota")
	private Foto foto;

	//bi-directional many-to-one association to Propietario
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idPropietario")
	private Propietario propietario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idUsuarioInsc", nullable=true)
	private Usuario usuarioInsc;

	//bi-directional many-to-one association to Rfid
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idRfid", nullable=true)
	private Rfid rfid;

    public Mascota() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColorMascota() {
		return colorMascota;
	}

	public void setColorMascota(String colorMascota) {
		this.colorMascota = colorMascota;
	}

	public String getEspecieMascota() {
		return especieMascota;
	}

	public void setEspecieMascota(String especieMascota) {
		this.especieMascota = especieMascota;
	}

	public Date getFchNacMascota() {
		return fchNacMascota;
	}

	public void setFchNacMascota(Date fchNacMascota) {
		if(fchNacMascota!=null){
			this.fchNacMascota = fchNacMascota;
			Calendar cal = Calendar.getInstance();
			cal.setTime(fchNacMascota);
			this.anioNacMascota = cal.get(Calendar.YEAR);
			this.mesNacMascota = cal.get(Calendar.MONTH);
		}
	}

	public String getGeneroMascota() {
		return generoMascota;
	}

	public void setGeneroMascota(String generoMascota) {
		this.generoMascota = generoMascota;
	}

	public String getNombreMascota() {
		return nombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}

	public String getRazaMascota() {
		return razaMascota;
	}

	public void setRazaMascota(String razaMascota) {
		this.razaMascota = razaMascota;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public Rfid getRfid() {
		return rfid;
	}

	public void setRfid(Rfid rfid) {
		this.rfid = rfid;
	}

	public int getAnioNacMascota() {
		return anioNacMascota;
	}

	public void setAnioNacMascota(int anioNacMascota) {
		this.anioNacMascota = anioNacMascota;
	}

	public int getMesNacMascota() {
		return mesNacMascota;
	}

	public void setMesNacMascota(int mesNacMascota) {
		this.mesNacMascota = mesNacMascota;
	}

	public Usuario getUsuarioInsc() {
		return usuarioInsc;
	}

	public void setUsuarioInsc(Usuario usuarioInsc) {
		this.usuarioInsc = usuarioInsc;
	}
    
    
	
}
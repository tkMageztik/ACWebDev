package proyectos.cibertec.core.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findByLogin", query="SELECT u FROM Usuario u where u.usuario like :usuario")
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idUsuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellidoMat;

	private String apellidoPat;

	private String dni;

	private String emailVet;

	private byte flagActivo;

	private String nombres;

	private String password;

	private String usuario;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="usuario")
	private List<Movimiento> movimientos;

	//bi-directional many-to-one association to Rfid
	@OneToMany(mappedBy="usuario")
	private List<Rfid> rfids;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idEmpresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Foto
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idFotoUsuario", nullable = true)
	private Foto foto;

	//bi-directional many-to-one association to Rol
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idRol")
	private Rol rol;

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

	public String getApellidoMat() {
		return apellidoMat;
	}

	public void setApellidoMat(String apellidoMat) {
		this.apellidoMat = apellidoMat;
	}

	public String getApellidoPat() {
		return apellidoPat;
	}

	public void setApellidoPat(String apellidoPat) {
		this.apellidoPat = apellidoPat;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmailVet() {
		return emailVet;
	}

	public void setEmailVet(String emailVet) {
		this.emailVet = emailVet;
	}

	public byte getFlagActivo() {
		return flagActivo;
	}

	public void setFlagActivo(byte flagActivo) {
		this.flagActivo = flagActivo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public List<Rfid> getRfids() {
		return rfids;
	}

	public void setRfids(List<Rfid> rfids) {
		this.rfids = rfids;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
		
}
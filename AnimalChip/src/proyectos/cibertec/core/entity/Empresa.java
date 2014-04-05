package proyectos.cibertec.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@Table(name="empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idEmpresa")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte flagActivo;

	private int maximoUsuarios;

	private String ruc;
	
	private String razonSocial;
	
	private String distrito;
	
	private String provincia;
	
	private String departamento;	

	//bi-directional many-to-one association to Foto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFotoEmpresa")
	private Foto foto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTipoEntidad")
	private TipoEntidad tipoEntidad;
	
	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="empresa")
	private List<Movimiento> movimientos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="empresa")
	private List<Usuario> usuarios;

    @SuppressWarnings("unused")
	@PrePersist
    private void prePersist(){
    	this.maximoUsuarios = 5;
    	this.flagActivo = (byte)1;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getFlagActivo() {
		return flagActivo;
	}

	public void setFlagActivo(byte flagActivo) {
		this.flagActivo = flagActivo;
	}

	public int getMaximoUsuarios() {
		return maximoUsuarios;
	}

	public void setMaximoUsuarios(int maximoUsuarios) {
		this.maximoUsuarios = maximoUsuarios;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public TipoEntidad getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(TipoEntidad tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}	
	
	
}
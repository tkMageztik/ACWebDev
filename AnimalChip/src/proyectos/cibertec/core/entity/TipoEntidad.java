package proyectos.cibertec.core.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipoentidad")
public class TipoEntidad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String descripcion;
	
	private boolean activo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

    // This must return true for another Foo object with same key/id.
    public boolean equals(Object other) {
        return other instanceof TipoEntidad && (id >= 0) ? id == (((TipoEntidad) other).id) : (other == this);
    }

    // This must return the same hashcode for every Foo object with the same key.
    public int hashCode() {
        return id >=0 ? this.getClass().hashCode() + id : super.hashCode();
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}

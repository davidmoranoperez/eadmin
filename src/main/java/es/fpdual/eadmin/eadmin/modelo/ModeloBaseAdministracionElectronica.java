package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class ModeloBaseAdministracionElectronica {

	protected Integer codigo;
	protected String nombre;
	protected Date fechaCreacion;
	protected Date fechaModificacion;

	public ModeloBaseAdministracionElectronica(Integer codigo, String nombre, Date fechaCreacion, Date fechaModificacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	  
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		
		hashCodeBuilder.append(this.codigo);
		hashCodeBuilder.append(this.nombre);
		hashCodeBuilder.append(this.fechaCreacion);
		hashCodeBuilder.append(this.fechaModificacion);
		
		return hashCodeBuilder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if(obj instanceof ModeloBaseAdministracionElectronica) {
			final ModeloBaseAdministracionElectronica param = (ModeloBaseAdministracionElectronica)obj;
			EqualsBuilder equalsBuilder = new EqualsBuilder();

			equalsBuilder.append(this.codigo, param.codigo);
			equalsBuilder.append(this.nombre, param.nombre);
			equalsBuilder.append(this.fechaCreacion, param.fechaCreacion);
			equalsBuilder.append(this.fechaModificacion, param.fechaModificacion);
			
			return equalsBuilder.isEquals();

		}
		return false;
	}

}

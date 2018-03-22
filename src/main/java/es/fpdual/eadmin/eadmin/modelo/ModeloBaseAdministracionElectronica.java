package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

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
		return codigo.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Documento) {
			return codigo.equals(((Documento) obj).getCodigo());
		}
		return false;
	}

}

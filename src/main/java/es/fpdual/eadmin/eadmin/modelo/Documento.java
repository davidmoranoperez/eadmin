package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;

public class Documento extends ModeloBaseAdministracionElectronica{

	private Boolean publico;
	private EstadoDocumento estado;
	
	public Documento(Integer codigo, String nombre, Date fechaCreacion, Date fechaModificacion, Boolean publico, EstadoDocumento estado) {
		super(codigo, nombre, fechaCreacion, fechaModificacion);
		this.publico = publico;
		this.estado = estado;
	}

 
	public Boolean getPublico() {
		return publico;
	}

	public EstadoDocumento getEstado() {
		return estado;
	}


	@Override
	public String toString() {
		return "El código del documento es: " + getCodigo();
	}
	
	
	
	
}

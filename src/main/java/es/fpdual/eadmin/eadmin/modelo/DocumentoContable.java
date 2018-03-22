package es.fpdual.eadmin.eadmin.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class DocumentoContable extends Documento{
	
	private BigDecimal importe;
	private String dniInteresado;
	
	public DocumentoContable(Integer codigo, String nombre, Date fechaCreacion, Date fechaModificacion, Boolean publico, EstadoDocumento estado, BigDecimal importe, String dniInteresado) {
		super(codigo, nombre, fechaCreacion, fechaModificacion, publico, estado);
		this.dniInteresado = dniInteresado;
		this.importe = importe;
	} 

	public BigDecimal getImporte() {
		return importe;
	}

	public String getDniInteresado() {
		return dniInteresado;
	}

	@Override
	public String toString() {
		return "Documento_Contable importe=" + importe + ", dniInteresado=" + dniInteresado + " y código " + super.getCodigo();
	}

	
	
	
}

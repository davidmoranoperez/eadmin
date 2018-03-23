package es.fpdual.eadmin.eadmin.servicio;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface ServicioExpediente {

	public Expediente altaExpediente(Expediente expediente);

	public Expediente modificarExpediente(Expediente expediente);

	public void eliminarExpediente(Integer codigo);
	
	public Expediente asociarExpediente(Integer codigo, Documento documento);
	
	public Expediente desasociarExpediente(Integer codigo, Documento documento);
}

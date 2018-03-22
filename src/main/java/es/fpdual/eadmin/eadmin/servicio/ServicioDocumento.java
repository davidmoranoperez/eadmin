package es.fpdual.eadmin.eadmin.servicio;

import es.fpdual.eadmin.eadmin.modelo.Documento;

public interface ServicioDocumento {

	public Documento altaDocumento(Documento documento);

	public Documento modificarDocumento(Documento documento);

	public void eliminarDocumento(Integer codigo);
}

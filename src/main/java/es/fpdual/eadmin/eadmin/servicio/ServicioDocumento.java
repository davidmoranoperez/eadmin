package es.fpdual.eadmin.eadmin.servicio;

import es.fpdual.eadmin.eadmin.modelo.Documento;

public interface ServicioDocumento {

	public void altaDocumento(Documento documento);

	public void modificarDocumento(Documento documento);

	public void eliminarDocumento(Integer codigo);
}

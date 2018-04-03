package es.fpdual.eadmin.eadmin.servicio.ipl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.builder.DocumentoBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ServicioDocumento;

@Service
public class ServicioDocumentoIpl implements ServicioDocumento {

	RepositorioDocumento repositorioDocumento;

	@Autowired
	public ServicioDocumentoIpl(RepositorioDocumento repositorioDocumento) {
		this.repositorioDocumento = repositorioDocumento;
	}

	@Override
	public Documento altaDocumento(Documento documento) {
		final Documento documentoModificado = obtenerDocumentoConFechaCreacion(documento);
		repositorioDocumento.altaDocumento(documentoModificado);
		return documentoModificado;
	}

	@Override
	public Documento modificarDocumento(Documento documento) {

		final Documento documentoModificado = obtenerDocumentoConFechaModificacion(documento);
		repositorioDocumento.modificarDocumento(documentoModificado);
		return documentoModificado;
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		repositorioDocumento.eliminarDocumento(codigo);
	}

	@Override
	public Documento consultaDocumento(Integer codigo) {
		return repositorioDocumento.obtenerDocumentoPorCodigo(codigo);

	}

	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		return repositorioDocumento.obtenerTodosLosDocumentos();

	}

	protected Documento obtenerDocumentoConFechaModificacion(Documento documento) {
		return new DocumentoBuilder().clonar(documento).conFechaModificacion(obtenerFechaActual()).construir();
	}

	protected Documento obtenerDocumentoConFechaCreacion(Documento documento) {
		return new DocumentoBuilder().clonar(documento).conFechaCreacion(obtenerFechaActual()).construir();
	}

	protected Date obtenerFechaActual() {
		// TODO Auto-generated method stub
		return new Date();
	}

}

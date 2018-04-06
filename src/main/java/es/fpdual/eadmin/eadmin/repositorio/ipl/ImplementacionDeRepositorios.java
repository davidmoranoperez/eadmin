package es.fpdual.eadmin.eadmin.repositorio.ipl;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;

@Repository
public class ImplementacionDeRepositorios implements RepositorioDocumento{
	 
	private List <Documento> documentos = new ArrayList<>();
	private static final Logger logger = LoggerFactory.getLogger(ImplementacionDeRepositorios.class);
	
	PrintWriter pw = null;
	FileWriter file = null;
	String archivoAlta = "Alta.txt";
	String archivoLista = "Listado.txt";
	String archivoEliminar = "Eliminar.txt";
	String archivoModificado = "Modificar.txt";

	
	
	
	@Override
	public void altaDocumento(Documento documento) {
		logger.info("Comienza el método de altaDocumento");
		if(documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		altaDocumentoFichero(documento);
		

		documentos.add(documento);
		logger.info(toString()+documento.getCodigo()+" se ha creado correctamente");
		logger.info("Saliendo del método de altaDocumento");
	}

	
	
	
	private void altaDocumentoFichero(Documento documento) {
		try {
			file = new FileWriter(archivoAlta, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: "+ documento.getCodigo());
			pw.println("Nombre: "+ documento.getNombre());
			pw.println("Fecha: "+documento.getFechaCreacion());;
			pw.println("Estado: "+documento.getEstado());
			pw.println("Fecha modificacion: "+documento.getFechaModificacion());
			pw.println("Publico: "+documento.getPublico()+"\n");
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void modificarDocumento(Documento documento) {
		logger.info("Comienza el método de modificarDocumento");
		logger.info("El documento a modificar es: "+documento.getNombre()+", "+documento.getCodigo()+", "+"Fecha: "+documento.getFechaCreacion());
		if(!documentos.contains(documento)) {
			throw new IllegalArgumentException("El documento a modificar no existe");
		}
		
		modificarDocumentoFichero(documento);
		
		logger.info("El documento modificado se queda: "+documento.getNombre()+", "+documento.getCodigo()+", "+"Fecha: "+documento.getFechaCreacion());
		documentos.set(documentos.indexOf(documento), documento);
		logger.info("Saliendo del método de modificarDocumento");
	}

	
	
	private void modificarDocumentoFichero(Documento documento) {
		try {
			file = new FileWriter(archivoModificado, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: "+ documento.getCodigo());
			pw.println("Nombre: "+ documento.getNombre());
			pw.println("Fecha: "+documento.getFechaCreacion());;
			pw.println("Estado: "+documento.getEstado());
			pw.println("Fecha modificacion: "+documento.getFechaModificacion());
			pw.println("Publico: "+documento.getPublico()+"\n");
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarDocumento(Integer codigo) {
		logger.info("Comienza el método de eliminarDocumento");
		Optional <Documento> documentoEncontrado = documentos.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
		
		
		if(documentoEncontrado.isPresent()) {
			eliminarDocumentoFichero(documentoEncontrado);
			
			documentos.remove(documentoEncontrado.get());
			logger.info("El documento con codigo: "+documentoEncontrado.get().getCodigo()+", ha sido eliminado correctamente");
		}
		logger.info("Saliendo del método de eliminarDocumento");
	}
	
	private void eliminarDocumentoFichero(Optional<Documento> documentoEncontrado) {
		try {
			file = new FileWriter(archivoEliminar, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: "+ documentoEncontrado.get().getCodigo());
			pw.println("Nombre: "+ documentoEncontrado.get().getNombre());
			pw.println("Fecha: "+documentoEncontrado.get().getFechaCreacion());;
			pw.println("Estado: "+documentoEncontrado.get().getEstado());
			pw.println("Fecha modificacion: "+documentoEncontrado.get().getFechaModificacion());
			pw.println("Publico: "+documentoEncontrado.get().getPublico()+"\n");
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Documento> obtenerTodosLosDocumentos() {
		logger.info("Comienza el método de eliminar documento");
		for (Documento doc : documentos) {
			logger.info("***********");
			logger.info("Documento: "+ doc.getCodigo());
			logger.info("Nombre: "+ doc.getNombre());
			logger.info("Fecha: "+doc.getFechaCreacion());
			
			logger.info("****************");
		}
		logger.info("Saliendo del método de obtenerTodosLosDocumento");
		return getDocumentos();
	}
	
	@Override
	public Documento obtenerDocumentoPorCodigo(Integer codigo) {
		logger.info("Entrando en el método de obtenerDocumentoPorCodigo");
		Optional<Documento> documentoEncontrado = documentos.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
		if(documentoEncontrado.isPresent()) {
			logger.info("Saliendo del método de obtenerDocumentoPorCodigo");
			return documentoEncontrado.get();
		}
		logger.info("Saliendo del método de obtenerDocumentoPorCodigo");
		return null;
	}
	
	public void escribeLista() {
		logger.info("Comienza la escritura en la lista");
		try {
			file = new FileWriter(archivoLista, true);
			pw = new PrintWriter(file);

			for (Documento doc : documentos) {
				pw.println("*********************");
				pw.println("Documento: "+ doc.getCodigo());
				pw.println("Nombre: "+ doc.getNombre());
				pw.println("Fecha: "+doc.getFechaCreacion());;
				pw.println("Estado: "+doc.getEstado());
				pw.println("Fecha modificacion: "+doc.getFechaModificacion());
				pw.println("Publico: "+doc.getPublico());

			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Saliendo del método de escribir lista");
	}
	
	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		return documento.getCodigo().equals(codigo);
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}
	
	public String toString() {
		return "El documento con codigo ";
	}
	

}

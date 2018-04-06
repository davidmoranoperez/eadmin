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
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Repository
public class ImplementacionDeRepositoriosExpedientes implements RepositorioExpediente{

	private List <Expediente> expedientes = new ArrayList<>();
	private static final Logger logger = LoggerFactory.getLogger(ImplementacionDeRepositorios.class);

	PrintWriter pw = null;
	FileWriter file = null;
	String archivoAltaExpediente = "AltaExpediente.txt";
	String archivoListaExpediente = "ListadoExpediente.txt";
	String archivoEliminarExpediente = "EliminarExpediente.txt";
	String archivoModificadoExpediente = "ModificarExpediente.txt";
	

	@Override
	public void altaExpediente(Expediente expediente) {
		if(expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El expediente ya existe");
		}
		
		altaDocumentoExpediente(expediente);
		expedientes.add(expediente);
	}
	
	private void altaDocumentoExpediente(Expediente expediente) {
		try {
			file = new FileWriter(archivoAltaExpediente, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: "+ expediente.getNombre());
			pw.println("Nombre: "+ expediente.getCodigo());
			pw.println("Fecha: "+expediente.getEstado());;
			pw.println("Estado: "+expediente.getFechaArchivado());
			pw.println("Fecha modificacion: "+expediente.getFechaCreacion());
			pw.println("Publico: "+expediente.getFechaModificacion());
			pw.println("Publico: "+expediente.getListaDocumentos());
			pw.println("Publico: "+expediente.getPublico());

			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modificarExpediente(Expediente expediente) {
		
		if(!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El documento a modificar no existe");
		}
		
		modificarExpedienteFichero(expediente);
		expedientes.set(expedientes.indexOf(expediente), expediente);
		 
	}
	
	private void modificarExpedienteFichero(Expediente expediente) {
		try {
			file = new FileWriter(archivoModificadoExpediente, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: "+ expediente.getNombre());
			pw.println("Nombre: "+ expediente.getCodigo());
			pw.println("Fecha: "+expediente.getEstado());;
			pw.println("Estado: "+expediente.getFechaArchivado());
			pw.println("Fecha modificacion: "+expediente.getFechaCreacion());
			pw.println("Publico: "+expediente.getFechaModificacion());
			pw.println("Publico: "+expediente.getListaDocumentos());
			pw.println("Publico: "+expediente.getPublico());
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarExpediente(Integer codigo) {
		
		Optional <Expediente> expedienteEncontrado = expedientes.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
		
		
		if(expedienteEncontrado.isPresent()) {
			eliminarExpedienteFichero(expedienteEncontrado);
			expedientes.remove(expedienteEncontrado.get());
		}
	}
	
	private void eliminarExpedienteFichero(Optional<Expediente> expedienteEncontrado) {
		try {
			file = new FileWriter(archivoEliminarExpediente, true);
			pw = new PrintWriter(file);
			pw.println("*********************");
			pw.println("Documento: "+ expedienteEncontrado.get().getCodigo());
			pw.println("Nombre: "+ expedienteEncontrado.get().getNombre());
			pw.println("Fecha: "+expedienteEncontrado.get().getFechaCreacion());;
			pw.println("Estado: "+expedienteEncontrado.get().getEstado());
			pw.println("Estado: "+expedienteEncontrado.get().getFechaArchivado());
			pw.println("Fecha modificacion: "+expedienteEncontrado.get().getFechaModificacion());
			pw.println("Publico: "+expedienteEncontrado.get().getPublico()+"\n");
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Expediente asociarExpediente(Integer codigoExpediente, Documento documento) {
		Optional <Expediente> expedienteEncontrado = expedientes.stream().filter(d -> d.getCodigo().equals(codigoExpediente)).findFirst();
		if(expedienteEncontrado.isPresent()) {
			expedienteEncontrado.get().getListaDocumentos().add(documento);
			return expedienteEncontrado.get();
		}
		return null;
	}

	@Override
	public Expediente desasociarExpediente(Integer codigoExpediente, Documento documento) {
		Optional <Expediente> expedienteEncontrado = expedientes.stream().filter(d -> d.getCodigo().equals(codigoExpediente)).findFirst();
		if(expedienteEncontrado.isPresent()) {
			expedienteEncontrado.get().getListaDocumentos().remove(documento);
			return expedienteEncontrado.get();
		}
		return null;
		
	}
	
	public void escribeListaExpediente() {
		logger.info("Comienza la escritura en la lista");
		try {
			file = new FileWriter(archivoListaExpediente, true);
			pw = new PrintWriter(file);

			for (Expediente exp : expedientes) {
				pw.println("*********************");
				pw.println("Documento: "+ exp.getNombre());
				pw.println("Nombre: "+ exp.getCodigo());
				pw.println("Fecha: "+exp.getEstado());;
				pw.println("Estado: "+exp.getFechaArchivado());
				pw.println("Fecha creación: "+exp.getFechaCreacion());
				pw.println("Fecha modificación: "+exp.getFechaModificacion());
				pw.println("Lista de documentos: "+exp.getListaDocumentos());
				pw.println("Publico: "+exp.getPublico());


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

	public List<Expediente> getExpedientes() {
		return expedientes;
	}
	
}

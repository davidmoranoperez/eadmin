package es.fpdual.eadmin.eadmin.repositorio.ipl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;

@Repository
public class ImplementacionDeRepositoriosExpedientes implements RepositorioExpediente{

	private List <Expediente> expedientes = new ArrayList<>();

	@Override
	public void altaExpediente(Expediente expediente) {
		if(expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El documento ya existe");
		}
		expedientes.add(expediente);
	}

	@Override
	public void modificarExpediente(Expediente expediente) {
		
		if(!expedientes.contains(expediente)) {
			throw new IllegalArgumentException("El documento a modificar no existe");
		}
		expedientes.set(expedientes.indexOf(expediente), expediente);
		 
	}

	@Override
	public void eliminarExpediente(Integer codigo) {
		
		Optional <Expediente> documentoEncontrado = expedientes.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst();
		
		
		if(documentoEncontrado.isPresent()) {
			expedientes.remove(documentoEncontrado.get());
		}
	}
	
	protected boolean tieneIgualCodigo(Documento documento, Integer codigo) {
		return documento.getCodigo().equals(codigo);
	}

	public List<Expediente> getExpedientes() {
		return expedientes;
	}
	
}

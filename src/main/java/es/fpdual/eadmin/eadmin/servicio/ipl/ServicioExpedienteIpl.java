package es.fpdual.eadmin.eadmin.servicio.ipl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.modelo.builder.ExpedienteBuilder;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioExpediente;
import es.fpdual.eadmin.eadmin.repositorio.ipl.ImplementacionDeRepositoriosExpedientes;
import es.fpdual.eadmin.eadmin.servicio.ServicioExpediente;

@Service
public class ServicioExpedienteIpl implements ServicioExpediente{

	RepositorioExpediente repositorioExpediente;

	@Autowired
	public ServicioExpedienteIpl(RepositorioExpediente repositorioExpediente) {
		this.repositorioExpediente = repositorioExpediente;
	}

	@Override
	public Expediente altaExpediente(Expediente expediente) {
		repositorioExpediente.altaExpediente(expediente);
		return expediente;
	}

	@Override
	public Expediente modificarExpediente(Expediente expediente) {

		final Expediente expedienteModificado = obtenerExpedienteConFechaCorrecta(expediente);

		repositorioExpediente.modificarExpediente(expedienteModificado);
		return expedienteModificado;
	}

	@Override
	public void eliminarExpediente(Integer codigo) {
		repositorioExpediente.eliminarExpediente(codigo);
	}


	@Override
	public void asociarExpediente(Integer codigoExpediente, Documento documento) {
		
	}

	@Override
	public void desasociarExpediente(Integer codigo, Documento documento) {
		// TODO Auto-generated method stub
		
	}
	
	protected Expediente obtenerExpedienteConFechaCorrecta(Expediente expediente) {
		
		return new ExpedienteBuilder().clonar(expediente).conFechaCreacion(obtenerFechaActual()).construir();
		
	}

	protected Date obtenerFechaActual() {
		// TODO Auto-generated method stub
		return new Date();
	}
}

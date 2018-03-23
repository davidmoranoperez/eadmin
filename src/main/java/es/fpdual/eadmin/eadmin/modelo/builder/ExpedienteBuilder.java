package es.fpdual.eadmin.eadmin.modelo.builder;

import java.util.Date;
import java.util.List;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;
import es.fpdual.eadmin.eadmin.modelo.Expediente;

public class ExpedienteBuilder {

	protected Integer codigo;
	protected String nombre;
	protected Date fechaCreacion;
	protected Date fechaModificacion;
	protected Date fechaArchivado;
	protected Boolean publico;
	protected EstadoExpediente estado;
	protected List <Documento> listaDocumentos;	
	
	public Expediente construir() {
		return new Expediente(codigo, nombre, fechaCreacion, fechaModificacion, fechaArchivado, publico, estado, listaDocumentos);
	}
	
	public ExpedienteBuilder conFechaArchivado(Date fechaArchivado) {
		this.fechaArchivado = fechaArchivado;
		return this;
	}
	
	public ExpedienteBuilder conNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public ExpedienteBuilder conFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
		return this;
	}
	
	public ExpedienteBuilder conFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
		return this;
	}
	
	public ExpedienteBuilder conPublico(Boolean publico) {
		this.publico = publico;
		return this;
	}
	public ExpedienteBuilder conEstado(EstadoExpediente estado) {
		this.estado = estado;
		return this;
	}
	
	public ExpedienteBuilder conCodigo(Integer codigo) {
		this.codigo = codigo;
		return this;
	}
	
	public ExpedienteBuilder conListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
		return this;
	}

	
	public ExpedienteBuilder clonar(Expediente expediente) {
		this.codigo = expediente.getCodigo();
		this.nombre = expediente.getNombre();
		this.fechaCreacion = expediente.getFechaCreacion();
		this.fechaModificacion = expediente.getFechaModificacion();
		this.publico = expediente.getPublico();
		this.estado = expediente.getEstado();
		this.fechaArchivado = expediente.getFechaArchivado();
		this.listaDocumentos = expediente.getListaDocumentos();
		
		return this;
	}
}

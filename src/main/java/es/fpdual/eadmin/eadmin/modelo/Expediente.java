package es.fpdual.eadmin.eadmin.modelo;

import java.util.Date;
import java.util.List;

public class Expediente {

	private Integer codigo;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaArchivado;
	private Boolean publico;
	private EstadoExpediente estado;
	private List <Documento> listaDocumentos;
	
	
	public Expediente(Integer codigo, String nombre, Date fechaCreacion, Date fechaArchivado, Boolean publico,
			EstadoExpediente estado, List<Documento> listaDocumentos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaArchivado = fechaArchivado;
		this.publico = publico;
		this.estado = estado;
		this.listaDocumentos = listaDocumentos;
	}
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public Date getFechaArchivado() {
		return fechaArchivado;
	}
	public Boolean getPublico() {
		return publico;
	}
	public EstadoExpediente getEstado() {
		return estado;
	}
	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}
	
	@Override
	public int hashCode() {
			return codigo.hashCode() 
					+ nombre.hashCode()
					+ fechaCreacion.hashCode()
					+ fechaArchivado.hashCode()
					+ publico.hashCode()
					+ estado.hashCode()
					;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Expediente) {
			return codigo.equals(((Expediente) obj).getCodigo()) 
					&& nombre.equals(((Expediente) obj).getNombre())
					&& publico.equals(((Expediente) obj).getPublico())
					&& fechaCreacion.equals(((Expediente) obj).getFechaCreacion())
					&& estado.equals(((Expediente) obj).getEstado())
					&& fechaArchivado.equals(((Expediente) obj).getFechaArchivado())
					;
		}
		return false;
	}

	
	
}

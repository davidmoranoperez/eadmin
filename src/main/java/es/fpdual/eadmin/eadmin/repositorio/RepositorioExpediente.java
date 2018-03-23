package es.fpdual.eadmin.eadmin.repositorio;

import es.fpdual.eadmin.eadmin.modelo.Expediente;

public interface RepositorioExpediente {

	public abstract void altaExpediente (Expediente expediente);

	public abstract void modificarExpediente (Expediente expediente);
	
	public abstract void eliminarExpediente (Integer codigo);
}

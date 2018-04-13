package es.fpdual.eadmin.eadmin.modelo;

import org.assertj.core.util.Arrays;

public enum EstadoDocumento {

	ACTIVO(1), APROBADO(2), ELIMINADO(3);
	
	private int codigo;
	
	private EstadoDocumento(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public static EstadoDocumento obtenerPorCodigo(Integer codigo) {
		switch(codigo) {
		case 1:
			return EstadoDocumento.ACTIVO;
		case 2:
			return EstadoDocumento.APROBADO;
		case 3: 
			return EstadoDocumento.ELIMINADO;
		}
		return null;
	}
}

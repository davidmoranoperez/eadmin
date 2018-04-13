package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.util.AbstractoModeloBeanTest;

public class DocumentoTest extends AbstractoModeloBeanTest<Documento> {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final Date FECHAMODIFICACION = new Date();
	private static final Integer CODIGO = 1;

	@Override
	public void before() {
		this.entityA1 =  new Documento(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION, FECHAMODIFICACION, DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO);
		this.entityA2 =  new Documento(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION, FECHAMODIFICACION, DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO);
		this.entityB =  new Documento(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION, FECHAMODIFICACION, DOCUMENTO_PUBLICO,EstadoDocumento.ELIMINADO);
		
	}

	@Override
	public void deberiaInvocarLosMetodosGetModelo() {
		assertEquals(DOCUMENTO_PUBLICO,entityA1.getPublico());
		assertEquals(CODIGO,entityA1.getCodigo(),0.0);
		assertEquals(EstadoDocumento.ACTIVO,entityA1.getEstado());
		assertEquals(FECHA_CREACION,entityA1.getFechaCreacion());
		assertEquals(NOMBRE_DOCUMENTO,entityA1.getNombre());
		
	}
	

	
}

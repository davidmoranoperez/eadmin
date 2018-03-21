package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DocumentoTest {

	private static final Date FECHA_CREACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO = 1;
	private Documento documento;

	@Before
	public void inicializar() {
	documento = new Documento(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION,DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO);
	}
	
	@Test
	public void testComprobarGetter() {	
		Documento prueba = new Documento(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION,DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO);
		assertEquals(DOCUMENTO_PUBLICO,prueba.getPublico());
		assertEquals(CODIGO,prueba.getCodigo(),0.0);
		assertEquals(EstadoDocumento.ACTIVO,prueba.getEstado());
		assertEquals(FECHA_CREACION,prueba.getFechaCreacion());
		assertEquals(NOMBRE_DOCUMENTO,prueba.getNombre());
	}
	
	
	@Test
	public void deberiaDevolverTrueSiTienenIgualCodigo() {
		final Documento documento2 = new Documento(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION,DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO);
		final Boolean resultado = documento2.equals(documento);
		assertTrue(resultado);
	}
	
	@Test
	public void deberiaDevolverFalseSiTienenDistintoCodigo() {
		final Documento documento2 = new Documento(5,NOMBRE_DOCUMENTO,FECHA_CREACION,DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO);
		final Boolean resultado = documento2.equals(documento);
		assertFalse(resultado);
	}

	
	@Test
	public void deberiaDevolverFalseSiNoEsUnDocumento() {
		final Boolean resultado = documento.equals(new Date());
		assertFalse(resultado);
	}
	
	@Test
	public void deberiaDevolverHasCodeDelCodigo() {
		final int resultado = documento.hashCode();
		assertEquals(CODIGO.hashCode(),resultado);
	}
	
	@Test
	public void deberiaDevolverNoNulo() {
		assertNotNull(documento.toString());	
		}
	
}

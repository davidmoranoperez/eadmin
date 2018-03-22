package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DocumentoPropuestaTest {

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHAMODIFICACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final Integer CODIGOPROPUESTA = 56;
	private static final Integer EJERCICIO = 30;
	private static final String GRUPOPOLITICO = "PP";
	private DocumentoPropuesta documentoPropuesta;
 
	@Before
	public void inicializar() {
	documentoPropuesta = new DocumentoPropuesta(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION, FECHAMODIFICACION,DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO, CODIGOPROPUESTA, EJERCICIO, GRUPOPOLITICO);
	}

	
	@Test
	public void testComprobarGetter() {	
		assertEquals(CODIGOPROPUESTA, documentoPropuesta.getCodigoPropuesta());
		assertEquals(EJERCICIO, documentoPropuesta.getEjercicio());
		assertEquals(GRUPOPOLITICO, documentoPropuesta.getGrupoPolitico());
	}
}

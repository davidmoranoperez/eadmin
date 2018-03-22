package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DocumentoRegistroTest {

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHAMODIFICACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final String CODIGOREGISTRO = "452";
	private static final String DNIINTERESADO = "15456786G";
	private DocumentoRegistro documentoRegistro;

	@Before
	public void inicializar() {
	documentoRegistro = new DocumentoRegistro(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION,FECHAMODIFICACION,DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO, DNIINTERESADO,CODIGOREGISTRO);
	}

	@Test
	public void testComprobarGetter() {	
		assertEquals(CODIGOREGISTRO, documentoRegistro.getCodigoRegistro());
		assertEquals(DNIINTERESADO, documentoRegistro.getDniInteresado());
	}
}

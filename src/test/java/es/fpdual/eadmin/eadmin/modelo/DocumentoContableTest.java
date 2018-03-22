package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DocumentoContableTest {

	private static final Date FECHA_CREACION = new Date();
	private static final Date FECHAMODIFICACION = new Date();
	private static final String NOMBRE_DOCUMENTO = "nombre";
	private static final Boolean DOCUMENTO_PUBLICO = true;
	private static final Integer CODIGO = 1;
	private static final BigDecimal IMPORTE = new BigDecimal(50.55);
	private static final String DNIINTERESADO = "15456786G";
	private DocumentoContable documentoContable;
 
	@Before
	public void inicializar() {
	documentoContable = new DocumentoContable(CODIGO,NOMBRE_DOCUMENTO,FECHA_CREACION, FECHAMODIFICACION,DOCUMENTO_PUBLICO,EstadoDocumento.ACTIVO, IMPORTE, DNIINTERESADO);
	}
	
	@Test
	public void testComprobarGetter() {	
		assertEquals(IMPORTE, documentoContable.getImporte());
		assertEquals(DNIINTERESADO, documentoContable.getDniInteresado());
	}

}

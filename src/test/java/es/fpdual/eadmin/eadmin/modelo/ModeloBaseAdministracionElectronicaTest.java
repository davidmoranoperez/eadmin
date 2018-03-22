package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModeloBaseAdministracionElectronicaTest {

	private static final Integer CODIGO = 56;
	private static final String NOMBRE = "prueba";
    private static final Date FECHACREACION = new Date();
    private static final Date FECHAMODIFICACION = new Date();
    private ModeloBaseAdministracionElectronicaFake documento;
	
 
	@Before
	public void crearObjeto() {
		documento = new ModeloBaseAdministracionElectronicaFake(CODIGO, NOMBRE, FECHACREACION, FECHAMODIFICACION);
	}

	@Test
	public void comprobarGetters() {
		assertEquals(CODIGO, documento.getCodigo());
		assertEquals(NOMBRE, documento.getNombre());
	}

}

class ModeloBaseAdministracionElectronicaFake extends ModeloBaseAdministracionElectronica {

	public ModeloBaseAdministracionElectronicaFake(Integer codigo, String nombre, Date fechaCreacion, Date fechaModificacion) {
		super(codigo, nombre, fechaCreacion, fechaModificacion);
	}

}

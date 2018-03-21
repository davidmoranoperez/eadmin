package es.fpdual.eadmin.eadmin.modelo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class ModeloBaseAdministracionElectronicaTest {

	private static final Integer CODIGO = 56;
	private static final String NOMBRE = "prueba";
    private static final Date FECHACREACION = new Date();
    private ModeloBaseAdministracionElectronicaFake documento;
	

	@BeforeClass
	public void crearObjeto() {
		documento = new ModeloBaseAdministracionElectronicaFake(CODIGO, NOMBRE, FECHACREACION);
	}
	
	@Test
	public void comprobarGetters() {
		assertEquals(CODIGO, documento.getCodigo());
		assertEquals(NOMBRE, documento.getNombre());
	}

}

class ModeloBaseAdministracionElectronicaFake extends ModeloBaseAdministracionElectronica {

	public ModeloBaseAdministracionElectronicaFake(Integer codigo, String nombre, Date fechaCreacion) {
		super(codigo, nombre, fechaCreacion);
	}

}

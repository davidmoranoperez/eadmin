package es.fpdual.eadmin.eadmin.repositorio.ipl;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;

public class ImplementacionDeRepositoriosTest {

	private static final Integer CODIGO = 5; 
	private static final String NOMBRE= "prueba"; 
	private static final Date FECHACREACION = new Date(); 
	private static final Date FECHAMODIFICACION = new Date();
	private static final Boolean PUBLICO = true;
	private static final EstadoDocumento ESTADO = EstadoDocumento.ACTIVO;
	private Documento documento1 = new Documento(CODIGO, NOMBRE, FECHACREACION,FECHAMODIFICACION, PUBLICO, ESTADO);
	private ImplementacionDeRepositorios repositorioDocumento;
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUpBeforeClass() throws Exception {
		repositorioDocumento = new ImplementacionDeRepositorios();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAltaDocumento() {
		this.repositorioDocumento.getDocumentos().add(documento1);
		assertFalse(repositorioDocumento.getDocumentos().isEmpty());
	}
 
	@Test
	public void testModificarDocumento() {
		repositorioDocumento.getDocumentos().add(documento1);
		Documento documento2 = new Documento(CODIGO, "Prueba", FECHACREACION,FECHAMODIFICACION, PUBLICO, ESTADO);
		repositorioDocumento.modificarDocumento(documento2);
		assertEquals(documento1, documento2);
		
	}

	@Test
	public void testEliminarDocumento() {
		this.repositorioDocumento.getDocumentos().add(documento1);
		this.repositorioDocumento.eliminarDocumento(documento1.getCodigo());
		assertTrue(repositorioDocumento.getDocumentos().isEmpty());
	}

	@Test
	public void testEliminarDocumentoSiNoExiste() {
		this.repositorioDocumento.eliminarDocumento(documento1.getCodigo());
		assertTrue(repositorioDocumento.getDocumentos().isEmpty());
	}
	
	@Test
	public void testObtenerTodosLosDocumentos() {
		assertTrue(this.repositorioDocumento.obtenerTodosLosDocumentos().isEmpty());
	}
	
	@Test
	public void testObtenerDocumentoPorCodigo() {
		this.repositorioDocumento.getDocumentos().add(documento1);
		assertEquals(this.repositorioDocumento.obtenerDocumentoPorCodigo(CODIGO),documento1);
		assertEquals(this.repositorioDocumento.obtenerDocumentoPorCodigo(4), null);
	}

}

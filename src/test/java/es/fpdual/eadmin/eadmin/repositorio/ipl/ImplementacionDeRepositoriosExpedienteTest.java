package es.fpdual.eadmin.eadmin.repositorio.ipl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.modelo.EstadoDocumento;
import es.fpdual.eadmin.eadmin.modelo.Expediente;
import es.fpdual.eadmin.eadmin.modelo.EstadoExpediente;

public class ImplementacionDeRepositoriosExpedienteTest {

	private static final Integer CODIGO = 5; 
	private static final String NOMBRE= "prueba"; 
	private static final Date FECHACREACION = new Date(); 
	private static final Date FECHAMODIFICACION = new Date();
	private static final Date FECHAARCHIVADO = new Date();
	private static final Boolean PUBLICO = true;
	private static final EstadoExpediente ESTADOEXPEDIENTE = EstadoExpediente.INICIADO;
	private static final EstadoDocumento ESTADODOCUMENTO = EstadoDocumento.ACTIVO;
	private static final List<Documento> LISTADOCUMENTOS = new ArrayList<Documento>();
	private Expediente expediente = new Expediente(CODIGO, NOMBRE, FECHACREACION, FECHAMODIFICACION, FECHAARCHIVADO, PUBLICO, ESTADOEXPEDIENTE, LISTADOCUMENTOS);
	private Documento documento = new Documento(CODIGO, NOMBRE, FECHACREACION,FECHAMODIFICACION, PUBLICO, ESTADODOCUMENTO);
	private ImplementacionDeRepositoriosExpedientes repositorioExpediente;
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUpBeforeClass() throws Exception {
		repositorioExpediente = new ImplementacionDeRepositoriosExpedientes();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAltaExpediente() {
		repositorioExpediente.altaExpediente(expediente);
		assertFalse(repositorioExpediente.getExpedientes().isEmpty());
	}
 
	@Test
	public void testModificarExpediente() {
		repositorioExpediente.getExpedientes().add(expediente);
		Expediente expediente2 = new Expediente(CODIGO, NOMBRE, FECHACREACION, new Date(21/05/2010), FECHAARCHIVADO, PUBLICO, ESTADOEXPEDIENTE, LISTADOCUMENTOS);
		repositorioExpediente.modificarExpediente(expediente2);
		assertEquals(expediente, expediente2);
		
	}

	@Test
	public void testEliminarExpediente() {
		this.repositorioExpediente.getExpedientes().add(expediente);
		this.repositorioExpediente.eliminarExpediente(expediente.getCodigo());
		assertTrue(repositorioExpediente.getExpedientes().isEmpty());
	}

	@Test
	public void testEliminarExpedienteSiNoExiste() {
		this.repositorioExpediente.eliminarExpediente(expediente.getCodigo());
		assertTrue(repositorioExpediente.getExpedientes().isEmpty());
	}
	
	@Test
	public void testAsociarExpediente() {
		
		Expediente prueba = this.repositorioExpediente.asociarExpediente(expediente.getCodigo(), documento);
		assertEquals(1, prueba.getListaDocumentos().size());
	}
	
	@Test
	public void testDesasociarExpediente() {
		this.expediente.getListaDocumentos().add(documento);
		this.repositorioExpediente.desasociarExpediente(expediente.getCodigo(), documento);
		assertTrue(expediente.getListaDocumentos().isEmpty());
	}
}

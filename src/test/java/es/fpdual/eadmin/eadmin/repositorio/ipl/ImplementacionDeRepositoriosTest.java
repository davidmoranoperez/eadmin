package es.fpdual.eadmin.eadmin.repositorio.ipl;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import es.fpdual.eadmin.eadmin.mapper.DocumentoMapper;
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
	private DocumentoMapper mapper;
 
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUpBeforeClass() throws Exception {
		this.mapper = mock(DocumentoMapper.class);
		repositorioDocumento = new ImplementacionDeRepositorios(this.mapper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAltaDocumento() {
		this.repositorioDocumento.altaDocumento(documento1);
		verify(this.mapper).insertarDocumento(this.documento1);
	}
 
	@Test
	public void testModificarDocumento() {
		when(this.mapper.modificarDocumento(this.documento1)).thenReturn(1);
		repositorioDocumento.modificarDocumento(documento1);
		verify(this.mapper).modificarDocumento(this.documento1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLanzarExcepcionSiDocumentoNoExiste() {
		when(this.mapper.modificarDocumento(this.documento1)).thenReturn(0);
		
		this.repositorioDocumento.modificarDocumento(documento1);
	}
	

	@Test
	public void testEliminarDocumento() {
		this.repositorioDocumento.eliminarDocumento(this.documento1.getCodigo());
		verify(this.mapper).eliminarDocumento(this.documento1.getCodigo());
	}
	
	@Test
	public void testObtenerTodosLosDocumentos() {
		final List<Documento> todosLosDocumentos = Arrays.asList(this.documento1);
		
		when(this.mapper.seleccionarTodosLosDocumentos()).thenReturn(todosLosDocumentos);
		
		List<Documento> resultado = repositorioDocumento.obtenerTodosLosDocumentos();
		
		assertThat(resultado, hasSize(1));
		assertThat(resultado, hasItem(this.documento1));
	}
	
	@Test
	public void testObtenerDocumentoPorCodigo() {
		when(mapper.consultarDocumento(this.documento1.getCodigo())).thenReturn(this.documento1);
		final Documento resultado = this.repositorioDocumento.obtenerDocumentoPorCodigo(this.documento1.getCodigo());
		assertThat(resultado, is(this.documento1));
	}

}

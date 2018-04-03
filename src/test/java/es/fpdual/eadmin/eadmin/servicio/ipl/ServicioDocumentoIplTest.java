package es.fpdual.eadmin.eadmin.servicio.ipl;

import org.junit.*;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ipl.ServicioDocumentoIpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ServicioDocumentoIplTest {

	private static final Integer CODIGO = 5;

	private ServicioDocumentoIpl servicioDocumento;
	private final RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);
	private Documento documento = mock(Documento.class);
	private List<Documento> lista = new ArrayList<Documento>();

	@Before
	public void inicializarEnCadaTest() {
		this.servicioDocumento = spy(new ServicioDocumentoIpl(repositorioDocumento));

	}

	@Test
	public void deberiaAlmacenarUnDocumento() {
		final Documento documentoModificado = mock(Documento.class);
		
		doReturn(documentoModificado).when(servicioDocumento).obtenerDocumentoConFechaCreacion(documento);
		
		final Documento resultado = this.servicioDocumento.altaDocumento(documento);
		
		verify(this.repositorioDocumento).altaDocumento(documentoModificado);
		assertSame(resultado, documentoModificado);
	}

	@Test
	public void deberiaModificarUnDocumento() {

		final Documento documentoModificado = mock(Documento.class);
		doReturn(documentoModificado).when(servicioDocumento).obtenerDocumentoConFechaModificacion(documento);
		final Documento resultado = this.servicioDocumento.modificarDocumento(documento);
		verify(this.repositorioDocumento).modificarDocumento(documentoModificado);
		assertSame(resultado, documentoModificado);
	}

	@Test
	public void deberiaEliminarUnDocumento() {
		when(documento.getCodigo()).thenReturn(CODIGO);
		this.servicioDocumento.eliminarDocumento(documento.getCodigo());
		verify(this.repositorioDocumento).eliminarDocumento(CODIGO);
	}

	@Test
	public void deberiaConsultarDocumento() {
		when(repositorioDocumento.obtenerDocumentoPorCodigo(CODIGO)).thenReturn(documento);
		final Documento resultado = this.servicioDocumento.consultaDocumento(CODIGO);
		assertEquals(resultado, documento);
	}

	@Test
	public void deberiaDevolverTodosLosDocumentos() {
		when(repositorioDocumento.obtenerTodosLosDocumentos()).thenReturn(lista);
		final List<Documento> resultado = this.servicioDocumento.obtenerTodosLosDocumentos();
		assertSame(repositorioDocumento.obtenerTodosLosDocumentos(), resultado);
	}
	
}

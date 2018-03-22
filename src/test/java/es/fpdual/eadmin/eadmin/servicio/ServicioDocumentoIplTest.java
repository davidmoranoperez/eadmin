package es.fpdual.eadmin.eadmin.servicio;

import org.junit.*;

import es.fpdual.eadmin.eadmin.modelo.Documento;
import es.fpdual.eadmin.eadmin.repositorio.RepositorioDocumento;
import es.fpdual.eadmin.eadmin.servicio.ipl.ServicioDocumentoIpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;

public class ServicioDocumentoIplTest {

	private static final Integer CODIGO = 5;

	private ServicioDocumento servicioDocumento;
	private final RepositorioDocumento repositorioDocumento = mock(RepositorioDocumento.class);
	private Documento documento = mock(Documento.class);

	@Before
	public void inicializarEnCadaTest() {
		this.servicioDocumento = new ServicioDocumentoIpl(repositorioDocumento);

	}

	@Test
	public void deberiaAlmacenarUnDocumento() {
		when(documento.getCodigo()).thenReturn(1);
		when(documento.getFechaCreacion()).thenReturn(new Date(12/07/1997));

		final Documento resultado = this.servicioDocumento.altaDocumento(documento);
		verify(this.repositorioDocumento).altaDocumento(resultado);
		assertEquals(resultado.getNombre(), documento.getNombre());
		assertEquals(resultado.getCodigo(), Integer.valueOf(1));
	}

	@Test
	public void deberiaModificarUnDocumento() {
		when(documento.getCodigo()).thenReturn(1);
		final Documento resultado = this.servicioDocumento.modificarDocumento(documento);

		verify(this.repositorioDocumento).modificarDocumento(any());
		assertEquals(resultado.getNombre(), documento.getNombre());
		assertEquals(resultado.getCodigo(), Integer.valueOf(1));
	}
	
	@Test
	public void deberiaEliminarUnDocumento() {
		when(documento.getCodigo()).thenReturn(CODIGO);
		this.servicioDocumento.eliminarDocumento(documento.getCodigo());
		verify(this.repositorioDocumento).eliminarDocumento(CODIGO);
	}
}
